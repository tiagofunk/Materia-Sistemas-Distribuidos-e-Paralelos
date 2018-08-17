package codigo;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Produtor extends Thread {
    
//    private Armazem armazem;
    private ArmazemSemaforo armazem;
    private int itensProduzir;

    public Produtor(ArmazemSemaforo armazem, int itensProduzir) {
        this.armazem = armazem;
        this.itensProduzir = itensProduzir;
    }
    
    @Override
    public void run(){
        for( int i = 0; i < itensProduzir; i++ ){
            String item = "p" + this.getId() + "_i" + i;
            try {
                armazem.addItem( item );
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
