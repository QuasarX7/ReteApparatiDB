package it.quasar_x7.gestione_rete.Dati;

/**
 *
 * @author Dr Domenico della PERUTA
 */
public class DatiTipoRete extends DatiLista{
    
    public static final String NOME_TABELLA        = "tipo_rete";
    protected static final String VOCE_TABELLA_TIPO   = "tipo";

    public DatiTipoRete() {
        super(NOME_TABELLA,VOCE_TABELLA_TIPO);
    }
    
}