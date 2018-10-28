package Principal;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
    
    private final int PORTA = 56001;
    private byte[] buffer = new byte[256];
    
    public void iniciar(){
        DatagramSocket socket;
        DatagramPacket recebido;
        
        try {
            socket = new DatagramSocket(PORTA);
            socket.setReuseAddress(true);
            
            recebido = new DatagramPacket(buffer, buffer.length);
            socket.receive(recebido);
            String mensagemRecebida = new String( recebido.getData() );

            System.out.println("Recebido: " + mensagemRecebida );
            
        } catch (SocketException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
