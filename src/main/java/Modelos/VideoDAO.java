package Modelos;

import java.util.List;

import javax.persistence.EntityManager;

import database.Conn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VideoDAO implements InterfaceDAO<Video>{
private static ObservableList<Video> videos;
	
	
	@Override
	public Video get(String obj) {
		if (videos != null)
			for (Video m : videos)
				if (m.getNome().contentEquals(obj))
					return m;

		EntityManager entityMng = Conn.getEntityManager();
		Video m = entityMng.find(Video.class, obj);
		entityMng.close();
		return m;
	}

	@Override
	public List<Video> getAll() {
		if (videos== null) {
			EntityManager entityMng = Conn.getEntityManager();
			videos = FXCollections.observableArrayList(
					entityMng.createQuery("select v from Video as v", Video.class).getResultList());
			entityMng.close();
		}
		return videos;
	}

	@Override
	public void add(Video obj) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		entityMng.persist(obj);
		entityMng.getTransaction().commit(); 
		entityMng.close();

		if (videos != null)
			videos.add(obj);
	}

	@Override
	public void delete(Video obj) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		Video videoDB = entityMng.find(Video.class, obj.getId());
		entityMng.remove(videoDB);
		entityMng.getTransaction().commit();
		entityMng.close();

		Video found = null;
		if (videos != null)
			for (Video v : videos)
				if (v.getId() == obj.getId())
					found = v;
		if(found != null)
			videos.remove(found);
		
	}

	@Override
	public void update(Video obj) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		Video video = entityMng.find(Video.class, obj.getId());
		video.setNome(obj.getNome());
		entityMng.getTransaction().commit();
		entityMng.close();

		if (videos != null) {
			for (Video v : videos) {
				if (v.getId() == obj.getId()) {
					v.setNome(obj.getNome());

				}
			}
		}
		
	}
}
