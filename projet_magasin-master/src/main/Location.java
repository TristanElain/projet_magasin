package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Location {

	private List<Article> articles;
	private Calendar dateLocation;
	private Calendar dateRetour;
	private double montant;

	
	/**
	 * @param dateLocation
	 * @param dateRetour
	 * @param montant
	 */
	public Location(Calendar dateLocation, Calendar dateRetour, double montant) {
		this.articles = new ArrayList<>();
		this.dateLocation = dateLocation;
		this.dateRetour = dateRetour;
		this.montant = montant;
	}

	/**
	 * @param articles
	 * @param dateLocation
	 * @param dateRetour
	 * @param montant
	 */
	public Location(List<Article> articles, Calendar dateLocation, Calendar dateRetour, double montant) {
		this.articles = articles;
		this.dateLocation = dateLocation;
		this.dateRetour = dateRetour;
		this.montant = montant;
	}

	/**
	 * @return the articles
	 */
	public List<Article> getArticles() {
		return articles;
	}

	/**
	 * @param articles
	 *            the articles to set
	 */
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	/**
	 * @return the dateLocation
	 */
	public Calendar getDateLocation() {
		return dateLocation;
	}

	/**
	 * @param dateLocation
	 *            the dateLocation to set
	 */
	public void setDateLocation(Calendar dateLocation) {
		this.dateLocation = dateLocation;
	}

	/**
	 * @return the dateRetour
	 */
	public Calendar getDateRetour() {
		return dateRetour;
	}

	/**
	 * @param dateRetour
	 *            the dateRetour to set
	 */
	public void setDateRetour(Calendar dateRetour) {
		this.dateRetour = dateRetour;
	}

	/**
	 * @return the montant
	 */
	public double getMontant() {
		return montant;
	}

	/**
	 * @param montant
	 *            the montant to set
	 */
	public void setMontant(double montant) {
		this.montant = montant;
	}

}
