package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Constantes;
import persistence.LeitorConfiguracoes;
import server.Conexao;
import server.ObservadorConexao;
import server.Servidor;
import view.TelaNovoUsuario;

public class Controller {

    public static void main(String[] args) {
        int porta = 0;
        try {
            porta = LeitorConfiguracoes.lerPortaServidor();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        
        System.out.println( "Porta: " + porta );
        
        ProcessadorMensagens pm = new ProcessadorMensagens( new Controller() );
        
        List<ObservadorConexao> listaObs = new ArrayList<>();
        listaObs.add( pm );
        
        Servidor servidor = new Servidor( porta, listaObs );
        servidor.start();
        
        System.out.println("pronto");
        String dadosUsuario[];
        try {
            dadosUsuario = LeitorConfiguracoes.lerDadosUsuario();
            Controller c = new Controller();
        
            if( dadosUsuario[0].equals("null") ){
                TelaNovoUsuario t = new TelaNovoUsuario();
                t.setVisible(true);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    public void criarNovoUsuario(String senha, String nome, String telefone) {
        String dadosServidor[];
        try {
            dadosServidor = LeitorConfiguracoes.lerDadosServidor();
            Conexao conexao = new Conexao(dadosServidor[0], dadosServidor[1]);
            Thread.sleep(2000);
            conexao.enviar(Constantes.CRIAR_USUARIO+":"+senha+";"+nome+";"+telefone);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void autenticarUsuario() {

    }

    public void adicionarContato() {

    }

    public void conectarContato() {

    }

    public void informarStatusConexao() {

    }

    public void enviarMensagem() {

    }

    public void alterarDados() {

    }
}
