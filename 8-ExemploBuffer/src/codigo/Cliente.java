package codigo;

public class Cliente extends Thread {
    
    private Buffer buffer;

    public Cliente(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        String item;
        while (true) {            
            try {
                item = buffer.removeItem();
                System.out.println("Cons: " + getId() + " retirou " + item );
                consumir( item );
            } catch (Exception e) {
                System.out.println("Cons: " + getId() + " BUFFER VAZIO. Aguardando.");
                try {
                    sleep( 100 );
                } catch (InterruptedException ie) {
                    System.out.println("Sleep interrompido. Ignorar.");
                    e.printStackTrace();
                }
            }
        }
    }
    
    private void consumir( String item ){
        System.out.println("Cons: " + getId() + " consumido " + item );
        try {
            sleep( 100 );
        } catch (Exception e) {
            System.out.println("Sleep interrompido. Ignorar.");
            e.printStackTrace();
        }
    }
}
