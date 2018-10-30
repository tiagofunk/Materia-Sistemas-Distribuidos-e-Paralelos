package Principal;

import java.util.Scanner;

public class Principal {
    
    public static void main(String[] args) {
        System.out.print("Tecle ENTER para começar: ");
        new Scanner( System.in ).nextLine();
        
        System.out.println("INIT");
        
        ProcessadorMensagem pm = new ProcessadorMensagem();
        pm.setResposta("sim");
        
        Conexao c = Conexao.getInstance();
        c.addObservador( pm );
        
        c.receber();
    }
    
}
