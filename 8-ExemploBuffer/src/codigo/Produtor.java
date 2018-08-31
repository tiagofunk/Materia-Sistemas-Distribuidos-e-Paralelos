package codigo;

public class Produtor extends Thread{
    
    private int contador;
    private Buffer buffer;

    public Produtor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        String item;
        boolean colocou;
        while (true) {
            item = produzir();
            colocou = false;
            do{
                try {
                    buffer.addItem( item );
                    colocou = true;
                    System.out.println("Prod: " + getId() + " colocou item " + item );
                } catch (Exception e) {
                    System.out.println("Prod: " + getId() + " Item:  " + item + " BUFFER CHEIO. Aguardando." );
                    try {
                        sleep( 100 );
                    } catch (InterruptedException ie) {
                        System.out.println("Sleep interrompido. Ignorar.");
                        e.printStackTrace();
                    }
                }
            }while ( !colocou );
        }
    }

    private String produzir() {
        try {
            sleep( 250 );
        } catch (InterruptedException e) {
            System.out.println("Sleep interrompido. Ignorar.");
            e.printStackTrace();
        }
        
        contador++;
        String item = "p" + this.getId() + "_i" + contador;
        System.out.println("Prod: " + getId() + " produziu item " + item );
        return item;
    }
    
    
    
}
