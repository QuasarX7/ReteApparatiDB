package it.quasar_x7.gestione_rete.Dati;

import it.quasar_x7.gestione_rete.modello.Apparato;
import it.quasar_x7.gestione_rete.modello.Utilizzatore;
import it.quasar_x7.gestione_rete.programma.R;
import it.quasar_x7.java.BaseDati.EccezioneBaseDati;
import it.quasar_x7.java.BaseDati.Relazione;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dr. Domenico della Peruta
 */
public class DatiApparato extends DatiDB{
    public static final String NOME_TABELLA                	= "apparato";
    
    protected static final String VOCE_TABELLA_NOME        	= "nome";
    public static final String VOCE_TABELLA_TIPO        	= "tipo";
    public static final String VOCE_TABELLA_WG          	= "workgroup";
    protected static final String VOCE_TABELLA_IP          	= "ip";
    protected static final String VOCE_TABELLA_MAC_PC      	= "mac pc";
    protected static final String VOCE_TABELLA_MAC_VOIP    	= "mac voip";
    public static final String VOCE_TABELLA_POSIZIONE   	= "posizione";
    public static final String VOCE_TABELLA_UTENTE      	= "utente";
    public static final String VOCE_TABELLA_SWITCH      	= "switch";
    protected static final String VOCE_TABELLA_INTERNET    	= "internet";
    protected static final String VOCE_TABELLA_SIGILLO     	= "sigillo";
    protected static final String VOCE_TABELLA_PASSWORD    	= "password";
    public static final String VOCE_TABELLA_STATO       	= "stato";

    public DatiApparato() {
        try {
            tabella = new Relazione(NOME_TABELLA);
            
            tabella.creaAttributo(VOCE_TABELLA_NOME, TESTO, "", Relazione.CHIAVE);
            
            tabella.creaAttributo(VOCE_TABELLA_TIPO, TESTO, null, Relazione.NO_CHIAVE);      // *
            tabella.creaAttributo(VOCE_TABELLA_WG, TESTO, null, Relazione.NO_CHIAVE);        // *
            tabella.creaAttributo(VOCE_TABELLA_IP, TESTO, "", Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_MAC_PC, TESTO, "", Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_MAC_VOIP, TESTO, "", Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_POSIZIONE, TESTO, null, Relazione.NO_CHIAVE); // *
            tabella.creaAttributo(VOCE_TABELLA_UTENTE, TESTO, null, Relazione.NO_CHIAVE);    // *
            //tabella.creaAttributo(VOCE_TABELLA_SWITCH, TESTO, null, Relazione.NO_CHIAVE);    // *
            tabella.creaAttributo(VOCE_TABELLA_INTERNET, BOOLEANO, null, Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_SIGILLO, INTERO, null, Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_PASSWORD, TESTO, "", Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_STATO, TESTO, null, Relazione.NO_CHIAVE);     // *
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiApparato.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    /**
     * Lista degli apparati ragruppati sia per rete di appartenenza, responsabile sito e ufficio (o locale),
     * attraverso l'uso di tre mappe e una lista nidificate.
     * 
     * @return 
     */
    public HashMap<String,HashMap<String,HashMap<String,ArrayList<Apparato>>>> listaApparati(){
        HashMap<String,HashMap<String,HashMap<String,ArrayList<Apparato>>>> lista = new HashMap<>();
        try {
            db.connetti();
                    
            ArrayList<Object[]> datiApparati = db.vediTutteLeTuple(tabella);
            if(datiApparati != null){
                for(Object[] record: datiApparati){
                    Apparato apparato = new Apparato(
                            (String)record[0],   // nome
                            (String)record[1],   // tipo
                            (String)record[2],   // rete (dominio)
                            (String)record[3],   // IP
                            (String)record[4],   // MAC PC
                            (String)record[5],   // MAC VOIP
                            (String)record[6],   // posizione (ufficio)
                            (String)record[7],   // utilizzatore
                            (Boolean)record[8],  // accesso internet
                            (Integer)record[9], // sigillo
                            (String)record[10],  // password
                            (String)record[11]   // stato
                    );
                    
                    String workgroup =   
                            record[2] != null  
                            ?  (record[2].toString().length() > 0) 
                                ? record[2].toString() 
                                : R.ChiaviDati.NESSUNA_RETE
                            :  R.ChiaviDati.NESSUNA_RETE; 
                    
                    String ufficio =   
                            record[6] != null  
                            ?  (record[6].toString().length() > 0)
                                ? record[6].toString() 
                                : R.ChiaviDati.NESSUN_GRUPPO
                            :  R.ChiaviDati.NESSUN_GRUPPO; 
                    
                    DatiPosizione posizione = new DatiPosizione();
                    String responsabile = posizione.info(ufficio).getResponsabile();
                    
                    
                    if(!lista.containsKey(workgroup)){
                       lista.put(workgroup, new HashMap<>());
                    }
                    if(!lista.get(workgroup).containsKey(responsabile)){
                        lista.get(workgroup).put(responsabile, new HashMap<>());
                    }
                    if(!lista.get(workgroup).get(responsabile).containsKey(ufficio)){
                        lista.get(workgroup).get(responsabile).put(ufficio, new ArrayList<>());
                    }
                    lista.get(workgroup).get(responsabile).get(ufficio).add(apparato);
                    
                }
            }
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiApparato.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    

    /**
     * Elenco dei nomi degli apparati
     * @return 
     */
    public ArrayList<String> nomiApparati() {
        return super.lista(0);
    }
    

    
    /**
     * Trova tutte le informazioni su un apparato.
     * 
     * @param nome
     * @return Apparato
     */
    public Apparato info(String nome){
        try {
            db.connetti();
            Object[] record = db.vediTupla(tabella, new Object[]{nome});
            db.chiudi();
            
            if(record != null)
                return new Apparato(
                        nome,
                        (String)record[1],  // tipo
                        (String)record[2],  // gruppo (dominio rete)
                        (String)record[3],  // IP
                        (String)record[4],  // MAC rete
                        (String)record[5],  // MAC VOIP
                        (String)record[6],  // posizione
                        (String)record[7],  // utilizzatore
                        (Boolean)record[8], // internet
                        (Integer)record[9], // sigillo
                        (String)record[10], // password
                        (String)record[11]  // stato
                );
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiApparato.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
