package it.quasar_x7.gestione_rete.Dati;

/**
 *
 * @author Dr Domenico della PERUTA
 */
public class DatiTipoHardware extends DatiLista{
    
    public static final String NOME_TABELLA        = "tipo_hardware";
    protected static final String VOCE_TABELLA_TIPO           = "tipo";

    public DatiTipoHardware() {
        super(NOME_TABELLA,VOCE_TABELLA_TIPO);
    }
    
}