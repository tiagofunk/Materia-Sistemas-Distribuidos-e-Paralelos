package Principal;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
    
    private static Conexao conexao = new Conexao();
    
    private Conexao(){
    }
    
    public static Conexao getInstance(){
        return conexao;
    }
    
    private final int PORTA = 56000;
    private byte[] buffer = new byte[256];
    
    private List<ObservadorConexao> listaObs = new ArrayList<>();
    
    public void addObservador( ObservadorConexao obs ){
        listaObs.add( obs );
    }
    
    public void removeObservador( ObservadorConexao obs ){
        listaObs.remove( obs );
    }
    
    public void receber(){
        DatagramSocket socket;
        DatagramPacket recebido;
        
        try {
            socket = new DatagramSocket(PORTA);
            socket.setReuseAddress(true);
            System.out.println("Minha porta: " + PORTA);
            
            recebido = new DatagramPacket(buffer, buffer.length);
            socket.receive(recebido);
            String mensagemRecebida = new String( recebido.getData() );
            mensagemRecebida = mensagemRecebida.trim();

            System.out.println("Recebido: " + mensagemRecebida );
            for (ObservadorConexao o: listaObs) {
                o.informarMensagem(mensagemRecebida, recebido.getAddress().toString(), recebido.getPort());
            }
            
            socket.close();
            
        } catch (SocketException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviar( String mensagem, String host, int porta ){
        DatagramSocket socket;
        DatagramPacket enviado;
        
        try {
            socket = new DatagramSocket();
            System.out.println("Enviando: " + mensagem);
            
            buffer = mensagem.getBytes();
            
            enviado = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(host), porta);
            socket.send(enviado);
            
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
