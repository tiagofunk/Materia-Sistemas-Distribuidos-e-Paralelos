package model;

public class Sessao {
    
    private TipoAgente agente;
    private TipoResposta resposta;
    
    private Votacao votacao;
    
    private int indiceFalhaVoteRequest;
    private int indiceFalhaVoteGlobal;
    private boolean falhaVoteLocal;
    
    public Sessao(TipoAgente agente, Votacao votacao) {
        this.agente = agente;
        this.votacao = votacao;
        indiceFalhaVoteGlobal = -1;
        indiceFalhaVoteRequest = -1;
        falhaVoteLocal = false;
    }

    public Sessao(TipoAgente agente, TipoResposta resposta) {
        this.agente = agente;
        this.resposta = resposta;
    }
    
    public TipoAgente getAgente() {
        return agente;
    }
    
    public TipoResposta getResposta() {
        return resposta;
    }

    public Votacao getVotacao() {
        return votacao;
    }

    public int getIndiceFalhaVoteRequest() {
        return indiceFalhaVoteRequest;
    }

    public void setIndiceFalhaVoteRequest(int indiceFalhaVoteRequest) {
        this.indiceFalhaVoteRequest = indiceFalhaVoteRequest;
    }

    public int getIndiceFalhaVoteGlobal() {
        return indiceFalhaVoteGlobal;
    }

    public void setIndiceFalhaVoteGlobal(int indiceFalhaVoteGlobal) {
        this.indiceFalhaVoteGlobal = indiceFalhaVoteGlobal;
    }

    public boolean getFalhaVoteLocal() {
        return falhaVoteLocal;
    }

    public void setFalhaVoteLocal(boolean falhaVoteLocal) {
        this.falhaVoteLocal = falhaVoteLocal;
    }
    
    
    
}
