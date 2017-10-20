package main.view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.MainApp;
import main.classes.Location;
import main.classes.Personne;
import main.view.popups.AddLocationController;

public class EditClientController {
	
	@FXML
	private AnchorPane mainPanel;
	
	@FXML
	private TextField champNom;
	
	@FXML
	private TextField champPrenom;
	
	@FXML
	private TextField champAdresse;
	
	@FXML
	private TextField champTelephone;
	
	@FXML
	private ListView listeLocations;
	
	private Personne personneSelected;
	
	private Personne personneCloned;
	
	private DataAccesser dataAccesser;
	

	
	@FXML
	private void initialize() {
		dataAccesser = DataAccesser.getInstance();
	}
	
	
	public void setPersonne(Personne personne) {
		this.personneSelected = personne;
		
		// Clonage de la personne au cas ou l'utilisateur veuille annuler les modifications
		personneCloned = new Personne(personneSelected.getNom(), personneSelected.getPrenom()
				, personneSelected.getAdresse(), personneSelected.getTelephone());
		for(Location location : personneSelected.getLocations()) {
			personneCloned.ajouterLocation(location);
		}
		
		champNom.setText(personneCloned.getNom());
		champPrenom.setText(personneCloned.getPrenom());
		champAdresse.setText(personneCloned.getAdresse());
		champTelephone.setText(personneCloned.getTelephone());
		
		listeLocations.getItems().addAll(personneCloned.getLocations());
	}
	
	public void handleReturn() {
		dataAccesser.showHomeChildView(MainApp.CLIENTS_VIEW_NAME);
	}
	
	@FXML
	private void handleNewLocation(Event event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/popups/AddLocation.fxml"));
			AnchorPane addPopup = loader.load();

			// Création de la fenetre de dialogue en la liant à la fenetre principale
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Ajouter une Location");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(DataAccesser.getInstance().getPrimaryStage());
			Scene scene = new Scene(addPopup);
			dialogStage.setScene(scene);

			AddLocationController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPersonne(personneCloned);

			dialogStage.showAndWait();
			
			listeLocations.setItems(FXCollections.observableArrayList(personneCloned.getLocations()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleSave(Event event) {
		this.personneSelected = personneCloned;
		
	}

}
