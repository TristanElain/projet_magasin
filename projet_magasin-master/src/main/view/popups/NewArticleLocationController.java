package main.view.popups;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import main.classes.Article;
import main.classes.Location;
import main.view.DataAccesser;

public class NewArticleLocationController {
	
	@FXML
	private ScrollPane mainPanel;

	private ListView<Article> listView;
	
	private DataAccesser dataAccesser;
	
	private Stage dialogStage;
	
	private Location location;
	
	 public NewArticleLocationController() {
		dataAccesser = DataAccesser.getInstance();
	}
	
	@FXML
	private void initialize() {
		this.listView = new ListView<>(FXCollections.observableList(dataAccesser.getArticles()));
		
		listView.setPrefWidth(mainPanel.getPrefWidth()-3);
		listView.setPrefHeight(mainPanel.getPrefHeight()-3);
		mainPanel.setContent(listView);
		
		listView.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				ListView<Article> lv = (ListView) event.getSource();
				
				Article articleSelected = lv.getSelectionModel().getSelectedItem();
				if(articleSelected != null) {
					//parentList.getItems().add(articleSelected);
					location.ajouterArticle(articleSelected);
					dialogStage.close();
				}
				
			}
		});
		
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
}
