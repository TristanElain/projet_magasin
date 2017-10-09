package main;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.classes.Article;
import main.classes.LitMedicalise;
import main.classes.Matelas;
import main.classes.TableAlite;
import main.view.DataAccesser;
import main.view.HomeController;

public class MainApp extends Application {

	private List<Article> articles = new LinkedList<>();

	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Magasin");
		this.primaryStage.setResizable(false);

		DataAccesser.getInstance(this);

		initRootLayout();

		showHome();

	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {

			articles.add(new LitMedicalise("ref 1", "marque", "modele", 1000, 15, 20, new double[] {2d, 2d}));
			articles.add(new LitMedicalise("ref 2", "marque", "modele", 1000, 15, 20, new double[] {2d, 2d}));
			articles.add(new Matelas("ref", "marque", "modele", 2000, 23, 21, new double[] {2d, 2d},  2d));
			articles.add(new TableAlite("ref", "marque", "modele", 1000, 15, 20, new double[] {2d, 2d}));
			
			
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
			AnchorPane homeView = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(homeView);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
