package th.sTV;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.persistence.EntityManager;
import Modelos.Aplicativos;
import Modelos.AplicativosDAO;
import Modelos.AppsLoja;
import Modelos.AppsLojaDAO;
import Modelos.Categoria;
import database.Conn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AppsController implements Initializable {


	@FXML
	private GridPane gridApps;

	@FXML
	private ComboBox<Categoria> cbCategoria;

	@FXML
	private TextField textFieldBusca;

	@FXML
	private Button btnBuscar;

	@FXML
	private Button addNewApp;

	@FXML
	void callSmartTV() throws IOException {
		App.setRoot("smartTV");
	}
	
	@FXML
	void callAddApps() throws IOException {
		App.setRoot("loja");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadCategories();
		loadApps();
	}

	public ComboBox<Categoria> getCbCategoria() {
		return cbCategoria;
	}

	public void setCbCategoria(ComboBox<Categoria> cbCategoria) {
		this.cbCategoria = cbCategoria;
	}

	public TextField getTextFieldBusca() {
		return textFieldBusca;
	}

	public void setTextFieldBusca(TextField textFieldBusca) {
		this.textFieldBusca = textFieldBusca;
	}

	public Button getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(Button btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public Button getAddNewApp() {
		return addNewApp;
	}

	public void setAddNewApp(Button addNewApp) {
		this.addNewApp = addNewApp;
	}

	public void loadCategories() {
		EntityManager em = Conn.getEntityManager();
		List<Categoria> categoria = em.createQuery("select cat from Categoria as cat", Categoria.class).getResultList();
		List<Categoria> lCat = new ArrayList<>();
		ObservableList<Categoria> obsCat;

		for (Categoria cat : categoria) {
			lCat.add(cat);
		}

		obsCat = FXCollections.observableArrayList(lCat);
		cbCategoria.setItems(obsCat);
	}
	
	Aplicativos appQueVaiMudar;
	
	public void loadApps() {
		int linha = 0;
		int coluna = 0;
		AplicativosDAO dao = new AplicativosDAO();
		List<Aplicativos> appList = dao.getAll();

		for (Aplicativos app : appList) {
			String n = app.getNome();
			Button btn = new Button(n);
			btn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					AplicativosDAO dao = new AplicativosDAO();
					Aplicativos app = dao.get(btn.getText());
					appQueVaiMudar = app;
					

				}
			});
			
			GridPane.setRowIndex(btn, linha);
			GridPane.setColumnIndex(btn, coluna);
			gridApps.getChildren().add(btn);
			coluna++;
		}
	}

	public void buscarApps() throws IOException {
		if(textFieldBusca!=null) {	
			EntityManager em = Conn.getEntityManager();
			List<Aplicativos> appList = em.createQuery("select app from Aplicativos as app", Aplicativos.class)
					.getResultList();
			gridApps.getChildren().clear();
			for (int indice = 0; indice < appList.size(); indice++) {
				if (getTextFieldBusca().getText().equals(appList.get(indice).getNome())) {
					Button btn = new Button(appList.get(indice).getNome());
					GridPane.setRowIndex(btn, 0);
					GridPane.setColumnIndex(btn, 0);
					gridApps.getChildren().add(btn);
				}
			}
		}else if(cbCategoria.getValue()!=null) {
			List<Aplicativos> appList = cbCategoria.getValue().getApps();;
			gridApps.getChildren().clear();
			for (int indice = 0; indice < appList.size(); indice++) {
				if (getTextFieldBusca().getText().equals(appList.get(indice).getNome())) {
					Button btn = new Button(appList.get(indice).getNome());
					GridPane.setRowIndex(btn, 0);
					GridPane.setColumnIndex(btn, 0);
					gridApps.getChildren().add(btn);
				}
			}
		}
	}
	
    @FXML
    void callDeleteApp() {
    	AplicativosDAO appdao = new AplicativosDAO();
    	AppsLojaDAO apploja = new AppsLojaDAO();
    	AppsLoja appLojaNovo = new AppsLoja(appQueVaiMudar.getNome(), appQueVaiMudar.getDescricao(), appQueVaiMudar.getLink(), appQueVaiMudar.getCat());
    	apploja.add(appLojaNovo);
    	appdao.delete(appQueVaiMudar);
    	limparGrid();
    }
	
    public void limparGrid() {
		gridApps.getChildren().clear();
		loadApps();
    }

}
