package codigo;

public class MinhaThread extends Thread{
    
    private final String DELIMITADOR = "-----------------";
    private final int id;
    private final int valor;
    private final Escritor escritor;

    public MinhaThread(int id, int valor, Escritor escritor) {
        this.id = id;
        this.valor = valor;
        this.escritor = escritor;
    }
    
    @Override
    public void run(){
        String s =
            DELIMITADOR + "\n" +
            System.currentTimeMillis() +
            "\nThread: " + id +
            "\nValor: " + valor + "\n" +
            DELIMITADOR + "\n";
        escritor.gravar( s );
    }
}
