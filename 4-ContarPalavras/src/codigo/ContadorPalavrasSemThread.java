package codigo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ContadorPalavrasSemThread {

    private int[] indices;
    private String caminhoArquivo;

    public ContadorPalavrasSemThread(int inicio, int fim, String caminhoArquivo) {
        indices = new int[ fim-inicio ];
        for( int i = 0; i < (fim-inicio); i++ ){
            indices[ i ] = i + inicio;
        }
        this.caminhoArquivo = caminhoArquivo;
    }
    
    public void contar( String busca ){
        int soma = 0;
        for( int i : indices ){
            soma += lerPalavras( busca, i);
        }
        System.out.println( "Soma: " +  soma );
    }

    private int lerPalavras(String busca, int arquivo) {
        int contador = 0;
        String linha;
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(new File(caminhoArquivo + arquivo + ".txt")));

            while ((linha = br.readLine()) != null) {
                if( busca.equals( linha ) ){
                    contador++;
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return contador;
    }
}
