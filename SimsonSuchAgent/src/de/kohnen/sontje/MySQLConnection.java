/**
 * 
 */
package de.kohnen.sontje;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

/**
 * @author dirk
 *
 */
public class MySQLConnection {

	private static Connection conn = null;
	private static String dbHost = "kohnen.selfip.com";
	private static String dbPort = "3306";
	private static String database = "sontje_dev";
	private static String dbUser = "root";
	private static String dbPassword = "i4HgAZ3rbT7n8ir7fGkXvwEVZ";

	private MySQLConnection() {
		try {
			// Datenbanktreiber für ODBC Schnittstellen laden.
			// Für verschiedene ODBC-Datenbanken muss dieser Treiber
			// nur einmal geladen werden.
			Class.forName("com.mysql.jdbc.Driver");
		 
			// Verbindung zur ODBC-Datenbank 'sakila' herstellen.
			// Es wird die JDBC-ODBC-Brücke verwendet.
			conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":"
					+ dbPort + "/" + database + "?" + "user=" + dbUser + "&"
					+ "password=" + dbPassword);
		} catch (ClassNotFoundException e) {
			System.out.println("Treiber nicht gefunden");
		} catch (SQLException e) {
			System.out.println("Connect nicht moeglich");
		}
	}
		 
	private static Connection getInstance(){
		if(conn == null)
			new MySQLConnection();
		return conn;
	}
	
	public static void insertQueryLog(QueryLog ql){
		conn = getInstance();
		Timestamp current = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

		if(conn != null){
			Statement query;
			int result;
			try {
				query = conn.createStatement();
				String sql = String.format("INSERT INTO `QueryLog`(`shopID`, `start`, `ende`, `nSeiten`, `nArtikel`, `status`, `meldung`, `timeCreated`, `timeModified`)"
						+ " VALUES ('%d','%s','%s','%d','%d','%s','%s','%s','%s')", ql.getShopID(),ql.getStart(), ql.getEnde(), ql.getnArtikel(), ql.getnSeiten(), ql.getStatus(),
						ql.getMeldung(), current.toString(), current.toString());
				result = query.executeUpdate(sql);
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
			}
		}
	}
	
	public static Vector<Shop> getShops(){
		conn = getInstance();
		Shop shop = null;
		Vector<Shop> v = new Vector<Shop>();
		
		if(conn != null){
			Statement query;
			try {
				query = conn.createStatement();
	 
				String sql = "SELECT * FROM Shop";
				ResultSet result = query.executeQuery(sql);
	 
				while (result.next()) {
					shop = new Shop(result);
					v.add(shop);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return v;
	}
	
	public static HashMap<String, Integer> getArtikelHashMap(){
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		conn = getInstance();

		if(conn != null){
			Statement query;
			try {
				query = conn.createStatement();
	 
				String sql = "SELECT ID,ean FROM Artikel";
				ResultSet result = query.executeQuery(sql);
	 
				while (result.next()) {
					hm.put(result.getString("ean"), result.getInt("ID"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return hm;
	}
	
	public static void updatePreis(int shopId, int artikelId, String artikelNr, String preis){
		//INSERT INTO `ShopArtikelZuordnung`(`ID`, `shopID`, `artikelID`, `artikelNr`, `preis`, `timeCreated`, `timeModified`) VALUES ([value-1],[value-2],[value-3],[value-4],[value-5],[value-6],[value-7])
		conn = getInstance();
		Timestamp current = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

		if(conn != null){
			Statement query;
			int result;
			try {
				query = conn.createStatement();
				String sql = String.format("INSERT INTO `ShopArtikelZuordnung`(`shopID`, `artikelID`, `artikelNr`, `preis`, `timeCreated`, `timeModified`) VALUES ('%d','%d','%s','%s','%s','%s')",
						shopId, artikelId, artikelNr, preis, current, current);
				result = query.executeUpdate(sql);
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
			}
		}
	}
	
	public static int insertArtikel(Artikel a){
		int generatedId = -1;
		conn = getInstance();
		Timestamp current = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

		if(conn != null){
			Statement query;
			ResultSet result;
			try {
				query = conn.createStatement();
				String sql = String.format("INSERT INTO `Artikel`(`ean`, `hersteller`, `herstellerNr`, `simsonNr`, `titel`, `image`, "
						+ "`beschreibung`, `timeCreated`, `timeModified`) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s')", a.getEan(),
						a.getHersteller(), a.getHerstellerNr(), a.getSimsonNr(), a.getTitel(), a.getImageUrl(), a.getBeschreibung(), current,current);
				query.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
				result =  query.getGeneratedKeys();
				if (result.next()) {
					generatedId = result.getInt(1);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
			}
		}
		return generatedId;
	}
}
