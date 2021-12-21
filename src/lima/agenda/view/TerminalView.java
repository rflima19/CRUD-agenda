package lima.agenda.view;

import java.io.IOException;
import java.util.List;

import lima.agenda.exceptions.AgendaViewException;
import lima.agenda.model.Contato;

public class TerminalView implements View {

	private TerminalMenuView tMenu;
	private TerminalSalvarContatoView tSalvarContato;
	private TerminalNotificacaoView tNotificacao;
	private TerminalListarContatoView tListaContato;
	private TerminalSolicitaLetraView tSolicitarLetra;
	private TerminalSolicitaNomeView tSolicitarNome;
	private TerminalAlterarContatoView tAlterarContato;
	private TerminalExcluirContatoView tExcluirContato;

	public TerminalView() {
		super();
		this.tMenu = new TerminalMenuView();
		this.tNotificacao = new TerminalNotificacaoView();
		this.tSalvarContato = new TerminalSalvarContatoView();
		this.tListaContato = new TerminalListarContatoView();
		this.tSolicitarLetra = new TerminalSolicitaLetraView();
		this.tSolicitarNome = new TerminalSolicitaNomeView();
		this.tAlterarContato = new TerminalAlterarContatoView();
		this.tExcluirContato = new TerminalExcluirContatoView();
	}

	@Override
	public int telaMenu() throws AgendaViewException {
		try {
			return this.tMenu.imprimirMenu();
		} catch (IOException e) {
			throw new AgendaViewException("Erro de I/O ao imprimir menu de opções", e);
		}
	}

	@Override
	public void telaNotificaUsuario(String mensagem) {
		this.tNotificacao.imprimirNotificacao(mensagem);
	}

	@Override
	public void telaNotificaUsuario(Exception exc) {
		this.tNotificacao.imprimirNotificacaoErro(exc);
	}

	@Override
	public String[] telaSalvarContato() throws AgendaViewException {
		try {
			return this.tSalvarContato.imprimirSalvarContato();
		} catch (IOException e) {
			throw new AgendaViewException("Erro de I/O ao salvar contato", e);
		}
	}

	@Override
	public void telaListarContatos(List<Contato> cotatos) {
		this.tListaContato.imprimirListaContato(cotatos);
	}

	@Override
	public Character telaSolicitarLetra() throws AgendaViewException {
		try {
			return this.tSolicitarLetra.imprimirSolicitacaoLetra();
		} catch (IOException e) {
			throw new AgendaViewException("Erro de I/O ao lista contato por letra", e);
		}
	}
	
	@Override
	public String telaSolicitarNome() throws AgendaViewException {
		try {
			return this.tSolicitarNome.imprimirSolicitacaoNome();
		} catch (IOException e) {
			throw new AgendaViewException("Erro de I/O ao procurar contato", e);
		}
	}
	
	@Override
	public String[] telaAlterarContato() throws AgendaViewException {
		try {
			return this.tAlterarContato.imprimirAlterarContato();
		} catch (IOException e) {
			throw new AgendaViewException("Erro de I/O ao alterar contato", e);
		}
	}
	
	@Override
	public String telaExcluirContato() throws AgendaViewException {
		try {
			return this.tExcluirContato.imprimirExcluirContato();
		} catch (IOException e) {
			throw new AgendaViewException("Erro de I/O ao excluir contato", e);
		}
	}
}
