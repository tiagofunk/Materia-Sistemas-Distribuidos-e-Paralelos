package Principal;

import java.util.ArrayList;
import java.util.List;

public class CriadorConexoes {
    
    public List<SocketThread> criarConexoes(){
        List<SocketThread> listaConexao = new ArrayList<SocketThread>();
        
        String[][] valoresConexao = new LeitorConfiguracoes().lerConfiguracoes();
        for (int i = 0; i < valoresConexao.length; i++) {
            SocketThread st = new SocketThread(valoresConexao[i][0], valoresConexao[i][1]);
            st.start();
            listaConexao.add( st );
        }
        
        return listaConexao;        
    }
}
