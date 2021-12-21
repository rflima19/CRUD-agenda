package lima.agenda.view;

import java.io.IOException;

import lima.agenda.utils.Console;

public class TerminalSolicitaNomeView {

	public String imprimirSolicitacaoNome() throws IOException {
		String nome = null;
		
		System.out.printf("%nPROCURAR CONTATOS%n%n");
		
		do {
			System.out.println("Nome: (Digite ? para cancelar)");
			System.out.print(">>");
			nome = Console.readString();
			System.out.println();
			if (nome.equals("?")) {
				return null;
			}
			break;
		} while (true);
		
		return nome;
	}
}
