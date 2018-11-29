package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Thread{
    
    private int porta;
    private List<ObservadorConexao> listaObs;
    private ServerSocket server;

    public Servidor(int porta, List<ObservadorConexao> listaObs ) {
        this.porta = porta;
        this.listaObs = listaObs;
    }
    
    private void iniciar() throws IOException{
        Socket socket;
        ThreadSocket ts;
        
        server = new ServerSocket( porta );
        server.setReuseAddress( true );
        
        while( true ){
            socket = server.accept();
            System.out.println("Conectou " + socket.getInetAddress().getHostAddress() );
            ts = new ThreadSocket( socket );
            ts.adicionarObservador(listaObs);
            ts.start();
        }
    }

    @Override
    public void run() {
        try {
            iniciar();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
