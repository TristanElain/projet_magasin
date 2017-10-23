package main.classes;

public class SouleveMalade extends Article {

	private int capaciteLevage;
	private double degrePivot;

	/**
	 * @param reference
	 * @param marque
	 * @param modele
	 * @param prix
	 * @param stock
	 * @param capaciteLevage
	 * @param degrePivot
	 */
	public SouleveMalade(String reference, String marque, String modele, Double prix, Integer stock, Integer capaciteLevage,
			Double degrePivot) {
		super(reference, marque, modele, prix, stock);
		this.capaciteLevage = capaciteLevage;
		this.degrePivot = degrePivot;
	}

	/**
	 * @return the capaciteLevage
	 */
	public int getCapaciteLevage() {
		return capaciteLevage;
	}

	/**
	 * @param capaciteLevage
	 *            the capaciteLevage to set
	 */
	public void setCapaciteLevage(int capaciteLevage) {
		this.capaciteLevage = capaciteLevage;
	}

	/**
	 * @return the degrePivot
	 */
	public double getDegrePivot() {
		return degrePivot;
	}

	/**
	 * @param degrePivot
	 *            the degrePivot to set
	 */
	public void setDegrePivot(int degrePivot) {
		this.degrePivot = degrePivot;
	}

}
