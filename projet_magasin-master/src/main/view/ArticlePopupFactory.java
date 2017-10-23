package main.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.MainApp;
import main.classes.Article;
import main.classes.FauteuilRoulant;
import main.classes.LitMedicalise;
import main.classes.Matelas;
import main.classes.SouleveMalade;
import main.classes.TableAlite;
import main.view.popups.AddArticleController;


public abstract class ArticlePopupFactory {
	private static final String CLASS_PACKAGE = "main.classes.";
	
	public static AnchorPane createPopup(String className, Stage dialogStage) throws IOException, ClassNotFoundException {
		return createPopup(className, null, dialogStage);
		
	}
	
	public static AnchorPane createPopup(String className, Article article, Stage dialogStage) throws IOException, ClassNotFoundException { 
		boolean setFields = article != null;
		
		AddArticleController controller = new AddArticleController(CLASS_PACKAGE+className);
		controller.setDialogStage(dialogStage);
		if(setFields) {
			controller.setArticle(article);
		}
	
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/popups/AddArticle.fxml"));
		loader.setController(controller);
		AnchorPane popupPanel = loader.load();
		GridPane gridPanel = (GridPane) popupPanel.getChildren().get(0);
		
		Label assiseLabel = new Label("Assise");
		Label capaciteLevageLabel = new Label("Capacité de levage");
		Label degrePivotLabel = new Label("Degré de pivot");
		Label dimensionsLabel = new Label("Dimensions*");
		Label explications = new Label("* Dimensions sous\n la forme : lxL");
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
		
		
			
		if(setFields) {
			
		}
		switch (className) {
			case "Matelas" :
				gridPanel.add(poidsMaxLabel, 0, 5);
				gridPanel.add(poidsMaxField, 1, 5);
				gridPanel.add(dimensionsLabel, 0, 6);
				gridPanel.add(dimensionsField, 1, 6);
				gridPanel.add(gonflageLabel, 0, 7);
				gridPanel.add(gonflageField, 1, 7);
				gridPanel.add(explications, 0, 8);
				
				if(setFields) {
					Matelas matelas = (Matelas) article;
					poidsMaxField.setText(Integer.toString(matelas.getPoidsMax()));
					gonflageField.setText(Double.toString(matelas.getTempsGonflage()));
					
					Double[] dimensions = matelas.getDimension();
					String dimensionsString = Double.toString(dimensions[0]) + "x" + Double.toString(dimensions[1]);
					
					dimensionsField.setText(dimensionsString);
					
				}
				break;
				
			case "TableAlite" :
				
				gridPanel.add(poidsMaxLabel, 0, 5);
				gridPanel.add(poidsMaxField, 1, 5);
				gridPanel.add(dimensionsLabel, 0, 6);
				gridPanel.add(dimensionsField, 1, 6);
				gridPanel.add(explications, 0, 8);
				
				if(setFields) {
					TableAlite tableAlite = (TableAlite) article;
					poidsMaxField.setText(Integer.toString(tableAlite.getPoidsMax()));
					
					Double[] dimensions = tableAlite.getDimension();
					String dimensionsString = Double.toString(dimensions[0]) + "x" + Double.toString(dimensions[1]);
					
					dimensionsField.setText(dimensionsString);
					
				}
				
				break;
				
			case "LitMedicalise" :
				
				gridPanel.add(poidsMaxLabel, 0, 5);
				gridPanel.add(poidsMaxField, 1, 5);
				gridPanel.add(dimensionsLabel, 0, 6);
				gridPanel.add(dimensionsField, 1, 6);
				gridPanel.add(explications, 0, 8);
				
				if(setFields) {
					LitMedicalise litMedicalise = (LitMedicalise) article;
					poidsMaxField.setText(Integer.toString(litMedicalise.getPoidsMax()));
					
					Double[] dimensions = litMedicalise.getDimension();
					String dimensionsString = Double.toString(dimensions[0]) + "x" + Double.toString(dimensions[1]);
					
					dimensionsField.setText(dimensionsString);
					
				}
				
				break;
				
			case "SouleveMalade" :
				
				gridPanel.add(capaciteLevageLabel, 0, 5);
				gridPanel.add(capaciteLevageField, 1, 5);
				gridPanel.add(degrePivotLabel, 0, 6);
				gridPanel.add(degrePivotField, 1, 6);
				
				
				if(setFields) {
					SouleveMalade souleveMalade = (SouleveMalade) article;
					capaciteLevageField.setText(Integer.toString(souleveMalade.getCapaciteLevage()));
					
					degrePivotField.setText(Double.toString(souleveMalade.getDegrePivot()));
					
				}
				break;
				
			case "FauteuilRoulant" :
				
				gridPanel.add(assiseLabel, 0, 5);
				gridPanel.add(assiseField, 1, 5);
				gridPanel.add(poidsLabel, 0, 6);
				gridPanel.add(poidsField, 1, 6);
				
				if(setFields) {
					FauteuilRoulant fauteuilRoulant = (FauteuilRoulant) article;
					assiseField.setText(Integer.toString(fauteuilRoulant.getAssise()));
					
					poidsField.setText(Double.toString(fauteuilRoulant.getPoids()));
					
				}
				
				break;
			
		}
		return popupPanel;
	}

}
