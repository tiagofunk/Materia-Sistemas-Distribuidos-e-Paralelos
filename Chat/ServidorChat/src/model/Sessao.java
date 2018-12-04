package model;

import java.util.ArrayList;
import java.util.List;

public class Sessao {
    
    private List<Contato> listaContatos = new ArrayList<>();
    
    private List<ObservadorSessao> listaObs = new ArrayList<>();

    public Sessao() {
    }
    
    public void adicionarObservador(ObservadorSessao obs){
        listaObs.add( obs );
    }
    
    public synchronized void adicionarContato( Contato c ){
        c.setOnline(true);
        c.setTempoUltimaConexao( System.currentTimeMillis() );
        int posicao = -1;
        for (int i = 0; i < listaContatos.size(); i++) {
            if( c.getToken().equals( listaContatos.get( i ).getToken() ) ){
                posicao = i;
            }
        }
        if( posicao == -1 ){
            listaContatos.add( c );
            for(ObservadorSessao obs: listaObs){
                obs.avisarConexao( c.getToken() );
            }
        }else{
            listaContatos.set( posicao, c );
        }
    }
    
    public synchronized void atualizarContato(String token){
        Contato c;
        for (int i = 0; i < listaContatos.size(); i++) {
            c = listaContatos.get( i );
            if( c.getToken().equals( token ) ){
                c.setOnline( true );
                c.setTempoUltimaConexao( System.currentTimeMillis() );
                break;
            }
        }
    }
    
    public synchronized void removerContato( Contato c ){
        for (int i = 0; i < listaContatos.size(); i++) {
            if( c.getToken().equals( listaContatos.get( i ).getToken() ) ){
                listaContatos.remove( i );
                for(ObservadorSessao obs: listaObs){
                    obs.avisarDesconexao( c.getToken() );
                }
                break;
            }
        }
    }
    
    public void verificar(){
        long tempoAtual;
        for (int i = 0; i < listaContatos.size(); i++) {
            tempoAtual = System.currentTimeMillis();
            if( (tempoAtual - listaContatos.get( i ).getTempoUltimaConexao() ) 
                    > Constantes.TEMPO_ESPERA ){
                removerContato( listaContatos.get( i ) );
            }
        }
    }
    
    public String gerarRelatorio(){
        String s = "Token\t\tOnline\t\tTempo Ultima conexao\n";
        Contato c;
        for (int i = 0; i < listaContatos.size(); i++) {
            c = listaContatos.get( i );
            s += c.getToken() + "\t\t" + c.isOnline() + "\t\t" 
                + (System.currentTimeMillis() - c.getTempoUltimaConexao()) + "\n";
        }
        return s+"\n\n\n";
    }
    
    public String getIp(String token){
        for (int i = 0; i < listaContatos.size(); i++) {
            if( listaContatos.get( i ).getToken().equals( token ) ){
                return listaContatos.get( i ).getIp();
            }
        }
        return null;
    }
    
    public String getPorta(String token){
        for (int i = 0; i < listaContatos.size(); i++) {
            if( listaContatos.get( i ).getToken().equals( token ) ){
                return listaContatos.get( i ).getPorta();
            }
        }
        return null;
    }
}
