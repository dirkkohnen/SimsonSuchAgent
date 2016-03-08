package de.kohnen.sontje;
/**
 * 
 */

import java.io.File;
import java.io.IOException;
import java.util.Vector;

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
	
	private Vector<Shop> vShops;

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
		vShops = MySQLConnection.getShops();
		
		for (Shop s: vShops){
			ShopQuery sq = new ShopQuery(s);
		}
		//getArtikel();
		Main.debug(1, "Beende Programm");
	}

	private void getArtikel(){
		Main.debug(1, "Starte Methode 'getArtikel()'");
	}
	
	/**
	 * Methode f�r Debug-Meldungen
	 * @param level Debug-Level
	 * @param text Debug-Meldung
	 */
	public static void debug(int level, String text){
		if (level <= DEBUG && DEBUG > 0){
			System.out.println("DEBUGLEVEL "+ level + ": " + text);
		}
	}

}
