package th.sTV;

import java.net.URL;
import java.util.ResourceBundle;


import Modelos.AppsLoja;
import Modelos.AppsLojaDAO;
import Modelos.Categoria;
import Modelos.CategoriaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addAppsLojaController implements Initializable{

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtDesc;

	@FXML
	private TextField txtLink;
	
	private LojaController lojaController;

	@FXML
	private ComboBox<Categoria> cbAdd;

	@FXML
	private Button btnAdd;

	public TextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(TextField txtNome) {
		this.txtNome = txtNome;
	}

	public TextField getTxtDesc() {
		return txtDesc;
	}

	public void setTxtDesc(TextField txtDesc) {
		this.txtDesc = txtDesc;
	}

	public ComboBox<Categoria> getCbAdd() {
		return cbAdd;
	}

	public void setCbAdd(ComboBox<Categoria> cbAdd) {
		this.cbAdd = cbAdd;
	}

	public Button getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(Button btnAdd) {
		this.btnAdd = btnAdd;
	}

	public TextField getTxtLink() {
		return txtLink;
	}

	public void setTxtLink(TextField txtLink) {
		this.txtLink = txtLink;
	}

	public void loadCategories() {
		ObservableList<Categoria> obsCat;
		obsCat = FXCollections.observableArrayList(new CategoriaDAO().getAll());
		cbAdd.setItems(obsCat);
	}

	public void addDataBase(ActionEvent e) { 
		String nome = txtNome.getText();
		String desc = txtDesc.getText();
		String link = txtLink.getText();
		Categoria cat = cbAdd.getValue();
		AppsLoja app = new AppsLoja(nome, desc, link, cat);

		new AppsLojaDAO().add(app);
		
		lojaController.limparGrid();
		Button btn = (Button) e.getSource();
		Scene scene = btn.getScene();
		Stage stage = (Stage) scene.getWindow();
		lojaController.limparGrid();
		stage.close();
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadCategories();
	}
	
	public void selected(LojaController lojaController) {
		this.lojaController = lojaController;
	}

}
