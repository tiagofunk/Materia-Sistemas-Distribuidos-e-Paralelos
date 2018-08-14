package codigo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ContadorPalavrasComThread extends Thread{

    private int[] indices;
    private String caminhoArquivo;
    private String busca;
    
    private int qtd = 0;

    public ContadorPalavrasComThread(int inicio, int fim, String busca, String caminhoArquivo) {
        indices = new int[ fim-inicio ];
        for( int i = 0; i < (fim-inicio); i++ ){
            indices[ i ] = i + inicio;
        }
        this.busca = busca;
        this.caminhoArquivo = caminhoArquivo;
    }
    
    @Override
    public void run(){
        for( int i : indices ){
            qtd += lerPalavras( busca, i);
        }
    }
    
    public int getQtd(){
        return qtd;
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
