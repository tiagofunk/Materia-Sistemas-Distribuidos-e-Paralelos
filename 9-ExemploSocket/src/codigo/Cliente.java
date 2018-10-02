package codigo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    public static void main(String[] args) {
        String endereco = "10.60.184.4";
//        String endereco = "127.0.0.1";
        int porta = 56000;
        
        Socket conn = null;
        BufferedReader in = null;
        
        try {
            System.out.println("Tentando conectar...");
            conn = new Socket(endereco, porta);
            System.out.println("Conetcado !!");
            
            in = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );
            
            String linha = in.readLine();
            while (linha != null) {                
                System.out.println( linha );
                linha = in.readLine();
            }
        } catch (UnknownHostException e) {
            System.out.println("Host não encontrado");
            e.printStackTrace();
        }catch ( IOException e ){
            System.out.println("Erro de entrada/saida ao criar socket");
            e.printStackTrace();
        }finally{
            try {
                if( in != null ){
                    in.close();
                }
                if( conn != null ){
                    conn.close();
                }
            } catch (IOException ex) {
                System.out.println("Erro ao fechar input stream ou socket");
                ex.printStackTrace();
            }
        }
    }
}
