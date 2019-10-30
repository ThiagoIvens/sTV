package Modelos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categoria {

	@Id
	private String nome;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Aplicativos> apps;

	public Categoria() {
		super();
	}
	
	public Categoria(String nome) {
		super();
		this.nome = nome;
	}

	public Categoria(String nome, List<Aplicativos> apps) {
		super();
		this.nome = nome;
		this.apps = apps;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void addFile(Aplicativos appPassado) {
		if(this.apps == null)
			this.apps = new ArrayList<Aplicativos>();
		this.apps.add(appPassado);
	}
	
	public List<Aplicativos> getApps() {
		return apps;
	}

	public void setApps(List<Aplicativos> apps) {
		this.apps = apps;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apps == null) ? 0 : apps.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Categoria other = (Categoria) obj;
		if (apps == null) {
			if (other.apps != null)
				return false;
		} else if (!apps.equals(other.apps))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  nome;
	}

}
