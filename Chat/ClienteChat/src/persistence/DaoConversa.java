package persistence;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Contato;
import model.Conversa;

public class DaoConversa {
    
    private static final String ARQUIVO = "contato.txt";
    
    public static List<Conversa> lerConversas() throws FileNotFoundException, IOException{
        String linha;
        List<Conversa> listaConversas = new ArrayList<>();
        BufferedReader br = new BufferedReader( new FileReader( ARQUIVO ) );
        
        while( (linha = br.readLine()) != null ){
            listaConversas.add( new Conversa( new Contato(linha, false) ) );
        }
        
        return listaConversas;
    }
}
