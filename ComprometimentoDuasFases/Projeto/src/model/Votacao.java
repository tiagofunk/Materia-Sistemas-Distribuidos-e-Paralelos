package model;

public class Votacao {

    private boolean resultado;
    private int numeroParticipantes;
    private int votos;
    
    public Votacao(int numeroParticipantes) {
        this.numeroParticipantes = numeroParticipantes;
        this.resultado = true;
    }
    
    public void votar(boolean voto){
        votos++;
        resultado = resultado && voto;
    }
    
    public boolean tudoMundoVotou(){
        if (numeroParticipantes == votos) {
            return true;
        }else{
            return false;
        }
    }
    
    public boolean resultadoVotacao(){
        return resultado;
    }
    
}
