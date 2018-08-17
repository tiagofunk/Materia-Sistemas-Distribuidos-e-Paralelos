package Principal;

public class Principal {
    
    private static final String CAMINHO_1 = "/home/tiago/Repositorios/Github/DistribuidosParalelos/Arquivos/1-CasosTesteSomatorioMatrizes/caso1/A.TXT";
    private static final String CAMINHO_2 = "/home/tiago/Repositorios/Github/DistribuidosParalelos/Arquivos/1-CasosTesteSomatorioMatrizes/caso2/A.TXT";
    private static final String CAMINHO_3 = "/home/tiago/Repositorios/Github/DistribuidosParalelos/Arquivos/1-CasosTesteSomatorioMatrizes/caso3/A.TXT";
    
    public static void main(String[] args) {
        comThread();
    }
    
    public static void semThread(){
        //        int linhas = 3;
        int linhas = 10000;
        SomaLinhaSemThread sl = new SomaLinhaSemThread( buscarLink( 1 ) );
        
        for( int i = 1; i <= linhas; i++ ){
            System.out.printf( "%10d-%d\n", i, sl.somarLinha( i ) );
        }
    }
    
    public static void comThread(){
        //        int linhas = 3;
        int linhas = 3;

        for( int i = 1; i <= linhas; i++ ){
            new SomaLinhaComThread( buscarLink( 1 ), i ).start();
        }
    }
    
    public static String buscarLink( int caso ){
        String sistema = System.getProperty("os.name").toLowerCase();
        if( sistema.startsWith( "linux" ) ){
            return "/home/tiago/Repositorios/Github/DistribuidosParalelos/"
                    + "Arquivos/1-CasosTesteSomatorioMatrizes/caso" + 
                    caso + "/A.TXT";
        }if( sistema.startsWith( "Windows" ) ){
            return "C:\\Users\\10516125940\\Documents\\GitHub\\DistribuidosParalelos"
                    + "\\Arquivos\\1-CasosTesteSomatorioMatrizes/caso" +
                    caso + "\\A.txt";
        }
        throw new RuntimeException( "Tipo de sistema não suportado" );
    }
}
