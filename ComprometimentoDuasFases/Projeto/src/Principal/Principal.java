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
import server.ConexaoAtiva;
import server.CriadorConexoes;
import utils.LeitorConfiguracoes;

public class Principal {
    
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            args[ i ] = args[ i ].toLowerCase();
        }
        System.out.print("Tecle ENTER para começar: ");
        new Scanner( System.in ).nextLine();
        System.out.println("INIT");
        
        if( args[0].equals("c") ){
            coordenador();
        }else if( args[0].equals("p") ){
            participante( args[1] );
        }else{
            throw new RuntimeException("Argumento errado para tipo de agente: " + args[0] );
        }
        
    }
    
    private static void coordenador(){
        
        try {
            List<Conexao> listaConexoes = new CriadorConexoes().criarConexoes();
            Sessao s = new Sessao(TipoAgente.COORDENADOR, new Votacao( listaConexoes.size() ) );
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
    
    private static void participante( String opcaoVotacao ){
        TipoResposta tr;
        if( opcaoVotacao.equals("p") ){
            tr = TipoResposta.POSITIVA;
        }else if( opcaoVotacao.equals("n") ){
            tr = TipoResposta.NEGATIVA;
        }else{
            throw new RuntimeException("Argumento errado para tipo de resposta: " + opcaoVotacao );
        }
        
        Sessao s = new Sessao(TipoAgente.PARTICIPANTE, tr);
        ProcessadorMensagem pm = new ProcessadorMensagem( s, null);
        ConexaoPassiva c;
        String conf = new LeitorConfiguracoes().lerConfiguracoesLocais();

        try {
            c = new ConexaoPassiva( conf );
            c.addObservador( pm );
            Thread t = new Thread( c );
            t.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
