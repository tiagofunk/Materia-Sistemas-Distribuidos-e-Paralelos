package model;

public class Contato {
    
    private String token;
    private String senha;
    private String nome;
    private String telefone;
    
    private String ip;
    private String porta;
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
    
}
