package lima.agenda.DAO;

public class AgendaDAOFactory {

	public static AgendaDAO getInstance() {
		return new AgendaArquivoDAO();
	}
}
