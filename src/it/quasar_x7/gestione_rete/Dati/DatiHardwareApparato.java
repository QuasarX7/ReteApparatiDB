package it.quasar_x7.gestione_rete.Dati;

import it.quasar_x7.gestione_rete.modello.Hardware;
import it.quasar_x7.java.BaseDati.Attributo;
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
public class DatiHardwareApparato extends DatiDB {
    public static final String NOME_TABELLA              = "hardware_apparato";
    
    public static final String VOCE_APPARATO          = "apparato";
    public static final String VOCE_HW                = "tipo_hardware";
    public static final String VOCE_MODELLO        	 = "modello_hardware";
    public static final String VOCE_MATRICOLA         = "matricola_hardware";
    
    public DatiHardwareApparato(){
        try {
            tabella = new Relazione(NOME_TABELLA);
            
            tabella.creaAttributo(VOCE_APPARATO,   TESTO, "", Relazione.CHIAVE);
            tabella.creaAttributo(VOCE_HW,  TESTO, "", Relazione.CHIAVE);
            tabella.creaAttributo(VOCE_MODELLO,  TESTO, "", Relazione.CHIAVE);
            tabella.creaAttributo(VOCE_MATRICOLA,  TESTO, "", Relazione.CHIAVE);
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiHardwareApparato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Elenco del hardware associato a un dato apparato.
     * 
     * @param nomeApparato
     * @return 
     */
    public ArrayList<Hardware> listaHW(String nomeApparato) {
        ArrayList<Hardware> lista = new ArrayList<>();
        try {
            db.connetti();
            ArrayList<Object[]> query = db.interrogazioneSQL(
                    String.format(
                              "SELECT sw.`%s`, sw.`%s`, sw.`%s`, sw.`%s`, sw.`%s`, sw.`%s`, sw.`%s` "
                            + "FROM `%s` AS sw, `%s` AS hwApp "
                            + "WHERE sw.`%s` = hwApp.`%s` AND  sw.`%s` = hwApp.`%s` AND  sw.`%s` = hwApp.`%s` AND hwApp.`%s` = '%s' ; ", 
                            //select
                            DatiHardware.VOCE_TABELLA_TIPO,
                            DatiHardware.VOCE_TABELLA_MODELLO,
                            DatiHardware.VOCE_TABELLA_MATRICOLA,
                            DatiHardware.VOCE_TABELLA_CASA,
                            DatiHardware.VOCE_TABELLA_NUC,
                            DatiHardware.VOCE_TABELLA_STATO,
                            DatiHardware.VOCE_TABELLA_NOTE,
                            //from
                            DatiHardware.NOME_TABELLA,// hw
                            DatiHardwareApparato.NOME_TABELLA, // hwApp
                            //where
                            DatiHardware.VOCE_TABELLA_TIPO,
                            DatiHardwareApparato.VOCE_HW,
                            
                            DatiHardware.VOCE_TABELLA_MODELLO,
                            DatiHardwareApparato.VOCE_MODELLO,
                            
                            DatiHardware.VOCE_TABELLA_MATRICOLA,
                            DatiHardwareApparato.VOCE_MATRICOLA,
                            
                            DatiHardwareApparato.VOCE_APPARATO,
                            nomeApparato
                            
                    ), 
                    new Attributo[]{
                        new Attributo(DatiHardware.VOCE_TABELLA_TIPO,new DatoTesto(),false),
                        new Attributo(DatiHardware.VOCE_TABELLA_MODELLO,new DatoTesto(),false),
                        new Attributo(DatiHardware.VOCE_TABELLA_MATRICOLA,new DatoTesto(),false),
                        new Attributo(DatiHardware.VOCE_TABELLA_CASA,new DatoTesto(),false),
                        new Attributo(DatiHardware.VOCE_TABELLA_NUC,new DatoTesto(),false),
                        new Attributo(DatiHardware.VOCE_TABELLA_STATO,new DatoTesto(),false),
                        new Attributo(DatiHardware.VOCE_TABELLA_NOTE,new DatoTesto(),false),
                    }
            );
            db.chiudi();
            if(query != null)
                for(Object[] record: query){
                    if(record != null){
                        lista.add(new Hardware(record));
                    }
                }
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiHardwareApparato.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

	public void elimina(String tipo) {
		elimataAlcuniRecord(String.format(" `%s` = '%s' ", DatiHardwareApparato.VOCE_HW,tipo));
	}

    
}
