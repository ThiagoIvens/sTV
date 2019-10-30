package th.sTV;

import java.net.URL;
import java.util.ResourceBundle;
import Modelos.Video;
import Modelos.VideoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class updateVideoController implements Initializable {

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtFormato;

	@FXML
	private TextField txtCaminho;

	@FXML
	private Button btnAdd;

	public TextField getTxtId() {
		return txtId;
	}

	public void setTxtId(TextField txtId) {
		this.txtId = txtId;
	}

	public TextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(TextField txtNome) {
		this.txtNome = txtNome;
	}

	public TextField getTxtFormato() {
		return txtFormato;
	}

	public void setTxtFormato(TextField txtFormato) {
		this.txtFormato = txtFormato;
	}

	public TextField getTxtCaminho() {
		return txtCaminho;
	}

	public void setTxtCaminho(TextField txtCaminho) {
		this.txtCaminho = txtCaminho;
	}

	public Button getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(Button btnAdd) {
		this.btnAdd = btnAdd;
	}
	

	@FXML
	void addDataBase(ActionEvent e) {
		String nome = txtNome.getText();
		String autor = txtFormato.getText();
		String link = txtCaminho.getText();
		Video v = new Video(videoController.getVideoOperador().getId(), nome, autor, link);

		new VideoDAO().update(v);
		
		videoController.limparLista();

		Button btn = (Button) e.getSource();
		Scene scene = btn.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtId.setText(String.valueOf(videoController.getVideoOperador().getId()));
		txtFormato.setText(videoController.getVideoOperador().getFormato());
		txtCaminho.setText(videoController.getVideoOperador().getNomeformato());
		txtNome.setText(videoController.getVideoOperador().getNome());
		txtId.setEditable(false);
		txtFormato.setEditable(false);
		txtCaminho.setEditable(false);
	}
	
	private videoController videoController;
	
	public void selected(videoController videoController) {
		this.videoController = videoController;
	}
}
