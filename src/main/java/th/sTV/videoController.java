package th.sTV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import Modelos.Video;
import Modelos.VideoDAO;
import database.Conn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


public class videoController {
	

	@FXML
	private ListView<Video> listVideo;

	static Video videoOperador;

	public Video getVideoOperador() {
		return videoOperador;
	}

	public void setVideoOperador(Video videoOperador) {
		videoController.videoOperador = videoOperador;
	}

	@FXML
	void RemoveVideo() {
		videoOperador = listVideo.getSelectionModel().getSelectedItem();
		VideoDAO vdao = new VideoDAO();
		vdao.delete(videoOperador);
		limparLista();
	}
	
	public void loadList() {
		EntityManager em = Conn.getEntityManager();
		List<Video> videos = em.createQuery("select video from Video as video", Video.class).getResultList();
		List<Video> lVideo = new ArrayList<>();
		ObservableList<Video> obsVideo;

		
		for (Video v : videos) {
			lVideo.add(v);
		}

		obsVideo = FXCollections.observableArrayList(lVideo);
		listVideo.setItems(obsVideo);
	}

	@FXML
	void addVideo() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("addVideo.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void editVideo() throws IOException {
		videoOperador = listVideo.getSelectionModel().getSelectedItem();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("updateVideo.fxml"));
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
	
    public void limparLista() {
    	listVideo.setItems(null);
    	loadList();
    }
	

}
