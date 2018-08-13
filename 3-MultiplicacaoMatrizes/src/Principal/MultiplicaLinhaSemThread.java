package Principal;

public class MultiplicaLinhaSemThread {
    
    public void multiplicaoLinha(int indiceLinha, int indiceColuna, int[][] matrizA, int[][] matrizB, int[][] matriz ){
        int soma = 0;
        for( int i = 0; i < matriz[ 0 ].length; i++ ){
            soma +=
                    matrizA[ indiceLinha ][ i ] * matrizB[ i ][ indiceColuna ];
        }
        matriz[ indiceLinha ][ indiceColuna ] = soma;
    }
    
}
