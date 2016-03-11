package de.kohnen.sontje;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
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
    	
    	private Vector<String> artikelLinkListe;
       
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
    			for (int i = 1; i <= 708 ; i++ ){
    				Document doc = Jsoup.connect(shopPath+i).userAgent("Mozilla").timeout(10000).get();
    				this.elements = doc.getElementsByClass("single-product");
    				for (Element el : this.elements) {
    					Elements links = el.getElementsByTag("a");
    					for (Element link : links) {
    						artikelLinkListe.add(link.attr("href"));
    					}
    					nArtikel ++;
    					//System.out.println(el.text());
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
    		
    		// geht die Liste mit den Links auf die Artikel durch
    		try {
    			    		

    		} catch (IOException e) {
    			Main.debug(0, "Fehler bei Seite: " + nSeiten + "und Artikel: " + nArtikel);
    			e.printStackTrace();
    		}
/*            try {
                ende = new Timestamp(new Date().getTime());
                queryString = String.format("INSERT INTO `sontje_dev`.`QueryLog` (`ID`, `shopID`, `start`, `ende`, `nSeiten`, `nArtikel`, `status`, `meldung`) VALUES (NULL, '" + shopID + "', '" + start + "', '" + ende + "', '" + nSeiten + "', '" + nArtikel + "', '" + status + "', '" + meldung + "');");
                rs = st.executeUpdate(queryString);
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(ShopQuery.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);

            } finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    Logger lgr = Logger.getLogger(ShopQuery.class.getName());
                    lgr.log(Level.WARNING, ex.getMessage(), ex);
                }
            }*/
        }
        
        private void updatePreise(){
        	
        }
		
	
}
