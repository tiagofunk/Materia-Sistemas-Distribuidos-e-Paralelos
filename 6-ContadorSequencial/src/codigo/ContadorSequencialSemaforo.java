package codigo;

import java.util.concurrent.Semaphore;

public class ContadorSequencialSemaforo implements ContadorSequencial{
    
    private long valorSequencial;
    private static ContadorSequencialSemaforo contador;
    
    private static Semaphore semaforo;
    
    private ContadorSequencialSemaforo() {
        valorSequencial = 0;
        semaforo = new Semaphore( 1 );
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
        return valorSequencial++;
    }
    
}
