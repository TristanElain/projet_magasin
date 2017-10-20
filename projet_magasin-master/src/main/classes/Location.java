package main.classes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import main.exceptions.ArchivageException;

public class Location {

	private static final String REPERTOIRE_ARCHIVES = "files/loc/";

	private List<Article> articles;
	private Calendar dateLocation;
	private Calendar dateRetour;

	/**
	 * @param dateLocation
	 * @param dateRetour
	 */
	public Location(Calendar dateLocation, Calendar dateRetour) {
		this.articles = new ArrayList<>();
		this.dateLocation = dateLocation;
		this.dateRetour = dateRetour;
	}

	/**
	 * @param articles
	 * @param dateLocation
	 * @param dateRetour
	 */
	public Location(List<Article> articles, Calendar dateLocation, Calendar dateRetour) {
		this.articles = articles;
		this.dateLocation = dateLocation;
		this.dateRetour = dateRetour;
	}

	/**
	 * Ajoute un article dans le magasin, s'il existe, incrémente le stock avec
	 * le stock de l'article à ajouter
	 * 
	 * @param article
	 *            - article à ajouter
	 */
	public void ajouterArticle(Article article) {
		if (articles.contains(article)) {
			Article existant = articles.get(articles.indexOf(article));
			existant.setStock(existant.getStock() + article.getStock());
		} else {
			articles.add(article);
		}
	}

	/**
	 * Retire un article de la liste d'articles du magasin
	 * 
	 * @param article
	 *            - article à retirer
	 */
	public void supprimerArticle(Article article) {
		articles.remove(articles.indexOf(article));
	}

	public static void archiver(Location location, Personne client) throws ArchivageException {
		SimpleDateFormat formatNomFichier = new SimpleDateFormat("YYYYMM");
		SimpleDateFormat formatAffichage = new SimpleDateFormat("dd/MM/YYYY");
		String nomFichier = formatNomFichier.format(location.dateRetour.getTime()) + ".loc";

		try {
			File f = new File(REPERTOIRE_ARCHIVES + nomFichier);
			FileWriter fw = new FileWriter(f, true);
			BufferedWriter bw = new BufferedWriter(fw);
			StringBuffer sb = new StringBuffer("");

			sb.append(client.getNom() + " " + client.getPrenom() + ",");
			sb.append(location.calculerMontant() + ",");
			sb.append(formatAffichage.format(location.dateLocation.getTime()) + ",");
			sb.append(formatAffichage.format(location.dateRetour.getTime()) + ",");
			sb.append(location.articles);

			bw.newLine();
			bw.write(sb.toString());

			bw.close();

		} catch (IOException e) {
			throw new ArchivageException(e.getMessage(), e.getCause());
		}
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
	public double calculerMontant() {
		double montant = 0;
		for (Article article : articles) {
			montant += (article.getPrix() * ChronoUnit.DAYS.between(dateLocation.toInstant(), dateRetour.toInstant()));
		}
		return montant;
	}

	public static void main(String[] args) {
		Personne jeanphi = new Personne("Babtou", "Jean-phillipe", "aaaa", "02020202");
		Personne jules = new Personne("Lebris", "Jules", "Granchan", "04040404");

		Article a1 = new FauteuilRoulant("111111a", "AUDI", "A1", 1000d, 25, 38, 558000d);
		Article a2 = new Matelas("2222221a", "BULTEX", "B28", 984d, 120, 150, new Double[] { 120d, 555d, 2048d }, 120d);

		Calendar dateLocation = Calendar.getInstance();
		dateLocation.set(2017, Calendar.OCTOBER, 19);

		Calendar dateRetour = Calendar.getInstance();
		dateRetour.set(2017, Calendar.OCTOBER, 22);
		Location locjp1 = new Location(Calendar.getInstance(), dateRetour);
		locjp1.ajouterArticle(a1);

		dateRetour = Calendar.getInstance();
		dateRetour.set(2017, Calendar.OCTOBER, 30);
		Location locjp2 = new Location(Calendar.getInstance(), dateRetour);
		locjp2.ajouterArticle(a2);

		jeanphi.ajouterLocation(locjp1);
		jeanphi.ajouterLocation(locjp2);

		dateRetour = Calendar.getInstance();
		dateRetour.set(2017, Calendar.DECEMBER, 25);
		Location locjlb1 = new Location(dateLocation, dateRetour);

		dateRetour = Calendar.getInstance();
		dateRetour.set(2017, Calendar.DECEMBER, 25);
		Location locjlb = new Location(dateLocation, dateRetour);
		
		try {
			for (Location location : jeanphi.getLocations()) {
				Location.archiver(location, jeanphi);
			}
			for (Location location : jules.getLocations()) {
				Location.archiver(location, jules);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return La liste des articles d'une commande
	 * 
	 */
	public String toString() {
		String lesArticles = "";
		for (Article art : articles) {
			lesArticles += "\t" + art.getMarque() + " - " + art.getModele() + " (ref " + art.getReference() + ")\n";
		}
		return lesArticles;
	}
}
