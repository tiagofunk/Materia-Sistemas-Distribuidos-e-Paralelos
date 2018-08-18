package codigo;

public class ArmazemSimples extends Armazem{
    
    @Override
    public void addItem( String item ){
        itens[ posicaoLivre ] = item;
        posicaoLivre++;
    }
    
    @Override
    public void imprimir(){
        for( int i = 0; i < itens.length; i++ ){
            System.out.println( i + "-" + itens[ i ] );
        }
    }
}
