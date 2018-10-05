package codigo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Servidor {
    public static void main(String[] args) {
        int porta = 56000;
        DatagramSocket socket = null;
        
        try {
            socket = new DatagramSocket(porta);
            socket.setReuseAddress(true);
            while( true ){
                System.out.println("Aguardando dados de cliente...");
                byte[] bufferRecepcao = new byte[256];
                DatagramPacket pkgRecebido = new DatagramPacket(bufferRecepcao, bufferRecepcao.length);
                
                socket.receive(pkgRecebido);
                
                String dadosRecebidos = new String( pkgRecebido.getData() );
                System.out.println("Recebido: " + dadosRecebidos );
                System.out.println("Enviando mensagem ao cliente...");
                String dadosEnviar = "Voce me enviou a mensagem: " + dadosRecebidos;
                byte[] bufferEnvio = dadosEnviar.getBytes();
                DatagramPacket pkgEnviar = new DatagramPacket(
                    bufferEnvio, bufferEnvio.length, pkgRecebido.getAddress(), pkgRecebido.getPort()
                );
                
                socket.send(pkgEnviar);
            }
        } catch (SocketException ex) {
            System.out.println("SocketException");
        } catch (IOException ex) {
            System.out.println("IOException");
        }finally{
            System.out.println("Encerrando DatagramSocket");
            if( socket != null ){
                socket.close();
            }
        }
    }
}
