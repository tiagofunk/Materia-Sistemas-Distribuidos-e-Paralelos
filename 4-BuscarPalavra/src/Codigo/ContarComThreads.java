package Codigo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ContarComThreads extends Thread {
    
    private static int contador = 0;
    
    @Override
    public void run(){
        for( int nome : nomeArquivos ){
            contador += lerArquivo( busca, nome);
            System.out.println( nome + " " + contador);
        }
    }
    
    private String busca;
    private int[] nomeArquivos;

    public ContarComThreads(String busca, int inicio, int fim) {
        this.busca = busca;
        nomeArquivos = new int[ fim - inicio ];
        for( int i = 0; i < fim-inicio; i++ ){
            nomeArquivos[ i ] = i+inicio;
        }
    }
    
    private int lerArquivo(String busca, int nomeArquivo) {
        int contador = 0;
        String linha;
        final String CAMINHO
                = "/home/tiago/Repositorios/Github/DistribuidosParalelos/Arquivos/"
                + "3-CasosTestePalavrasArquivos/";
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(new File(CAMINHO + nomeArquivo + ".txt")));

            while( ( linha = br.readLine() ) != null ){
                if( linha.equals( busca ) ){
                    contador++;
                }
            }

            br.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return contador;
    }
}
