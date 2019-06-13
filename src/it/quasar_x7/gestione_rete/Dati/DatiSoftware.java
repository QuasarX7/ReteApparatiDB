package it.quasar_x7.gestione_rete.Dati;

import it.quasar_x7.gestione_rete.modello.Software;
import it.quasar_x7.java.BaseDati.Attributo;
import it.quasar_x7.java.BaseDati.DatoBooleano;
import it.quasar_x7.java.BaseDati.DatoTesto;
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
    
    public static final String VOCE_TABELLA_TIPO               = "tipo";
    public static final String VOCE_TABELLA_NOME_VERSIONE      = "nome";
    public static final String VOCE_TABELLA_LICENZA            = "licenza";
   
    public static final String VOCE_TABELLA_CASA               = "casa";
    public static final String VOCE_TABELLA_NOTE               = "note";
    
    public static final String VOCE_TABELLA_PREDEFINITO        = "predefinito";
    

    public DatiSoftware() {
        try {
            tabella = new Relazione(NOME_TABELLA);
            
            tabella.creaAttributo(VOCE_TABELLA_NOME_VERSIONE,   TESTO, "", Relazione.CHIAVE);
            
            tabella.creaAttributo(VOCE_TABELLA_TIPO,  TESTO, null, Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_CASA,  TESTO, null, Relazione.NO_CHIAVE);
            
            tabella.creaAttributo(VOCE_TABELLA_LICENZA, TESTO, "", Relazione.CHIAVE);
            
            tabella.creaAttributo(VOCE_TABELLA_NOTE,  TESTO, "", Relazione.NO_CHIAVE);
            
            tabella.creaAttributo(VOCE_TABELLA_PREDEFINITO,  BOOLEANO, "false", Relazione.NO_CHIAVE);
        
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
                       riga.add(record[5].toString());
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
    
    
    /**
     * Elenco del software predefinito, da installare su tutte le macchine.
     * 
     * @return
     */
	public ArrayList<Software> listaSoftwarePredefinito() {
		ArrayList<Software> lista = new ArrayList<>();
        try {
            db.connetti();
            ArrayList<Object[]> query = db.interrogazioneSQL(
                    String.format(
                              "SELECT sw.`%s`, sw.`%s`, sw.`%s`, sw.`%s`, sw.`%s`, sw.`%s` "
                            + "FROM `%s` AS sw "
                            + "WHERE sw.`%s` = true ; ", 
                            //select
                            DatiSoftware.VOCE_TABELLA_NOME_VERSIONE,
                            DatiSoftware.VOCE_TABELLA_LICENZA,
                            DatiSoftware.VOCE_TABELLA_TIPO,
                            DatiSoftware.VOCE_TABELLA_CASA,
                            DatiSoftware.VOCE_TABELLA_NOTE,
                            DatiSoftware.VOCE_TABELLA_PREDEFINITO,
                            //from
                            DatiSoftware.NOME_TABELLA,// sw
                            //where
                            DatiSoftware.VOCE_TABELLA_PREDEFINITO
                            
                    ), 
                    new Attributo[]{
                        new Attributo(DatiSoftware.VOCE_TABELLA_NOME_VERSIONE,new DatoTesto(),false),
                        new Attributo(DatiSoftware.VOCE_TABELLA_LICENZA,new DatoTesto(),false),
                        new Attributo(DatiSoftware.VOCE_TABELLA_TIPO,new DatoTesto(),false),
                        new Attributo(DatiSoftware.VOCE_TABELLA_CASA,new DatoTesto(),false),
                        new Attributo(DatiSoftware.VOCE_TABELLA_NOTE,new DatoTesto(),false),
                        new Attributo(DatiSoftware.VOCE_TABELLA_PREDEFINITO,new DatoBooleano(),false),
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
            Logger.getLogger(DatiSoftware.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
	}


	public ArrayList<Software> listaSoftware() {
		ArrayList<Software> lista = new ArrayList<>();
        try {
            db.connetti();
            ArrayList<Object[]> query = db.interrogazioneSQL(
                    String.format(
                              "SELECT sw.`%s`, sw.`%s`, sw.`%s`, sw.`%s`, sw.`%s`, sw.`%s` "
                            + "FROM `%s` AS sw ", 
                            //select
                            DatiSoftware.VOCE_TABELLA_NOME_VERSIONE,
                            DatiSoftware.VOCE_TABELLA_LICENZA,
                            DatiSoftware.VOCE_TABELLA_TIPO,
                            DatiSoftware.VOCE_TABELLA_CASA,
                            DatiSoftware.VOCE_TABELLA_NOTE,
                            DatiSoftware.VOCE_TABELLA_PREDEFINITO,
                            //from
                            DatiSoftware.NOME_TABELLA// sw
                            
                    ), 
                    new Attributo[]{
                        new Attributo(DatiSoftware.VOCE_TABELLA_NOME_VERSIONE,new DatoTesto(),false),
                        new Attributo(DatiSoftware.VOCE_TABELLA_LICENZA,new DatoTesto(),false),
                        new Attributo(DatiSoftware.VOCE_TABELLA_TIPO,new DatoTesto(),false),
                        new Attributo(DatiSoftware.VOCE_TABELLA_CASA,new DatoTesto(),false),
                        new Attributo(DatiSoftware.VOCE_TABELLA_NOTE,new DatoTesto(),false),
                        new Attributo(DatiSoftware.VOCE_TABELLA_PREDEFINITO,new DatoBooleano(),false),
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
            Logger.getLogger(DatiSoftware.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
	}


	public void predefinito(String nomeSoftware, String licenza, Boolean predefinito) {
	    Object[] chiave = new Object[] {nomeSoftware,licenza};
		Object[] record = accedi(chiave);
		record[5] = predefinito;
		this.modifica(chiave, record);		
	}

    
}
