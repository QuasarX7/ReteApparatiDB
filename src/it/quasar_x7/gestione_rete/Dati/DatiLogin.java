package it.quasar_x7.gestione_rete.Dati;

import it.quasar_x7.java.BaseDati.Attributo;
import it.quasar_x7.java.BaseDati.EccezioneBaseDati;
import it.quasar_x7.java.BaseDati.Relazione;
import it.quasar_x7.modello.DatiUtente;
import it.quasar_x7.modello.LivelloAccesso;

import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Dr. Domenico della Peruta
 */
public class DatiLogin extends DatiDB implements DatiUtente{
    
    
    

    public static final String NOME_TABELLA    = "login";
    protected static final String VOCE_TABELLA_UTENTE   = "utente";
    protected static final String VOCE_TABELLA_PASSWORD = "password";
    protected static final String VOCE_TABELLA_LIVELLO  = "livello";
    
    
    
    public DatiLogin() {
        try {
            tabella = new Relazione(NOME_TABELLA);
            tabella.creaAttributo(VOCE_TABELLA_UTENTE,   TESTO, "", Relazione.CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_PASSWORD, TESTO, "", Relazione.NO_CHIAVE);
            tabella.creaAttributo(VOCE_TABELLA_LIVELLO,  TESTO, null, Relazione.NO_CHIAVE);
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }



    @Override
    public void modificaLivello(String utente, String livello) {
        try {
            db.connetti();
            Object[] record = db.vediTupla(tabella, new Object[]{utente});
            record[2] = livello;
            db.modificaTupla(tabella, new Object[]{utente}, record);
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Visualizza tutti gli account registati per l'accesso.
     * 
     * @return Restituisce una lista di account, null se si è virificato un errore di accesso
     */
    @Override
    public ArrayList<String> tuttiUtenti() {
        try {
            ArrayList<String> utenti = new ArrayList<>();
            db.connetti();
            String sql = String.format(
                    "SELECT `%s` "+
                            "FROM `%s`; ",
                    tabella.vediAttributo(VOCE_TABELLA_UTENTE).nome(),
                    tabella.nome()
            );

            ArrayList<Object[]> record = db.interrogazioneSQL(
                    sql,
                    new Attributo[]{
                        tabella.vediAttributo(VOCE_TABELLA_UTENTE)
                    }
            );
            if(record != null)
                for(Object[] voce:record){
                    if(voce != null){
                        if(voce[0] != null){
                            utenti.add(voce[0].toString());
                        }
                    }
                    }
            
            db.chiudi();
            return utenti;
        } catch (EccezioneBaseDati ex) {
            return null;
        }
    }

    @Override
    public String livello(String utente) {
        String livello = "";
        try {
            db.connetti();
            Object[] record = db.vediTupla(tabella, new Object[]{utente});
            if(record != null)
                if(record[2] instanceof String)
                    livello = (String)record[2];
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
        }
        return livello;
    }

    @Override
    public boolean verifica(String utente, String livello) {
        boolean stessoLivello = false;
        try {
            db.connetti();
            Object[] record = db.vediTupla(tabella, new Object[]{utente});
            if(record != null)
                if(record[2] instanceof String)
                    stessoLivello = (livello.equals((String)record[2]));
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stessoLivello;
    }

    @Override
    public void eliminaUtente(String utente) {
        elimina(new Object[]{utente});
    }

    @Override
    public void aggiungiUtente(String nome, String password) {
        String livello = LivelloAccesso.OSSERVATORE;
        if(tuttiUtenti() != null)
            if(tuttiUtenti().isEmpty())
                livello = LivelloAccesso.AMMINISTRATORE;
        aggiungi(new Object[]{nome,codifica(password),livello});
    }



	@Override
	public void modificaPassword(String utente, String password) {
		try {
            db.connetti();
            Object[] record = db.vediTupla(tabella, new Object[]{utente});
            record[1] = codifica(password);
            db.modificaTupla(tabella, new Object[]{utente}, record);
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	/**
	 * Codifica in Base64.
	 * 
	 * @param password
	 * @return
	 */
	private String codifica(String password) {
		return Base64.getEncoder().encodeToString(password.getBytes());
	}
    
	/**
	 * Decodifica in Base64.
	 * 
	 * @param password
	 * @return
	 */
	private String decodifica(String password) {
		return new String(Base64.getDecoder().decode(password));
	}

	@Override
    public Object[] accedi(Object[] chiave) {
        Object[] record = null;
        try {
            db.connetti();
            record = db.vediTupla(tabella, chiave);
            if(record[1] != null)
            	record[1] = decodifica((String) record[1]);
            db.chiudi();
        } catch (EccezioneBaseDati ex) {
        }
        return record;
    }
    
    
}
