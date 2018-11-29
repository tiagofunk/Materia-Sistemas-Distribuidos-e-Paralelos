package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.DaoCliente;
import persistence.LeitorConfiguracoes;
import server.Conexao;
import server.ObservadorConexao;
import server.Servidor;

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
        
        ProcessadorMensagens pm = new ProcessadorMensagens( new Controller() );
        
        List<ObservadorConexao> listaObs = new ArrayList<>();
        listaObs.add( pm );
        
        Servidor servidor = new Servidor( porta, listaObs );
        try {
            servidor.iniciar();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void criarNovoUsuario( String senha, String nome, String telefone, String ip, String porta) {
        try {
            boolean achou = DaoCliente.pesquisarCliente(nome, telefone);
            if( !achou ){
                String token = DaoCliente.gerarToken();
                DaoCliente.salvarCliente(token, nome, telefone, senha);
                Thread.sleep(2000);
                System.out.println( ip + ":" + porta);
                Conexao c = new Conexao(ip, porta);
                c.enviar(Constantes.DEVOLVE_TOKEN+":"+token);
                c.fecharConexao();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void autenticarUsuario( String token, String senha ) {
        
    }

    public void adicionarContato(String token, String senha, String tokenNovoUsuario) {

    }

    public void confirmarHash(String token, String hash) {

    }

    public void informarStatusContatos(String token, String senha) {

    }

    public void alterarDados(String token, String senha, String nome, String telefone) {

    }
}
