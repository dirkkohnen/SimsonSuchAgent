/**
 * 
 */
package de.kohnen.sontje;

/**
 * @author dirk
 *
 */
public enum EShopArt {
	SHOPGATE ("Shopgate"), EBAY("Ebay");
	
	private final String name;
	
	private EShopArt(String name){
		this.name = name;
	}
}
