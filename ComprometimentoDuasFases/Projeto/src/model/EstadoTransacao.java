package model;

public enum EstadoTransacao {
    INIT,
    VOTE_REQUEST,
    VOTE_COMMIT,
    VOTE_ABORT,
    GLOBAL_ABORT,
    GLOBAL_COMMIT;
    
}
