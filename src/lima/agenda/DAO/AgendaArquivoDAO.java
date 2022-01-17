package lima.agenda.DAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import lima.agenda.exceptions.AgendaModelException;
import lima.agenda.model.Contato;

public class AgendaArquivoDAO implements AgendaDAO {
	
	private static final String SEPARATOR = FileSystems.getDefault().getSeparator();
	public static final Path DIRECTORY = Paths.get("files" + AgendaArquivoDAO.SEPARATOR);
	public static final Path FILE = AgendaArquivoDAO.DIRECTORY.resolve("agenda.txt");

	@Override
	public boolean salvarContatos(List<Contato> contatos) throws AgendaModelException {
		try (Writer writer = Files.newBufferedWriter(AgendaArquivoDAO.FILE,
				StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
				PrintWriter pw = new PrintWriter(writer)) {
			for (Contato contato : contatos) {
				StringBuilder strb = new StringBuilder();
				strb.append(contato.getNome());
				strb.append(";");
				strb.append(contato.getNumero());
				strb.append(";");
				strb.append(contato.getEmail());
				pw.println(strb.toString());
			}
		} catch (IOException e) {
			throw new AgendaModelException(
				"Falha ao abrir o arquivo " + AgendaArquivoDAO.FILE.toAbsolutePath() + 
					" para escrita", e);
		}
		return true;
	}

	@Override
	public List<Contato> recuperarContatos() throws AgendaModelException {
		if (Files.exists(AgendaArquivoDAO.FILE) == false) {
			throw new AgendaModelException("Arquivo " + AgendaArquivoDAO.FILE.toAbsolutePath() + 
						" não existe");
		}
		List<Contato> lista = new ArrayList<>();
		try (Reader inReader = Files.newBufferedReader(AgendaArquivoDAO.FILE, Charset.defaultCharset());
				BufferedReader buffer = new BufferedReader(inReader)) {
			String line = null;
			while ((line = buffer.readLine()) != null) {
				String[] tokens = line.split(";");
				lista.add(new Contato(tokens[0], tokens[1], tokens[2]));
			}
		} catch (FileNotFoundException e) {
			throw new AgendaModelException("Arquivo " + AgendaArquivoDAO.FILE.toAbsolutePath() + 
					" não existe");
		} catch (IOException e) {
			throw new AgendaModelException("Erro de I/O ao recuperar contatos", e);
		}
		return lista;
	}
}
