package Modelos;

import java.util.List;

import javax.persistence.EntityManager;

import database.Conn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MusicaDAO implements InterfaceDAO<Musica>{
	private static ObservableList<Musica> music;
	
	
	@Override
	public Musica get(String id) {
		if (music != null)
			for (Musica m : music)
				if (m.getNome().contentEquals(id))
					return m;

		EntityManager entityMng = Conn.getEntityManager();
		Musica m = entityMng.find(Musica.class, id);
		entityMng.close();
		return m;
	}

	@Override
	public List<Musica> getAll() {
		if (music== null) {
			EntityManager entityMng = Conn.getEntityManager();
			music = FXCollections.observableArrayList(
					entityMng.createQuery("select musica from Musica as musica", Musica.class).getResultList());
			entityMng.close();
		}
		return music;
	}

	@Override
	public void add(Musica obj) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		entityMng.persist(obj);
		entityMng.getTransaction().commit();
		entityMng.close();

		if (music != null)
			music.add(obj);
	}

	@Override
	public void delete(Musica obj) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		Musica musicaDB = entityMng.find(Musica.class, obj.getId());
		entityMng.remove(musicaDB);
		entityMng.getTransaction().commit();
		entityMng.close();

		Musica found = null;
		if (music != null)
			for (Musica m : music)
				if (m.getId() == obj.getId())
					found = m;
		if(found != null)
			music.remove(found);
		
	}

	@Override
	public void update(Musica obj) {
		// TODO Auto-generated method stub
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		Musica musica = entityMng.find(Musica.class, obj.getId());
		musica.setNome(obj.getNome());
		musica.setAutor(obj.getAutor());
		musica.setLink(obj.getLink());
		entityMng.getTransaction().commit();
		entityMng.close();

		if (music != null) {
			for (Musica m : music) {
				if (m.getId() == obj.getId()) {
					m.setNome(obj.getNome());
					m.setAutor(obj.getAutor());
					m.setLink(obj.getLink());
				}
			}
		}
		
	}


}
