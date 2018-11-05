package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Conexao implements Runnable{
    
    protected List<ObservadorConexao> listaObservadores = new ArrayList<>();
    
    public abstract void enviar( String mensagem ) throws IOException;
    public abstract void fecharConexao() throws IOException;
    
    public void addObservador( ObservadorConexao obs ){
        listaObservadores.add( obs );
    }
    
    public void removeObservador( ObservadorConexao obs ){
        listaObservadores.remove( obs );
    }
}
