package codigo;

public class Armazem {
    
    private String[] itens = new String[ 10 ];
    private int posicaoLivre = 0;
    
    public void addItem( String item ){
        itens[ posicaoLivre ] = item;
        posicaoLivre++;
    }
    
    public void imprimir(){
        for( int i = 0; i < itens.length; i++ ){
            System.out.println( i + "-" + itens[ i ] );
        }
    }
}
