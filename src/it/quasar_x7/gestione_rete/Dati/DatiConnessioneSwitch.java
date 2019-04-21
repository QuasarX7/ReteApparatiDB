package it.quasar_x7.gestione_rete.Dati;

import it.quasar_x7.gestione_rete.modello.Switch;
import it.quasar_x7.java.BaseDati.EccezioneBaseDati;
import it.quasar_x7.java.BaseDati.Relazione;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dott. Domenico dellaPeruta
 */
public class DatiConnessioneSwitch extends DatiDB{
    
    public static final String NOME_TABELLA              = "connessione_switch";
    protected static final String VOCE_HOST              = "apparato";
    protected static final String VOCE_SWITCH            = "switch";
    protected static final String VOCE_PORTA             = "porta";

    public DatiConnessioneSwitch() {
        try {
            tabella = new Relazione(NOME_TABELLA);
            tabella.creaAttributo(VOCE_HOST,   TESTO, "", Relazione.CHIAVE);
            
            tabella.creaAttributo(VOCE_SWITCH, TESTO, null, Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_PORTA,  TESTO, "", Relazione.NO_CHIAVE);
            
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiConnessioneSwitch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Switch creaSwitch(String apparato) {
        Switch _switch = null;
        try {
            db.connetti();
            Object[] riga = db.vediTupla(tabella, new Object[]{apparato});
            if(riga != null){
               _switch = new Switch((String)riga[1],(String)riga[2]); 
            }
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiConnessioneSwitch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return _switch;
    }
    
    
    
    

}
