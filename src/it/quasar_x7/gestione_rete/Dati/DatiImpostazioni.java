package it.quasar_x7.gestione_rete.Dati;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.quasar_x7.java.BaseDati.EccezioneBaseDati;
import it.quasar_x7.java.BaseDati.Relazione;

/**
* Classe che implementa la tabella contenente tutti i valori chiavi delle impostazioni
* del programma, principalmente usata nei report di stampa.
* 
* @author Dr. Domenico della Peruta
*/
public class DatiImpostazioni extends DatiDB {

public static final String NOME_TABELLA    = "impostazioni";
    
    protected static final String VOCE_TABELLA_CHIAVE    = "chiave";
    protected static final String VOCE_TABELLA_VALORE    = "valore";
    
    
    
    public DatiImpostazioni() {
        try {
            tabella = new Relazione(NOME_TABELLA);
            tabella.creaAttributo(VOCE_TABELLA_CHIAVE, TESTO, "", Relazione.CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_VALORE,  TESTO, null, Relazione.NO_CHIAVE);
            
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiImpostazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Trova il valore associato alla chiave
     * 
     * @param chiave
     * @return
     */
    public String valore(String chiave) {
    	Object[] record = accedi(new Object[] {chiave});
    	if(record != null )
    		if(record[1] != null)
    			return (String)record[1];
    	return "";
    }

    /**
     * Aggiungi o modifica un valore associato a una chiave.
     * 
     * @param chiave
     * @param valore
     */
    public void valore(String chiave, String valore) {
    	if(accedi(new Object[] {chiave}) == null)
    		aggiungi(new Object[] {chiave,valore});
    	else
    		modifica(new Object[] {chiave}, new Object[] {chiave,valore});
    }
    
    /**
     * Elimina il record di impostazione associato alla chiave.
     * 
     * @param chiave
     */
    public void elimina(String chiave) {
    	elimina(new Object[]{chiave});
    }
	
}
