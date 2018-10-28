package Principal;

import java.util.Scanner;

public class Principal {
    
    public static void main(String[] args) {
        System.out.print("Tecle ENTER para começar: ");
        new Scanner( System.in ).nextLine();
        
        System.out.println("INIT");
        
        Conexao c = new Conexao();
        c.iniciar();
    }
    
}
