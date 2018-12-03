package model;

import java.util.ArrayList;
import java.util.List;

public class Contato {
    
    private String token;
    private String senha;
    private String nome;
    private String telefone;
    private List<String> listaContatos = new ArrayList<>();
    
    private String ip;
    private String porta;
    private long tempoUltimaConexao;
    private boolean online;

    public Contato() {
    }

    public Contato(String token, String senha, String nome, String telefone) {
        this.token = token;
        this.senha = senha;
        this.nome = nome;
        this.telefone = telefone;
    }

    public Contato(String token, String ip, String porta, boolean online) {
        this.token = token;
        this.ip = ip;
        this.porta = porta;
        this.online = online;
    }

    public Contato(String token, String senha, String nome, String telefone, String ip, String porta, boolean online) {
        this.token = token;
        this.senha = senha;
        this.nome = nome;
        this.telefone = telefone;
        this.ip = ip;
        this.porta = porta;
        this.online = online;
    }
    
    public void adicionarContato( String token ){
        listaContatos.add( token );
    }
    
    public void removerContato( String token ){
        listaContatos.remove( token );
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public long getTempoUltimaConexao() {
        return tempoUltimaConexao;
    }

    public void setTempoUltimaConexao(long tempoUltimaConexao) {
        this.tempoUltimaConexao = tempoUltimaConexao;
    }

    @Override
    public String toString() {
        return "Contato{" + "token=" + token + ", senha=" + senha + ", nome=" + nome + ", telefone=" + telefone + ", listaContatos=" + listaContatos + ", ip=" + ip + ", porta=" + porta + ", tempoUltimaConexao=" + tempoUltimaConexao + ", online=" + online + '}';
    }
    
}
