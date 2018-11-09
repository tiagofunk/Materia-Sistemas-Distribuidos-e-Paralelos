package utils;

import Principal.Constantes;
import java.io.IOException;
import java.util.List;
import model.EstadoTransacao;
import model.Sessao;
import model.TipoAgente;
import model.TipoResposta;
import server.Conexao;
import server.ObservadorConexao;

public class ProcessadorMensagem implements ObservadorConexao{
    
    private EstadoTransacao estado;
    private Sessao sessao;
    private List<Conexao> listaConexoes;

    public ProcessadorMensagem(Sessao sessao, List<Conexao> listaConexoes) {
        this.estado = EstadoTransacao.INIT;
        this.sessao = sessao;
        this.listaConexoes = listaConexoes;
    }
    
    public void iniciarRequest(){
        estado = EstadoTransacao.VOTE_REQUEST;
        enviarParaTodosInicial( Constantes.VOTE_REQUEST );
    }

    @Override
    public synchronized void encaminharMensagem(String mensagem, Conexao con) {
        mensagem = mensagem.trim();
        
        try{
            
            if (mensagem.equals(Constantes.VOTE_REQUEST) 
                && sessao.getAgente() == TipoAgente.PARTICIPANTE) {
                
                System.out.println("Coordenador disse: " + mensagem);
                if (sessao.getResposta() == TipoResposta.POSITIVA) {
                    if( !sessao.getFalhaVoteLocal() ){
                        System.out.println("Minha resposta: VOTE_COMMIT");
                        estado = EstadoTransacao.VOTE_COMMIT;
                        con.enviar(Constantes.VOTE_COMMIT);
                    }else{
                        System.out.println("Não respondi o vote request.");
                    }
                } else {
                    if( !sessao.getFalhaVoteLocal() ){
                        System.out.println("Minha resposta: VOTE_ABORT");
                        estado = EstadoTransacao.VOTE_ABORT;
                        con.enviar(Constantes.VOTE_ABORT);
                    }else{
                        System.out.println("Não respondi o vote request.");
                    }
                }
                
                
                
            }else if( (mensagem.equals(Constantes.VOTE_COMMIT) 
                    || mensagem.equals(Constantes.VOTE_ABORT) )
                    && sessao.getAgente() == TipoAgente.COORDENADOR ){
                System.out.println("Recebi: " + mensagem);
                if( mensagem.equals( Constantes.VOTE_COMMIT ) ){
                    sessao.getVotacao().votar( true );
                }else{
                    sessao.getVotacao().votar( false );
                }
                if( sessao.getVotacao().tudoMundoVotou() ){
                    System.out.println("Tudo mundo votou.");
                    if( sessao.getVotacao().resultadoVotacao() ){
                        estado = EstadoTransacao.GLOBAL_COMMIT;
                        enviarParaTodosFinal( Constantes.GLOBAL_COMMIT );
                        System.out.println("Resultado: " + Constantes.GLOBAL_COMMIT);
                    }else{
                        estado = EstadoTransacao.GLOBAL_ABORT;
                        enviarParaTodosFinal( Constantes.GLOBAL_ABORT );
                        System.out.println("Resultado: " + Constantes.GLOBAL_ABORT);
                    }
                }
                
                
            }else if( mensagem.equals( Constantes.GLOBAL_COMMIT ) 
                        && sessao.getAgente() == TipoAgente.PARTICIPANTE ){
                System.out.println("Coordenador deu veridito final: " + Constantes.GLOBAL_COMMIT);
                estado = EstadoTransacao.GLOBAL_COMMIT;
//                con.fecharConexao();
                
            }else if( mensagem.equals( Constantes.GLOBAL_ABORT ) 
                    && sessao.getAgente() == TipoAgente.PARTICIPANTE ){
                System.out.println("Coordenador deu veridito final: " + Constantes.GLOBAL_ABORT);
                estado = EstadoTransacao.GLOBAL_ABORT;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
//                con.fecharConexao();
                
            }else{
                System.out.println("Mensagem invalida: " + mensagem);
            }
            
        }catch (IOException ex ){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void avisarTimeout( Conexao con ) {
        try {
            if( estado == EstadoTransacao.INIT && sessao.getAgente() == TipoAgente.PARTICIPANTE){
                System.out.println("Voto de timeout: " + Constantes.VOTE_ABORT);
                estado = EstadoTransacao.VOTE_ABORT;
                con.enviar( Constantes.VOTE_ABORT );
            }else if(estado == EstadoTransacao.VOTE_REQUEST && sessao.getAgente() == TipoAgente.COORDENADOR) {
                System.out.println("Nem todos responderam: " + Constantes.GLOBAL_ABORT );
                estado = EstadoTransacao.GLOBAL_ABORT;
                enviarParaTodosFinal( Constantes.GLOBAL_ABORT );
            }else{
                System.out.println("Deu ruim mesmo avisarTimeout");
            }
            
            
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void enviarParaTodosInicial(String mensagem ){
        try {        
            for (int i = 0; i < listaConexoes.size(); i++) {
                if( i != sessao.getIndiceFalhaVoteRequest() ){
                    listaConexoes.get(i).enviar(mensagem);
                }else{
                    System.out.println("Não enviou no voto request");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void enviarParaTodosFinal( String mensagem ){
        try {        
            for (int i = 0; i < listaConexoes.size(); i++) {
                if( i != sessao.getIndiceFalhaVoteGlobal()){
                    listaConexoes.get(i).enviar(mensagem);
                }else{
                    System.out.println("Não enviou no resultado global");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
