package model;

import java.util.ArrayList;
import java.util.List;

public class Conversa {

    private Contato contato;
    private List<Mensagem> listaMensagens = new ArrayList<>();

    public Conversa(Contato contato) {
        this.contato = contato;
    }

    public void adicionarMensagem(Mensagem m) {
        listaMensagens.add(m);
    }

    public List<Mensagem> getListaMensagens() {
        return listaMensagens;
    }

    public Contato getContato() {
        return contato;
    }

}
