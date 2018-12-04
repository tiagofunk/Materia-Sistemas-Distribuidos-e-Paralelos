package model;

import java.util.ArrayList;
import java.util.List;
import server.Conexao;

public class VerificadorConexoes extends Thread{
    
    private Sessao sessao;
    private List<ObservadorVerificarConexao> listaObs = new ArrayList<>();

    public VerificadorConexoes(Sessao sessao) {
        this.sessao = sessao;
    }

    public void adicionarObservador(ObservadorVerificarConexao obs){
        listaObs.add( obs );
    }
    
    @Override
    public void run() {
        String s;
        Conexao c;
        try{
            while( true ){
                Thread.sleep( Constantes.TEMPO_ESPERA );
                sessao.verificar();
                s = sessao.gerarRelatorio();
                for(ObservadorVerificarConexao obs : listaObs){
                    obs.informarDados(s);
                }
            }
        }catch (InterruptedException ex ){
            ex.printStackTrace();
        }
    }
    
    
}
