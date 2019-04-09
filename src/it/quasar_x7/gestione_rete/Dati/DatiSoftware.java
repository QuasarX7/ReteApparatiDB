package it.quasar_x7.gestione_rete.Dati;

import it.quasar_x7.java.BaseDati.EccezioneBaseDati;
import it.quasar_x7.java.BaseDati.Relazione;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dr. Domenico della Peruta
 */
public class DatiSoftware extends DatiDB {
    
    public static final String NOME_TABELLA                 = "software";
    
    static final String VOCE_TABELLA_TIPO               = "tipo";
    static final String VOCE_TABELLA_NOME_VERSIONE      = "nome";
    static final String VOCE_TABELLA_LICENZA            = "licenza";
   
    static final String VOCE_TABELLA_CASA               = "casa";
    static final String VOCE_TABELLA_NOTE               = "note";

    

    public DatiSoftware() {
        try {
            tabella = new Relazione(NOME_TABELLA);
            
            tabella.creaAttributo(VOCE_TABELLA_NOME_VERSIONE,   TESTO, "", Relazione.CHIAVE);
            
            tabella.creaAttributo(VOCE_TABELLA_TIPO,  TESTO, null, Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_CASA,  TESTO, null, Relazione.NO_CHIAVE);
            
            tabella.creaAttributo(VOCE_TABELLA_LICENZA, TESTO, "", Relazione.CHIAVE);
            
            tabella.creaAttributo(VOCE_TABELLA_NOTE,  TESTO, "", Relazione.NO_CHIAVE);
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiSoftware.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    
    //
    /**
     * <pre>
     * +-----------------+---------+---------+-------------+---------------+
     * | Nome/versione ˙ Licenza   |  Tipo   |   Casa      |      Note     |
     * +-----------------+---------+---------+-------------+---------------+
     * |                           |         |             |               |
     * </pre>
     * @return 
     */
    @Override
    public ArrayList<ArrayList<String>> tabella() {
        try {
            ArrayList<ArrayList<String>> tab = new ArrayList<>();
            db.connetti();
            ArrayList<Object[]> tabellaSoftware = db.vediTutteLeTuple(tabella);
            if(tabellaSoftware != null)
                for(Object[] record : tabellaSoftware ){
                    if(record != null){
                       ArrayList<String> riga =  new ArrayList<>();
                       riga.add(String.format("%s ˙ %s",record[0],record[3]));
                       riga.add(record[1].toString());
                       riga.add(record[2].toString());
                       riga.add(record[4].toString());
                       tab.add(riga);
                    }
                }
            db.chiudi();
            return tab;
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiSoftware.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    static public String[] scomponiNomeLicenza(String stringa){
        return stringa.split(" ˙ ");
    }

    static public String componiNomeLicenza(String nome,String licenza){
        return String.format("%s ˙ %s", nome,licenza);
    }
    
    public TreeSet<String> listaNomi() {
        return listaOrdinata(0);
    }
    
    
    public TreeSet<String> listaLicenza(String nomeSW) {
        return ricercaOrdinata(0, nomeSW, 3);
    }
    
    
}
