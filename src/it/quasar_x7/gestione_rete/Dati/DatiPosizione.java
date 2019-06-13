package it.quasar_x7.gestione_rete.Dati;

import it.quasar_x7.gestione_rete.modello.Ufficio;
import it.quasar_x7.java.BaseDati.EccezioneBaseDati;
import it.quasar_x7.java.BaseDati.Relazione;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dr. Domenico della PERUTA
 */
public class DatiPosizione extends DatiDB {
    
    public static final String NOME_TABELLA          = "posizione";
    
    protected static final String VOCE_TABELLA_SITO               = "sito";
    public static final String VOCE_TABELLA_RESPONSABILE       = "responsabile";

    

    public DatiPosizione() {
        try {
            tabella = new Relazione(NOME_TABELLA);
            
            tabella.creaAttributo(VOCE_TABELLA_SITO,   TESTO, "", Relazione.CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_RESPONSABILE,  TESTO, null, Relazione.NO_CHIAVE); // *
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiPosizione.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> lista() {
        ArrayList<String> posizioni = new ArrayList<>();
        try {
            db.connetti();
            ArrayList<Object[]> vociTabella = db.vediTutteLeTuple(tabella);
            if(vociTabella != null)
                for(Object[] record : vociTabella){
                    if(record != null){
                            posizioni.add((String) record[0]);

                        }
                    }
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiPosizione.class.getName()).log(Level.SEVERE, null, ex);
        }
        return posizioni;
    }
    
    /**
     * Trova i dati relativi al locale/ufficio: come il nome del responsabile.
     * 
     * @param posizione		nome del locale o dell'ufficio
     * @return	Ufficio
     */
    public Ufficio info(String posizione){
        try {
            db.connetti();
            Object[] record = db.vediTupla(tabella, new Object[]{posizione});
            
            db.chiudi();
            if(record != null)
                return new Ufficio(
                        (String)record[0],
                        (String)record[1]
                );
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiUtilizzatore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Ufficio(posizione,"");
    }
    
    
    public ArrayList<String> listaSiti(){
        return super.lista(0);
    }
    
    

}
