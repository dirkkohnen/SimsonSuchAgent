package de.kohnen.sontje;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShopQuery {
		private Connection con = null;
		private Statement st = null;
		private int rs = 0;

        private String url = "jdbc:mysql://kohnen.selfip.com/sontje_dev";
        private String user = "root";
        private String password = "i4HgAZ3rbT7n8ir7fGkXvwEVZ";
        //INSERT INTO `sontje_dev`.`QueryLog` (`ID`, `shopID`, `start`, `ende`, `nSeiten`, `nArtikel`, `status`, `meldung`) VALUES (NULL, '%d', %s, '%s', '%d', '%d', '%s', '%s');";
        private String queryString = "";
        
        private int shopID = 1;
        private Date start = new Date();
        private Date ende = null;
        private int nSeiten = 0;
        private int nArtikel = 0;
        private String status = "abgeschlossen";
        private String meldung = "Erster Test mit der KLasse ShopQuery";
        
	/**
	 * Constructor
	 */
	public ShopQuery(){

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            ende = new Date();
            queryString = String.format("INSERT INTO `sontje_dev`.`QueryLog` (`ID`, `shopID`, `start`, `ende`, `nSeiten`, `nArtikel`, `status`, `meldung`) VALUES (NULL, '" + shopID + "', '" + start.getTime() + "', '" + ende.getTime() + "', '" + nSeiten + "', '" + nArtikel + "', '" + status + "', '" + meldung + "');");
            rs = st.executeUpdate(queryString);

 /*           if (rs.next()) {
                System.out.println(rs.getString(1));
            }
*/
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(ShopQuery.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
/*                if (rs != null) {
                    rs.close();
                }*/
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
        }
		
	}
}
