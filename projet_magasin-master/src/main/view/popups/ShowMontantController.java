package main.view.popups;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ShowMontantController{
	
	@FXML
	private Label label;

	private Double montant;
	
	public ShowMontantController(Double montant) {
		this.montant = montant;
	}
	
	@FXML
	private void initialize() {
		label.setText(montant.toString() + " €");
		
	
	}
}
