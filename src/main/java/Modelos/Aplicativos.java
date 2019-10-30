package Modelos;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Aplicativos {

	@Id
	private String nome;
	
	private String descricao;
	
	private String link;
	
	@ManyToOne
	@JoinColumn(name = "nome_cat" , foreignKey = @ForeignKey(name = "Categoria_nome"))
//	@Cascade (CascadeType.SAVE_UPDATE)
	private Categoria cat;

	public Aplicativos() {
		super();
	}

	public Aplicativos(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	public Aplicativos(String nome, String descricao, String link) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.link = link;
	}

	public Aplicativos(String nome, String descricao, String link, Categoria cat) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.link = link;
		this.cat = cat;
	}

	public Categoria getCat() {
		return cat;
	}

	public void setCat(Categoria cat) {
		this.cat = cat;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cat == null) ? 0 : cat.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
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
		Aplicativos other = (Aplicativos) obj;
		if (cat == null) {
			if (other.cat != null)
				return false;
		} else if (!cat.equals(other.cat))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

 
	

}
