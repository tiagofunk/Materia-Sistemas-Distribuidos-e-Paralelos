package model;

public class Sessao {
    
    private TipoAgente agente;
    private TipoResposta resposta;
    
    private Votacao votacao;
    
    public Sessao(TipoAgente agente, Votacao votacao) {
        this.agente = agente;
        this.votacao = votacao;
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
    
}
