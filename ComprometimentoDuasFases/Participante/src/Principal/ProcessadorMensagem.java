package Principal;

public class ProcessadorMensagem implements ObservadorConexao{
    
    private String resposta;

    @Override
    public void informarMensagem(String mensagem, String host, int porta) {
        if( mensagem.equals( Constantes.VOTE_REQUEST ) ){
            System.out.println("foi");
            if( resposta.equalsIgnoreCase("yes") ){
                Conexao.getInstance().enviar(Constantes.VOTE_COMMIT, host, porta);
            }else{
                Conexao.getInstance().enviar(Constantes.VOTE_ABORT, host, porta);
            }
        }
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
    
}
