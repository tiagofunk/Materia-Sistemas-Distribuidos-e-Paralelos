package codigo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EscritorSemaforo extends Escritor{
    private Semaphore semaforo = new Semaphore( 1 );
    
    @Override
    public void gravar(String mensagem) {
        try {
            semaforo.acquire();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        try {
            FileOutputStream fos = new FileOutputStream( new File( CAMINHO ), true );
            for( int i = 0; i < mensagem.length(); i++ ){
                fos.write( mensagem.charAt( i ) );
            }
            fos.flush();
            fos.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        semaforo.release();
    }
}
