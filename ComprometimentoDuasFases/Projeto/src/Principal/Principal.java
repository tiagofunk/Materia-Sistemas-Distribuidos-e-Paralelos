package Principal;

import utils.ProcessadorMensagem;
import server.ConexaoPassiva;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import server.ConexaoAtiva;
import server.CriadorConexoes;

public class Principal {
    
    public static void main(String[] args) {
        System.out.print("Tecle ENTER para começar: ");
        new Scanner( System.in ).nextLine();
        System.out.println("INIT");
        
        if( args[0].equals("c") ){
            coordenador();
        }else if( args[0].equals("p") ){
            participante();
        }else{
            throw new RuntimeException("Argumentos errado: " + args[0] );
        }
        
    }
    
    private static void coordenador(){
        try {
            List<ConexaoAtiva> listaConexoes = new CriadorConexoes().criarConexoes();
            for( ConexaoAtiva c: listaConexoes ){
                c.enviar( Constantes.VOTE_REQUEST );
                Thread t = new Thread( c );
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private static void participante(){        
        ProcessadorMensagem pm = new ProcessadorMensagem();
        pm.setResposta("sim");
        
        ConexaoPassiva c;
        try {
            c = new ConexaoPassiva("127.0.0.1", "56001");
            c.addObservador( pm );
            Thread t = new Thread( c );
            t.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
