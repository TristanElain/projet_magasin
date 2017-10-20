package main.view;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.MainApp;
import main.classes.Location;
import main.view.popups.AddClientController;
import main.view.popups.ShowMontantController;

public class LogsController {
	@FXML
	private AnchorPane mainPanel;
	
	private ListView<File> listView;
	
	private List<File> files;
	
	private DataAccesser dataAccesser;
	
	public LogsController() {
		dataAccesser = DataAccesser.getInstance();
		File archiveFolder = new File(Location.REPERTOIRE_ARCHIVES);
		
		files = new LinkedList<>();
		for(File file : archiveFolder.listFiles()) {
			files.add(file);
		}
	}
	
	@FXML
	private void initialize() {
		listView = new ListView<>(FXCollections.observableList(files));
		
		mainPanel.getChildren().add(listView);
		listView.setPrefWidth(mainPanel.getPrefWidth());
		listView.setPrefHeight(mainPanel.getPrefHeight()-60);
	}
	
	@FXML
	private void handleShowMontant() {
		File file = listView.getSelectionModel().getSelectedItem();
		
		if(file != null) {
			String fileName = file.getName();
			
			String year = fileName.substring(0, 4);
			String month = fileName.substring(4,6);

			Calendar cal = Calendar.getInstance();
			cal.set(Integer.parseInt(year), Integer.parseInt(month)-1, 1);
			
			Double montant = dataAccesser.getMagasin().calculerRevenuMensuel(cal);
			try {
				ShowMontantController controller = new ShowMontantController(montant);
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainApp.class.getResource("view/popups/ShowMontant.fxml"));
				loader.setController(controller);
				AnchorPane addPopup = loader.load();

				// Création de la fenetre de dialogue en la liant à la fenetre principale
				Stage dialogStage = new Stage();
				dialogStage.setTitle("Montant pour le " + month + "/" + year);
				dialogStage.initModality(Modality.WINDOW_MODAL);
				dialogStage.initOwner(DataAccesser.getInstance().getPrimaryStage());
				Scene scene = new Scene(addPopup);
				dialogStage.setScene(scene);

				dialogStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("INFORMATION");
			alert.setHeaderText("Aucun fichier sélectionné");
			alert.setContentText("Veuillez sélectionner un fichier dans la liste");

			alert.showAndWait();
		}
	}
}
