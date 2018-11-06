package server;

import model.TipoAgente;

public interface ObservadorConexao {
    
    public void encaminharMensagem( String mensagem, Conexao con );

    public void avisarTimeout(Conexao con);
}
