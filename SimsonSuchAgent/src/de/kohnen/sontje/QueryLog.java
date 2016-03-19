/**
 * 
 */
package de.kohnen.sontje;

import java.sql.Timestamp;

/**
 * @author dirk.kohnen
 *
 */
public class QueryLog {
	private int ID;
	private int shopID;
	private Timestamp start;
	private Timestamp ende;
	private int nSeiten;
	private int nArtikel;
	private String status;
	private String meldung;

	private Timestamp timeCreated;
	private Timestamp timemodified;
	
	public QueryLog(){
		
	}

	public QueryLog(int shopId, Timestamp start){
		this.shopID = shopId;
		this.start = start;
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
	 * @return shopID
	 */
	public int getShopID() {
		return shopID;
	}

	/**
	 * @param shopID das zu setzende Objekt shopID
	 */
	public void setShopID(int shopID) {
		this.shopID = shopID;
	}

	/**
	 * @return start
	 */
	public Timestamp getStart() {
		return start;
	}

	/**
	 * @param start das zu setzende Objekt start
	 */
	public void setStart(Timestamp start) {
		this.start = start;
	}

	/**
	 * @return ende
	 */
	public Timestamp getEnde() {
		return ende;
	}

	/**
	 * @param ende das zu setzende Objekt ende
	 */
	public void setEnde(Timestamp ende) {
		this.ende = ende;
	}

	/**
	 * @return nSeiten
	 */
	public int getnSeiten() {
		return nSeiten;
	}

	/**
	 * @param nSeiten das zu setzende Objekt nSeiten
	 */
	public void setnSeiten(int nSeiten) {
		this.nSeiten = nSeiten;
	}

	/**
	 * @return nArtikel
	 */
	public int getnArtikel() {
		return nArtikel;
	}

	/**
	 * @param nArtikel das zu setzende Objekt nArtikel
	 */
	public void setnArtikel(int nArtikel) {
		this.nArtikel = nArtikel;
	}

	/**
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status das zu setzende Objekt status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return meldung
	 */
	public String getMeldung() {
		return meldung;
	}

	/**
	 * @param meldung das zu setzende Objekt meldung
	 */
	public void setMeldung(String meldung) {
		this.meldung = meldung;
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
	 * @return timemodified
	 */
	public Timestamp getTimemodified() {
		return timemodified;
	}

	/**
	 * @param timemodified das zu setzende Objekt timemodified
	 */
	public void setTimemodified(Timestamp timemodified) {
		this.timemodified = timemodified;
	}
}
