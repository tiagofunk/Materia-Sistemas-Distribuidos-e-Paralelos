package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Constantes;
import model.Contato;
import model.Conversa;
import model.MantenedorConexao;
import model.Mensagem;
import persistence.DaoContato;
import persistence.DaoConversa;
import persistence.LeitorConfiguracoes;
import server.Conexao;

public class Controller{
    
    private static String token;
    private static String senha;
    private static String nome;
    private static String telefone;
    
    private int indiceConversaAtual = 0;
    private List<Conversa> listaConversas = new ArrayList<>();
    
    private List<ObservadorTelaNovoUsuario> listaObsTelaNovoUsuario = new ArrayList<>();
    private List<ObservadorTelaPrincipal> listaObsTelaPrincipal = new ArrayList<>();
    
    public void addObservadorTelaNovoUsuario(ObservadorTelaNovoUsuario obs){
        listaObsTelaNovoUsuario.add(obs);
    }
    
    public void addObservadorTelaPrincipal(ObservadorTelaPrincipal obs){
        listaObsTelaPrincipal.add(obs);
    }

    public Controller() {
    }
    
    public void carregarConversas(){
        try {
            listaConversas = DaoConversa.lerConversas();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        for(ObservadorTelaPrincipal obs: listaObsTelaPrincipal){
            obs.atualizarConversas(listaConversas);
        }
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
        this.token = token;
        String dadosServidor[];
        try {
            String meuIP = LeitorConfiguracoes.lerIpServidor();
            int porta = LeitorConfiguracoes.lerPortaServidor();
            dadosServidor = LeitorConfiguracoes.lerDadosServidorRemoto();
            Conexao conexao = new Conexao(dadosServidor[0], dadosServidor[1]);
            conexao.enviar(meuIP +";"+ porta +":"+ Constantes.AUTENTICAR_USUARIO+":"+token+";"+senha);
            conexao.fecharConexao();
            
            this.iniciarMantenedoraConexao();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void adicionarContato( String tokenAdicionado ) {
        try {
            String meuIP = LeitorConfiguracoes.lerIpServidor();
            int porta = LeitorConfiguracoes.lerPortaServidor();
            String[] dadosServidor = LeitorConfiguracoes.lerDadosServidorRemoto();
            Conexao conexao = new Conexao(dadosServidor[0], dadosServidor[1]);
            conexao.enviar(meuIP+";"+porta+":"+Constantes.ADICIONAR_CONTATO+":"+token+";"+tokenAdicionado);
            conexao.fecharConexao();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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

    public void sucessoAutenticacao() {
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

    public void iniciarMantenedoraConexao() throws IOException {
        MantenedorConexao mc = new MantenedorConexao( token );
        mc.start();
    }

    public void salvarContato(String token) {
        try {
            DaoContato.salvarContao(token);
            
            Contato contato = new Contato( token, false);
            
            Conversa conversa = new Conversa(contato);
            listaConversas.add( conversa );
            
            for(ObservadorTelaPrincipal obs: listaObsTelaPrincipal){
                obs.atualizarConversas( listaConversas );
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void avisarConexaoContato(String token, String ip, String porta) {
        for(Conversa c:listaConversas){
            if( c.getContato().getToken().equals( token ) ){
                c.getContato().setIp(ip);
                c.getContato().setPorta( Integer.parseInt(porta) );
                c.getContato().setStatus( true );
                break;
            }
        }
        for(ObservadorTelaPrincipal obs:listaObsTelaPrincipal){
            obs.atualizarConversas(listaConversas);
        }
    }
    
    public void avisarDesconexaoContato(String token){
        for(Conversa c:listaConversas){
            if( c.getContato().getToken().equals( token ) ){
                c.getContato().setStatus( false );
                break;
            }
        }
        for(ObservadorTelaPrincipal obs:listaObsTelaPrincipal){
            obs.atualizarConversas(listaConversas);
        }
    }

    public void carregarConversas(int indiceLinha) {
        this.indiceConversaAtual = indiceLinha;
        for(ObservadorTelaPrincipal obs: listaObsTelaPrincipal){
            obs.carregarConversa( listaConversas.get(indiceLinha) );
        }
    }

    public void enviarMensagem(String mensagem) {
        Conversa conversaAtual = listaConversas.get( indiceConversaAtual );
        try {
            Conexao conexao = new Conexao( conversaAtual.getContato().getIp(), conversaAtual.getContato().getPorta() );
            conexao.enviar(Constantes.ENVIAR_MENSAGEM+":"+token+";"+mensagem);
            conexao.enviar(mensagem);
            conversaAtual.adicionarMensagem( new Mensagem(mensagem, true) );
            for(ObservadorTelaPrincipal obs:listaObsTelaPrincipal){
                obs.carregarConversa(conversaAtual);
            }
        } catch (IOException ex) {
            for(ObservadorTelaPrincipal obs:listaObsTelaPrincipal){
                obs.avisarErroEnviarMensagem();
            }
            ex.printStackTrace();
        }
    }

    public void receberMensagem(String token, String mensagem) {
        for(Conversa con:listaConversas){
            if( con.getContato().getToken().equals( token ) ){
                con.adicionarMensagem( new Mensagem(mensagem, false) );
                break;
            }
        }
        for(ObservadorTelaPrincipal obs:listaObsTelaPrincipal){
            obs.carregarConversa( listaConversas.get( indiceConversaAtual ) );
        }
    }

}
