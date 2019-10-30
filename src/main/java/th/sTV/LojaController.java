package th.sTV;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javax.persistence.EntityManager;
import Modelos.Aplicativos;
import Modelos.AplicativosDAO;
import Modelos.AppsLoja;
import Modelos.AppsLojaDAO;
import database.Conn;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LojaController implements Initializable {
	@FXML
	private TextField txtBuscarLoja;

	@FXML
	private GridPane gridLoja;

	@FXML
	private TextArea txtArea;

	@FXML
	private TextField txtCategoria;

	@FXML
	private Slider feedback;

	static AppsLoja appQueVaiMudar;


	public static AppsLoja getAppQueVaiMudar() {
		return appQueVaiMudar;
	}

	public static void setAppQueVaiMudar(AppsLoja appQueVaiMudar) {
		LojaController.appQueVaiMudar = appQueVaiMudar;
	}

	@FXML
	void BuscarApps() {
		if (txtBuscarLoja != null) {
			EntityManager em = Conn.getEntityManager();
			List<AppsLoja> appList = em.createQuery("select apploja from AppsLoja as apploja", AppsLoja.class)
					.getResultList();
			gridLoja.getChildren().clear();
			for (int indice = 0; indice < appList.size(); indice++) {
				if (getTxtBuscarLoja().getText().equals(appList.get(indice).getNome())) {
					Button btn = new Button(appList.get(indice).getNome());
					btn.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							// obtém o botão (source) e seu texto, após isso remova da lista passando o
							// valor como argumento.
							AppsLojaDAO get = new AppsLojaDAO();
							AppsLoja app = get.get(btn.getText());
							appQueVaiMudar = app;
							// procura na lista de aplicativos e entao mostra as informações que a tela pede

							txtArea.setText(app.getDescricao());
							txtCategoria.setText(app.getCat().getNome());
						}
					});
					GridPane.setRowIndex(btn, 0);
					GridPane.setColumnIndex(btn, 0);
					gridLoja.getChildren().add(btn);
				}
			}
		}
	}

	public TextField getTxtBuscarLoja() {
		return txtBuscarLoja;
	}

	public void setTxtBuscarLoja(TextField txtBuscarLoja) {
		this.txtBuscarLoja = txtBuscarLoja;
	}

	public GridPane getGridLoja() {
		return gridLoja;
	}

	public void setGridLoja(GridPane gridLoja) {
		this.gridLoja = gridLoja;
	}

	public TextArea getTxtArea() {
		return txtArea;
	}

	public void setTxtArea(TextArea txtArea) {
		this.txtArea = txtArea;
	}

	public TextField getTxtCategoria() {
		return txtCategoria;
	}

	public void setTxtCategoria(TextField txtCategoria) {
		this.txtCategoria = txtCategoria;
	}

	public Slider getFeedback() {
		return feedback;
	}

	public void setFeedback(Slider feedback) {
		this.feedback = feedback;
	}

	@FXML
	void callSmartTV() throws IOException {
		App.setRoot("smartTV");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((feedback == null) ? 0 : feedback.hashCode());
		result = prime * result + ((gridLoja == null) ? 0 : gridLoja.hashCode());
		result = prime * result + ((txtArea == null) ? 0 : txtArea.hashCode());
		result = prime * result + ((txtBuscarLoja == null) ? 0 : txtBuscarLoja.hashCode());
		result = prime * result + ((txtCategoria == null) ? 0 : txtCategoria.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LojaController other = (LojaController) obj;
		if (feedback == null) {
			if (other.feedback != null)
				return false;
		} else if (!feedback.equals(other.feedback))
			return false;
		if (gridLoja == null) {
			if (other.gridLoja != null)
				return false;
		} else if (!gridLoja.equals(other.gridLoja))
			return false;
		if (txtArea == null) {
			if (other.txtArea != null)
				return false;
		} else if (!txtArea.equals(other.txtArea))
			return false;
		if (txtBuscarLoja == null) {
			if (other.txtBuscarLoja != null)
				return false;
		} else if (!txtBuscarLoja.equals(other.txtBuscarLoja))
			return false;
		if (txtCategoria == null) {
			if (other.txtCategoria != null)
				return false;
		} else if (!txtCategoria.equals(other.txtCategoria))
			return false;
		return true;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadApps();

	}

	public void loadApps(){
		int linha = 0;
		int coluna = 0;
		AppsLojaDAO dao = new AppsLojaDAO();
		List<AppsLoja> appList = dao.getAll();

		for (AppsLoja app : appList) {
			String n = app.getNome();
			Button btn = new Button(n);
//			final ToggleGroup group = new ToggleGroup();
//			ToggleButton tb1 = new ToggleButton(n);

			btn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					// obtém o botão (source) e seu texto, após isso remova da lista passando o
					// valor como argumento.
					AppsLojaDAO get = new AppsLojaDAO();
					AppsLoja app = get.get(btn.getText());
					appQueVaiMudar = app;
					// procura na lista de aplicativos e entao mostra as informações que a tela pede

					txtArea.setText(app.getDescricao());
					txtCategoria.setText(app.getCat().getNome());
				}
			});

//			tb1.setToggleGroup(group);

			GridPane.setRowIndex(btn, linha);
			GridPane.setColumnIndex(btn, coluna);
			gridLoja.getChildren().add(btn);
			coluna++;
		}
	}

	@FXML
	void callIntalar() throws IOException {// muda o Aplicativo de loja para o BD de APPS
		if (appQueVaiMudar != null) {
			AplicativosDAO appdao = new AplicativosDAO();
			Aplicativos novo = new Aplicativos(appQueVaiMudar.getNome(), appQueVaiMudar.getDescricao(),
					appQueVaiMudar.getLink(), appQueVaiMudar.getCat());
			appdao.add(novo);
			AppsLojaDAO appremove = new AppsLojaDAO();
			appremove.delete(appQueVaiMudar);

			limparGrid();
		}
	}
	
	@FXML
	void callAddApps() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("addAppsLoja.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage(); 
		stage.setScene(scene);
		stage.show();
		
		addAppsLojaController controller = (addAppsLojaController) fxmlLoader.getController();
		controller.selected(this);
	}

	@FXML
	void callUpdateApps() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("updateLoja.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage(); 
		stage.setScene(scene);
		stage.show();
		
		updateAppsLojaController controller = (updateAppsLojaController) fxmlLoader.getController();
		controller.selected(this);
	}
	
    @FXML
    void callDeleteApp() {
    	AppsLojaDAO ald = new AppsLojaDAO();
    	ald.delete(appQueVaiMudar);
    	limparGrid();
    }
    
    
    public void limparGrid() {
		gridLoja.getChildren().clear();
		loadApps();
    }
    
    @FXML
	private void exit() {
		Platform.exit();
	}
    
	static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}
}
