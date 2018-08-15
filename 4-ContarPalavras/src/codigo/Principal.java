package codigo;

import java.util.ArrayList;
import java.util.List;

public class Principal {
    
    public static void main(String[] args) throws InterruptedException {
        String palavra = args[ 0 ];
        String numeroThreads = args[ 1 ];
        String caminho = buscarLink();
        
        if( numeroThreads.equals( "n" )){
            semThread(caminho, palavra);
        }else{
            comThread(caminho, palavra, Integer.parseInt( numeroThreads ) );
        }
    }
    
    public static void semThread( String caminho, String busca ){
        ContadorPalavrasSemThread c = new ContadorPalavrasSemThread(0, 250, caminho);
        c.contar( busca );
    }
    
    public static void comThread( String caminho, String busca, int numeroThreads ) throws InterruptedException{
        int soma = 0;
        List<ContadorPalavrasComThread> lista = new ArrayList<>();
        
        switch( numeroThreads ){
            case 1:
                lista.add( new ContadorPalavrasComThread(0, 250, busca, caminho) );
                break;
                
            case 2:
                System.out.println("Entrou");
                lista.add( new ContadorPalavrasComThread(0, 150, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(150, 250, busca, caminho) );
                break;
               
            case 3:
                System.out.println("Entrou");
                lista.add( new ContadorPalavrasComThread(0, 85, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(85, 170, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(170, 250, busca, caminho) );
                break;
                
            case 4:
                System.out.println("Entrou");
                lista.add( new ContadorPalavrasComThread(0, 60, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(60, 120, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(120, 180, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(180, 250, busca, caminho) );
                break;
            
            case 5:
                System.out.println("Entrou");
                lista.add( new ContadorPalavrasComThread(0, 50, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(50, 100, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(100, 150, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(150, 200, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(200, 250, busca, caminho) );
                break;
            
            case 6:
                System.out.println("Entrou");
                lista.add( new ContadorPalavrasComThread(0, 40, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(40, 80, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(80, 120, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(120, 160, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(160, 200, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(200, 250, busca, caminho) );
                break;
            
                
            case 7:
                System.out.println("Entrou");
                lista.add( new ContadorPalavrasComThread(0, 35, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(35, 70, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(70, 105, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(105, 140, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(140, 175, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(175, 210, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(210, 250, busca, caminho) );
                break;
            
            case 8:
                System.out.println("Entrou");
                lista.add( new ContadorPalavrasComThread(0, 30, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(30, 60, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(60, 90, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(90, 120, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(120, 150, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(150, 180, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(180, 210, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(210, 250, busca, caminho) );
                break;
            
            case 9:
                System.out.println("Entrou");
                lista.add( new ContadorPalavrasComThread(0, 27, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(27, 54, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(81, 108, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(108, 135, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(135, 162, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(162, 189, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(189, 216, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(216, 243, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(243, 250, busca, caminho) );
                break;
                
            case 10:
                System.out.println("Entrou");
                lista.add( new ContadorPalavrasComThread(0, 25, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(25, 50, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(50, 75, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(75, 100, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(100, 125, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(125, 150, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(150, 175, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(175, 200, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(200, 225, busca, caminho) );
                lista.add( new ContadorPalavrasComThread(225, 250, busca, caminho) );
                break;
                
        }
        
        for( ContadorPalavrasComThread c : lista ){
            c.start();
        }
        
        for( ContadorPalavrasComThread c : lista ){
            c.join();
            soma += c.getQtd();
        }
        
        System.out.println("Soma: " + soma );
    }
    
    public static String buscarLink(){
        String sistema = System.getProperty("os.name").toLowerCase();
        if( sistema.startsWith("linux") ){
            return "/home/tiago/Repositorios/Github/DistribuidosParalelos/"
                    + "Arquivos/3-CasosTestePalavrasArquivos/";
        }if( sistema.startsWith( "Windows" ) ){
            return "C:\\Users\\10516125940\\Documents\\GitHub\\DistribuidosParalelos"
                    + "\\Arquivos\\3-CasosTestePalavrasArquivos\\";
        }
        throw new RuntimeException( "Tipo de sistema não suportado" );
    }
}
