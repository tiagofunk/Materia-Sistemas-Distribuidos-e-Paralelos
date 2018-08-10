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
    
    public void buscarNumerosPrimosGrifo(int inicio, int fim ){
        boolean valores[] = new boolean[ fim ];
        for(int i = 2; i < fim; i++){
            if( !valores[ i ] ){
                for( int j = 2*i; j < fim; j += i ){
                    valores[ j ] = true;
                }   
            }
        }
        
//        List<Integer> numeros = new ArrayList<>();
        System.out.print("Numeros primos: ");
        int contador = 0;
        for( int i = 2; i < fim; i++ ){
            if( !valores[ i ] ){
//                numeros.add( i );
                System.out.print( i + ", " );
                contador++;
                if( contador % 10 == 0 ){
                    System.out.println("");
                }
            }
        }
        System.out.println( "" );
    }
}
