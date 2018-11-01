package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ConexaoAtiva implements Runnable{
    
    private String host;
    private int port;
    
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    
    private List<ObservadorConexao> listaObservadores = new ArrayList<>();
    
    public ConexaoAtiva(String host, String port) throws IOException {
        this.host = host;
        try{
            this.port = Integer.parseInt(port);
        }catch(NumberFormatException ex ){
            throw new IllegalArgumentException("Parâmetro porta para SocketThread deve ser um número.");
        }
        System.out.println(host);
        System.out.println(this.port);
        socket = new Socket( host, this.port );
        output = new ObjectOutputStream( socket.getOutputStream() );
        input  = new ObjectInputStream( socket.getInputStream() );
    }
    
    public void addObservador( ObservadorConexao obs ){
        listaObservadores.add( obs );
    }
    
    public void removeObservador( ObservadorConexao obs ){
        listaObservadores.remove( obs );
    }
    
    public void enviar( String mensagem ) throws IOException{
        output.writeUTF( mensagem );
        output.flush();
    }
    
    public void fecharConexao() throws IOException {
        input.close();
        output.close();
        socket.close();
    }

    @Override
    public void run(){
        String mensagem;
        
        try {
            socket = new Socket(host, port);
            input = new ObjectInputStream(socket.getInputStream());
            
            while( true ){
                mensagem = input.readUTF();
                
                Thread.sleep(100);
                
                if( mensagem != null && !mensagem.isEmpty() ){
                    for( ObservadorConexao obs : listaObservadores ){
                        obs.encaminharMensagem( mensagem, this );
                    }
                }
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
