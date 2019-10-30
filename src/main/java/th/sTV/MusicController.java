package th.sTV;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.persistence.EntityManager;
import Modelos.Musica;
import Modelos.MusicaDAO;
import database.Conn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class MusicController implements Initializable{
	
	@FXML
    private ListView<Musica> listMusic;
	
	static Musica musicaOperador;
	

    public static Musica getMusicaOperador() {
		return musicaOperador;
	}

	public void setMusicaOperador(Musica musicaOperador) {
		MusicController.musicaOperador = musicaOperador;
	}

	@FXML
    void addMusic() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("addMusic.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
    }

	@FXML
	void callSmartTV() throws IOException {
		App.setRoot("smartTV");
	}

    @FXML
    void editar() throws IOException {
    	musicaOperador = listMusic.getSelectionModel().getSelectedItem();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("updateMusic.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void excluir() {
    	musicaOperador = listMusic.getSelectionModel().getSelectedItem();
    	MusicaDAO mdao = new MusicaDAO();
    	mdao.delete(musicaOperador);
    	limparLista();
    }
    
	public void loadList() {
		EntityManager em = Conn.getEntityManager();
		List<Musica> musicas = em.createQuery("select music from Musica as music", Musica.class).getResultList();
		List<Musica> lMusic = new ArrayList<>();
		ObservableList<Musica> obsMusic;

		for (Musica m : musicas) {
			lMusic.add(m);
		}
		MusicaDAO mdao = new MusicaDAO();
		

		obsMusic = FXCollections.observableArrayList(mdao.getAll());
		listMusic.setItems(obsMusic);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadList();
	}
	
    public void limparLista() {
    	MusicaDAO mdao = new MusicaDAO();
		listMusic.setItems(null);;
		listMusic.setItems( (ObservableList<Musica>) mdao.getAll());
		
    }
	
}
