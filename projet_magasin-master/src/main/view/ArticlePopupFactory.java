package main.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.MainApp;
import main.view.popups.AddArticleController;


public abstract class ArticlePopupFactory {
	private static final String CLASS_PACKAGE = "main.classes.";
	
	public static AnchorPane createPopup(String className, Stage dialogStage) throws IOException, ClassNotFoundException {
		
		AddArticleController controller = new AddArticleController(CLASS_PACKAGE+className);
		controller.setDialogStage(dialogStage);
	
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/popups/AddArticle.fxml"));
		loader.setController(controller);
		AnchorPane popupPanel = loader.load();
		GridPane gridPanel = (GridPane) popupPanel.getChildren().get(0);
		
		Label assiseLabel = new Label("Assise");
		Label capaciteLevageLabel = new Label("Capacité de levage");
		Label degrePivotLabel = new Label("Degré de pivot");
		Label dimensionsLabel = new Label("Dimensions*");
		Label explications = new Label("*Dimensions sous\n la forme : lxL");
		Label gonflageLabel = new Label("Temps de Gonflage");
		Label poidsLabel	= new Label("Poids");
		Label poidsMaxLabel = new Label("Poids max");
		
		TextField assiseField = new TextField();
		TextField capaciteLevageField = new TextField();
		TextField degrePivotField = new TextField();
		TextField dimensionsField = new TextField();
		TextField gonflageField = new TextField();
		TextField poidsField = new TextField();
		TextField poidsMaxField = new TextField();
		
		switch (className) {
			case "Matelas" :
				gridPanel.add(poidsMaxLabel, 0, 5);
				gridPanel.add(poidsMaxField, 1, 5);
				gridPanel.add(dimensionsLabel, 0, 6);
				gridPanel.add(dimensionsField, 1, 6);
				gridPanel.add(gonflageLabel, 0, 7);
				gridPanel.add(gonflageField, 1, 7);
				gridPanel.add(explications, 0, 8);
				
				break;
				
			case "TableAlite" :
				
				gridPanel.add(poidsMaxLabel, 0, 5);
				gridPanel.add(poidsMaxField, 1, 5);
				gridPanel.add(dimensionsLabel, 0, 6);
				gridPanel.add(dimensionsField, 1, 6);
				gridPanel.add(explications, 0, 8);
				
				break;
				
			case "LitMedicalise" :
				
				gridPanel.add(poidsMaxLabel, 0, 5);
				gridPanel.add(poidsMaxField, 1, 5);
				gridPanel.add(dimensionsLabel, 0, 6);
				gridPanel.add(dimensionsField, 1, 6);
				gridPanel.add(explications, 0, 8);
				
				break;
				
			case "SouleveMalade" :
				
				gridPanel.add(capaciteLevageLabel, 0, 5);
				gridPanel.add(capaciteLevageField, 1, 5);
				gridPanel.add(degrePivotLabel, 0, 6);
				gridPanel.add(degrePivotField, 1, 6);
				
				break;
				
			case "FauteuilRoulant" :
				
				gridPanel.add(assiseLabel, 0, 5);
				gridPanel.add(assiseField, 1, 5);
				gridPanel.add(poidsLabel, 0, 6);
				gridPanel.add(poidsField, 1, 6);
				
				break;
			
		}
		return popupPanel;
	}

}
