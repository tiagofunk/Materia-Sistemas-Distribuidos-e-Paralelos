package codigo;

public class Executar {
    public static void main(String[] args) throws InterruptedException {
//        Armazem a = new ArmazemSimples();
//        Armazem a = new ArmazemSemaforo();
        Armazem a = new ArmazemMonitor();
        
        Produtor p1 = new Produtor( a, 5 );
        Produtor p2 = new Produtor( a, 5 );
        
        p1.start();
        p2.start();
        
        p1.join();
        p2.join();
        
        a.imprimir();
    }
}
