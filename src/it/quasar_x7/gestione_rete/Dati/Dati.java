package it.quasar_x7.gestione_rete.Dati;

import it.quasar_x7.java.BaseDati.EccezioneBaseDati;

/**
 * Struttura dati di tipo relazionale.
 * 
 * @author Dr. Domenico della Peruta
 */
public interface Dati {
    public void creaTabella() throws EccezioneBaseDati ;
    public boolean aggiungi(Object[] record);
    public boolean modifica(Object[] chiave,Object[] record);
    public Object[] accedi(Object[]chiave);
    public boolean elimina(Object[]chiave);
    
}
