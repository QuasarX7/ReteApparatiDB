package it.quasar_x7.gestione_rete.Dati;

/**
 *
 * @author Dr. Domenico della Peruta
 */
public class DatiSwitch extends DatiLista{
    
    public static final String NOME_TABELLA              = "switch";
    protected static final String VOCE_NOME              = "posizione";

    public DatiSwitch() {
        super(NOME_TABELLA, VOCE_NOME);
    }
    
}
