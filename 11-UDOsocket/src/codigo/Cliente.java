package codigo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Cliente {
    
    public static void main(String[] args) {
        String endereco = "10.60.184.4";
//        String endereco = "127.0.0.1";
        int porta = 56001;
        DatagramSocket socket = null;
        
        try {
            socket = new DatagramSocket(porta);
            socket.setReuseAddress(true);
            
            String dadosEnviar = "nene";
            
            byte[] bufferEnvio = dadosEnviar.getBytes();
            DatagramPacket pkgEnviar = new DatagramPacket( 
                bufferEnvio, bufferEnvio.length, InetAddress.getByName(endereco), porta
            );
            
            socket.send( pkgEnviar );
            
            byte[] bufferRecepcao = new byte[256];
            DatagramPacket pkgRecebido = new DatagramPacket( bufferRecepcao, bufferRecepcao.length );
            
            socket.receive(pkgRecebido );
            String dadosRecebidos = new String( pkgRecebido.getData() );
            
            System.out.println("Recebido: " + dadosRecebidos );
        } catch (SocketException ex) {
            System.out.println("Erro: SocketException");
        } catch (UnknownHostException ex) {
            System.out.println("Erro: UnknownHostException");
        } catch (IOException ex) {
            System.out.println("Erro: IOException");
        }finally{
            System.out.println("Encerrado DatagramSocket");
            if( socket != null ){
                socket.close();
            }
        }
    }
}
