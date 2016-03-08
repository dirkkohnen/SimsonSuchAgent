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
	
	public static boolean getFirstQuery(int id){
		conn = getInstance();
		if(conn != null){
			Statement query;
			try {
				query = conn.createStatement();
	 
				String sql = "SELECT ID FROM Shop";
				ResultSet result = query.executeQuery(sql);
		
	}
	 

		 
}
