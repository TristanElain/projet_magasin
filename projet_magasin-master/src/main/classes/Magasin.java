package main.classes;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Magasin {

	private List<Article> articles;
	private List<Personne> clients;

	/**
	 * Constructeur par défaut
	 */
	public Magasin() {
		this.articles = new LinkedList<>();
		this.clients = new LinkedList<>();
	}

	/**
	 * @param articles
	 * @param clients
	 */
	public Magasin(List<Article> articles, List<Personne> clients) {
		this.articles = articles;
		this.clients = clients;
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

	/**
	 * Ajoute une personne si elle n'existe pas
	 * 
	 * @param client
	 *            - personne à ajouter
	 */
	public boolean ajouterClient(Personne client) {
		boolean ajoutOk = false;
		if (!clients.contains(client)) {
			ajoutOk = clients.add(client);
		}

		return ajoutOk;
	}

	/**
	 * Affiche la totalité des articles triés en fonction de <code>tri</code>
	 * 
	 * @param tri
	 *            - type de tri à effectuer. 0 = reference; 1 = marque; 2 =
	 *            modèle; 3 = prix par jour
	 */
	public void afficher(int tri) {
		Collections.sort(articles, new Comparator<Article>() {

			@Override
			public int compare(Article o1, Article o2) {
				switch (tri) {
				case 0:
					return o1.getReference().compareTo(o2.getReference());
				case 1:
					return o1.getMarque().compareTo(o2.getMarque());
				case 2:
					return o1.getModele().compareTo(o2.getModele());
				case 3:
					return (int) Math.round(o1.getPrix() - o2.getPrix());
				}

				return 0;
			}
		});

		System.out.println(articles.toString());
	}

	/**
	 * calcule le revenue du moi de la date passé en paramètre à partir des locations enregistrer de le fichier correspondant
	 *
	 * @param date
	 * 			- la date d'un jour au format Calendar
	 */
	public double calculerRevenuMensuel(Calendar date){
		String REPERTOIRE_ARCHIVES = "files/loc/";
		SimpleDateFormat formatNomFichier = new SimpleDateFormat("YYYYMM");
		SimpleDateFormat formatAffichage = new SimpleDateFormat("dd/MM/YYYY");
		String nomFichier = formatNomFichier.format(date.getTime()) + ".loc";
		double som = 0;

		try {
			File f = new File(REPERTOIRE_ARCHIVES + nomFichier);
			FileReader fr = new FileReader(f);
			BufferedReader bw = new BufferedReader(fr);

			BufferedReader reader = new BufferedReader(fr);
			String line = null;



			while ((line = reader.readLine()) != null) {
				if (!"".equals(line)) {
					String[] tab = line.split(",");
					som += Double.parseDouble(tab[1]);
				}
			}
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return som;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<Personne> getClients() {
		return clients;
	}

	public void setClients(List<Personne> clients) {
		this.clients = clients;
	}

	public static void main(String args[]){
		Magasin m = new Magasin();

		Double som = m.calculerRevenuMensuel(Calendar.getInstance());
		System.out.println(som);
	}

}
