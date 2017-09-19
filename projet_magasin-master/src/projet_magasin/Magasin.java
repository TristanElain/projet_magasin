package projet_magasin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

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
	 * Ajoute un article dans le magasin, s'il existe, incrémente le stock avec le stock de l'article à ajouter
	 *  
	 * @param article - article à ajouter
	 */
	public void ajouterArticle(Article article){
		if(articles.contains(article)){
			Article existant = articles.get(articles.indexOf(article));
			existant.setStock(existant.getStock() + article.getStock());
		} else {
			articles.a
		}
	}
	
	/**
	 * Ajoute une personne si elle n'existe pas
	 *  
	 * @param client - personne à ajouter
	 */
	public boolean ajouterClient(Personne client){
		boolean ajoutOk = false;
		if(!clients.contains(client)){
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
				case 1 :
					return o1.getMarque().compareTo(o2.getMarque());
				case 2 :
					return o1.getModele().compareTo(o2.getModele());
				case 3 : 
					return (int) Math.round(o1.getPrix() - o2.getPrix());
				}
				
				return 0;
			}
		});
		
		System.out.println(articles.toString());
	}

}
