package controller;

import server.ObservadorConexao;

public class ProcessadorMensagens implements ObservadorConexao{
    
    Controller controle;

    public ProcessadorMensagens(Controller controle) {
        this.controle = controle;
    }

    @Override
    public void encaminharMensagem(String ip, int porta, String mensagem) {
        String[] valores;
        String[] partes = mensagem.trim().split(":");
        
        switch( partes[0] ){
            case Constantes.CRIAR_USUARIO:
                valores = partes[1].split(";");
                if( valores.length != 3 ){
                    try {
                        throw new Exception(
                            "criar_usuario não possue três parâmetros: "
                            + mensagem );
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }else{
                    System.out.println("Vamos criar o usuário.");
                    controle.criarNovoUsuario( valores[0], valores[1], valores[2], ip, porta );
                }
                break;
                
            case Constantes.AUTENTICAR_USUARIO:
                valores = partes[1].split(";");
                if( valores.length != 2 ){
                    try {
                        throw new Exception(
                            "autenticar_usuario não possue dois parâmetros: "
                            + mensagem );
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }else{
                    controle.autenticarUsuario( valores[0], valores[1] );
                }
                break;
                
            case Constantes.ADICIONAR_USUARIO:
                valores = partes[1].split(";");
                if( valores.length != 3 ){
                    try {
                        throw new Exception(
                            "adicionar_contato não possue três parâmetros: "
                            + mensagem );
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }else{
                    controle.adicionarContato( valores[0], valores[1], valores[2] );
                }
                break;
                
            case Constantes.INFORMAR_STATUS_CONEXAO:
                valores = partes[1].split(";");
                if( valores.length != 2 ){
                    try {
                        throw new Exception(
                            "informar_status_conexao não possue dois parâmetros: "
                            + mensagem );
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }else{
                    controle.informarStatusContatos( valores[0], valores[1] );
                }
                break;
                
            case Constantes.ALTERAR_DADOS:
                valores = partes[1].split(";");
                if( valores.length != 4 ){
                    try {
                        throw new Exception(
                            "alterar_dados não possue dois parâmetros: "
                            + mensagem );
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }else{
                    controle.alterarDados(valores[0], valores[1], valores[2], valores[3] );
                }
                break;
                
            case Constantes.CONFIRMAR_HASH:
                valores = partes[1].split(";");
                if( valores.length != 2 ){
                    try {
                        throw new Exception(
                            "confirmar_hash não possue dois parâmetros: "
                            + mensagem );
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }else{
                    controle.confirmarHash( valores[0], valores[1] );
                }
                break;
        }
    }

    @Override
    public void avisarErroIOException() {
        throw new UnsupportedOperationException("avisarErroIOException");
    }

}
