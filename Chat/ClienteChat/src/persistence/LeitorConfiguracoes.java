package persistence;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import model.Constantes;

public class LeitorConfiguracoes {
    
    private static final String ARQUIVO_CONF = "conf.txt";
    
    public static String[] lerDadosUsuario() throws FileNotFoundException, IOException{
        String dados[] = new String[4];
        String linha;
        String[] leitura;
        BufferedReader br = new BufferedReader( new FileReader( ARQUIVO_CONF ) );
        
        while( (linha = br.readLine()) != null ){
            leitura = linha.split(":");
            if( leitura[0].equals( Constantes.NOME ) ){
                dados[2] = leitura[1];
            }else if( leitura[0].equals( Constantes.TOKEN ) ){
                dados[0] = leitura[1];
            }else if( leitura[0].equals( Constantes.SENHA ) ){
                dados[1] = leitura[1];
            }else if( leitura[0].equals( Constantes.TELEFONE ) ){
                dados[3] = leitura[1];
            }
        }
        return dados;
    }
    
    
    public static String[] lerDadosServidor() throws FileNotFoundException, IOException{
        String dados[] = new String[2];
        String linha;
        String[] leitura;
        BufferedReader br = new BufferedReader( new FileReader( ARQUIVO_CONF ) );
        
        while( (linha = br.readLine()) != null ){
            leitura = linha.split(":");
            if( leitura[0].equals( Constantes.IP_SERVIDOR_REMOTO ) ){
                dados[0] = leitura[1];
            }else if( leitura[0].equals( Constantes.PORTA_SERVIDOR_REMOTO ) ){
                dados[1] = leitura[1];
            }
        }
        return dados;
    }
    
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
