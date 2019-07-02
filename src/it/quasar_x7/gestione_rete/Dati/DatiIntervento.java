package it.quasar_x7.gestione_rete.Dati;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.quasar_x7.java.BaseDati.EccezioneBaseDati;
import it.quasar_x7.java.BaseDati.Relazione;

public class DatiIntervento extends DatiDB {
	
	public static final String NOME_TABELLA    = "intervento";
    
	protected static final String VOCE_TABELLA_TIKET    = "ticket";
    protected static final String VOCE_TABELLA_DATA     = "data";
    protected static final String VOCE_TABELLA_APPARATO = "apparato";
    protected static final String VOCE_TABELLA_MOTIVO   = "richiesta";
    protected static final String VOCE_TABELLA_ATTIVITA_SVOLTA   = "attivita_svolta";
    protected static final String VOCE_TABELLA_ESITO   = "esito";
    
    
    public DatiIntervento() {
        try {
            tabella = new Relazione(NOME_TABELLA);
            tabella.creaAttributo(VOCE_TABELLA_TIKET, TESTO, "", Relazione.CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_DATA,  TESTO, "", Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_APPARATO,  TESTO, "", Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_MOTIVO,  TESTO, "", Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_ATTIVITA_SVOLTA,  TESTO, "", Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_ESITO, TESTO, "", Relazione.NO_CHIAVE);
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiIntervento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
