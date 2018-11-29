package server;

public interface ObservadorConexao {
    
    public void encaminharMensagem( String ip, String mensagem );
    
    public void avisarErroIOException();
}
