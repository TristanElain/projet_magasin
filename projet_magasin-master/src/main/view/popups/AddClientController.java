package main.view.popups;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.classes.Personne;
import main.view.DataAccesser;

public class AddClientController {
	
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
	
	
	
	private Stage dialogStage;
	
	private DataAccesser dataAccesser;
	
	@FXML
	public void initialize() {
		dataAccesser = DataAccesser.getInstance();
	}
	
	
	public void handleAdd(Event event) {
		
		if(isAllFieldsOk()) {
			int index = 0;
			
			String nom = champNom.getText();
			String prenom = champPrenom.getText();
			String adresse = champAdresse.getText();
			String telephone = champTelephone.getText();
			
			Personne newPersonne = new Personne(nom, prenom, adresse, telephone);
			
			dataAccesser.getClients().add(newPersonne);
			
			// TODO - Afficher la fiche du client.
			
			dialogStage.close();
		}
	}
	
	private boolean isAllFieldsOk() {
		String errorMsg = "";
		
		if(champNom.getText().isEmpty()) {
			errorMsg += "Champ nom invalide \n";
			
		} 
		if(champPrenom.getText().isEmpty()) {
			errorMsg += "Champ prenom invalie\n";
			
		} 	
		if(champAdresse.getText().isEmpty()) {
			errorMsg += "Champ adresse invalide\n";
			
		}
		if(champTelephone.getText().isEmpty()) {
			errorMsg += "Champ téléphone invalide \n";
		}
		
		
		if(errorMsg.length() > 0) {
			showError(errorMsg);
			return false;
		}
		
		return true;
	}
	
	private void showError(String error) {
		Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(dataAccesser.getPrimaryStage());
        alert.setTitle("Saisie Incorrecte");
        alert.setHeaderText("Champ(s) invalide(s)");
        alert.setContentText(error);

        alert.showAndWait();
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}
