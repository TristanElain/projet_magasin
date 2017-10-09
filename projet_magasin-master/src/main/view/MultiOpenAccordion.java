package main.view;
 
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
 
public class MultiOpenAccordion extends ScrollPane {
   private ObservableList<TitledPane> panes = FXCollections.observableArrayList();
   private VBox vBox = new VBox();
 
   public MultiOpenAccordion() {
      // Falls keine horizontale Scrollleiste gewünscht ist, muss die Breite der VBox 
      // and die ScrollPane angepasst werden:
	 
      widthProperty().addListener(new ChangeListener<Number>() {
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			vBox.setMinWidth(newValue.doubleValue());
            vBox.setPrefWidth(newValue.doubleValue());
            vBox.setMaxWidth(newValue.doubleValue());
		}
      });
      setHbarPolicy(ScrollBarPolicy.NEVER);
 
      // Änderungen an der Liste werden hier behandelt:
      panes.addListener((ListChangeListener<TitledPane>) c -> {
         while (c.next()) {
            if (c.wasAdded()) {
            for(TitledPane pane : c.getAddedSubList()) {
            	VBox.setVgrow(pane, Priority.NEVER);
            	vBox.getChildren().add(pane);
            }
//               vBox.getChildren().addAll(c.getAddedSubList());
            }
            if (c.wasRemoved()) {
               vBox.getChildren().removeAll(c.getRemoved());
            }
            if (c.wasReplaced()) {
            }
            if (c.wasUpdated()) {
            }
            if (c.wasPermutated()) {
            }
         }
      });
      setContent(vBox);
   }
 
   public List<TitledPane> getPanes() {
      return panes;
   }
   
   public void add(TitledPane pane) {
	   panes.add(pane);
   }
   
   public void remove(TitledPane pane) {
	   panes.remove(pane);
   }
 
   public void setExpandedPane(TitledPane expandedPane) {
      expandedPane.setExpanded(true);
   }
}