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

    public void salvarUsuario(String token) {
        System.out.println("entrando");
        this.token = token;
        try {
            LeitorConfiguracoes.salvarUsuario(token, senha, nome, telefone);
            System.out.println("salvou");
            for(ObservadorTelaNovoUsuario obs : listaObsTelaNovoUsuario){
                obs.sucesso();
            }
            System.out.println( listaObsTelaNovoUsuario.size() );
            for(ObservadorTelaPrincipal obs: listaObsTelaPrincipal){
                obs.aparecer();
                obs.inserirDadosUsuario(token, nome, telefone);
            }
            System.out.println( listaObsTelaPrincipal.size() );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
