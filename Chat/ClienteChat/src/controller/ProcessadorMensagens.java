package controller;

import model.Constantes;
import server.ObservadorConexao;

public class ProcessadorMensagens implements ObservadorConexao{
    
    Controller controle;

    public ProcessadorMensagens(Controller controle) {
        this.controle = controle;
    }

    @Override
    public void encaminharMensagem(String mensagem) {
        String[] valores;
        String[] partes = mensagem.trim().split(":");
        
        switch( partes[0] ){
            case Constantes.DEVOLVE_TOKEN:
                controle.salvarUsuario(partes[1]);
                break;
            case Constantes.CONFIRMAR_AUTENTICACAO:
                controle.sucessoAutenticacao();
                break;
            case Constantes.ADICIONAR_CONTATO:
                controle.salvarContato(partes[1]);
                break;
                
            default:
                System.out.println("Tipo de mensagem inválida: " + mensagem);
        }
    }

    @Override
    public void avisarErroIOException() {
        System.out.println("Conexão fechada inesperadamente.");
    }

	

}
