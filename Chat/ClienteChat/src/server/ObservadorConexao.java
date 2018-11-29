package server;

public interface ObservadorConexao {
    
    public void encaminharMensagem( String mensagem );
    
    public void avisarErroIOException();
}
