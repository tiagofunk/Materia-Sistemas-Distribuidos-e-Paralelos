package Codigo;

public class Principal {

    public static void main(String[] args) {
        comThread( 2 );
    }
    
    public static void semThread(){
        final int n = 250;
        int[] nomes = new int[n];
        for (int i = 0; i < n; i++) {
            nomes[i] = i;
        }
        ContarSemThreads c = new ContarSemThreads("realize", nomes);
        System.out.println(c.contarPalavras());
    }
    
    public static void comThread( int numeroThreads ){
        int inicio, fim, total = 10;
        int intervalo = total/ numeroThreads;
//        List<ContarComThreads> lista = new ArrayList<>();
        
        for( int i = 0; i < numeroThreads; i++ ){
            inicio = ( i * intervalo );
            fim = (i+1) * intervalo;
            
            new ContarComThreads("realize", inicio, fim).start();
        }
    }
}
