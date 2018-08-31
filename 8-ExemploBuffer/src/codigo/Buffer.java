package codigo;

public abstract class Buffer {
    
    protected String[] listaItens;
    protected int quantidade = 0;
    protected int inicio = 0;
    protected int fim = 0;
    
    public abstract void addItem( String item ) throws Exception;
    
    public abstract String removeItem() throws Exception;
}
