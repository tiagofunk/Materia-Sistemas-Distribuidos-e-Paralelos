package codigo;

import java.util.concurrent.Semaphore;

public class ArmazemSemaforo {
    
    private String[] itens = new String[ 10 ];
    private int posicaoLivre = 0;
    
    private Semaphore semaforo = new Semaphore( 1 );
    
    public void addItem( String item ) throws InterruptedException{
        semaforo.acquire();
        
        itens[ posicaoLivre ] = item;
        posicaoLivre++;
        
        semaforo.release();
        System.out.println("abc");
    }
    
    public void imprimir(){
        for( int i = 0; i < itens.length; i++ ){
            System.out.println( i + "-" + itens[ i ] );
        }
    }
}
