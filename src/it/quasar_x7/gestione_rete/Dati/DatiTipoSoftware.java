package it.quasar_x7.gestione_rete.Dati;

/**
 *
 * @author Dr Domenico della PERUTA
 */
public class DatiTipoSoftware extends DatiLista{
    
    public static final String NOME_TABELLA        = "tipo_software";
    protected static final String VOCE_TABELLA_TIPO           = "tipo";

    public DatiTipoSoftware() {
        super(NOME_TABELLA,VOCE_TABELLA_TIPO);
    }
    
}