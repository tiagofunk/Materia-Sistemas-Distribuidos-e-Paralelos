package model;

import java.util.ArrayList;
import java.util.List;

public class Sessao {
    
    private List<Contato> listaContatos = new ArrayList<>();

    public Sessao() {
    }
    
    public synchronized void adicionarContato( Contato c ){
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
    
    public synchronized void removerContato( Contato c ){
        for (int i = 0; i < listaContatos.size(); i++) {
            if( c.getToken().equals( listaContatos.get( i ).getToken() ) ){
                listaContatos.remove( i );
            }
        }
    }
}
