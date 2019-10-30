package th.sTV;

import Modelos.Video;
import Modelos.VideoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addVideoController {
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextField txtFormato;
	
    @FXML
    private TextField txtCaminho;
    
	private videoController videoController;

	@FXML
	private Button btnAdd;

	@FXML
	void addDataBase(ActionEvent e) {
		String nome = txtNome.getText();
		String formato = txtFormato.getText();
		String caminho = txtCaminho.getText();
		
		Video v = new Video(nome, formato, caminho);

		new VideoDAO().add(v);

		videoController.limparLista();
		Button btn = (Button) e.getSource();
		Scene scene = btn.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();

	}
	
	public void selected(videoController videoController) {
		this.videoController = videoController;
	}
}
