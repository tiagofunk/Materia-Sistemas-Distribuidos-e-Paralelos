package model;

import controller.Constantes;
import java.util.ArrayList;
import java.util.List;

public class Sessao {
    
    private List<Contato> listaContatos = new ArrayList<>();

    public Sessao() {
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
            }
        }
    }
    
    public void verificar(){
        long tempoAtual;
        for (int i = 0; i < listaContatos.size(); i++) {
            tempoAtual = System.currentTimeMillis();
            if( (tempoAtual - listaContatos.get( i ).getTempoUltimaConexao() ) 
                    > Constantes.TEMPO_ESPERA ){
                listaContatos.remove( i );
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
}
