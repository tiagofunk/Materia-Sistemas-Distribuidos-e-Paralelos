package SemThread;

import java.util.ArrayList;
import java.util.List;

public class NumeroPrimo {
    
    public void buscarNumerosPrimos(int inicio, int fim ){
        boolean primo;
        List<Integer> numeros = new ArrayList<>();
        
        for( int i = inicio; i <= fim; i++ ){
            primo = true;
            for( int j = i-1; j > 1; j-- ){
                if( i % j == 0 ){
                    primo = false;
                    break;
                }
            }
            if( primo ){
                numeros.add( i );
            }
        }
        
        System.out.println(numeros);
    }
}
