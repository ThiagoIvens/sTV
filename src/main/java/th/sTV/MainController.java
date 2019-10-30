package th.sTV;

import java.io.IOException;

import javafx.fxml.FXML;

public class MainController {
	

    @FXML
    void callSmartTV() throws IOException {
    	App.setRoot("smartTV");
    }
	
}
