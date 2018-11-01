package utils;

import Principal.Constantes;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.ConexaoAtiva;
import server.ConexaoPassiva;
import server.ObservadorConexao;

public class ProcessadorMensagem implements ObservadorConexao{
    
    private String resposta;

     @Override
     public void encaminharMensagem( String mensagem, ConexaoPassiva con ) {
        if( mensagem.equals( Constantes.VOTE_REQUEST ) ){
            System.out.println("foi");
            
            try{
                if( resposta.equalsIgnoreCase("yes") ){
                    con.enviar( Constantes.VOTE_COMMIT );
                }else{
                    con.enviar( Constantes.VOTE_ABORT );
                }
            }catch (IOException ex ){
                ex.printStackTrace();
            }
        }
    }
     
    @Override
    public void encaminharMensagem(String mensagem, ConexaoAtiva con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
    
}
