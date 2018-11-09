package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ConexaoAtiva extends Conexao{
    
    private String host;
    private int port;
    
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    
    public ConexaoAtiva(int timeout, String host, String port) throws IOException {
        super( timeout );
        this.host = host;
        try{
            this.port = Integer.parseInt(port);
        }catch(NumberFormatException ex ){
            throw new IllegalArgumentException("Parâmetro porta para SocketThread deve ser um número.");
        }
        
        socket = new Socket( host, this.port );
        output = new ObjectOutputStream( socket.getOutputStream() );
        input  = new ObjectInputStream( socket.getInputStream() );
    }
    
    @Override
    public void enviar( String mensagem ) throws IOException{
        output.writeUTF( mensagem );
        output.flush();
    }
    
    @Override
    public void fecharConexao() throws IOException {
        input.close();
        output.close();
        socket.close();
    }

    @Override
    public void run(){
        String mensagem = "";
        
        try {
            socket.setSoTimeout( 2*super.timeout );
            
            try{
                mensagem = input.readUTF();
                if( mensagem != null && !mensagem.isEmpty() ){
                    for( ObservadorConexao obs : listaObservadores ){
                        obs.encaminharMensagem( mensagem, this );
                    }
                }
            }catch (SocketTimeoutException ex ){
                for( ObservadorConexao obs : listaObservadores ){
                    obs.avisarTimeout( this );
                }
            }
            Thread.sleep( 3*1000 );
            
        } catch (java.io.EOFException ex){
            System.out.println("Conexão fechada (Linux).");
        } catch (java.net.SocketException ex){
            System.out.println("Conexão fechada (Windows).");
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
