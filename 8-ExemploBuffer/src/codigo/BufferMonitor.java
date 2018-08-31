package codigo;

public class BufferMonitor extends Buffer {

    public BufferMonitor( int capacidade ) {
        this.listaItens = new String[ capacidade ];
    }
    
    @Override
    public synchronized void addItem(String item) throws Exception {
        if( quantidade == listaItens.length ){
            wait();
        }
        if( quantidade == listaItens.length ){
            throw new Exception( "Buffer cheio" );
        }
        
        listaItens[ fim ] = item;
        fim = (fim + 1) % listaItens.length;
        quantidade++;
        
        notify();
    }

    @Override
    public synchronized String removeItem() throws Exception {
        if( quantidade == 0 ){
            wait();
        }
        if( quantidade == 0 ){
            throw new Exception( "Buffer vazio !!" );
        }
        
        String item = listaItens[ inicio ];
        listaItens[ inicio ] = null;
        inicio = ( inicio + 1 ) % listaItens.length;
        quantidade--;
        notify();
        return item;
    }
    
}
