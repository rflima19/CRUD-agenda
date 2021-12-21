package lima.agenda.view;

import java.util.List;

import lima.agenda.exceptions.AgendaViewException;
import lima.agenda.model.Contato;

public interface View {

	public abstract int telaMenu() throws AgendaViewException;
	
	public abstract String[] telaSalvarContato() throws AgendaViewException;
	
	public abstract void telaListarContatos(List<Contato> contatos);
	
	public abstract void telaNotificaUsuario(String mensagem);
	
	public abstract void telaNotificaUsuario(Exception exc);
	
	public abstract Character telaSolicitarLetra() throws AgendaViewException;
	
	public abstract String telaSolicitarNome() throws AgendaViewException;
	
	public abstract String telaExcluirContato() throws AgendaViewException;
	
	public abstract String[] telaAlterarContato() throws AgendaViewException;
}
