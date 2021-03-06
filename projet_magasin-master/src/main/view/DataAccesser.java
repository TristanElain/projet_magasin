package main.view;

import java.util.List;

import javafx.stage.Stage;
import main.MainApp;
import main.classes.Article;
import main.classes.Magasin;
import main.classes.Personne;

public class DataAccesser {
	
	private MainApp mainApp;
	
	private static DataAccesser instance = null;
	
	private DataAccesser(MainApp mainApp) {
		System.out.println("Création du singleton");
		this.mainApp = mainApp;
	}
	
	public static DataAccesser getInstance(MainApp mainApp) {
		if(instance == null) {
			instance = new DataAccesser(mainApp);
		}
		return instance;
	}
	
	
	public static DataAccesser getInstance() {
		return instance;
	}
	
	public void showHomeChildView(String childName) {
		mainApp.showHomeChildView(childName);
	}
	
	public List<Article> getArticles() {
		return getMagasin().getArticles();
	}
	
	public List<Personne> getClients() {
		return getMagasin().getClients();
	}
	
	public Magasin getMagasin() {
		return mainApp.getMagasin();
	}

	public Stage getPrimaryStage() {
		return mainApp.getPrimaryStage();
	}
	

}
