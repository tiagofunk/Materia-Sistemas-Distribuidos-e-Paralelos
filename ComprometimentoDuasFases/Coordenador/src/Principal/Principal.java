package Principal;

import java.util.List;
import java.util.Scanner;

public class Principal {
    
    public static void main(String[] args) {
        System.out.print("Tecle ENTER para começar: ");
        new Scanner( System.in ).nextLine();
        
        System.out.println("INIT");
        
        CriadorConexoes cc = new CriadorConexoes();
        List<SocketThread> listaConexoes = cc.criarConexoes();
    }
    
}
