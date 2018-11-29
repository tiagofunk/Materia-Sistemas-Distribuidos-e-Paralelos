package controller;

import server.ObservadorConexao;

public class ProcessadorMensagens implements ObservadorConexao{
    
    Controller controle;

    public ProcessadorMensagens(Controller controle) {
        this.controle = controle;
    }

    @Override
    public void encaminharMensagem(String ip, String mensagem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void avisarErroIOException() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	

}
