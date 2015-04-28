package view;

import java.util.ArrayList;
import java.util.Calendar;

import main.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class RootLayoutController {
    
    private ArrayList<Integer> initialDays = new ArrayList<Integer>();
    private ObservableList<Integer> initialDaysOL;
    private MainApp mainApp;
    private Calendar date;
    private int month,
    	day,
    	year,
    	selMonth,
    	oldDayValue;
    
    @FXML private ComboBox<String> monthBox;
    @FXML private ComboBox<Integer> dayBox;
    @FXML private ComboBox<Integer> yearBox;
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    @FXML
    private void setDays() {
    	this.selMonth = monthBox.getItems().indexOf(monthBox.getValue());
    	this.oldDayValue = dayBox.getValue();
    	dayBox.getItems().clear();
    	dayBox.setValue(oldDayValue);
		dayBox.getItems().addAll(initialDaysOL);
    	
    	if (selMonth != 1) {
    		for (int i = 29; i <= 30; i++) {
    			dayBox.getItems().add(i);
    		}
    		
	    	if (selMonth % 2 == 0) {
	    		dayBox.getItems().add(31);
	    	}
    	} else {
    		if (yearBox.getValue() % 4 == 0) {
    			dayBox.getItems().add(29);
    		}
    	}
    	
    	if (dayBox.getValue() > dayBox.getItems().get(dayBox.getItems().size() - 1)) {
    		dayBox.setValue(dayBox.getItems().get(dayBox.getItems().size() - 1));
    	}
    	
    }
    
    @FXML
    void initialize() {
    	
        for (int i = 1; i < 29; i++) {
        	this.initialDays.add(i);
        }
        
        this.initialDaysOL = FXCollections.observableArrayList(initialDays);

        this.date = Calendar.getInstance();
        
        this.month = date.get(Calendar.MONTH);
        this.day = date.get(Calendar.DAY_OF_MONTH);
        this.year = date.get(Calendar.YEAR);
		
		monthBox.getItems().addAll(
				"January","February","March","April","May","June","July","August","September","October","November","December"
		);
		
		for (int i = year - 5; i <= (year + 5); i++) {
			yearBox.getItems().add(i);
		}
	    
		monthBox.setValue(monthBox.getItems().get(month));
		dayBox.setValue(day);
		yearBox.setValue(year);
		
		setDays();
    }
}