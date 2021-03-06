package main.classes;

public abstract class MobilierMedical extends Article {

	private int poidsMax;
	private Double[] dimension;

	/**
	 * @param reference
	 * @param marque
	 * @param modele
	 * @param prix
	 * @param stock
	 * @param poids
	 * @param dimension
	 */
	public MobilierMedical(String reference, String marque, String modele, Double prix, Integer stock, Integer poids,
			Double[] dimension) {
		super(reference, marque, modele, prix, stock);
		this.poidsMax = poids;
		this.dimension = dimension;
	}

	/**
	 * @return the poidsMax
	 */
	public int getPoidsMax() {
		return poidsMax;
	}

	/**
	 * @param poidsMax
	 *            the poidsMax to set
	 */
	public void setPoidsMax(int poidsMax) {
		this.poidsMax = poidsMax;
	}

	/**
	 * @return the dimension
	 */
	public Double[] getDimension() {
		return dimension;
	}

	/**
	 * @param dimension
	 *            the dimension to set
	 */
	public void setDimension(Double[] dimension) {
		this.dimension = dimension;
	}

}
