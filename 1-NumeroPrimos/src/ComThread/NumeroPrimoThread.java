package ComThread;

import java.util.ArrayList;
import java.util.List;

public class NumeroPrimoThread extends Thread{
    
    private int inicio;
    private int fim;
    private List<Integer> numeros = new ArrayList<>();
    
    @Override
    public void run(){
        boolean primo;

        for (int i = inicio; i <= fim; i++) {
            primo = true;
            for (int j = i - 1; j > 1; j--) {
                if (i % j == 0) {
                    primo = false;
                    break;
                }
            }
            if (primo) {
                numeros.add(i);
            }
        }
        
        System.out.println( inicio + "-" + fim + ": "+ numeros );
    }

    public void setIntervalo(int inicio, int fim) {
        this.inicio = inicio;
        this.fim = fim;
    }
    
}
