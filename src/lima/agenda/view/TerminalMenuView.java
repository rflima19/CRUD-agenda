package lima.agenda.view;

import java.io.IOException;

import lima.agenda.utils.Console;

public class TerminalMenuView {
	
	public static final int SAIR = 0;
	public static final int INSERIR_CONTATO = 1;
	public static final int EXCLUIR_CONTATO = 2;
	public static final int ALTERAR_CONTATO = 3;
	public static final int LISTAR_CONTATOS = 4;
	public static final int LISTAR_LETRA = 5;
	public static final int PROCURAR_CONTATO = 6;

	public int imprimirMenu() throws IOException {
		int opcao = -1;

		System.out.printf("%nAGENDA%n%n");
		System.out.println(TerminalMenuView.INSERIR_CONTATO + " - Salvar contato");
		System.out.println(TerminalMenuView.EXCLUIR_CONTATO + " - Remover contato");
		System.out.println(TerminalMenuView.ALTERAR_CONTATO + " - Alterar contato");
		System.out.println(TerminalMenuView.LISTAR_CONTATOS + " - Listar contatos");
		System.out.println(TerminalMenuView.LISTAR_LETRA + " - Listar contatos por letra");
		System.out.println(TerminalMenuView.PROCURAR_CONTATO + " - Procurar contato");
		System.out.println(TerminalMenuView.SAIR + " - Sair");
		while (true) {
			System.out.print(">>");
	
			try {
				opcao = Console.readInteger();
			} catch (NumberFormatException e) {
				System.out.println("Digite um número inteiro");
				continue;
			}
			break;
		}

		return opcao;
	}
}
