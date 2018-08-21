package codigo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class Escritor {
//    protected final String CAMINHO = "/home/tiago/log.txt";
    protected final String CAMINHO = "C:\\Users\\10516125940\\Documents\\Github\\log.txt";
    
    public void limparArquivo(){
        try {
            FileOutputStream fos = new FileOutputStream(new File(CAMINHO), false);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public abstract void gravar( String mensagem );
}
