package codigo;

import java.util.ArrayList;
import java.util.List;

public class Executar {
    
    public static void main(String[] args) {
        final int TIPO_CONTADOR = 2;
        final int NUMERO_THREADS = 10;
        List<MinhaThread> lt = new ArrayList<>();
        
        for( int i = 0; i < NUMERO_THREADS; i++ ){
            lt.add( new MinhaThread( TIPO_CONTADOR ) );
        }
        
        for( int i = 0; i < NUMERO_THREADS; i++ ){
            lt.get( i ).start();
        }
    }
}
