package lima.agenda.view;

import java.util.List;

import lima.agenda.model.Contato;

public class TerminalListarContatoView {

	public void imprimirListaContato(List<Contato> contatos) {
		System.out.printf("%nCONTATOS CADRASTRADOS%n");
		System.out.printf("%n%-20s\t%-20s\t%-20s%n", "NOME", "TELEFONE", "E-MAIL");
		for (int i = 0; i < 100; i++) {
			System.out.print("-");
		}
		for (Contato contato : contatos) {
			System.out.printf("%n%-20s\t%-20s\t%-20s%n", contato.getNome(), contato.getNumero(), contato.getEmail());
			for (int i = 0; i < 100; i++) {
				System.out.print("-");
			}
		}
		System.out.println();
	}
}
