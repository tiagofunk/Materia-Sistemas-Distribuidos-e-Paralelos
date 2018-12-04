package controller;

import model.Constantes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Contato;
import model.ObservadorSessao;
import model.Sessao;
import model.VerificadorConexoes;
import persistence.DaoCliente;
import persistence.DaoContato;
import persistence.LeitorConfiguracoes;
import server.Conexao;
import server.ObservadorConexao;
import server.Servidor;
import view.Tela;

public class Controller implements ObservadorSessao{
    
    private static Sessao sessao;

    public static void main(String[] args) {
        Tela t = new Tela();
        t.setVisible(true);

        int porta = 0;
        try {
            porta = LeitorConfiguracoes.lerPortaServidor();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        
        Controller controle = new Controller();
        
        sessao = new Sessao();
        sessao.adicionarObservador(controle);
        
        VerificadorConexoes vc = new VerificadorConexoes( controle.getSessao() );
        vc.adicionarObservador( t );
        vc.start();
        
        ProcessadorMensagens pm = new ProcessadorMensagens( controle );
        
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
                Conexao conexao = new Conexao(ip, porta);
                conexao.enviar( Constantes.CONFIRMAR_AUTENTICACAO );
                conexao.fecharConexao();
                
                contato.setIp(ip);
                contato.setPorta(porta);
                sessao.adicionarContato( contato );
                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void adicionarContato(String token, String tokenNovoUsuario, String ip, String porta) {
        try {
            if( DaoContato.buscarContato(token, tokenNovoUsuario) ){
                // Se ele achou não faz nada.
            }else{
                DaoContato.adicionarContato(token, tokenNovoUsuario);
                
                Conexao conexao = new Conexao(ip, porta);
                conexao.enviar(Constantes.ADICIONAR_CONTATO+":"+tokenNovoUsuario);
                conexao.fecharConexao();
                
                conexao = new Conexao(sessao.getIp(tokenNovoUsuario),
                    sessao.getPorta(tokenNovoUsuario));
                conexao.enviar(Constantes.ADICIONAR_CONTATO+":"+token);
                conexao.fecharConexao();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void informarStatusContatos(String token) {
        sessao.atualizarContato( token );
    }

    public Sessao getSessao() {
        return sessao;
    }

    @Override
    public void avisarConexao(String token) {
        try {
            String ip, porta;
            String ipToken = sessao.getIp(token), portaToken = sessao.getPorta(token);
            Conexao conexao;
            List<String> contatos = DaoContato.lerTodosContatos( token );
            for(String s : contatos){
                ip = sessao.getIp( s );
                porta = sessao.getPorta( s );
                
                if( ip != null || porta != null ){
                    conexao = new Conexao( ip, porta );
                    conexao.enviar( Constantes.INFORMAR_STATUS_CONTATO + ":" +
                        token + ";" + ipToken + ";" + portaToken );
                    conexao.fecharConexao();
                    
                    conexao = new Conexao( ipToken, portaToken );
                    conexao.enviar( Constantes.INFORMAR_STATUS_CONTATO + ":" +
                        s + ";" + ip + ";" + porta );
                    conexao.fecharConexao();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void avisarDesconexao(String token) {
        try {
            System.out.println("entrou no avisar desconexao");
            String ip, porta;
            String ipToken = sessao.getIp(token), portaToken = sessao.getPorta(token);
            Conexao conexao;
            List<String> contatos = DaoContato.lerTodosContatos( token );
            for(String s : contatos){
                ip = sessao.getIp( s );
                porta = sessao.getPorta( s );
                
                if( ip != null || porta != null ){
                    conexao = new Conexao( ip, porta );
                    conexao.enviar( Constantes.INFORMAR_DESCONEXAO_CONTATO + ":" + token );
                    conexao.fecharConexao();
                    
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
