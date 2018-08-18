package codigo;

public class Produtor extends Thread {
    
    private Armazem armazem;
    private int itensProduzir;

    public Produtor(Armazem armazem, int itensProduzir) {
        this.armazem = armazem;
        this.itensProduzir = itensProduzir;
    }
    
    @Override
    public void run(){
        for( int i = 0; i < itensProduzir; i++ ){
            String item = "p" + this.getId() + "_i" + i;
            armazem.addItem( item );
        }
    }
}
