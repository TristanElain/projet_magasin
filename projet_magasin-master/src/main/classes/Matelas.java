package main.classes;

public class Matelas extends MobilierMedical {

	private double tempsGonflage;

	/**
	 * @param reference
	 * @param marque
	 * @param modele
	 * @param prix
	 * @param stock
	 * @param poids
	 * @param dimension
	 * @param temps
	 */
	public Matelas(String reference, String marque, String modele, Double prix, Integer stock, Integer poids,
			Double[] dimension, Double temps) {
		super(reference, marque, modele, prix, stock, poids, dimension);
		this.tempsGonflage = temps;
	}

	/**
	 * @return the tempsGonflage
	 */
	public double getTempsGonflage() {
		return tempsGonflage;
	}

	/**
	 * @param tempsGonflage
	 *            the tempsGonflage to set
	 */
	public void setTempsGonflage(double tempsGonflage) {
		this.tempsGonflage = tempsGonflage;
	}

}
