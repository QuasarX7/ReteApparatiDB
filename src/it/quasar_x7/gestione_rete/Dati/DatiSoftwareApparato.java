package it.quasar_x7.gestione_rete.Dati;

import it.quasar_x7.gestione_rete.modello.Software;
import it.quasar_x7.java.BaseDati.AccessoSQLite;
import it.quasar_x7.java.BaseDati.Attributo;
import it.quasar_x7.java.BaseDati.DatoStringa;
import it.quasar_x7.java.BaseDati.DatoTesto;
import it.quasar_x7.java.BaseDati.EccezioneBaseDati;
import it.quasar_x7.java.BaseDati.Relazione;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dr. Domenico della Peruta
 */
public class DatiSoftwareApparato extends DatiDB {
    public static final String NOME_TABELLA              = "software_apparato";
    
    protected static final String VOCE_APPARATO          = "apparato";
    protected static final String VOCE_SW                = "software";
    protected static final String VOCE_SW_LICENZA        = "licenza_software";
    
    public DatiSoftwareApparato(){
        try {
            tabella = new Relazione(NOME_TABELLA);
            
            tabella.creaAttributo(VOCE_APPARATO,   TESTO, "", Relazione.CHIAVE);
            tabella.creaAttributo(VOCE_SW,  TESTO, "", Relazione.CHIAVE);
            tabella.creaAttributo(VOCE_SW_LICENZA,  TESTO, "", Relazione.CHIAVE);
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiSoftwareApparato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Elenco del software associato a un dato apparato.
     * 
     * @param nomeApparato
     * @return 
     */
    public ArrayList<Software> listaSW(String nomeApparato) {
        ArrayList<Software> lista = new ArrayList<>();
        try {
            db.connetti();
            ArrayList<Object[]> query = db.interrogazioneSQL(
                    String.format(
                              "SELECT sw.`%s`, sw.`%s`, sw.`%s`, sw.`%s`, sw.`%s` "
                            + "FROM `%s` AS sw, `%s` AS swApp "
                            + "WHERE sw.`%s` = swApp.`%s` AND  sw.`%s` = swApp.`%s` AND swApp.`%s` = '%s' ; ", 
                            //select
                            DatiSoftware.VOCE_TABELLA_NOME_VERSIONE,
                            DatiSoftware.VOCE_TABELLA_LICENZA,
                            DatiSoftware.VOCE_TABELLA_TIPO,
                            DatiSoftware.VOCE_TABELLA_CASA,
                            DatiSoftware.VOCE_TABELLA_NOTE,
                            //from
                            DatiSoftware.NOME_TABELLA,// sw
                            DatiSoftwareApparato.NOME_TABELLA, // swApp
                            //where
                            DatiSoftware.VOCE_TABELLA_NOME_VERSIONE,
                            DatiSoftwareApparato.VOCE_SW,
                            
                            DatiSoftware.VOCE_TABELLA_LICENZA,
                            DatiSoftwareApparato.VOCE_SW_LICENZA,
                            
                            DatiSoftwareApparato.VOCE_APPARATO,
                            nomeApparato
                            
                    ), 
                    new Attributo[]{
                        new Attributo(DatiSoftware.VOCE_TABELLA_NOME_VERSIONE,new DatoTesto(),false),
                        new Attributo(DatiSoftware.VOCE_TABELLA_LICENZA,new DatoTesto(),false),
                        new Attributo(DatiSoftware.VOCE_TABELLA_TIPO,new DatoTesto(),false),
                        new Attributo(DatiSoftware.VOCE_TABELLA_CASA,new DatoTesto(),false),
                        new Attributo(DatiSoftware.VOCE_TABELLA_NOTE,new DatoTesto(),false),
                    }
            );
            db.chiudi();
            if(query != null)
                for(Object[] record: query){
                    if(record != null){
                        lista.add(new Software(record));
                    }
                }
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiSoftwareApparato.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    
    
}
