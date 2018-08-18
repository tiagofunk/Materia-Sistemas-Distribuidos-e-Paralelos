package codigo;

public class ArmazemMonitor extends Armazem{

    @Override
    public synchronized void addItem(String item) {
        itens[ posicaoLivre ] = item;
        posicaoLivre++;
    }

    @Override
    public synchronized void imprimir() {
        for( int i = 0; i < itens.length; i++ ){
            System.out.println( i + "-" + itens[ i ] );
        }
    }
    
}
