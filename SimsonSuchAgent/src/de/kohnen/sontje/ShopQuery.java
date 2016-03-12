package de.kohnen.sontje;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ShopQuery {
	private Connection con = null;
	private Statement st = null;
	private int rs = 0;

    //INSERT INTO `sontje_dev`.`QueryLog` (`ID`, `shopID`, `start`, `ende`, `nSeiten`, `nArtikel`, `status`, `meldung`) VALUES (NULL, '%d', %s, '%s', '%d', '%d', '%s', '%s');";
    private String queryString = "";
        
    private int shopID = 1;
	private Timestamp start = new Timestamp(new Date().getTime());
	private Timestamp ende = null;
	private int nSeiten = 0;
	private int nArtikel = 0;
	private String status = "abgeschlossen";
	private String meldung = "Erster Test mit der KLasse ShopQuery";
	private Elements elements;
	private String shopPath = "";
	private Shop shop;
    	
	private Vector<String> artikelLinkListe = new Vector<String>();
	private HashMap<String, Integer> vorhandeneArtikel;
       
	/**
	 * Constructor
	 */
	public ShopQuery(Shop s){
		this.shop = s;
		if (MySQLConnection.isFirstQuery(shop.getID())){
			this.updateArtikel();
		}
		
 	}
	
	private void updateArtikel(){
		shopPath = shop.getSearchUrl();
		// holt die Liste mit Links auf die Einzelartikel;
		try {
			for (int i = 1; i <= 3 ; i++ ){
				Document doc = Jsoup.connect(shopPath+i).userAgent("Mozilla").timeout(10000).get();
				this.elements = doc.getElementsByClass("single-product");
				for (Element el : this.elements) {
					Elements links = el.getElementsByTag("a");
					for (Element link : links) {
						artikelLinkListe.add(link.attr("href"));
					}
					nArtikel ++;
				}
				nSeiten++;
				Main.debug(1, "auslesen seite " + nSeiten + " abgeschlossen");
			}
			Main.debug(1, nSeiten + " Seiten ausgelesen");
			Main.debug(1, nArtikel + " Artikel erkannt");
		} catch (IOException e) {
			Main.debug(0, "Fehler bei Seite: " + nSeiten + "und Artikel: " + nArtikel);
			e.printStackTrace();
		}
		
		//Holt die Liste vorhandener Artikel aus der Dantenbank
		vorhandeneArtikel = MySQLConnection.getArtikelHashMap();
    		
		// geht die Liste mit den Links auf die Artikel durchartikelLinkListe.size()
		try {
			Artikel artikel;
			Element elementsDescription;
			Element elementProperties;
			Elements subElementsProperties;
			for (int i = 1; i < 5 ; i++ ){
				Document doc = Jsoup.connect(artikelLinkListe.elementAt(i)).userAgent("Mozilla").timeout(10000).get(); // Holt die Artikelseite
				elementsDescription = doc.getElementById("product-description"); //Holt den Beschreibungs-Tag
				elementProperties = doc.getElementById("product-properties"); //Holt die Properties-Tags
				subElementsProperties = elementProperties.getElementsByClass("col-xs-6");
				if (vorhandeneArtikel.containsKey(subElementsProperties.get(7).text())){
					
				}else{
					artikel = new Artikel();
					artikel.setBeschreibung(elementsDescription.text());
					artikel.setArtikelNr(subElementsProperties.get(1).text());
					artikel.setHersteller(subElementsProperties.get(3).text());
					artikel.setHerstellerNr(subElementsProperties.get(5).text());
					artikel.setEan(subElementsProperties.get(7).text());
					MySQLConnection.insertArtikel(artikel);
					System.out.println(artikel.toString());
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
