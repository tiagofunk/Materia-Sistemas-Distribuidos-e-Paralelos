package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import model.Contato;

public class DaoCliente {
    
    private static final String DAO = "cliente.txt";

    public static void salvarCliente(Contato c) throws IOException {
        BufferedWriter bw = new BufferedWriter( new FileWriter(DAO, true) );
        bw.write(c.getToken()+";"+c.getSenha()+";"+c.getNome()+";"+c.getTelefone());
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
    
    public static Contato buscarContato(String token) throws FileNotFoundException, IOException{
        String linha;
        String[] valores = null;
        Contato c = null;
        BufferedReader br = new BufferedReader( new FileReader( DAO ) );
        
        while( ( linha = br.readLine() ) != null ){
            valores = linha.split(";");
            if( token.equals( valores[0] ) ){
                c = new Contato(valores[0], valores[1], valores[2], valores[3] );
                break;
            }
        }
        br.close();
        
        return c;
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
        br.close();
        
        if( linha == null && valores == null){
            return "1";
        }else{
            return String.valueOf( Integer.parseInt( valores[0] ) + 1 );
        }
    }

}
