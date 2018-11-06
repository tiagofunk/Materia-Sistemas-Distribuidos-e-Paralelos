package Principal;

import utils.ProcessadorMensagem;
import server.ConexaoPassiva;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import model.Sessao;
import model.TipoAgente;
import model.TipoResposta;
import model.Votacao;
import server.Conexao;
import server.CriadorConexoes;
import utils.LeitorConfiguracoes;

public class Principal {
    
    private static int TIMEOUT = 5000;
    
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            args[ i ] = args[ i ].toLowerCase();
        }
        System.out.print("Tecle ENTER para começar: ");
        new Scanner( System.in ).nextLine();
        System.out.println("INIT");
        
        if( args[0].equals("c") ){
            coordenador( TIMEOUT, args[1] );
        }else if( args[0].equals("p") ){
            participante( args[1], TIMEOUT, "" );
        }else{
            throw new RuntimeException("Argumento errado para tipo de agente: " + args[0] );
        }
        
    }
    
    private static void coordenador(int timeout, String tipoFalha ){
        
        try {
            List<Conexao> listaConexoes = new CriadorConexoes().criarConexoes( timeout );
            Sessao s = new Sessao(TipoAgente.COORDENADOR, new Votacao( listaConexoes.size() ) );
            
            
            switch(tipoFalha){
                case "f0":
                    s.setIndiceFalhaVoteRequest( -1 );
                    s.setIndiceFalhaVoteGlobal( -1 );
                    s.setFalhaVoteLocal( false );
                    break;
                    
                case "f1":
                    s.setIndiceFalhaVoteRequest( 0 );
                    s.setIndiceFalhaVoteGlobal( -1 );
                    s.setFalhaVoteLocal( false );
                    break;
                    
                    default:
                        throw new IllegalArgumentException("Argumento para tipo de falha inválido: " + tipoFalha);
            }
            
            ProcessadorMensagem pm = new ProcessadorMensagem( s, listaConexoes );
            
            for( Conexao c: listaConexoes ){
                c.addObservador( pm );
                Thread t = new Thread( c );
                t.start();
            }
            pm.iniciarRequest();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private static void participante( String opcaoVotacao, int timeout, String tipoFalha ){
        TipoResposta tr;
        if( opcaoVotacao.equals("p") ){
            tr = TipoResposta.POSITIVA;
        }else if( opcaoVotacao.equals("n") ){
            tr = TipoResposta.NEGATIVA;
        }else{
            throw new RuntimeException("Argumento errado para tipo de resposta: " + opcaoVotacao );
        }
        
        Sessao s = new Sessao(TipoAgente.PARTICIPANTE, tr);
        ProcessadorMensagem pm = new ProcessadorMensagem( s, null );
        ConexaoPassiva c;
        String conf = new LeitorConfiguracoes().lerConfiguracoesLocais();

        try {
            c = new ConexaoPassiva( timeout, conf );
            c.addObservador( pm );
            Thread t = new Thread( c );
            t.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
