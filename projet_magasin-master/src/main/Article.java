package main;

public abstract class Article {
	
	protected String reference;
	protected String marque;
	protected String modele;
	protected double prix;
	protected int stock;
	
	/**
	 * Constructeur de la classe
	 * 
	 * @param reference - r�f�rence du produit
	 * @param marque - marque du produit
	 * @param modele - modele du produit
	 * @param prix - prix par jour de location
	 * @param stock - nombre de produits en stock
	 */
	public Article(String reference, String marque, String modele, double prix, int stock) {
		this.reference = reference;
		this.marque = marque;
		this.modele = modele;
		this.prix = prix;
		this.stock = stock;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the marque
	 */
	public String getMarque() {
		return marque;
	}

	/**
	 * @param marque the marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}

	/**
	 * @return the modele
	 */
	public String getModele() {
		return modele;
	}

	/**
	 * @param modele the modele to set
	 */
	public void setModele(String modele) {
		this.modele = modele;
	}

	/**
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * @param prix the prix to set
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	
	
}
