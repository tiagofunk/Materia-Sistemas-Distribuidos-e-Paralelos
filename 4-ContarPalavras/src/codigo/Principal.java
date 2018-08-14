package codigo;

public class Principal {
    
    public static void main(String[] args) {
        final String CAMINHO = "C:\\Users\\10516125940\\Documents\\GitHub\\DistribuidosParalelos\\Arquivos\\3-CasosTestePalavrasArquivos\\";
        ContadorPalavras c = new ContadorPalavras(0, 10, CAMINHO);
        c.contar( "realize" );
    }
}
