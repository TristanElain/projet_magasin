package main.view.popups;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.MainApp;
import main.classes.Article;
import main.classes.Location;
import main.classes.Personne;
import main.view.DataAccesser;

public class AddLocationController {
	
	@FXML
	private DatePicker dateLocation;
	
	@FXML
	private AnchorPane mainPanel;
	
	@FXML
	private ScrollPane scrollPane;
	
	private ListView<Article> listArticle;
	
	private DataAccesser dataAccesser;
	
	private Stage dialogStage;
	
	private Location location;
	
	private Personne personne;
	
	public AddLocationController() {
		dataAccesser = DataAccesser.getInstance();
	}

	@FXML
	private void initialize() {
		LocalDate localToday = LocalDate.now();
		dateLocation.setValue(localToday);
		
		Calendar today = GregorianCalendar.from(localToday.atStartOfDay(ZoneId.systemDefault()));
		location = new Location(today, null);
		//personne.ajouterLocation(location);
		
		listArticle = new ListView<Article>(FXCollections.observableArrayList(location.getArticles()));
		listArticle.setPrefWidth(scrollPane.getPrefWidth()-4);
		listArticle.setPrefHeight(scrollPane.getPrefHeight()-4);
		
		scrollPane.setContent(listArticle);
	}
	
	@FXML
	private void handleSave(Event event) {
			personne.ajouterLocation(location);
			
			
			dialogStage.close();
	}
	
	@FXML
	private void handleAdd(Event event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/popups/NewArticleLocation.fxml"));
			ScrollPane addPopup = loader.load();

			// Création de la fenetre de dialogue en la liant à la fenetre principale
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Ajouter un Artcile à la location");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(DataAccesser.getInstance().getPrimaryStage());
			Scene scene = new Scene(addPopup);
			dialogStage.setScene(scene);

			NewArticleLocationController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setLocation(this.location);

			dialogStage.showAndWait();
			listArticle.setItems(FXCollections.observableArrayList(location.getArticles()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setDialogStage(Stage primaryStage) {
		this.dialogStage = primaryStage;
	}
	
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	
}
