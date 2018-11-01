package server;

import utils.LeitorConfiguracoes;
import server.ConexaoAtiva;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CriadorConexoes {
    
    public List<ConexaoAtiva> criarConexoes() throws IOException{
        List<ConexaoAtiva> listaConexao = new ArrayList<ConexaoAtiva>();
        
        String[][] valoresConexao = new LeitorConfiguracoes().lerConfiguracoes();
        for (int i = 0; i < valoresConexao.length; i++) {
            ConexaoAtiva st = new ConexaoAtiva(valoresConexao[i][0], valoresConexao[i][1]);
            listaConexao.add( st );
        }
        
        return listaConexao;        
    }
}
