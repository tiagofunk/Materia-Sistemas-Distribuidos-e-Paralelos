package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DaoCliente {
    
    private static final String DAO = "cliente.txt";

    public static void salvarCliente(String token, String nome, String telefone, String senha) throws IOException {
        BufferedWriter bw = new BufferedWriter( new FileWriter(DAO, true) );
        bw.write(token+";"+senha+";"+nome+";"+telefone);
        bw.newLine();
        bw.close();
    }

    public static boolean pesquisarCliente(String nome, String telefone) throws FileNotFoundException, IOException {
        String linha;
        String[] valores;
        BufferedReader br = new BufferedReader( new FileReader( DAO ) );
        
        while( (linha = br.readLine() ) != null ){
            valores = linha.split(";");
            if( valores[1].equals( nome ) && valores[2].equals( nome ) ){
                return true;
            }
        }
        return false;
    }

    public static void atualizarCliente(String token, String nome, String telefone, String senha) {

    }

    public static String gerarToken() throws IOException {
        String linha;
        String[] valores = null;
        BufferedReader br = new BufferedReader( new FileReader( DAO ) );
        
        while( (linha = br.readLine() ) != null ){
            valores = linha.split(";");
        }
        
        if( linha == null && valores == null){
            return "1";
        }else{
            return String.valueOf( Integer.parseInt( valores[0] ) + 1 );
        }
    }

}
