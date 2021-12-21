package lima.agenda.view;

import java.io.IOException;

import lima.agenda.utils.Console;

public class TerminalSolicitaLetraView {

	public Character imprimirSolicitacaoLetra() throws IOException {
		String letra = null;
		
		System.out.printf("%nLISTAR CONTATOS POR LETRA%n%n");
		
		do {
			System.out.println("Letra: (Digite ? para cancelar)");
			System.out.print(">>");
			letra = Console.readString();
			System.out.println();
			if (letra.equals("?")) {
				return null;
			}
			if (letra.length() > 1) {
				System.out.println("Digite uma letra");
				System.out.println();
				continue;
			}
			break;
		} while (true);
		
		return letra.charAt(0);
	}
}
