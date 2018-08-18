package codigo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Executar {
    public static void main(String[] args) {
        final int NUMERO_THREADS = 10;
        final Escritor escritor = new EscritorNormal();
        final Random gerador = new Random();
        List< MinhaThread > lista = new ArrayList<>();
        
        escritor.limparArquivo();
        
        for( int i = 0; i < NUMERO_THREADS; i++ ){
            lista.add( new MinhaThread( i, gerador.nextInt(), escritor ) );
        }
        
        for( int i = 0; i < NUMERO_THREADS; i++ ){
            lista.get( i ).start();
        }
    }
}
