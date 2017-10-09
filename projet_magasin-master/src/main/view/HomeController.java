package main.view;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import main.MainApp;

public class HomeController {
	
	@FXML
	private SplitPane splitPane;
	
	@FXML
	private Button articles;
	
	@FXML
	private Button clients;
	
	@FXML
	private Button logs;
	
	

	@FXML
	private void initialize() {}
	
	
	@FXML
	private void handleClickArticles() {
		AnchorPane articlesPane = null;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Articles.fxml"));
			articlesPane = (AnchorPane) loader.load();
			
			ObservableList<Node> list = splitPane.getItems();

			AnchorPane leftPane = (AnchorPane) list.get(1);
			leftPane.getChildren().add(articlesPane);
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
