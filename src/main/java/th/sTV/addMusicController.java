package th.sTV;

import Modelos.Musica;
import Modelos.MusicaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addMusicController {
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextField txtAutor;
	
    @FXML
    private TextField txtLink;

	@FXML
	private Button btnAdd;


	private MusicController musicController;

	
	@FXML
	void addDataBase(ActionEvent e) {
		String nome = txtNome.getText();
		String autor = txtAutor.getText();
		String link = txtLink.getText();
		
		Musica m = new Musica(nome, autor, link);

		new MusicaDAO().add(m);
		
		
		musicController.limparLista();
		Button btn = (Button) e.getSource();
		Scene scene = btn.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();

	}
	
	public void selected(MusicController musicController) {
		this.musicController = musicController;
	}
}
