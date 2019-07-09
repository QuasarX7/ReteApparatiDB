package it.quasar_x7.gestione_rete.Dati;

import it.quasar_x7.java.BaseDati.Attributo;
import it.quasar_x7.java.BaseDati.EccezioneBaseDati;
import it.quasar_x7.java.BaseDati.Relazione;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dr. Domenico della Peruta
 */
public class DatiGrado extends DatiDB{
    
    public static final String NOME_TABELLA    = "grado";
    
    protected static final String VOCE_TABELLA_ID       = "indice";
    protected static final String VOCE_TABELLA_NOME     = "nome";
    protected static final String VOCE_TABELLA_RUOLO    = "ruolo";
    protected static final String VOCE_TABELLA_SIGLA    = "sigla";
    
    
    
    public DatiGrado() {
        try {
            tabella = new Relazione(NOME_TABELLA);
            tabella.creaAttributo(VOCE_TABELLA_NOME, TESTO, "", Relazione.CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_RUOLO,  TESTO, null, Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_SIGLA,  TESTO, "", Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_ID,   INTERO, null, Relazione.NO_CHIAVE);
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiGrado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    public String sigla(String grado){
        try {
            db.connetti();
            Object[] record = db.vediTupla(tabella, new Object[]{grado});
            if(record != null){
                if(record[2] != null)
                    return record[2].toString();
            }
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiGrado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    /**
     * Cerca un eventuale nome esteso del grado corrispondente di una sigla.
     * 
     * @param sigla
     * @return  restituise un stringa vuota ( "" ) se non trova il grado corrispondente.
     */
    public String trovaGradoEsteso(String sigla) {
        if(sigla != null)
            if(!sigla.isEmpty())
                try {
                    db.connetti();
                    String SQL = String.format(
                              "SELECT `%s` "
                            + "FROM `%s` "
                            + "WHERE `%s` = '%s' ;",
                            //select
                            VOCE_TABELLA_NOME,
                            //from
                            NOME_TABELLA,
                            //where
                            VOCE_TABELLA_SIGLA, 
                            sigla
                    );

                    Attributo[] colonne = new Attributo[]{
                        tabella.vediAttributo(VOCE_TABELLA_NOME)
                    };
                    ArrayList<Object[]> record = db.interrogazioneSQL(SQL, colonne);
                    if(record != null){
                        if(record.size() > 0){
                            String nomeGrado = (String)record.get(0)[0];
                            if(nomeGrado != null)
                                return nomeGrado;
                        }
                    }
                    db.chiudi();
                } catch (EccezioneBaseDati ex) {
                    Logger.getLogger(DatiGrado.class.getName()).log(Level.SEVERE, null, ex);
                }
        return "";
    }
    

    public ArrayList<String> lista(boolean sigla) {
        int indice = sigla ? 2 : 0;
        return lista(indice);
    }
    
    

   
}
