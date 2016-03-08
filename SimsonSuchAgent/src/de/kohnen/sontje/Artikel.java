/**
 * 
 */
package de.kohnen.sontje;

import java.sql.Timestamp;

/**
 * @author dirk.kohnen
 *
 */
public class Artikel {

	private int ID = 0;
	private int ean = 0;
	private String hersteller = "";
	private String herstellerNr = "";
	private String simsonNr = "";
	private String titel = "";
	private String imageUrl = "";
	private String beschreibung = "";
	private Timestamp timeCreated = null;
	private Timestamp timeModified = null;
	
	public Artikel(){
		
	}

	
	/**
	 * @return iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD das zu setzende Objekt iD
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * @return ean
	 */
	public int getEan() {
		return ean;
	}

	/**
	 * @param ean das zu setzende Objekt ean
	 */
	public void setEan(int ean) {
		this.ean = ean;
	}

	/**
	 * @return hersteller
	 */
	public String getHersteller() {
		return hersteller;
	}

	/**
	 * @param hersteller das zu setzende Objekt hersteller
	 */
	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}

	/**
	 * @return herstellerNr
	 */
	public String getHerstellerNr() {
		return herstellerNr;
	}

	/**
	 * @param herstellerNr das zu setzende Objekt herstellerNr
	 */
	public void setHerstellerNr(String herstellerNr) {
		this.herstellerNr = herstellerNr;
	}

	/**
	 * @return simsonNr
	 */
	public String getSimsonNr() {
		return simsonNr;
	}

	/**
	 * @param simsonNr das zu setzende Objekt simsonNr
	 */
	public void setSimsonNr(String simsonNr) {
		this.simsonNr = simsonNr;
	}

	/**
	 * @return titel
	 */
	public String getTitel() {
		return titel;
	}

	/**
	 * @param titel das zu setzende Objekt titel
	 */
	public void setTitel(String titel) {
		this.titel = titel;
	}

	/**
	 * @return imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl das zu setzende Objekt imageUrl
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return beschreibung
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * @param beschreibung das zu setzende Objekt beschreibung
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * @return timeCreated
	 */
	public Timestamp getTimeCreated() {
		return timeCreated;
	}

	/**
	 * @param timeCreated das zu setzende Objekt timeCreated
	 */
	public void setTimeCreated(Timestamp timeCreated) {
		this.timeCreated = timeCreated;
	}

	/**
	 * @return timeModified
	 */
	public Timestamp getTimeModified() {
		return timeModified;
	}

	/**
	 * @param timeModified das zu setzende Objekt timeModified
	 */
	public void setTimeModified(Timestamp timeModified) {
		this.timeModified = timeModified;
	}

}
