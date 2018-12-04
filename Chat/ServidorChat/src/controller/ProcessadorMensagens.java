package controller;

import model.Constantes;
import server.ObservadorConexao;

public class ProcessadorMensagens implements ObservadorConexao{
    
    Controller controle;

    public ProcessadorMensagens(Controller controle) {
        this.controle = controle;
    }

    @Override
    public void encaminharMensagem(String mensagem) {
        String[] valores;
        String[] partes = mensagem.trim().split(":");
        String[] destinatario = partes[0].split(";");
        
        switch( partes[1] ){
            case Constantes.CRIAR_USUARIO:
                valores = partes[2].split(";");
                if( valores.length != 3 ){
                    try {
                        throw new Exception(
                            "criar_usuario não possue três parâmetros: "
                            + mensagem );
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }else{
                    controle.criarNovoUsuario( 
                        valores[0], valores[1], valores[2], 
                        destinatario[0], destinatario[1]
                    );
                }
                break;
                
            case Constantes.AUTENTICAR_USUARIO:
                valores = partes[2].split(";");
                if( valores.length != 2 ){
                    try {
                        throw new Exception(
                            "autenticar_usuario não possue dois parâmetros: "
                            + mensagem );
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }else{
                    controle.autenticarUsuario(
                        valores[0], valores[1],
                        destinatario[0], destinatario[1]
                    );
                }
                break;
                
            case Constantes.ADICIONAR_CONTATO:
                valores = partes[2].split(";");
                if( valores.length != 2 ){
                    try {
                        throw new Exception(
                            "adicionar_contato não possue dois parâmetros: "
                            + mensagem );
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }else{
                    controle.adicionarContato(
                        valores[0], valores[1],
                        destinatario[0], destinatario[1] );
                }
                break;
                
            case Constantes.INFORMAR_STATUS_CONEXAO:
                valores = partes[2].split(";");
                if( valores.length != 1 ){
                    try {
                        throw new Exception(
                            "informar_status_conexao não possue um parâmetro: "
                            + mensagem );
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }else{
                    controle.informarStatusContatos( valores[0] );
                }
                break;
                
            default:
                System.out.println("Tipo de mensagem inválida: " + mensagem);
        }
    }

    @Override
    public void avisarErroIOException() {
        System.out.println("Conexão fechada inesperadamente.");
    }

}
