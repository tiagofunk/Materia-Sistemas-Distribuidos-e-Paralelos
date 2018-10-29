package Principal;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SocketThread extends Thread{
    
    private String host;
    private int port;
    
    private byte[] buffer = new byte[256];
    

    public SocketThread(String host, String port) {
        this.host = host;
        try{
            this.port = Integer.parseInt(port);
        }catch(NumberFormatException ex ){
            throw new IllegalArgumentException("Parâmetro porta para SocketThread deve ser um número.");
        }
    }

    @Override
    public void run(){
        DatagramSocket socket = null;
        DatagramPacket enviado;
        DatagramPacket recebido;
        
        try {
            socket = new DatagramSocket();
            socket.setReuseAddress(true);
            
            buffer = Constantes.VOTE_REQUEST.getBytes();
            System.out.println("Enviando pela " + port );
            enviado = new DatagramPacket(
                buffer, buffer.length, InetAddress.getByName(host), port
            );
            
            socket.send( enviado );
            
            socket.close();
        } catch (SocketException ex) {
            ex.printStackTrace();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
