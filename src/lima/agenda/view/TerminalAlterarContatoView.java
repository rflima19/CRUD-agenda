package lima.agenda.view;

import java.io.IOException;

import lima.agenda.utils.Console;

public class TerminalAlterarContatoView {

	public String[] imprimirAlterarContato() throws IOException {
		String nome = null;
		String telefone = null;
		String email= null;
		
		System.out.printf("%nALTERAR CONTATO%n%n");
		
		do {
			System.out.println("Nome: (Digite ? para cancelar)");
			System.out.print(">>");
			nome = Console.readString();
			System.out.println("");
			
			if (nome.equals("?")) {
				return null;
			}
			break;
		} while (true);
		
		do {
			System.out.println("Telefone: (Digite ? para cancelar)");
			System.out.print(">>");
			telefone = Console.readString();
			System.out.println("");
			
			if (telefone.equals("?")) {
				return null;
			}
			break;
		} while (true);
			
		do {
			System.out.println("E-mail: (Digite ? para cancelar)");
			System.out.print(">>");
			email = Console.readString();
			System.out.println("");
			
			if (email.equals("?")) {
				return null;
			}
			break;
		} while (true);
		
		return new String[] {nome, telefone, email};
	}
}
