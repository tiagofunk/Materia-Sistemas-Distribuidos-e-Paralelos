package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Contato;
import model.Sessao;
import persistence.DaoCliente;
import persistence.LeitorConfiguracoes;
import server.Conexao;
import server.ObservadorConexao;
import server.Servidor;

public class Controller {
    
    private Sessao sessao = new Sessao();;

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
                DaoCliente.salvarCliente( new Contato( token, senha, nome, telefone ) );
                Thread.sleep(2000);
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

    public void autenticarUsuario( String token, String senha, String ip, String porta ) {
        try {
            Contato contato = DaoCliente.buscarContato( token );
            if( contato != null && contato.getSenha().equals( senha ) ){
                contato.setIp(ip);
                contato.setPorta(porta);
                sessao.adicionarContato( contato );
                
                Conexao conexao = new Conexao(ip, porta);
                conexao.enviar( Constantes.CONFIRMAR_AUTENTICACAO );
                conexao.fecharConexao();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
