package it.quasar_x7.gestione_rete.Dati;

/**
 *
 * @author Dr. Domenico della Peruta
 */
public class DatiTipoApparato extends DatiLista{
    
    
    public static final String NOME_TABELLA                   = "tipo_apparato";
    protected static final String VOCE_TABELLA_TIPO           = "tipo";

    public  DatiTipoApparato() {
        super(NOME_TABELLA,VOCE_TABELLA_TIPO);
    }
    
}
