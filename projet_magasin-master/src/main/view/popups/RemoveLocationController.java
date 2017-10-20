package main.view.popups;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import main.classes.Location;

public class RemoveLocationController {
	
	@FXML
	private DatePicker dateRetour;
	
	private Location location;
	
	private Stage dialogStage;
	
	@FXML
	private void initialize() {
		LocalDate localToday = LocalDate.now();
		dateRetour.setValue(localToday);
	}
	
	@FXML
	private void handleSave(Event event) {
		LocalDate localDateRetour = dateRetour.getValue();
		
		Calendar calendarDateRetour = GregorianCalendar.from(localDateRetour.atStartOfDay(ZoneId.systemDefault()));
		if(location.getDateLocation().compareTo(calendarDateRetour) > 0) {
			System.out.println(location.getDateLocation());
			System.out.println(calendarDateRetour);
			System.out.println("ERREUR");
		}
		else {
			location.setDateRetour(calendarDateRetour);
			dialogStage.close();
		}
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}
