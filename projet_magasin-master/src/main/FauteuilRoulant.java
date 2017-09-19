package main;

public class FauteuilRoulant extends Article {

	private int assise;
	private double poids;

	/**
	 * @param reference
	 * @param marque
	 * @param modele
	 * @param prix
	 * @param stock
	 * @param assise
	 * @param poids
	 */
	public FauteuilRoulant(String reference, String marque, String modele, double prix, int stock, int assise,
			double poids) {
		super(reference, marque, modele, prix, stock);
		this.assise = assise;
		this.poids = poids;
	}

	/**
	 * @return the assise
	 */
	public int getAssise() {
		return assise;
	}

	/**
	 * @param assise
	 *            the assise to set
	 */
	public void setAssise(int assise) {
		this.assise = assise;
	}

	/**
	 * @return the poids
	 */
	public double getPoids() {
		return poids;
	}

	/**
	 * @param poids
	 *            the poids to set
	 */
	public void setPoids(double poids) {
		this.poids = poids;
	}

}
