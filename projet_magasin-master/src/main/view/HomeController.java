package main.view;

import java.io.IOException;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.Event;
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
	private void initialize() {}
	
	
	@FXML
	private void handleClick(Event event) {
		Button buttonClicked = (Button) event.getSource();
		
		AnchorPane articlesPane = null;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/"+buttonClicked.getText()+".fxml"));
			articlesPane = (AnchorPane) loader.load();
			
			ObservableList<Node> list = splitPane.getItems();

			AnchorPane rightPane = (AnchorPane) list.get(1);
			
			List<Node> children = rightPane.getChildren();
			//On évite d'entasser les Panels les uns sur les autres
			if(!children.isEmpty()) {
				children.clear();
			}
			children.add(articlesPane);
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
