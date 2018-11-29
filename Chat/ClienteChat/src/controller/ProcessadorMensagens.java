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
        }
    }

    @Override
    public void avisarErroIOException() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	

}
