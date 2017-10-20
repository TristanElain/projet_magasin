package main.classes;

import java.util.ArrayList;
import java.util.List;

public class Personne {

	private String nom;
	private String prenom;
	private String adresse;
	private String telephone;
	private List<Location> locations;

	/**
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param telephone
	 */
	public Personne(String nom, String prenom, String adresse, String telephone) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.locations = new ArrayList<>();
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom
	 *            the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse
	 *            the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the locations
	 */
	public List<Location> getLocations() {
		return locations;
	}

	/**
	 * @param locations
	 *            the locations to set
	 */
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	/**
	 * Ajoute une location à une personne
	 * 
	 * @param loc
	 *            la location à ajouter
	 */
	public void ajouterLocation(Location loc) {
		if (!this.locations.contains(loc)) {
			this.locations.add(loc);
		}
	}

	/**
	 * Supprime une location d'une personne et archive la location
	 * 
	 * @param loc
	 *            la location à supprimer
	 */
	public void supprimerLocation(Location loc) {
		if (this.locations.contains(loc)) {
			// loc.archiver();
			this.locations.remove(loc);
		}
	}

	/**
	 * Affiche l'ensemble des locations
	 * 
	 */
	public void afficherLocations() {
		int i = 1;
		for (Location loc : this.locations) {
			System.out.println("Location " + i + " : ");
			System.out.println("_________________");
			System.out.println(loc);
			System.out.println("\n");
			i++;
		}
	}
	
	@Override
	public String toString() {
		
		return nom + " " + prenom;
	}

}
