package Modelos;

import java.util.List;
import javax.persistence.EntityManager;
import database.Conn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CategoriaDAO implements InterfaceDAO<Categoria>{
	private static ObservableList<Categoria> categorias;
	
	
	@Override
	public Categoria get(String id) {
		if (categorias != null)
			for (Categoria cat : categorias)
				if (cat.getNome().contentEquals(id))
					return cat;

		EntityManager entityMng = Conn.getEntityManager();
		Categoria cat = entityMng.find(Categoria.class, id);
		entityMng.close();
		return cat;
	}

	@Override
	public List<Categoria> getAll() {
		if (categorias == null) {
			EntityManager entityMng = Conn.getEntityManager();
			categorias = FXCollections.observableArrayList(
					entityMng.createQuery("select cat from Categoria as cat", Categoria.class).getResultList());
			entityMng.close();
		}
		return categorias;
	}

	@Override
	public void add(Categoria obj) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		entityMng.persist(obj);
		entityMng.getTransaction().commit(); // sempre que iniciamos uma transação, precisamos dar o commit
		entityMng.close();

		// adiciono na lista de usuários que está na memória, se todos os usuários já
		// foram carregador do banco
		if (categorias != null)
			categorias.add(obj);
	}

	@Override
	public void delete(Categoria obj) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		Categoria catDB = entityMng.find(Categoria.class, obj.getNome());
		entityMng.remove(catDB);
		entityMng.getTransaction().commit();
		entityMng.close();

		Categoria found = null;
		if (categorias != null)
			for (Categoria cat : categorias)
				if (cat.getNome().contentEquals(obj.getNome()))
					found = cat;
		if(found != null)
			categorias.remove(found);
	}

	@Override
	public void update(Categoria obj) {
		
	}



}
