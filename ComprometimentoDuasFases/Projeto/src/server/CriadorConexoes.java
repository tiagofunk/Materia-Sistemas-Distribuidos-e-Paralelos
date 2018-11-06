package server;

import utils.LeitorConfiguracoes;
import server.ConexaoAtiva;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CriadorConexoes {
    
    public List<Conexao> criarConexoes(int timeout) throws IOException{
        List<Conexao> listaConexao = new ArrayList<>();
        
        String[][] valoresConexao = new LeitorConfiguracoes().lerConfiguracoesGlobais();
        for (int i = 0; i < valoresConexao.length; i++) {
            ConexaoAtiva st = new ConexaoAtiva(timeout, valoresConexao[i][0], valoresConexao[i][1]);
            listaConexao.add( st );
        }
        
        return listaConexao;        
    }
}
