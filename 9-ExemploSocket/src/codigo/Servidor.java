package codigo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws IOException, InterruptedException {
        int porta = 56000;
        Socket conn = null;
        ServerSocket server = new ServerSocket(porta);
        server.setReuseAddress(true);
        PrintWriter out = null;

        while (true) {
            try {
                System.out.println("Aguardando conexao de cliente ... ");
                conn = server.accept();
                System.out.println("Conexao estabelecida. " + conn.getInetAddress().getHostAddress() + " Enviando dados ...");
                out = new PrintWriter(conn.getOutputStream(), true);

                String[] texto = {"111", "222", "333"};
                for (int i = 0; i < texto.length; i++) {
                    Thread.sleep(1000);
                    out.println(texto[i]);
                }
            }finally{
                conn.close();
                if( out != null ){
                    out.close();
                }
                System.out.println("Conexao fechada.");
            }
        }

    }
}
