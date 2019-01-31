package it.quasar_x7.gestione_rete.Dati;

import it.quasar_x7.java.BaseDati.EccezioneBaseDati;
import it.quasar_x7.java.BaseDati.Relazione;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dr. Domenico della Peruta
 */
public class DatiHardware extends DatiDB {
    
    public static final String NOME_TABELLA        = "hardware";
    
    protected static final String VOCE_TABELLA_TIPO      = "tipo";
    protected static final String VOCE_TABELLA_MODELLO   = "modello";
    protected static final String VOCE_TABELLA_MATRICOLA = "matricola";
   
    protected static final String VOCE_TABELLA_CASA      = "casa";
    protected static final String VOCE_TABELLA_NUC       = "NUC";
    protected static final String VOCE_TABELLA_STATO     = "stato";
    protected static final String VOCE_TABELLA_NOTE      = "note";

    

    public DatiHardware() {
        try {
            tabella = new Relazione(NOME_TABELLA);
            
            tabella.creaAttributo(VOCE_TABELLA_TIPO,  TESTO, "", Relazione.CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_CASA,  TESTO, null, Relazione.NO_CHIAVE); // *
            
            tabella.creaAttributo(VOCE_TABELLA_MODELLO,   TESTO, "", Relazione.CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_MATRICOLA, TESTO, "", Relazione.CHIAVE);
            
            tabella.creaAttributo(VOCE_TABELLA_NUC,  TESTO, "", Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_STATO,  TESTO, null, Relazione.NO_CHIAVE); // *
            tabella.creaAttributo(VOCE_TABELLA_NOTE,  TESTO, "", Relazione.NO_CHIAVE);
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiHardware.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static String marcaMatricola(Object[] record) {
        return String.format("%s\t[%s]", (String)record[2],(String)record[3]);
    }
    
    static public String[] marcaMatricola(String stringa){
        String[] lista = stringa.split("\t");
        lista[1] = lista[1].substring(1, lista[1].length()-1);
        return lista;
    }
    
    /**
     * <pre>
     * +-----------------+---------+---------+-----+-------+---------------+
     * | Modello [Matr.] |  Tipo   |   Casa  | NUC | Stato |      Note     |
     * +-----------------+---------+---------+-----+-------+---------------+
     * |                 |         |         |     |       |               |
     * </pre>
     * @return 
     */
    @Override
    public ArrayList<ArrayList<String>> tabella() {
        try {
            ArrayList<ArrayList<String>> tab = new ArrayList<>();
            db.connetti();
            ArrayList<Object[]> tabellaHardware = db.vediTutteLeTuple(tabella);
            if(tabellaHardware != null)
                for(Object[] record : tabellaHardware){
                    if(record != null){
                       ArrayList<String> riga =  new ArrayList<>();
                       riga.add(marcaMatricola(record));
                       riga.add((String)record[0]);
                       riga.add((String)record[1]);
                       riga.add((String)record[4]);
                       riga.add((String)record[5]);
                       riga.add((String)record[6]);
                       tab.add(riga);
                    }
                }
            db.chiudi();
            return tab;
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiHardware.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
