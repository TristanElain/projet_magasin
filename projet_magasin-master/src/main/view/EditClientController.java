package main.view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.classes.Location;
import main.classes.Personne;

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
	
	@FXML
	private void initialize() {
		
	}
	
	
	public void setPersonne(Personne personne) {
		this.personneSelected = personne;
		
		champNom.setText(personneSelected.getNom());
		champPrenom.setText(personneSelected.getPrenom());
		champAdresse.setText(personneSelected.getAdresse());
		champTelephone.setText(personneSelected.getTelephone());
		
		for(Location location : personneSelected.getLocations()) {
			System.out.println("Location : " + location);
			listeLocations.getItems().add(location);
		}
	}

}
