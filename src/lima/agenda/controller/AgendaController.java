package lima.agenda.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lima.agenda.exceptions.AgendaModelException;
import lima.agenda.exceptions.AgendaViewException;
import lima.agenda.model.Agenda;
import lima.agenda.model.Contato;
import lima.agenda.view.TerminalMenuView;
import lima.agenda.view.TerminalView;
import lima.agenda.view.View;

public class AgendaController {

	private View agendaViews;
	private Agenda agenda;

	public AgendaController() {
		super();
		this.agendaViews = new TerminalView();
	}

	public void iniciarApp() {
		try {
			this.agenda = Agenda.getAgenda();
			while (true) {
				int opcao = this.agendaViews.telaMenu();
				
				if (opcao == TerminalMenuView.SAIR) {
					System.exit(0);
				} else if (opcao == TerminalMenuView.INSERIR_CONTATO) {
					try {
						this.cadastro();
					} catch (AgendaModelException e) {
						this.agendaViews.telaNotificaUsuario(e.getMessage());
					}
				} else if (opcao == TerminalMenuView.LISTAR_CONTATOS) {
					this.listar();
				} else if (opcao == TerminalMenuView.LISTAR_LETRA) {
					this.listarPorLetra();
				} else if (opcao == TerminalMenuView.PROCURAR_CONTATO) {
					this.procurar();
				} else if (opcao == TerminalMenuView.ALTERAR_CONTATO) {
					try {
						this.alterar();
					} catch (AgendaModelException e) {
						this.agendaViews.telaNotificaUsuario(e.getMessage());
					}
				} else if (opcao == TerminalMenuView.EXCLUIR_CONTATO) {
					try {
						this.excluir();
					} catch (AgendaModelException e) {
						this.agendaViews.telaNotificaUsuario(e.getMessage());
					}
				} else {
					this.agendaViews.telaNotificaUsuario("Opção Inválida");
				}
			}
		} catch (AgendaViewException | AgendaModelException e) {
			Throwable cause = e.getCause();
			if (cause instanceof IOException) {
				e.printStackTrace();
				System.exit(1);
			} else {
				this.agendaViews.telaNotificaUsuario(e.getMessage());
			}
		}
	}

	private void excluir() throws AgendaViewException, AgendaModelException {
		String nome = this.agendaViews.telaExcluirContato();
		if (nome != null) {
			Contato contato = this.agenda.pesquisarContato(nome);
			if (contato == null) {
				this.agendaViews.telaNotificaUsuario("Nenhum contato com o nome " + nome + " encontrado");
			} else {
				this.agenda.excluirContato(nome);
				this.agendaViews.telaNotificaUsuario("Contato excluido com sucesso");
			}
		}
	}

	private void alterar() throws AgendaViewException, AgendaModelException {
		String nome = this.agendaViews.telaSolicitarNome();
		if (nome != null) {
			Contato contato = this.agenda.pesquisarContato(nome);
			if (contato == null) {
				this.agendaViews.telaNotificaUsuario("Nenhum contato com o nome " + nome + " encontrado");
			} else {
				List<Contato> lista = new ArrayList<>();
				lista.add(contato);
				this.agendaViews.telaListarContatos(lista);
				
				String[] dadosContato = null;
				while (true) {
					dadosContato = this.agendaViews.telaAlterarContato();
					if (dadosContato == null) {
						break;
					}
					if (Contato.validarNome(dadosContato[0]) == false) {
						this.agendaViews.telaNotificaUsuario("Nome '" + dadosContato[0] + "' inválido");
						continue;
					}
					if (Contato.validarNumero(dadosContato[1]) == false) {
						this.agendaViews.telaNotificaUsuario ("Telefone '" + dadosContato[1] + "' inválido");
						continue;
					}
					if (Contato.validarEmail(dadosContato[2]) == false) {
						this.agendaViews.telaNotificaUsuario("Email '" + dadosContato[2] + "' inválido");
						continue;
					}
					break;
				}
				if (dadosContato != null) {
					Contato c = new Contato(dadosContato[0], dadosContato[1], dadosContato[2]);
					this.agenda.alterarContato(nome, c);
					this.agendaViews.telaNotificaUsuario("Contato alterado com sucesso");
				}
			}
		}
	}

	private void procurar() throws AgendaViewException {
		String nome = this.agendaViews.telaSolicitarNome();
		if (nome != null) {
			List<Contato> lista = this.agenda.pesquisarContatos(nome);
			if (lista.size() == 0) {
				this.agendaViews.telaNotificaUsuario("Nenhum contato encontrado");
			} else {
				this.agendaViews.telaListarContatos(lista);
			}
		}
	}

	private void listarPorLetra() throws AgendaViewException {
		Character caracter = null;
		while (true) {
			caracter = this.agendaViews.telaSolicitarLetra();
			if (caracter == null) {
				break;
			}
			if (Character.isLetter(caracter) == false) {
				this.agendaViews.telaNotificaUsuario("Digite uma letra");
				continue;
			}
			break;
		}
		if (caracter != null) {
			List<Contato> lista = this.agenda.listarContatosPorLetra(caracter);
			if (lista.size() > 0) {
				this.agendaViews.telaListarContatos(lista);
			} else {
				this.agendaViews.telaNotificaUsuario("Não existem contatos cadastrados para letra " + Character.toUpperCase(caracter));
			}
		}
	}

	private void listar() {
		List<Contato> lista = this.agenda.listarContatos();
		if (lista.size() > 0) {
			this.agendaViews.telaListarContatos(lista);
		} else {
			this.agendaViews.telaNotificaUsuario("Não existem contatos cadastrados");
		}
	}
	
	private void cadastro() throws AgendaModelException, AgendaViewException {
		String[] dadosContato = null;
		while (true) {
			dadosContato = this.agendaViews.telaSalvarContato();
			if (dadosContato == null) {
				break;
			}
			if (Contato.validarNome(dadosContato[0]) == false) {
				this.agendaViews.telaNotificaUsuario("Nome '" + dadosContato[0] + "' inválido");
				continue;
			}
			if (Contato.validarNumero(dadosContato[1]) == false) {
				this.agendaViews.telaNotificaUsuario ("Telefone '" + dadosContato[1] + "' inválido");
				continue;
			}
			if (Contato.validarEmail(dadosContato[2]) == false) {
				this.agendaViews.telaNotificaUsuario("Email '" + dadosContato[2] + "' inválido");
				continue;
			}
			break;
		}
		if (dadosContato != null) {
			Contato c = new Contato(dadosContato[0], dadosContato[1], dadosContato[2]);
			this.agenda.salvarContato(c);
			this.agendaViews.telaNotificaUsuario("Cadastro realizado com sucesso");
		}
	}
}
