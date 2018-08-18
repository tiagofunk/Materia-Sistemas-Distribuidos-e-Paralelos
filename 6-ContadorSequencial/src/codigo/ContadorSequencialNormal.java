package codigo;

public class ContadorSequencialNormal {
    
    private long valorSequencial;
    private static ContadorSequencialNormal contador;
    
    private ContadorSequencialNormal() {
        valorSequencial = 0;
    }
    
    public static ContadorSequencialNormal getInstance() {
        if( contador == null ){
            contador = new ContadorSequencialNormal();
        }
        return contador;
    }
    
    public long next(){
        return valorSequencial++;
    }
    
}
