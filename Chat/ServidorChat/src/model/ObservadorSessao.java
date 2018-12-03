package model;

public interface ObservadorSessao {
    
    public void avisarConexao(String token);
    public void avisarDesconexao(String token);
}
