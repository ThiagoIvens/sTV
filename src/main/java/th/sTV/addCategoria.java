package th.sTV;

import Modelos.Categoria;
import Modelos.CategoriaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addCategoria {

	@FXML
	private TextField txtNome;

	@FXML
	private Button btnAdd;

	@FXML
	void addDataBase(ActionEvent e) {
		String nome = txtNome.getText();
		Categoria cat = new Categoria(nome);

		new CategoriaDAO().add(cat);

		configController.limparCB();
		Button btn = (Button) e.getSource();
		Scene scene = btn.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();

	}
	private ConfigController configController;
	
	public void selected(ConfigController configController) {
		this.configController = configController;
	}

}
