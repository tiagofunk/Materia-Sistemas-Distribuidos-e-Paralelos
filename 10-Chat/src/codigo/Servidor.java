package codigo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) throws IOException, InterruptedException {
        int porta = 56000;
        Socket conn = null;
        ServerSocket server = new ServerSocket(porta);
        server.setReuseAddress(true);
        PrintWriter out = null;
        BufferedReader in = null;
        
        System.out.println("Aguardando conexao de cliente ... ");
        conn = server.accept();
        System.out.println("Conexao estabelecida. " + conn.getInetAddress().getHostAddress() + " Enviando dados ...");
        out = new PrintWriter(conn.getOutputStream(), true);

        while (true) {
            System.out.print("\nDigite: ");
            String texto = new Scanner(System.in).nextLine();
            out.println(texto);

            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String linha = in.readLine();
            System.out.print(conn.getInetAddress().getHostAddress() + " disse: " + linha);
        }

    }
}
