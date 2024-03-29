package Modelos;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.persistence.EntityManager;

import database.Conn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AplicativosDAO implements InterfaceDAO<Aplicativos>{
	private static ObservableList<Aplicativos> apps;
	private String ipServer = "localhost";
	private int portServer = 1024;
	
	@Override
	public Aplicativos get(String id) {
		Aplicativos app = null;
		
		//====================================
		try {
			Socket server = new Socket(ipServer, portServer);

			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("Aplicativos;get;" + id);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(server.getInputStream());
			String msg = in.readUTF();

			if (!msg.contains("404")) {
				String[] splitResult = msg.split(";");
				app = new Aplicativos(splitResult[0], splitResult[1], splitResult[2], );
			}

			in.close();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		
		// ======================================================
		if (apps != null)
			for (Aplicativos aplicativo : apps)
				if (aplicativo.getNome().contentEquals(id))
					return aplicativo;


		return aplicativo;

	}
	
	@Override
	public List<Aplicativos> getAll() {
		if (apps == null) {
			EntityManager entityMng = Conn.getEntityManager();
			apps = FXCollections.observableArrayList(
					entityMng.createQuery("select app from Aplicativos as app", Aplicativos.class).getResultList());
			entityMng.close();
		}
		return apps;
	}

	@Override
	public void add(Aplicativos obj) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		entityMng.persist(obj);
		entityMng.getTransaction().commit(); // sempre que iniciamos uma transação, precisamos dar o commit
		entityMng.close();

		// adiciono na lista 
		// foram carregador do banco
		if (apps != null)
			apps.add(obj);
		
	}

	@Override
	public void delete(Aplicativos obj) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		Aplicativos appDB = entityMng.find(Aplicativos.class, obj.getNome());
		entityMng.remove(appDB);
		entityMng.getTransaction().commit();
		entityMng.close();

		Aplicativos found = null;
		if (apps != null)
			for (Aplicativos app : apps)
				if (app.getNome().contentEquals(obj.getNome()))
					found = app;
		if(found != null)
			apps.remove(found);
	}
	

	@Override
	public void update(Aplicativos obj) {
//		EntityManager entityMng = Conn.getEntityManager();
//		entityMng.getTransaction().begin();
//		Aplicativos appDB = entityMng.find(User.class, obj.getName());
//		userDB.setAge(obj.getAge());
//		userDB.setRegisterDate(obj.getRegisterDate());
//		entityMng.getTransaction().commit();
//		entityMng.close();
//
//		if (users != null) {
//			for (User user : users) {
//				if (user.getName().contentEquals(obj.getName())) {
//					user.setAge(obj.getAge());
//					user.setRegisterDate(obj.getRegisterDate());
//				}
//			}
//		}
		
	}

}
