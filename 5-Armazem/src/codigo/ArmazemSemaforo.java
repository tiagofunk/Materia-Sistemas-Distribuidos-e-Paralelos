package codigo;

import java.util.concurrent.Semaphore;

public class ArmazemSemaforo extends Armazem{
    
    private Semaphore semaforo = new Semaphore( 1 );
    
    @Override
    public void addItem( String item ){
        try {
            semaforo.acquire();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        itens[ posicaoLivre ] = item;
        posicaoLivre++;
        
        semaforo.release();
        System.out.println("abc");
    }
    
    @Override
    public void imprimir(){
        for( int i = 0; i < itens.length; i++ ){
            System.out.println( i + "-" + itens[ i ] );
        }
    }
}
