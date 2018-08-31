package codigo;

import java.util.concurrent.Semaphore;

public class BufferComMutex extends Buffer {
    
    private Semaphore mutex = new Semaphore( 1 );

    public BufferComMutex( int capacidade) {
        this.listaItens = new String[ capacidade ];
    }
    
    @Override
    public void addItem(String item) throws Exception {
        try{
            mutex.acquire();

            if( quantidade == listaItens.length ){
                throw new Exception( "Buffer cheio !!" );
            }

            listaItens[ fim ] = item;
            fim = ( fim + 1 ) % listaItens.length;
            quantidade++;
            System.out.println("Adicionou novo item");
        }catch (InterruptedException e ){
            System.out.println("Mutex interrompido. Abortando.");
            e.printStackTrace();
            return;
        }finally{
            mutex.release();
        }
    }

    @Override
    public String removeItem() throws Exception {
        String item = null;
        try {
            mutex.acquire();
            if( quantidade == 0 ){
                throw new Exception("Buffer vazio !!");
            }
            
            item = listaItens[ inicio ];
            listaItens[ inicio ] = null;
            inicio = ( inicio + 1 ) % listaItens.length;
            quantidade--;
            System.out.println("Removeu um item.");
        } catch (InterruptedException e) {
            System.out.println("Mutex interrompido. Abortando.");
            e.printStackTrace();
        }finally{
            mutex.release();
        }
        return item;
    }
    
}
