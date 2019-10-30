package Modelos;

import java.util.List;

import javax.persistence.EntityManager;

import database.Conn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AppsLojaDAO implements InterfaceDAO<AppsLoja>{
	private static ObservableList<AppsLoja> apps;
	
	
	@Override
	public AppsLoja get(String id) {
		if (apps != null)
			for (AppsLoja aplicativo : apps)
				if (aplicativo.getNome().contentEquals(id))
					return aplicativo;

		EntityManager entityMng = Conn.getEntityManager();
		AppsLoja aplicativo = entityMng.find(AppsLoja.class, id);
		entityMng.close();
		return aplicativo;
	}

	@Override
	public List<AppsLoja> getAll() {
		if (apps == null) {
			EntityManager entityMng = Conn.getEntityManager();
			apps = FXCollections.observableArrayList(
					entityMng.createQuery("select apploja from AppsLoja as apploja", AppsLoja.class).getResultList());
			entityMng.close();
		}
		return apps;
	}

	@Override
	public void add(AppsLoja obj) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		entityMng.persist(obj);
		entityMng.getTransaction().commit(); // sempre que iniciamos uma transação, precisamos dar o commit
		entityMng.close();

		// adiciono na lista de usuários que está na memória, se todos os usuários já
		// foram carregador do banco
		if (apps != null)
			apps.add(obj);
		
	}

	@Override
	public void delete(AppsLoja obj) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		AppsLoja applojaDB = entityMng.find(AppsLoja.class, obj.getNome());
		entityMng.remove(applojaDB);
		entityMng.getTransaction().commit();
		entityMng.close();

		AppsLoja found = null;
		if (apps != null)
			for (AppsLoja app : apps)
				if (app.getNome().contentEquals(obj.getNome()))
					found = app;
		if(found != null)
			apps.remove(found);
		
	}

	@Override
	public void update(AppsLoja obj) {
		// TODO Auto-generated method stub
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		AppsLoja applojaDB = entityMng.find(AppsLoja.class, obj.getNome());
		applojaDB.setDescricao(obj.getDescricao());
		applojaDB.setCat(obj.getCat());
		applojaDB.setLink(obj.getLink());
		entityMng.getTransaction().commit();
		entityMng.close();

		if (apps != null) {
			for (AppsLoja apl : apps) {
				if (apl.getNome().contentEquals(obj.getNome())) {
					apl.setDescricao(obj.getDescricao());
					apl.setCat(obj.getCat());
					apl.setLink(obj.getLink());
				}
			}
		}
	}

	

}
