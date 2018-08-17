package codigo;

public class Executar {
    public static void main(String[] args) throws InterruptedException {
        Armazem a = new Armazem();
        ArmazemSemaforo as = new ArmazemSemaforo();
        
        Produtor p1 = new Produtor( as, 5 );
        Produtor p2 = new Produtor( as, 5 );
        
        p1.start();
        p2.start();
        
        p1.join();
        p2.join();
        
        as.imprimir();
    }
}
