/**
 * 
 */
package de.kohnen.sontje;

import java.sql.Timestamp;

/**
 * @author dirk
 *
 */
public class Shop {

	private int ID = 0;
	private String name = "";
	private String url = "";
	private EShopArt art = null;
	private Timestamp timeCreated = null;
	private Timestamp timeModified = null;
	
	public Shop(){
	
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getArt() {
		return art.name();
	}

	public void setArt(EShopArt art) {
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
		return String.format("Shop: %d, %s (%s)", this.ID, this.name, this.url);
	}
	
}
