package codigo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EscritorMonitor extends Escritor{
    
    @Override
    public synchronized void gravar(String mensagem) {
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
    }
}
