package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Servidor {
    
    private int porta;
    private List<ObservadorConexao> listaObs;
    private ServerSocket server;

    public Servidor(int porta, List<ObservadorConexao> listaObs ) {
        this.porta = porta;
        this.listaObs = listaObs;
    }
    
    public void iniciar() throws IOException{
        Socket socket;
        ThreadSocket ts;
        
        server = new ServerSocket( porta );
        server.setReuseAddress( true );
        
        while( true ){
            socket = server.accept();
            ts = new ThreadSocket( socket );
            ts.adicionarObservador(listaObs);
            ts.start();
        }
    }
}
