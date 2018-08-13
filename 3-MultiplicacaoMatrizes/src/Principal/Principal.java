package Principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Principal {
    
    private static final String CAMINHO_1A = "caso1/A.TXT";
    private static final String CAMINHO_1B = "caso1/B.TXT";
    private static final String CAMINHO_2A = "caso2/A.TXT";
    private static final String CAMINHO_2B = "caso2/B.TXT";
    private static final String CAMINHO_3A = "caso3/A.TXT";
    private static final String CAMINHO_3B = "caso3/B.TXT";
    
    public static void main(String[] args) {
        final String A = CAMINHO_1A;final String B = CAMINHO_1B;
//        final String A = CAMINHO_2A;final String B = CAMINHO_2B;
        semThread( A, B );
    }
    
    private static int[][] carregarMatriz(String nomeArquivo){
        final String CAMINHO = 
           "/home/tiago/Repositorios/Github/DistribuidosParalelos/Arquivos/" +
            "2-CasosTesteMultiplicacaoMatrizes/";
        int linhas, colunas, contador = 0;
        int numeros[], matriz[][] = null;
        String linha, conteudo[];
        BufferedReader br;
        
        try {
            br = new BufferedReader(new FileReader(new File(CAMINHO + nomeArquivo)));
            
            linhas = Integer.parseInt( br.readLine() );
            colunas = Integer.parseInt( br.readLine() );
            
            numeros = new int[ colunas ];
            matriz = new int[ linhas ][ colunas ];
            
            for( int i = 0; i < linhas; i++ ){
                conteudo = br.readLine().split(" ");
                for( int j = 0; j < conteudo.length; j++ ){
                    matriz[ i ][ j ] = Integer.parseInt( conteudo[ j ] );
                }
            }
            
            br.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return matriz;
    }
    
    private static void semThread( String A, String B ){
        int matrizA[][] = carregarMatriz( A );
        int matrizB[][] = carregarMatriz( B );
        int matriz[][] = new int[ matrizA.length ][ matrizB[ 0 ].length ];
        
        MultiplicaLinhaSemThread m = new MultiplicaLinhaSemThread();
        
        for( int i = 0; i < matriz.length; i++ ){
            for( int j = 0; j < matriz[ i ].length; j++ ){
                m.multiplicaoLinha( i, j, matrizA, matrizB, matriz );
            }
            System.out.println("I: " + i );
        }
        
        for( int i = 0; i < matriz.length; i++ ){
            for( int j = 0; j < matriz[ i ].length; j++ ){
                System.out.printf( "%6d ", matriz[ i ][ j ]);
            }
            System.out.println("");
        }
    }
}
