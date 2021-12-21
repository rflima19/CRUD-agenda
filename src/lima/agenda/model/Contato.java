package lima.agenda.model;

public class Contato implements Comparable<Contato> {

	private String nome;
	private String numero;
	private String email;

	public Contato(String nome, String numero, String email) {
		super();
		this.nome = nome;
		this.numero = numero;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static boolean validarNome(String nome) {
		if ((nome.isBlank() == true) || (nome.length() < 3) == true
				|| (nome.matches("^.*[^A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+.*$") == true)) {
			return false;
		}
		return true;
	}

	public static boolean validarNumero(String numero) {
		if (numero.matches("^\\(\\d{2}\\)\\s?\\d{5}-?\\d{4}$") == false) {
			return false;
		}
		return true;
	}

	public static boolean validarEmail(String email) {
		if (email.matches("[a-zA-Z0-9]+@[a-z]+.+[a-z]") == false) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Contato o) {
		return this.nome.compareTo(o.getNome());
	}

	@Override
	public String toString() {
		return String.format("[nome=%s, numero=%s, email=%s]", this.nome, this.numero, this.email);
	}
}
