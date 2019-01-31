package it.quasar_x7.gestione_rete.Dati;

import it.quasar_x7.java.BaseDati.EccezioneBaseDati;
import it.quasar_x7.java.BaseDati.Relazione;
import it.quasar_x7.javafx.finestre.modello.VoceListaColore;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;

/**
 *
 * @author Dr. Domenico della Peruta
 */
public class DatiStato extends DatiDB {
    
    public static final String NOME_TABELLA              = "stato";
    protected static final String VOCE_CONDIZIONE        = "condizione";
    protected static final String VOCE_COLORE            = "colore";
    
    public DatiStato() {
        try {
            tabella = new Relazione(NOME_TABELLA);
            
            tabella.creaAttributo(VOCE_CONDIZIONE,   TESTO, "", Relazione.CHIAVE);
            tabella.creaAttributo(VOCE_COLORE,  TESTO, "", Relazione.NO_CHIAVE);
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiStato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Object[]> lista() {
        ArrayList<Object[]> lista = new ArrayList<>();
        try {
            db.connetti();
            ArrayList<Object[]> righe = db.vediTutteLeTuple(tabella);
            if(righe != null){
                for(Object[] riga : righe){
                    if(riga != null){
                        try{
                            lista.add(
                                    new Object[]{
                                        riga[0].toString(),
                                        Color.web(riga[1].toString())
                                    }
                            );
                        }catch(IllegalArgumentException | NullPointerException ex){
                        }
                    }
                }
            }
            db.chiudi();
            
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiStato.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  lista;
    }
    
    public ArrayList<String> listaSemplice() {
        ArrayList<String> lista = new ArrayList<>();
        try {
            db.connetti();
            ArrayList<Object[]> righe = db.vediTutteLeTuple(tabella);
            if(righe != null){
                for(Object[] record : righe){
                    if(record != null){
                        lista.add((String) record[0]);
                    }
                }
            }
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiStato.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public String colore(String stato) {
        String colore = null;
        try {
            db.connetti();
            Object[] riga = db.vediTupla(tabella, new Object[]{stato});
            if(riga != null){
                if(riga[1] != null){
                    colore = (String) riga[1];
                }
            }
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiStato.class.getName()).log(Level.SEVERE, null, ex);
        }
        return colore;
    }
    
}
