package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Conexao{
    
    private String host;
    private int port;
    
    private Socket socket;
    private ObjectOutputStream output;
    
    public Conexao(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        
        socket = new Socket( host, this.port );
        output = new ObjectOutputStream( socket.getOutputStream() );
    }
    
    public Conexao(String host, String port) throws IOException {
        this.host = host;
        try{
            this.port = Integer.parseInt(port);
        }catch(NumberFormatException ex ){
            throw new IllegalArgumentException("Parâmetro porta para SocketThread deve ser um número.");
        }
        
        socket = new Socket( host, this.port );
        output = new ObjectOutputStream( socket.getOutputStream() );
    }
    
    public void enviar( String mensagem ) throws IOException{
        output.writeUTF( mensagem );
        output.flush();
        if( !mensagem.contains( "informar_status_conexao" ) ){
            System.out.println("Enviou: " + mensagem);
        }
    }
    
    public void fecharConexao() throws IOException {
        output.close();
        socket.close();
    }
    
}
