package th.sTV;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import Modelos.Categoria;
import Modelos.CategoriaDAO;
import database.Conn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ConfigController implements Initializable{

	@FXML
	void callAddCategories() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("addCategoria.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage(); 
		stage.setScene(scene);
		stage.show();
		
		addCategoria controller = (addCategoria) fxmlLoader.getController();
		controller.selected(this);
	}

	@FXML
	private ComboBox<Categoria> cbCategoria;

	@FXML
	void removeCat() {
		CategoriaDAO cdao = new CategoriaDAO();
		cdao.delete(cbCategoria.getValue());
		limparCB();
	}
	
	@FXML
	void callSmartTV() throws IOException {
		App.setRoot("smartTV");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadCategories();
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
	
    public void limparCB() {
    	cbCategoria.setItems(null);
    	loadCategories();
    }
    
	static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}
}
