package persistence;

import controller.Constantes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeitorConfiguracoes {
    
    private static final String ARQUIVO_CONF = "conf.txt";
    
    public static int lerPortaServidor() throws FileNotFoundException, IOException{
        int porta = -1;
        String linha;
        String[] leitura;
        BufferedReader br = new BufferedReader( new FileReader( ARQUIVO_CONF ) );
        
        while( (linha = br.readLine()) != null ){
            leitura = linha.split(":");
            if( leitura[0].equals( Constantes.PORTA_SERVIDOR ) ){
                porta = Integer.parseInt( leitura[1] );
                break;
            }
        }
        return porta;
    }
}
