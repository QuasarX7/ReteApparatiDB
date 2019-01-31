package it.quasar_x7.gestione_rete.Dati;

import it.quasar_x7.gestione_rete.modello.Rete;
import it.quasar_x7.java.BaseDati.EccezioneBaseDati;
import it.quasar_x7.java.BaseDati.Relazione;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dr. Domenico della PERUTA
 */
public class DatiRete extends DatiDB{
    public static final String NOME_TABELLA                = "rete";
    
    protected static final String VOCE_TABELLA_NOME        = "workgroup";
    protected static final String VOCE_TABELLA_DOMINIO     = "dominio";
    protected static final String VOCE_TABELLA_TIPO_RETE   = "tipo";
    protected static final String VOCE_TABELLA_NETMASK     = "netmask";
    protected static final String VOCE_TABELLA_GATEWAY     = "gateway";
    
    
    public DatiRete() {
        try {
            tabella = new Relazione(NOME_TABELLA);
            tabella.creaAttributo(VOCE_TABELLA_NOME, TESTO, "", Relazione.CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_DOMINIO, TESTO, "", Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_TIPO_RETE, TESTO, null, Relazione.NO_CHIAVE); // *
            tabella.creaAttributo(VOCE_TABELLA_GATEWAY, TESTO, "", Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_NETMASK, TESTO, "", Relazione.NO_CHIAVE);
            
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiRete.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public ArrayList<String> lista() {
        ArrayList<String> reti = new ArrayList<>();
        try {
            db.connetti();
            ArrayList<Object[]> vociTabella = db.vediTutteLeTuple(tabella);
            if(vociTabella != null)
                for(Object[] record : vociTabella){
                    if(record != null){
                        reti.add((String)record[0]);
                    }
                }
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiRete.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reti;
    }
    
    public Rete crea(String workgroup){
        try {
            db.connetti();
            Object[] record = db.vediTupla(tabella, new Object[]{workgroup});
            
            db.chiudi();
            if(record != null)
                return new Rete(
                        (String)record[0], // workgroup
                        (String)record[1], // dominio
                        (String)record[2], // tipo
                        (String)record[3], // gateway
                        (String)record[4]  // netmask
                );
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiUtilizzatore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  Rete.STANDALONE;
    }

  
    
    
}
