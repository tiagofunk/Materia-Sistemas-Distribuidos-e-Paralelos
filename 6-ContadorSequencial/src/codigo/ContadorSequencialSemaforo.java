package codigo;

import java.util.concurrent.Semaphore;

public class ContadorSequencialSemaforo {
    
    private long valorSequencial;
    private static ContadorSequencialSemaforo contador;
    
    private static Semaphore semaforo = new Semaphore( 1 );
    
    private ContadorSequencialSemaforo() {
        valorSequencial = 0;
    }
    
    public static ContadorSequencialSemaforo getInstance() {
        try {
            semaforo.acquire();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        if( contador == null ){
            contador = new ContadorSequencialSemaforo();
        }
        semaforo.release();
        return contador;
    }
    
    public long next(){
        try {
            semaforo.acquire();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        valorSequencial++;
        semaforo.release();
        return valorSequencial;
    }
    
}
