package it.quasar_x7.gestione_rete.Dati;

import static it.quasar_x7.gestione_rete.Dati.DatiDB.TESTO;
import it.quasar_x7.java.BaseDati.EccezioneBaseDati;
import it.quasar_x7.java.BaseDati.Relazione;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dr. Domenico della Peruta
 */
public class DatiResponsabileSito extends DatiDB{
    
    public static final String NOME_TABELLA             = "responsabile_sito";
    
    protected static final String VOCE_TABELLA_RESPONSABILE     = "responsabile";
    protected static final String VOCE_TABELLA_GRADO            = "grado";
    protected static final String VOCE_TABELLA_COGNOME          = "cognome";
    protected static final String VOCE_TABELLA_NOME             = "nome";
    
    
    
    public DatiResponsabileSito() {
        try {
            tabella = new Relazione(NOME_TABELLA);
            tabella.creaAttributo(VOCE_TABELLA_RESPONSABILE,   TESTO, "", Relazione.CHIAVE);
            
            tabella.creaAttributo(VOCE_TABELLA_GRADO, TESTO, null, Relazione.NO_CHIAVE);    // *
            tabella.creaAttributo(VOCE_TABELLA_COGNOME,  TESTO, "", Relazione.NO_CHIAVE); 
            tabella.creaAttributo(VOCE_TABELLA_NOME,  TESTO, "", Relazione.NO_CHIAVE);
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiResponsabileSito.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    @Override
    public ArrayList<ArrayList<String>> tabella() {
        try {
            ArrayList<ArrayList<String>> tab = new ArrayList<>();
            db.connetti();
            ArrayList<Object[]> righe = db.vediTutteLeTuple(tabella);
            if(righe != null)
                for(Object[] record : righe){
                    if(record != null){
                       ArrayList<String> riga =  new ArrayList<>();
                       riga.add(record[0].toString());
                       riga.add(nominativo(record));
                       tab.add(riga);
                    }
                }
            db.chiudi();
            return tab;
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiResponsabileSito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   
    static public String[] nominativo(String stringa){
        return stringa.split(" ˙ ");
    }
    
    
    static public String nominativo(Object[] record){
        return String.format(
                "%s ˙ %s ˙ %s",
                record[1] != null ? record[1].toString() : "",
                record[2] != null ? record[2].toString() : "",
                record[3] != null ? record[3].toString() : ""
        );
    }

    public ArrayList<String> lista() {
        try {
            ArrayList<String> lista = new ArrayList<>();
            db.connetti();
            ArrayList<Object[]> righe = db.vediTutteLeTuple(tabella);
            if(righe != null)
                for(Object[] record : righe){
                    if(record != null){
                        lista.add(record[0].toString());
                    }
                }
            db.chiudi();
            return lista;
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiResponsabileSito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * grado/qualifica cognome e nome del responsabile del sito.
     * 
     * @param responsabile
     * @return 
     */
    public String nominativoResponsabileSito(String responsabile) {
        try {
            db.connetti();
            Object[] riga = db.vediTupla(tabella, new Object[]{responsabile});
            if(riga != null){
                return nominativo(riga);
            }
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiResponsabileSito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
}
