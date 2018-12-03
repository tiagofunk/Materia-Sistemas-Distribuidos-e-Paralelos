package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DaoContato {
    
    public final static String ARQUIVO = "contato.txt";
    
    public static void salvarContao(String token) throws IOException{
        BufferedWriter bw = new BufferedWriter( new FileWriter(ARQUIVO, true) );
        bw.write(token);
        bw.newLine();
        bw.close();
    }
    
    public static List<String> lerContatos() throws FileNotFoundException, IOException{
        String linha;
        List<String> tokens = new ArrayList<>();
        BufferedReader br = new BufferedReader( new FileReader(ARQUIVO) );
        
        while( (linha = br.readLine()) != null ){
            tokens.add( linha );
        }
        
        return tokens;
    }
}
