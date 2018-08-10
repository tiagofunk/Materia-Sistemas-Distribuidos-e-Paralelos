package Principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SomaLinhaComThread extends Thread{
    
    private final int indice;
    private final String caminho;

    public SomaLinhaComThread(String caminho, int indice) {
        this.indice = indice;
        this.caminho = caminho;
    }
    
    @Override
    public void run(){
        somarLinha();
    }
    
    private int[] lerLinha(){ 
        int linhas, colunas, contador = 1;
        String linha;
        BufferedReader br;
        
        try {
            br = new BufferedReader(new FileReader(new File(caminho)));
            
            linhas = Integer.parseInt( br.readLine() );
            colunas = Integer.parseInt( br.readLine() );
            
            while( (linha = br.readLine() ) != null ){
                if( contador == indice ){
                    int numeros[] = new int[ colunas ];
                    String conteudo[] = linha.split(" ");
                    for( int i = 0; i < conteudo.length; i++ ){
                        numeros[ i ] = Integer.parseInt( conteudo[ i ] );
                    }
                    return numeros;
                }
                contador++;
            }
            
            br.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public void somarLinha(){
        int soma = 0;
        int[] numeros = lerLinha();
        
        for( int i = 0; i < numeros.length; i++ ){
            soma += numeros[ i ];
        }
        System.out.println( indice + ":" + soma);
    }
}
