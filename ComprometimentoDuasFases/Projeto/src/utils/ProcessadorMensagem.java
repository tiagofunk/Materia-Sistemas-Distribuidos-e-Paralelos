package utils;

import Principal.Constantes;
import java.io.IOException;
import java.util.List;
import model.Sessao;
import model.TipoResposta;
import server.Conexao;
import server.ObservadorConexao;

public class ProcessadorMensagem implements ObservadorConexao {
    
    private List<Conexao> listaConexoes;

    private Sessao sessao;

    public ProcessadorMensagem(Sessao sessao, List<Conexao> listaConexoes) {
        this.sessao = sessao;
        this.listaConexoes = listaConexoes;
    }
    
    public void iniciarRequest(){
        System.out.println("naoooooooooooo");
        enviarParaTodos( Constantes.VOTE_REQUEST );
    }

    @Override
    public synchronized void encaminharMensagem(String mensagem, Conexao con) {
        mensagem = mensagem.trim();
        
        try{
            
            if (mensagem.equals(Constantes.VOTE_REQUEST)) {
                System.out.println("Coordenador disse: " + mensagem);
                if (sessao.getResposta() == TipoResposta.POSITIVA) {
                    System.out.println("Minha resposta: VOTE_COMMIT");
                    con.enviar(Constantes.VOTE_COMMIT);
                } else {
                    System.out.println("Minha resposta: VOTE_ABORT");
                    con.enviar(Constantes.VOTE_ABORT);
                }
                
                
                
            }else if( mensagem.equals(Constantes.VOTE_COMMIT) || mensagem.equals(Constantes.VOTE_ABORT) ){
                System.out.println("Recebi: " + mensagem);
                if( mensagem.equals( Constantes.VOTE_COMMIT ) ){
                    sessao.getVotacao().votar( true );
                }else{
                    sessao.getVotacao().votar( false );
                }
                if( sessao.getVotacao().tudoMundoVotou() ){
                    System.out.println("Tudo mundo votou.");
                    if( sessao.getVotacao().resultadoVotacao() ){
                        enviarParaTodos( Constantes.GLOBAL_COMMIT );
                        System.out.println("Resultado: " + Constantes.GLOBAL_COMMIT);
                    }else{
                        enviarParaTodos( Constantes.GLOBAL_ABORT );
                        System.out.println("Resultado: " + Constantes.GLOBAL_ABORT);
                    }
                }
                
                
                
            }else if( mensagem.equals( Constantes.GLOBAL_COMMIT ) ){
                System.out.println("Coordenador deu veridito final: " + Constantes.GLOBAL_COMMIT);
                
                
            }else if( mensagem.equals( Constantes.GLOBAL_ABORT ) ){
                System.out.println("Coordenador deu veridito final: " + Constantes.GLOBAL_ABORT);
            }
            
        }catch (IOException ex ){
            ex.printStackTrace();
        }
    }
    
    private void enviarParaTodos(String mensagem){
        for( Conexao c: listaConexoes ){
            try {
                c.enviar(mensagem);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
