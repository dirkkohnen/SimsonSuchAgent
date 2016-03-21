package de.kohnen.sontje;
/**
 * 
 */

import java.util.Vector;

/**
 * @author dirk.kohnen
 *
 */
public class Main {

	private static int DEBUG = 2; //Bestimmt die debuglevel; 0=Fehler; 1=Einfache Statusmeldung; 2=Detailierte Statusmeldung; 
	
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
		Main.debug(1, "Shops Geladen: " + vShops.size());
		
		int i = 0;
		for (Shop s: vShops){
			Main.debug(1, "Rufe " + ++i + ". Shop ab");
			new ShopQuery(s);
		}
		//getArtikel();
		Main.debug(1, "Beende Programm");
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
