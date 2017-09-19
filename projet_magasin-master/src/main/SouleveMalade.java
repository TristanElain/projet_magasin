package main;

public class SouleveMalade extends Article {

	private int capaciteLevage;
	private int degrePivot;

	/**
	 * @param reference
	 * @param marque
	 * @param modele
	 * @param prix
	 * @param stock
	 * @param capaciteLevage
	 * @param degrePrivot
	 */
	public SouleveMalade(String reference, String marque, String modele, double prix, int stock, int capaciteLevage,
			int degrePivot) {
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
	public int getDegrePivot() {
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
