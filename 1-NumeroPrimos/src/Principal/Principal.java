package Principal;

import ComThread.NumeroPrimoThread;
import SemThread.NumeroPrimo;

public class Principal {

    public static void main(String[] args) {
        new NumeroPrimo().buscarNumerosPrimos(2, 1000);
        
//        NumeroPrimoThread npt = new NumeroPrimoThread();
//        npt.setIntervalo( 2, 1000 );
//        npt.start();
    }
}
