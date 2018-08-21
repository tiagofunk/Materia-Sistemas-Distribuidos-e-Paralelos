package codigo;

public class MinhaThread extends Thread{
    
    private final int QUANTIDADE_NUMEROS = 10;
    private int tipoContador;
    private long[] valores;

    public MinhaThread(int tipoContador) {
        this.tipoContador = tipoContador;
        valores = new long[ QUANTIDADE_NUMEROS ];
    }
    
    @Override
    public void run(){
        switch (tipoContador){
            case 1:
                for( int i = 0; i < QUANTIDADE_NUMEROS; i++ ){
                    valores[ i ] = ContadorSequencialNormal.getInstance().next();
                }
                break;
            case 2:
                for( int i = 0; i < QUANTIDADE_NUMEROS; i++ ){
                    valores[ i ] = ContadorSequencialSemaforo.getInstance().next();
                }
                break;
            case 3:
                for( int i = 0; i < QUANTIDADE_NUMEROS; i++ ){
                    valores[ i ] = ContadorSequencialMonitor.getInstance().next();
                }
                break;
        }
        String s = "[";
        for( int i = 0; i < QUANTIDADE_NUMEROS; i++ ){
            s += valores[ i ] + ",";
        }
        s += "]";
        System.out.println( this.getId() + "-" + s );
    }
}
