package lima.agenda.DAO;

import java.util.List;

import lima.agenda.exceptions.AgendaModelException;
import lima.agenda.model.Contato;

public interface AgendaDAO {

	public abstract boolean salvarContatos(List<Contato> contatos)  throws AgendaModelException;
	
	public abstract List<Contato> recuperarContatos()  throws AgendaModelException;
}
