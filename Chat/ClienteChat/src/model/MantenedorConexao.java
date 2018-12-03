package model;

import java.io.IOException;
import persistence.LeitorConfiguracoes;
import server.Conexao;

public class MantenedorConexao extends Thread{
    
    private String token;

    public MantenedorConexao(String token) {
        this.token = token;
    }
    
    @Override
    public void run() {
        while(true){
            try {
                String[] dadosServidorRemoto
                        = LeitorConfiguracoes.lerDadosServidorRemoto();
                String ipDestino = LeitorConfiguracoes.lerIpServidor();
                int portaDestino = LeitorConfiguracoes.lerPortaServidor();
                Conexao c = new Conexao(
                        dadosServidorRemoto[0], dadosServidorRemoto[1]);

                Thread.sleep(Constantes.TEMPO_ESPERA);
                c.enviar(ipDestino + ";" + portaDestino + ":" + Constantes.INFORMAR_STATUS_CONEXAO + ":" + token);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
}
