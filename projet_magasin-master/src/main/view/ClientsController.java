package main.view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.MainApp;
import main.classes.Personne;
import main.view.popups.AddClientController;

public class ClientsController {

	@FXML
	private AnchorPane mainPanel;

	@FXML
	private void initialize() {
		ListView<Personne> listView = new ListView<>(
				FXCollections.observableArrayList(DataAccesser.getInstance().getClients()));
		mainPanel.getChildren().add(listView);
		listView.setPrefSize(mainPanel.getPrefWidth(), mainPanel.getPrefHeight() - 60);

		// Evenement quand l'utilisateur clicke sur une personne :
		// Ouverture de l'écrant de modification
		listView.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				ListView<Personne> lv = (ListView) event.getSource();

				Personne personneSelected = lv.getSelectionModel().getSelectedItem();
				if (personneSelected != null) {
					try {
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(MainApp.class.getResource("view/EditClient.fxml"));
						AnchorPane editPanel = (AnchorPane) loader.load();

						AnchorPane parentPanel = (AnchorPane) mainPanel.getParent();
						parentPanel.getChildren().add(editPanel);

						EditClientController controller = loader.getController();
						controller.setPersonne(personneSelected);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

	@FXML
	private void handleAdd(Event event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/popups/AddClient.fxml"));
			AnchorPane addPopup = loader.load();

			// Création de la fenetre de dialogue en la liant à la fenetre principale
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Ajouter un Client");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(DataAccesser.getInstance().getPrimaryStage());
			Scene scene = new Scene(addPopup);
			dialogStage.setScene(scene);

			AddClientController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			dialogStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
