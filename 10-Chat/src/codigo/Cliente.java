package codigo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {
        String endereco = "10.60.184.4";
//        String endereco = "127.0.0.1";
        int porta = 56000;

        Socket conn = null;
        PrintWriter out = null;
        BufferedReader in = null;

        System.out.println("Aguardando conexao de servidor ... ");
        conn = new Socket(endereco, porta);

        in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        out = new PrintWriter(conn.getOutputStream(), true);

        while (true) {

            String leitura = in.readLine();
            System.out.println("Servidor falou: " + leitura);

            System.out.print("Digite: ");
            String texto = new Scanner(System.in).nextLine();
            out.println(texto);
        }
    }
}
