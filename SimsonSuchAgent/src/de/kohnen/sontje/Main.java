package de.kohnen.sontje;
/**
 * 
 */

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author dirk.kohnen
 *
 */
public class Main {

	private static String shopPath = "http://akf-shop.shopgate.com/category_show_all_items/353631?cat_id=5342586&list_category=Simson+Ersatzteile&sort=relevance_desc&page=";
	private static int DEBUG = 1; //Bestimmt die debuglevel; 0=Fehler; 1=Einfache Statusmeldung; 2=Detailierte Statusmeldung; 
	private Elements elements;
	private int nArtikel = 0;
	private int nPages = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();
	}
	
	
/**
 * Contructor
 */
	public Main(){
		Main.debug(1, "Starte Programm");
		getArtikel();
		Main.debug(1, "Beende Programm");
	}

	private void getArtikel(){
		Main.debug(1, "Starte Methode 'getArtikel()'");
		try {
			for (int i = 1; i <= 708 ; i++ ){
				Document doc = Jsoup.connect(shopPath+i).userAgent("Mozilla").timeout(10000).get();
				this.elements = doc.getElementsByClass("product-title");
				for (Element el : this.elements) {
					nArtikel ++;
					//System.out.println(el.text());
				}
				nPages++;
				Main.debug(1, "auslesen seite " + nPages + " abgeschlossen");
			
			}
			Main.debug(1, nPages + " Seiten ausgelesen");
			Main.debug(1, nArtikel + " Artikel erkannt");
		} catch (IOException e) {
			Main.debug(0, "Fehler bei Seite: " + nPages + "und Artikel: " + nArtikel);
			e.printStackTrace();
		}
	}
	
	/**
	 * Methode für Debug-Meldungen
	 * @param level Debug-Level
	 * @param text Debug-Meldung
	 */
	public static void debug(int level, String text){
		if (level <= DEBUG && DEBUG > 0){
			System.out.println("DEBUGLEVEL "+ level + ": " + text);
		}
	}

}
