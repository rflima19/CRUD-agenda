package lima.agenda.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

import lima.agenda.DAO.AgendaArquivoDAO;
import lima.agenda.DAO.AgendaDAO;
import lima.agenda.exceptions.AgendaModelException;

public class Agenda {
	
	private static AgendaDAO agendaDAO = new AgendaArquivoDAO();
	private static Agenda instance = null;
	
	private Map<Character, List<Contato>> contatos;
	
	private Agenda() throws AgendaModelException {
		super();
		this.contatos = new TreeMap<>();
		this.carregarMap();
		Set<Map.Entry<Character, List<Contato>>> set =  this.contatos.entrySet();
		for (Map.Entry<Character, List<Contato>> entry : set) {
			System.out.print(entry.getKey() + "-> ");
			List<Contato> l = entry.getValue();
			for (Contato c : l) {
				System.out.println(c);
			}
		}
	}
	
	public static Agenda getAgenda() throws AgendaModelException {
		if (Agenda.instance == null) {
			Agenda.instance = new Agenda();
		}
		return Agenda.instance;
	}
	
	public void salvarContato(Contato contato) throws AgendaModelException {
		Contato c = this.pesquisarContato(contato.getNome());
		if (c != null) {
			throw new AgendaModelException("Contato " + contato.getNome() + " já foi cadastrado");
		}
		this.inserirContatoMap(contato);
		List<Contato> lista = this.getListaContatos();
		this.salvarContatos(lista);
	}
	
	public Contato pesquisarContato(String nome) {
		Character letra = Character.toUpperCase(nome.charAt(0));
		List<Contato> lista = this.contatos.get(letra);
		if (lista == null) {
			return null;
		}
		Contato contato = null;
		for (Contato c : lista) {
			if (c.getNome().equals(nome) == true) {
				contato = c;
				break;
			}
		}
		return contato;
	}
	
	public List<Contato> pesquisarContatos(String texto) {
		List<Contato> lista = this.getListaContatos();
		List<Contato> listaContratosEncontrados = new ArrayList<>();
		for (Contato c : lista) {
			if (Pattern.matches(".*" + texto + ".*", c.getNome())) {
				listaContratosEncontrados.add(c);
			}
		}
		return listaContratosEncontrados;
	}
	
	public boolean excluirContato(String nome) throws AgendaModelException {
		Character letra = Character.toUpperCase(nome.charAt(0));
		List<Contato> lista = this.contatos.get(letra);
		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).getNome().equals(nome)) {
					lista.remove(i);
					break;
				}
			}
		}
		List<Contato> l = this.getListaContatos();
		this.salvarContatos(l);
		return true;
	}
	
	public List<Contato> listarContatos() {
		return this.getListaContatos();
	}
	
	public List<Contato> listarContatosPorLetra(char letra) {
		letra = Character.toUpperCase(letra);
		List<Contato> lista = null;
		lista = this.contatos.get(letra);
		if (lista == null) {
			lista = new ArrayList<>();
		}
		return lista;
	}
	
	public boolean alterarContato(String nome, Contato contatoAtualizado) throws AgendaModelException {
		Contato contato = this.pesquisarContato(nome);
		contato.setNome(contatoAtualizado.getNome());
		contato.setNumero(contatoAtualizado.getNumero());
		contato.setEmail(contatoAtualizado.getEmail());
		List<Contato> lista = this.getListaContatos();
		this.salvarContatos(lista);
		return true;
	}
	
	private boolean salvarContatos(List<Contato> contatos) throws AgendaModelException {
		return Agenda.agendaDAO.salvarContatos(contatos);
	}
	
	private List<Contato> recuperarContatos() {
		List<Contato> lista = new ArrayList<>();
		try {
			lista = Agenda.agendaDAO.recuperarContatos();
		} catch (AgendaModelException e) {
			return lista;
		}
		return lista;
	}
	
	private List<Contato> getListaContatos() {
		List<Contato> lista = new ArrayList<>();
		Iterator<List<Contato>> iterator =  this.contatos.values().iterator();
		while (iterator.hasNext() == true) {
			lista.addAll(iterator.next());
		}
		return lista;
	}
	
	private void inserirContatoMap(Contato contato) {
		Character letra = Character.toUpperCase(contato.getNome().charAt(0));
		List<Contato> lista = this.contatos.get(letra);
		if (lista == null) {
			lista = new ArrayList<>();
			lista.add(contato);
			this.contatos.put(letra, lista);
		} else {
			lista.add(contato);
		}
		Collections.sort(lista);
	}
	
	private void carregarMap() throws AgendaModelException {
		List<Contato> lista = this.recuperarContatos();
		if (lista.size() == 0) {
			return;
		}
		for (Contato contato : lista) {
			this.inserirContatoMap(contato);
		}
	}
}
