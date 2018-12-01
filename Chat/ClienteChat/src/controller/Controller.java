package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Constantes;
import persistence.LeitorConfiguracoes;
import server.Conexao;

public class Controller {
    
    private static String token;
    private static String hash;
    private static String senha;
    private static String nome;
    private static String telefone;
    
    private static String ip;
    private static String porta;
    
    private List<ObservadorTelaNovoUsuario> listaObsTelaNovoUsuario = new ArrayList<>();
    private List<ObservadorTelaPrincipal> listaObsTelaPrincipal = new ArrayList<>();
    
    public void addObservadorTelaNovoUsuario(ObservadorTelaNovoUsuario obs){
        listaObsTelaNovoUsuario.add(obs);
    }
    
    public void addObservadorTelaPrincipal(ObservadorTelaPrincipal obs){
        listaObsTelaPrincipal.add(obs);
    }

    public void criarNovoUsuario(String senha, String nome, String telefone) {
        this.senha = senha;
        this.nome = nome;
        this.telefone = telefone;
        
        String dadosServidor[];
        try {
            String meuIP = LeitorConfiguracoes.lerIpServidor();
            int porta = LeitorConfiguracoes.lerPortaServidor();
            dadosServidor = LeitorConfiguracoes.lerDadosServidorRemoto();
            Conexao conexao = new Conexao(dadosServidor[0], dadosServidor[1]);
            Thread.sleep(2000);
            conexao.enviar(meuIP +";"+ porta +":"+ Constantes.CRIAR_USUARIO+":"+senha+";"+nome+";"+telefone);
            conexao.fecharConexao();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void autenticarUsuario(String token, String senha) {
        String dadosServidor[];
        try {
            String meuIP = LeitorConfiguracoes.lerIpServidor();
            int porta = LeitorConfiguracoes.lerPortaServidor();
            dadosServidor = LeitorConfiguracoes.lerDadosServidorRemoto();
            Conexao conexao = new Conexao(dadosServidor[0], dadosServidor[1]);
            conexao.enviar(meuIP +";"+ porta +":"+ Constantes.AUTENTICAR_USUARIO+":"+token+";"+senha);
            conexao.fecharConexao();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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

    public void salvarUsuario(String token) {
        this.token = token;
        try {
            LeitorConfiguracoes.salvarUsuario(token, senha, nome, telefone);
            for(ObservadorTelaNovoUsuario obs : listaObsTelaNovoUsuario){
                obs.fechar();
            }
            for(ObservadorTelaPrincipal obs: listaObsTelaPrincipal){
                obs.inserirDadosUsuario(token, nome, telefone);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.autenticarUsuario(token, senha);
    }

    void sucessoAutenticacao() {
        for(ObservadorTelaNovoUsuario obs: listaObsTelaNovoUsuario){
            obs.fechar();
        }
        try {
            String[] dadosUsuario = LeitorConfiguracoes.lerDadosUsuario();
            
            for(ObservadorTelaPrincipal obs: listaObsTelaPrincipal){
                obs.inserirDadosUsuario(dadosUsuario[0], dadosUsuario[2], dadosUsuario[3]);
                obs.aparecer();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
