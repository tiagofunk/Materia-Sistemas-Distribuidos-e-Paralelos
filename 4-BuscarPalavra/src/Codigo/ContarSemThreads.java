package Codigo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ContarSemThreads {
    
    private String busca;
    private int[] nomeArquivos;

    public ContarSemThreads(String busca, int[] nomeArquivos) {
        this.busca = busca;
        this.nomeArquivos = nomeArquivos;
    }
    
    public int contarPalavras(){
        int soma = 0;
        for( int nomes : nomeArquivos ){
            soma += lerArquivo( busca, nomes);
            System.out.println( nomes + " " + soma);
        }
        return soma;
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
