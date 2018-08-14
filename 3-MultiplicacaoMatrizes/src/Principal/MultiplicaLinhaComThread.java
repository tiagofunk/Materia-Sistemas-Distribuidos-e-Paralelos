package Principal;

public class MultiplicaLinhaComThread extends Thread{
    
    private int indiceLinha;
    private int indiceColuna;
    private int[][] matrizA;
    private int[][] matrizB;
    private int[][] matriz;

    public MultiplicaLinhaComThread(int indiceLinha, int indiceColuna, int[][] matrizA, int[][] matrizB, int[][] matriz) {
        this.indiceLinha = indiceLinha;
        this.indiceColuna = indiceColuna;
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        this.matriz = matriz;
    }
    
    @Override
    public void run(){
        int soma = 0;
        for( int i = 0; i < matrizA[ 0 ].length; i++ ){
            soma += matrizA[ indiceLinha ][ i ] * matrizB[ i ][ indiceColuna ];
        }
        matriz[ indiceLinha ][ indiceColuna ] = soma;
    }
}
