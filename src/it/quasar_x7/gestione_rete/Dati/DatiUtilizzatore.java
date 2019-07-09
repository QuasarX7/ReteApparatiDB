package it.quasar_x7.gestione_rete.Dati;

import it.quasar_x7.gestione_rete.modello.Utilizzatore;
import it.quasar_x7.java.BaseDati.EccezioneBaseDati;
import it.quasar_x7.java.BaseDati.Relazione;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dr. Domenico della Peruta
 */
public class DatiUtilizzatore extends DatiDB{
    
    public static final String NOME_TABELLA    = "utilizzatore";
    protected static final String VOCE_TABELLA_GRADO       = "grado";
    protected static final String VOCE_TABELLA_COGNOME     = "cognome";
    protected static final String VOCE_TABELLA_NOME        = "nome";
    protected static final String VOCE_TABELLA_ACCOUNT     = "account";
    protected static final String VOCE_TABELLA_MAIL        = "mail";
    
    
    
    public DatiUtilizzatore() {
        try {
            tabella = new Relazione(NOME_TABELLA);
            tabella.creaAttributo(VOCE_TABELLA_ACCOUNT,  TESTO, "", Relazione.CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_GRADO, TESTO, null, Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_COGNOME,  TESTO, "", Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_NOME,  TESTO, "", Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_MAIL,   TESTO, "", Relazione.NO_CHIAVE);
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiUtilizzatore.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    @Override
    public ArrayList<ArrayList<String>> tabella() {
        ArrayList<ArrayList<String>> tab = new ArrayList<>();
        try {
            db.connetti();
            ArrayList<Object[]> righe = db.vediTutteLeTuple(tabella);
            if(righe != null)
                for(Object[] record : righe){
                    if(record != null){
                       ArrayList<String> riga =  new ArrayList<>();
                       riga.add((String)record[0]);
                       riga.add(DatiUtilizzatore.nominativo(record));
                       riga.add((String)record[4]);
                       tab.add(riga);
                    }
                }
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiUtilizzatore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tab;
    }
    
    /**
     * Metodo statico che permette di scomporre il nominativo nelle sue componenti base:
     * "grado","cognome" e "nome".
     * 
     * @param stringa
     * @return 
     */
    static public String[] nominativo(String stringa){
        return stringa.split(" ˙ ");
    }
    
    /**
     * Metodo statico che crea un'unica stringa che contiene il nominativo dell'utilizzatore:
     * "grado","cognome" e "nome".
     * 
     * @param record
     * @return 
     */
    static public String nominativo(Object[] record){
        DatiGrado grado = new DatiGrado();
        
        return String.format(
                "%s ˙ %s ˙ %s",
                grado.sigla((String)record[1]),
                record[2] != null ? record[2].toString() : "",
                record[3] != null ? record[3].toString() : ""
        );
    }
    
    /**
     * Permette di trovare il nominativo ("grado","cognome" e "nome") dell'utente
     * associato all'account.
     * 
     * @param account
     * @return 
     */
    public String trovaNominativo(String account){
        Utilizzatore utilizzatore = info(account);
        if(utilizzatore != null)
            return utilizzatore.getNome();
        return "";
    }
    
    /**
     * Trova l'utilizzatore associato ad un account.
     * 
     * @param account
     * @return Utilizzatore
     */
    public Utilizzatore info(String account){
        try {
            db.connetti();
            Object[] record = db.vediTupla(tabella, new Object[]{account});
            
            db.chiudi();
            if(record != null)
                return new Utilizzatore(
                        DatiUtilizzatore.nominativo(record),
                        (String)record[0],
                        (String)record[4]
                );
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiUtilizzatore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Utilizzatore> lista() {
        ArrayList<Utilizzatore> nomi = new ArrayList<>();
        try {
            db.connetti();
            ArrayList<Object[]> vociTabella = db.vediTutteLeTuple(tabella);
            if(vociTabella != null)
                for(Object[] record : vociTabella){
                    if(record != null){
                        nomi.add(
                              new Utilizzatore(
                                      DatiUtilizzatore.nominativo(record),
                                      (String)record[0],
                                      (String)record[4]
                              )
                        );

                    }
                }
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiUtilizzatore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nomi;
    }
    
    public ArrayList<String> listaNomi() {
        return lista(3);
    }
    
    public ArrayList<String> listaCognomi() {
        return lista(2);
    }

    public ArrayList<String> listaMail() {
        return lista(4);
    }
    
    public ArrayList<String> listaAccount() {
        return lista(0);
    }


}
