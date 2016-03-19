package de.kohnen.sontje;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ShopQuery {
	private int nSeiten = 0;
	private int nArtikel = 0;
	private Shop shop;
	private QueryLog ql;
    	
	private Vector<String> artikelLinkListe = new Vector<String>();
	private HashMap<String, Integer> vorhandeneArtikel;
       
	/**
	 * Constructor
	 */
	public ShopQuery(Shop s){
		Main.debug(1, String.format("Shopquery für (%d, %s) gestartet", s.getID(), s.getName()));
		
		// Erstellt den QueryLog 
		ql = new QueryLog(s.getID(), new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
		
		this.shop = s;
		
		//Holt die Artikelliste aus der Datenbank
		this.getArtikelListeFromDb();
		
		//Holt die Artikelliste aus dem Shop
		this.getArtikelListeFromShop();
		
		//Holt die Artikel aus dem Shop
		this.getArtikelFromShop();
		
		this.ql.setEnde(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
		this.ql.setStatus("Erfolgreich abgeschlossen");
		this.ql.setnArtikel(this.nArtikel);
		this.ql.setnSeiten(this.nSeiten);
		this.ql.setMeldung(String.format("ShopQuery für %s abgeschlossen. Ergebenis:\r\nSeiten: %d\r\nArtikel %d",this.shop.getName(), this.nSeiten, this.nArtikel));
		MySQLConnection.insertQueryLog(this.ql);
		
 	}
	
	private void getArtikelListeFromDb(){
		Main.debug(2, "Hole ArtikelListe aus DB");
		
		//Holt die Liste vorhandener Artikel aus der Dantenbank
		this.vorhandeneArtikel = MySQLConnection.getArtikelHashMap();
		
		Main.debug(2, "ArtikelListe für aus der Datenbank geholt");

	}
	
	private void getArtikelListeFromShop(){
		Main.debug(2, String.format("Hole ArtikelListe für Shop: %s", this.shop.getName()));
		
		Elements elements;
		String shopPath = this.shop.getSearchUrl();
		// holt die Liste mit Links auf die Einzelartikel;
		try {
			for (int i = 1; i <= 305 ; i++ ){
				Document doc = Jsoup.connect(shopPath+i).userAgent("Mozilla").timeout(10000).get();
				elements = doc.getElementsByClass("single-product");
				for (Element el : elements) {
					Elements links = el.getElementsByTag("a");
					for (Element link : links) {
						artikelLinkListe.add(link.attr("href"));
					}
					this.nArtikel ++;
				}
				this.nSeiten++;
				Main.debug(2, String.format("Auslesen Seite %d abgeschlossen", this.nSeiten));
			}
		} catch (Exception e) {
			this.ql.setEnde(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
			this.ql.setStatus("Abgebrochen (Fehler)");
			this.ql.setnArtikel(this.nArtikel);
			this.ql.setnSeiten(this.nSeiten);
			this.ql.setMeldung(e.getMessage());
			MySQLConnection.insertQueryLog(this.ql);
			Main.debug(0, String.format("Fehler bei Seite: %d", this.nSeiten));
			e.printStackTrace();
		}
		Main.debug(2, String.format("ArtikelListe für %s geholt; Ergebnis: %d Seiten und %d Artikel", this.shop.getName(), this.nSeiten, this.nArtikel));
	}
	
	private void getArtikelFromShop(){
		Main.debug(2, String.format("Hole Artikel für Shop: %s", this.shop.getName()));
		int aktuellerArtikel = 0;
		Artikel artikel;
		Element elementsDescription;
		Element elementProperties;
		Elements subElementsProperties;
		String preisString;
		int artikelId = 0;
		String ean;
		String artikelNr;
		String herstellerNr;
		String hersteller;
		String titel;

		// geht die Liste mit den Links auf die Artikel durchartikelLinkListe.size()
		try {
			for (int i = 0; i < nArtikel ; i++ ){
				aktuellerArtikel = i;
				ean = "";
				artikelNr = "";
				herstellerNr = "";
				hersteller = "";
				titel = "";
				Document doc = Jsoup.connect(artikelLinkListe.elementAt(i)).userAgent("Mozilla").timeout(10000).get(); // Holt die Artikelseite
				elementsDescription = doc.getElementById("product-description"); //Holt den Beschreibungs-Tag
				elementProperties = doc.getElementById("product-properties"); //Holt die Properties-Tags
				titel = doc.getElementsByTag("h1").first().text();
				subElementsProperties = elementProperties.getElementsByClass("col-xs-6");
				for (int j = 0 ; j < subElementsProperties.size(); j+=2){
					if (subElementsProperties.get(j).text().compareTo("Artikel-Nr.") == 0){
						artikelNr = subElementsProperties.get(j+1).text();
					} else if (subElementsProperties.get(j).text().compareTo("Hersteller-Nr.") == 0){
						herstellerNr = subElementsProperties.get(j+1).text();
					} else if (subElementsProperties.get(j).text().compareTo("EAN") == 0){
						ean = subElementsProperties.get(j+1).text();
					} else if (subElementsProperties.get(j).text().compareTo("Hersteller") == 0){
						hersteller = subElementsProperties.get(j+1).text();
					}				
				}
				
				//prüft ob der Artikel angelegt und legt ihn ggf. an
				if (!vorhandeneArtikel.containsKey(ean)){
					artikel = new Artikel();
					artikel.setTitel(titel);
					artikel.setBeschreibung(elementsDescription.text());
					artikel.setHersteller(hersteller);
					artikel.setHerstellerNr(herstellerNr);
					artikel.setEan(ean);
					artikelId = MySQLConnection.insertArtikel(artikel);
					System.out.println(artikel.toString());
				}else{
					artikelId = vorhandeneArtikel.get(ean);
				}

				//liest den Preis aus
				preisString = doc.getElementsByClass("js-container-price").first().text();
				preisString = preisString.substring(0, preisString.length()-2);
				preisString = preisString.replace(",", ".");

				//aktuallisiert den Preis
				MySQLConnection.updatePreis(this.shop.getID(), artikelId, subElementsProperties.get(1).text(), preisString);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Main.debug(0, "Fehler in Methode ShopQuery->UpdateArtikel: " + e.getMessage());
			this.ql.setEnde(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
			this.ql.setStatus("Abgebrochen (Fehler)");
			this.ql.setnArtikel(this.nArtikel);
			this.ql.setnSeiten(this.nSeiten);
			this.ql.setMeldung(String.format("Fehler bei Artikel: %d\r\n Meldung: %s", aktuellerArtikel, e.getMessage()));
			MySQLConnection.insertQueryLog(this.ql);
			Main.debug(0, String.format("Fehler bei Artikel: %d", aktuellerArtikel));
		}
		Main.debug(2, String.format("Artikel für %s geholt; Ergebnis: %d Artikel", this.shop.getName(), this.nArtikel));
	}
}
