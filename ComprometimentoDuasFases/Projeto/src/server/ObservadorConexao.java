package server;

public interface ObservadorConexao {
    
    public void encaminharMensagem( String mensagem, ConexaoPassiva con );
    public void encaminharMensagem( String mensagem, ConexaoAtiva con );
}
