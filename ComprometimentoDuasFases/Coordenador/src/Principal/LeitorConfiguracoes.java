package Principal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeitorConfiguracoes {
    
    private final String CAMINHO_ARQUIVO = "conf.txt";
    private final String CARACTER_SEPARADOR = ":";
    
    public String[][] lerConfiguracoes() {
        String linha;
        String conteudo[];
        String retorno[][] = null;
        
        try {
            BufferedReader br = new BufferedReader( new FileReader( CAMINHO_ARQUIVO ) );
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
