package it.quasar_x7.gestione_rete.Dati;

/**
 *
 * @author Dr Domenico della Peruta
 */
public class DatiRuolo extends DatiLista {
    
    public static final String NOME_TABELLA        = "categoria_grado";
    protected static final String VOCE_TABELLA_NOME         = "ruolo";

    
    public DatiRuolo() {
        super(NOME_TABELLA, VOCE_TABELLA_NOME);
    }
    
}
