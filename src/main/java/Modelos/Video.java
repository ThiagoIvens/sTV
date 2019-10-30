package Modelos;

public class Video {

	private int id;
	private String nome;
	private String formato;
	private String nomeformato;

	public Video() {
		super();
	}

	public Video(String nome, String formato, String nomeformato) {
		super();
		this.nome = nome;
		this.formato = formato;
		this.nomeformato = nomeformato;
	}

	public Video(int id, String nome, String formato, String nomeformato) {
		super();
		this.id = id;
		this.nome = nome;
		this.formato = formato;
		this.nomeformato = nomeformato;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getNomeformato() {
		return nomeformato;
	}

	public void setNomeformato(String nomeformato) {
		this.nomeformato = nomeformato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((formato == null) ? 0 : formato.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nomeformato == null) ? 0 : nomeformato.hashCode());
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
		Video other = (Video) obj;
		if (formato == null) {
			if (other.formato != null)
				return false;
		} else if (!formato.equals(other.formato))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nomeformato == null) {
			if (other.nomeformato != null)
				return false;
		} else if (!nomeformato.equals(other.nomeformato))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + " - " + nome + " - " + formato;
	}

}
