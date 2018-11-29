package server;

public interface ObservadorConexao {
    
    public void encaminharMensagem( String ip, int porta, String mensagem );
    
    public void avisarErroIOException();
}
