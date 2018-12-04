package controller;

import java.util.List;
import model.Conversa;

public interface ObservadorTelaPrincipal {
    
    public void aparecer();
    public void inserirDadosUsuario(String token, String nome, String telefone);
    
    public void atualizarConversas( List<Conversa> listaConversas );

    public void carregarConversa(Conversa conversa);

    public void avisarErroEnviarMensagem();
}
