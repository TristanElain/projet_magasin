package main.classes;

public class LitMedicalise extends MobilierMedical {
	private double hauteurLit;

	/**
	 * @param reference
	 * @param marque
	 * @param modele
	 * @param prix
	 * @param stock
	 * @param poids
	 * @param dimension
	 * @param hauteurLit
	 */
	public LitMedicalise(String reference, String marque, String modele, Double prix, Integer stock, Integer poids,
			Double[] dimension, Double hauteurLit) {
		super(reference, marque, modele, prix, stock, poids, dimension);
		this.hauteurLit=hauteurLit;
	}

	/**
	 * @return la hauteur du lit
	 */
	public double getHauteurLit() {
		return hauteurLit;
	}

	/**
	 * @param hauteurLit
	 *            la hauteur a definir
	 */
	public void setHauteurLit(double hauteurLit) {
		this.hauteurLit = hauteurLit;
	}
}
