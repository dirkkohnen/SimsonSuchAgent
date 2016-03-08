/**
 * 
 */
package de.kohnen.sontje;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author dirk
 *
 */
public class Shop {

	private int ID = 0;
	private String name = "";
	private String searchUrl = "";
	private String itemUrl = "";
	private String art = null;
	private Timestamp timeCreated = null;
	private Timestamp timeModified = null;
	
	public Shop(){
	
	}
	
	public Shop(ResultSet r){
		try {
			this.ID = r.getInt("ID");
			this.name = r.getString("name");
			this.searchUrl = r.getString("searchUrl");
			this.itemUrl = r.getString("itemUrl");
			this.art = r.getString("art");
			this.timeCreated = r.getTimestamp("timeCreated");
			this.timeModified = r.getTimestamp("timeCreated");
		} catch (SQLException e) {
			// TODO Automatisch generierter Erfassungsblock
			e.printStackTrace();
		}

	}
	
	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSearchUrl() {
		return searchUrl;
	}

	public void setSearchUrl(String url) {
		this.searchUrl = url;
	}

	/**
	 * @return itemUrl
	 */
	public String getItemUrl() {
		return itemUrl;
	}

	/**
	 * @param itemUrl das zu setzende Objekt itemUrl
	 */
	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public Timestamp getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Timestamp timeCreated) {
		this.timeCreated = timeCreated;
	}

	public Timestamp getTimeModified() {
		return timeModified;
	}

	public void setTimeModified(Timestamp timeModified) {
		this.timeModified = timeModified;
	}

	public String toString(){
		return String.format("Shop: %d, %s (%s)", this.ID, this.name, this.searchUrl);
	}
	
}
