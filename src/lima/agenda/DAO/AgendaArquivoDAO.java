package lima.agenda.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import lima.agenda.exceptions.AgendaModelException;
import lima.agenda.model.Contato;

public class AgendaArquivoDAO implements AgendaDAO {
	
	public static final File DIRECTORY = new File("files" + File.separator);
	public static final File FILE = new File(AgendaArquivoDAO.DIRECTORY, "agenda.txt");

	@Override
	public boolean salvarContatos(List<Contato> contatos) throws AgendaModelException {
		try {
			this.criarDiretorio(AgendaArquivoDAO.DIRECTORY);
			this.criarArquivo(AgendaArquivoDAO.FILE);
		} catch (IOException e) {
			throw new AgendaModelException("Não foi possivel criar o arquivo", e);
		}
		try (Writer writer = new FileWriter(AgendaArquivoDAO.FILE);
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
				"Falha ao abrir o arquivo " + AgendaArquivoDAO.FILE.getAbsolutePath() + 
					" para escrita", e);
		}
		return true;
	}

	@Override
	public List<Contato> recuperarContatos() throws AgendaModelException {
		if (AgendaArquivoDAO.FILE.exists() == false) {
			throw new AgendaModelException("Arquivo " + AgendaArquivoDAO.FILE.getAbsolutePath() + 
						" não existe");
		}
		List<Contato> lista = new ArrayList<>();
		try (Reader inReader = new FileReader(AgendaArquivoDAO.FILE);
				BufferedReader buffer = new BufferedReader(inReader)) {
			String line = null;
			while ((line = buffer.readLine()) != null) {
				String[] tokens = line.split(";");
				lista.add(new Contato(tokens[0], tokens[1], tokens[2]));
			}
		} catch (FileNotFoundException e) {
			throw new AgendaModelException("Arquivo " + AgendaArquivoDAO.FILE.getAbsolutePath() + 
					" não existe");
		} catch (IOException e) {
			throw new AgendaModelException("Erro de I/O ao recuperar contatos", e);
		}
		return lista;
	}
	
	private void criarDiretorio(File diretorio) throws IOException {
		if (diretorio.exists() == false) {
			AgendaArquivoDAO.DIRECTORY.mkdir();
		}
	}
	
	private void criarArquivo(File arquivo) throws IOException {
		if (arquivo.exists() == false) {
			AgendaArquivoDAO.FILE.createNewFile();
		}
	}
}
