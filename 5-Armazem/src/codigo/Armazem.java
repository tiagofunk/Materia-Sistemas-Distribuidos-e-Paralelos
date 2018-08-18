package codigo;

public abstract class Armazem {
    
    protected String[] itens = new String[ 10 ];
    protected int posicaoLivre = 0;
    
    public abstract void addItem( String item );
    public abstract void imprimir();
}
