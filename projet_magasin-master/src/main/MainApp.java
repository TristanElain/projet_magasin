package main;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.classes.Article;
import main.classes.LitMedicalise;
import main.classes.Location;
import main.classes.Matelas;
import main.classes.Personne;
import main.classes.TableAlite;
import main.view.DataAccesser;

public class MainApp extends Application {
	public final static String CLIENTS_VIEW_NAME = "Clients";
	public final static String ARTICLES_VIEW_NAME = "Articles";

	private List<Article> articles;
	
	private List<Personne> clients;

	private Stage primaryStage;
	private BorderPane rootLayout;
	private AnchorPane homeView;

	@Override
	public void start(Stage primaryStage) {		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Magasin");
		this.primaryStage.setResizable(false);

		DataAccesser.getInstance(this);
		
		initDatas();

		initRootLayout();

		showHome();

	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);			
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows the main view of the application
	 */
	public void showHome() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Home.fxml"));
			homeView = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(homeView);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showHomeChildView(String childName) {
		AnchorPane articlesPane = null;
		try {
			SplitPane splitPane = (SplitPane) homeView.getChildren().get(0);
		
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/"+childName+".fxml"));
			articlesPane = (AnchorPane) loader.load();
			
			ObservableList<Node> list = splitPane.getItems();

			AnchorPane rightPane = (AnchorPane) list.get(1);
			
			List<Node> children = rightPane.getChildren();
			//On évite d'entasser les Panels les uns sur les autres
			if(!children.isEmpty()) {
				children.clear();
			}
			children.add(articlesPane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initDatas() {
		articles = new LinkedList<>();
		clients = new LinkedList<>();
		
		Article lit1 = new LitMedicalise("ref 1", "marque", "modele", 1000d, 15, 20, new Double[] {2d, 2d});
		Article lit2 = new LitMedicalise("ref 2", "marque", "modele", 1000d, 15, 20, new Double[] {2d, 2d});
		
		articles.add(lit1);
		articles.add(lit2);
		articles.add(new Matelas("ref", "marque", "modele", 2000d, 23, 21, new Double[] {2d, 2d},  2d));
		articles.add(new TableAlite("ref", "marque", "modele", 1000d, 15, 20, new Double[] {2d, 2d}));
		
		Personne tristan = new Personne("Elain", "Tristan", "Nantes", "0662935668");
		
		clients.add(tristan);
		clients.add(new Personne("Le Bris", "Jules", "GrandChamps Des Fontaines", "0624097037"));
		
		Calendar dateLocation = Calendar.getInstance();
		Calendar dateRetour = Calendar.getInstance();
		dateRetour.set(2017, Calendar.DECEMBER, 25);
		Location loc1 = new Location(dateLocation, dateRetour);
		loc1.ajouterArticle(lit1);
		loc1.ajouterArticle(lit2);
		
		dateRetour = Calendar.getInstance();
		dateRetour.set(2018, Calendar.JANUARY, 31);
		Location loc2 = new Location(dateLocation, dateRetour);
		loc2.ajouterArticle(lit2);
		
		tristan.ajouterLocation(loc1);
		tristan.ajouterLocation(loc2);
		
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public List<Personne> getClients() {
		return clients;
	}

	
	public static void main(String[] args) {

		launch(args);
	}
}
