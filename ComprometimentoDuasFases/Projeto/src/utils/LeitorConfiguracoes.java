package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeitorConfiguracoes {
    
    private final String CAMINHO_ARQUIVO_LOCAL = "local.txt";
    private final String CAMINHO_ARQUIVO_GLOBAL = "global.txt";
    private final String CARACTER_SEPARADOR = ":";
    
    public String lerConfiguracoesLocais(){
        String retorno = null;
        
        try {
            BufferedReader br = new BufferedReader( new FileReader( CAMINHO_ARQUIVO_LOCAL ) );
            
            retorno = br.readLine().trim();
            
            br.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return retorno;
    }
    
    public String[][] lerConfiguracoesGlobais() {
        String conteudo[];
        String retorno[][] = null;
        
        try {
            BufferedReader br = new BufferedReader( new FileReader( CAMINHO_ARQUIVO_GLOBAL ) );
            int numerosLinhas = Integer.parseInt( br.readLine().trim() );
            retorno = new String[numerosLinhas][2];
            
            for (int i = 0; i < numerosLinhas; i++) {
                conteudo = br.readLine().trim().split( CARACTER_SEPARADOR );
                for (int j = 0; j < conteudo.length; j++) {
                    retorno[i][j] = conteudo[j];
                }
            }
            
            br.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return retorno;
    }
}
