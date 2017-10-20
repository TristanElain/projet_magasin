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
	
	private DataAccesser dataAccesser;

	@FXML
	private void initialize() {
		dataAccesser = dataAccesser.getInstance();
	}
	
	
	@FXML
	private void handleClick(Event event) {
		Button buttonClicked = (Button) event.getSource();
		
		dataAccesser.showHomeChildView(buttonClicked.getText());
	}
}
