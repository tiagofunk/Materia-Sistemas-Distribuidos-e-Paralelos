package codigo;

public class ContadorSequencialMonitor {
    
    private long valorSequencial;
    private static ContadorSequencialMonitor contador;
    
    private ContadorSequencialMonitor() {
        valorSequencial = 0;
    }
    
    public synchronized static ContadorSequencialMonitor getInstance() {
        if( contador == null ){
            contador = new ContadorSequencialMonitor();
        }
        return contador;
    }
    
    public long next(){
        return valorSequencial++;
    }
    
}
