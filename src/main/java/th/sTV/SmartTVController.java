package th.sTV;

import java.io.IOException;

import javafx.fxml.FXML;

public class SmartTVController {
	
    @FXML
    void callApps() throws IOException {
    	App.setRoot("apps");
    }
    @FXML
    void callMusica() throws IOException {
    	App.setRoot("music");
    }
    @FXML
    void callLoja() throws IOException {
    	App.setRoot("loja");
    }
    @FXML
    void callVideo() throws IOException {
    	App.setRoot("video");
    }
    @FXML
    void callConfig() throws IOException {
    	App.setRoot("config");
    }
}
