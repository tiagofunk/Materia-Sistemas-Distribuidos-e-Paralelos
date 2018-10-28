package Principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketThread extends Thread{
    
    private Socket conn;
    private ServerSocket server;
    

    public SocketThread(Socket socket) {
        this.conn = socket;
    }

    @Override
    public void run(){
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            conn.setSoTimeout( 5000 );
            out = new PrintWriter(conn.getOutputStream(), true);
            in = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );
            
            out.println( Constantes.VOTE_REQUEST );
            
        } catch (SocketException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    
}
