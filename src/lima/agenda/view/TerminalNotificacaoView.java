package lima.agenda.view;

public class TerminalNotificacaoView {
	
	public void imprimirNotificacao(String mensagem) {
		System.out.println(mensagem);
	}
	
	public void imprimirNotificacaoErro(Exception e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
}
