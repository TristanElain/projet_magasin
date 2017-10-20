package main.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.MainApp;
import main.classes.Article;
import main.classes.FauteuilRoulant;
import main.classes.LitMedicalise;
import main.classes.Matelas;
import main.classes.SouleveMalade;
import main.classes.TableAlite;
import main.view.popups.AddArticleController;

public class ArticlesController {
	
	@FXML
	private AnchorPane mainPanel;	
	
	@FXML
	private ChoiceBox choiceBox;

	
	private Map<String, List<Article>> articlesByClass = new HashMap<>();;
	
	@FXML
	private void initialize() {
		System.out.println("articless controller initialize");
		
		List<String> classes = new ArrayList<>();
		classes.add(FauteuilRoulant.class.getSimpleName());
		classes.add(LitMedicalise.class.getSimpleName());
		classes.add(Matelas.class.getSimpleName());
		classes.add(SouleveMalade.class.getSimpleName());
		classes.add(TableAlite.class.getSimpleName());
		
		choiceBox.setItems(FXCollections.observableArrayList(classes));
		choiceBox.getSelectionModel().selectFirst();
		

		DataAccesser dataAccesser = DataAccesser.getInstance();

		ObservableList<Article> articles = FXCollections.observableArrayList(dataAccesser.getArticles());
		
		MultiOpenAccordion accordion = new MultiOpenAccordion();
		
		for(Article article : articles) {
			String articleClass = article.getClass().getSimpleName();
			
			if(!articlesByClass.containsKey(articleClass)) {
				List<Article> articleList = new ArrayList<>();
				articleList.add(article);
				
				articlesByClass.put(articleClass, articleList);
				TitledPane pane = new TitledPane();
				pane.setText(articleClass);
				accordion.add(pane);
				
			}
			else {
				articlesByClass.get(articleClass).add(article);
			}
		}
		
		for(TitledPane pane : accordion.getPanes()) {
			List<Article> articlesPane = articlesByClass.get(pane.getText());
			ListView<Article> listView = new ListView<>(FXCollections.observableArrayList(articlesPane)); 
			
			pane.setContent(listView);
		}
		
		mainPanel.getChildren().add(accordion);
		accordion.setPrefWidth(mainPanel.getPrefWidth());
		accordion.setMaxHeight(mainPanel.getPrefHeight()-60);
		
		
	}
	
	public void handleAdd(Event event) {
		try {
			AnchorPane addPopup;
			
			// Création de la fenetre de dialogue en la liant à la fenetre principale
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Ajouter un article");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(DataAccesser.getInstance().getPrimaryStage());
	        
	        addPopup = ArticlePopupFactory.createPopup((String) choiceBox.getSelectionModel().getSelectedItem(), dialogStage);
			
	        
	        Scene scene = new Scene(addPopup);
	        dialogStage.setScene(scene);

	        
	        dialogStage.show();
		} catch (IOException  | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
