package it.quasar_x7.gestione_rete.Dati;

import it.quasar_x7.gestione_rete.programma.R;
import it.quasar_x7.java.BaseDati.AccessoFileExcel;
import it.quasar_x7.java.BaseDati.AccessoSQLite;
import it.quasar_x7.java.BaseDati.Attributo;
import it.quasar_x7.java.BaseDati.DatoBooleano;
import it.quasar_x7.java.BaseDati.DatoInteroCorto;
import it.quasar_x7.java.BaseDati.DatoTesto;
import it.quasar_x7.java.BaseDati.Dominio;
import it.quasar_x7.java.BaseDati.EccezioneBaseDati;
import it.quasar_x7.java.BaseDati.FunzioneSQL;
import it.quasar_x7.java.BaseDati.Relazione;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe astratta che contiene gli elementi comuni a tutte le classi di gestione
 * dei dati.
 * @author Dr. Domenico della Peruta
 */
public abstract class DatiDB implements Dati {
    
    protected Relazione tabella = null;
    
    protected AccessoSQLite db = new AccessoSQLite(R.File.DB);

    protected static Dominio TESTO = new DatoTesto();
    protected static Dominio INTERO = new DatoInteroCorto();
    protected static Dominio BOOLEANO = new DatoBooleano();
   
   
    public boolean esiste(){
        try {
            db.connetti();
            db.interrogazioneSQL(
                    String.format("SELECT count(*) FROM `%s`", tabella.nome()), 
                    new Attributo[]{
                        new Attributo("count(*)",new FunzioneSQL(1),false)
                    }
            );
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
            if(ex.getMessage().contains("no such table")){
                return false;
            }
            Logger.getLogger(DatiDB.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return true;
    }
   
    public Object[] chiave(int id) {
        try {
            db.connetti();
            int riga=0;
            Object[] key = null;
            for(Object[] record : db.vediTutteLeTuple(tabella)){
                if(riga++ == id){
                    if(record != null){
                        key = new Object[tabella.chiave().size()];
                        for(int i=0,j=0; i < record.length ;i++){//scorrere il record...
                            if(tabella.vediAttributo(i).chiave())
                                key[j++] = record[i];
                        }
                    }
                    break;
                }
            }
            db.chiudi();
            return key;
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   
   public ArrayList<String> attributi() {
        ArrayList<String>lista = new ArrayList<>();
        for(Attributo colonna:tabella.vediTuttiAttributi()){
            lista.add(colonna.nome());
        }
        return lista;
    }
   
   
   
    public ArrayList<ArrayList<String>> tabella() {
        try {
            ArrayList<ArrayList<String>> tab = new ArrayList<>();
            db.connetti();
            ArrayList<Object[]> righe = db.vediTutteLeTuple(tabella);
            if(righe != null)
                for(Object[] record :righe ){
                    if(record != null){
                       ArrayList<String> riga =  new ArrayList<>();
                       for(Object voce:record){
                           if(voce != null)
                                riga.add(voce.toString());
                       }
                       tab.add(riga);
                    }
                }
            db.chiudi();
            return tab;
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
       
    @Override
    public boolean aggiungi(Object[] record) {
        try {
            db.connetti();
            db.aggiungiTupla(tabella, record);
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
            //Logger.getLogger(DatiDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean modifica(Object[] chiave, Object[] record) {
        try {
            db.connetti();
            db.modificaTupla(tabella, chiave,record);
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
            try {
                db.chiudi();
            } catch (EccezioneBaseDati ex1) {
                Logger.getLogger(DatiDB.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
        return true;
    }

    @Override
    public Object[] accedi(Object[] chiave) {
        Object[] record = null;
        try {
            db.connetti();
            record = db.vediTupla(tabella, chiave);
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
        }
        return record;
    }
   
  
    public boolean elimina(int id){
        Object[] key = chiave(id);
        if(key != null)
            return elimina(key);
        return false;
    }

    @Override
    public boolean elimina(Object[] chiave) {
        try {
            db.connetti();
            db.eliminaTupla(tabella, chiave);
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
            return false;
        }
        return true;
    }

    @Override
    public void creaTabella() throws EccezioneBaseDati {
        db.connetti();
        db.generaTabella(tabella);
        db.chiudi();
    }
    
    /**
     * Stampa gli elementi di una chiave separati da " ˙ ".
     * 
     * @param dati
     * @param record
     * @return 
     */
    public static String stampaChiave(DatiDB dati,Object[] record){
        String s = "";
        int i=0;
        Object[] chiave = chiave(dati,record);
        if(record != null)
            for(Object voce: chiave){
                s += (i == 0 ? "" : " ˙ ")+ voce.toString();
                i++;
            }
        return s;
    }
    
    public static Object[] chiave(DatiDB dati,Object[] record){
        ArrayList<Object> lista = new ArrayList<>();
        int i=0;
        if(record != null)
		    for(Object voce: record){
		        if(i < dati.tabella.vediTuttiAttributi().size())
		            if(dati.tabella.vediAttributo(i).chiave())
		                lista.add(voce);
		        i++;
		    }
        return lista.toArray();
    }
     
    public static String stampa(Object[] record){
        String s = "\nDATI INPUT »»»»»»»»\n";
        int i = 0;
        if(record != null)
            for(Object voce:record){
                s += "["+i + "]\t";
                if(voce != null)
                    s += voce.toString();
                s += "\n";
                i++;
            }
        return s + "«««««««««««««««««««";
    }
    
    /**
     * Esporta i dati della tabella in un foglio di calcolo 'xls'
     * @param file 
     * @throws it.quasar_x7.java.BaseDati.EccezioneBaseDati 
     */
    public void salva(File file) throws EccezioneBaseDati{
        
        AccessoFileExcel.creaFile(file,tabella);
        AccessoFileExcel scriviFile = new AccessoFileExcel(file.getAbsolutePath());
        scriviFile.connetti();
        db.connetti();
        ArrayList<Object[]> righe = db.vediTutteLeTuple(tabella);
        if(righe != null){
            for(Object[] riga: righe){
                if(riga != null){
                    scriviFile.aggiungiTupla(tabella, riga);
                }
            }
        }
        db.chiudi();
        scriviFile.chiudi();
        
    }
    
    public boolean carica(File file){
        try {
            
            AccessoFileExcel leggiFile = new AccessoFileExcel(file.getAbsolutePath());
            leggiFile.connetti();
            if(leggiFile.esiste(tabella))
                leggiFile.integrita(tabella);
            else{
                leggiFile.chiudi();
                throw new EccezioneBaseDati("Errore lettura file",String.format("foglio nome `%s` non trovato...",tabella.nome()) );
            }
            
            db.connetti();
            ArrayList<Object[]> righe = leggiFile.vediTutteLeTuple(tabella);

            if(righe != null)
                for(Object[] riga: righe){
                    if(riga != null)
                        db.aggiungiTupla(tabella, riga);
        
                }
            leggiFile.chiudi();
            db.chiudi();
            
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean elimataTuttiRecord() {
        try {
            db.connetti();
            db.eliminaTutteLeTuple(tabella);
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    /**
     * Restituisce tutti i valori di una colonna della tabella.
     * 
     * @param indice
     * @return 
     */
    protected ArrayList<String> lista(int indice) {
        ArrayList<String> lista =  new ArrayList<>();
        try {
            db.connetti();
            ArrayList<Object[]> gradi = db.vediTutteLeTuple(tabella);
            if(gradi != null)
                for(Object[] record : gradi){
                    if(record != null)
                        if(record[indice] != null)
                            lista.add(record[indice].toString());
                }
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiGrado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    /**
     * Effettua la modivica dei valori in una colonna di una tabella.
     * 
     * @param nomeTebella
     * @param colonna
     * @param vecchia
     * @param nuova
     * @return 
     */
    public boolean aggiorna(String nomeTebella, String colonna, String vecchia, String nuova){
        try {
            db.connetti();
            db.interrogazioneDiModificaSQL(
                    String.format(
                            "UPDATE `%s` SET `%s` = ? WHERE `%s` = ? ;",
                            //UPDATE
                            nomeTebella,
                            //SET
                            colonna,
                            // WHERE
                            colonna
                    ),
                    nuova,vecchia
            );
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiApparato.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
