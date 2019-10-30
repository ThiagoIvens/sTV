package th.sTV;

import java.net.URL;
import java.util.ResourceBundle;
import Modelos.Musica;
import Modelos.MusicaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class updateMusicController implements Initializable{
	
    @FXML
    private TextField txtId;
	
	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtAutor;

	@FXML
	private TextField txtLink;

	@FXML
	private Button btnAdd;

	public TextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(TextField txtNome) {
		this.txtNome = txtNome;
	}

	public TextField getTxtAutor() {
		return txtAutor;
	}

	public void setTxtAutor(TextField txtAutor) {
		this.txtAutor = txtAutor;
	}

	public TextField getTxtLink() {
		return txtLink;
	}

	public void setTxtLink(TextField txtLink) {
		this.txtLink = txtLink;
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
		String autor = txtAutor.getText();
		String link = txtLink.getText();
		Musica m = new Musica(MusicController.getMusicaOperador().getId(), nome, autor, link);
		
		new MusicaDAO().update(m);
		
		musicController.limparLista();
		Button btn = (Button) e.getSource();
		Scene scene = btn.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtId.setText(String.valueOf(MusicController.getMusicaOperador().getId()));
		txtAutor.setText(MusicController.getMusicaOperador().getAutor());
		txtLink.setText(MusicController.getMusicaOperador().getLink());
		txtNome.setText(MusicController.getMusicaOperador().getNome());
		txtId.setEditable(false);
	}
	
	private MusicController musicController;
	
	public void selected(MusicController musicController) {
		this.musicController = musicController;
	}
}
