package main.view.popups;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.MainApp;
import main.classes.Article;
import main.view.DataAccesser;

public class AddArticleController {
	
	private String classArticleName;
	
	private List<String> labelValues;
	private List<String> textFieldValues;
	
	private Stage dialogStage;
	
	private DataAccesser dataAccesser;
	@FXML
	private GridPane gridPanel;
	
	
	public AddArticleController(String className) {
		this.classArticleName = className;
		this.textFieldValues = new ArrayList<>();
		this.labelValues = new ArrayList<>();
		dataAccesser = DataAccesser.getInstance();
	}
	
	public AddArticleController() {
		this.textFieldValues = new ArrayList<>();
		this.labelValues = new ArrayList<>();
		dataAccesser = DataAccesser.getInstance();
	}
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void handleClick() {
		
		
		if(isAllFieldsOk()) {
			try {
				Class<?>[] articleContructorClassArguments = new Class<?>[textFieldValues.size()];
				Object[] articleContructorArguments = new Object[textFieldValues.size()];
				
				int i = 0;
				// Ajout des attributs String
				while( i < 3) {
					articleContructorClassArguments[i] = String.class;
					articleContructorArguments[i] = textFieldValues.get(i);
					i++;
				}
				
				// Ajout du prix et du stock
				articleContructorClassArguments[i] = Double.class;
				articleContructorArguments[i] = Double.parseDouble(textFieldValues.get(i++));
				
				articleContructorClassArguments[i] = Integer.class;
				articleContructorArguments[i] = Integer.parseInt(textFieldValues.get(i++));
				
				// Ajout des autres attributs		
				articleContructorClassArguments[i] = Integer.class;
				articleContructorArguments[i] = Integer.parseInt(textFieldValues.get(i++));
				
				switch(labelValues.get(i)) {
					case "Poids" :
						articleContructorClassArguments[i] = Double.class;
						articleContructorArguments[i] = Double.parseDouble(textFieldValues.get(i++));
						break;
					
					case "Degré de pivot" :
						articleContructorClassArguments[i] = Integer.class;
						articleContructorArguments[i] = Integer.parseInt(textFieldValues.get(i++));
						break;
						
					case "Dimensions*" :
						articleContructorClassArguments[i] = Double[].class;
						Double[] dimensions = new Double[3];
						int compteur = 0;
						for(String str : textFieldValues.get(i).split("x")) {
							dimensions[compteur] = Double.parseDouble(str);
							compteur++;
						}
						articleContructorArguments[i++] = dimensions;
						break;
				}
				
				if(articleContructorClassArguments.length > 7) {
					articleContructorClassArguments[i] = Double.class;
					articleContructorArguments[i] = Double.parseDouble(textFieldValues.get(i++));
					
				}
				
				
				// Instanciation de l'objet à partir du nom de la classe
				Class<?> articleClass = Class.forName(classArticleName);
				Constructor<?> articleConstructor = articleClass.getConstructor(articleContructorClassArguments);
				
				Object article = articleConstructor.newInstance(articleContructorArguments);
				
				// Fermeture de la fenêtre d'ajout et rafraichissement de la liste d'articles
				dataAccesser.getArticles().add((Article) article);

				dataAccesser.showHomeChildView(MainApp.ARTICLES_VIEW_NAME);
				
				dialogStage.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
	}
	
	private boolean isAllFieldsOk() {
		String errorMsg = "";
		
		int labelCompteur = 0;
		for(Node node : gridPanel.getChildren()) {
			if(node instanceof Label) {
				labelValues.add(((Label) node).getText());
			}
			else if(node instanceof TextField) {
				TextField textField = (TextField) node;
				if(textField.getText().isEmpty()) {
					errorMsg += "Champ " + labelValues.get(labelCompteur) + " invalide \n";
				}
				else {
					textFieldValues.add(textField.getText());
				}
				labelCompteur++;
			}
		}
		
		
		if(errorMsg.length() == 0) {			
			String prixStr = textFieldValues.get(3);
			try {
				Double.parseDouble(prixStr);
			} catch(Exception e) {
				errorMsg += labelValues.get(4) + " doit être un nombre relatif\n";
			}
			
			String stockStr = textFieldValues.get(4);
			try {
				Integer.parseInt(stockStr);
			} catch(Exception e) {
				errorMsg += labelValues.get(3) + " doit être un nombre relatif\n";
			}
			
			String champ5Str = textFieldValues.get(5);
			try {
				Integer.parseInt(champ5Str);
			} catch(Exception e) {
				errorMsg += labelValues.get(5) + " doit être entier\n";
			}
			
			String label6Str = labelValues.get(6);
			String champ6Str = textFieldValues.get(6);
			if(label6Str.equals("Dimensions*")) {
				if(!champ6Str.matches("\\d+.?\\d*x\\d+.?\\d*")) {					
					errorMsg += labelValues.get(6) + " doit être rempli sous la forme lxL\n";
				}
			}
			else if(label6Str.equals("Poids")){
				try {
					Double.parseDouble(champ6Str);
				}
				catch(Exception e) {
					errorMsg += labelValues.get(6) + " doit être un nombre relatif\n";
				}
			}
			else {
				try {
					Integer.parseInt(champ6Str);
				}
				catch(Exception e) {
					errorMsg += labelValues.get(6) + " doit être un nombre entier\n";
				}
			}
			
			
			if(labelValues.size() > 7) {
				String champ7Str = textFieldValues.get(7);
				try {
					Double.parseDouble(champ7Str);
				}
				catch (Exception e) {
					errorMsg += labelValues.get(7) + " doit être un nombre relatif\n";
				}
			}
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
	
	public void setClassName(String className) {
		this.classArticleName = className;
	}
}
