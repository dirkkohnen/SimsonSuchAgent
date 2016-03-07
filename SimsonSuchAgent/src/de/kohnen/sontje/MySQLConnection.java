/**
 * 
 */
package de.kohnen.sontje;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public static void getShops(){
		conn = getInstance();
		Shop shop = null;
		
		if(conn != null){
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();
	 
				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT * FROM Shop";
				ResultSet result = query.executeQuery(sql);
	 
				// Ergebnissätze durchfahren.
				while (result.next()) {
					shop = new Shop();
					shop.setID(result.getInt("ID"));
					shop.setName(result.getString("name"));
					shop.setUrl(result.getString("url"));
					System.out.println(shop.toString());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	 

		 
}
