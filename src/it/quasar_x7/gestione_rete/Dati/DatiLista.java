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
public abstract class DatiLista extends DatiDB {
    
    private String voceTabella = "";
    
    public DatiLista(String nomeTabella,String voceTabella) {
        this.voceTabella = voceTabella;
        tabella = new Relazione(nomeTabella);
        try {
            tabella.creaAttributo(voceTabella,   TESTO, "", Relazione.CHIAVE);
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(DatiLista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Lista di tutte le voci.
     *
     * @return
     */
    public ArrayList<String> lista() {
        try {
            ArrayList<String> voci = new ArrayList<>();
            db.connetti();
            String sql = String.format(
                    "SELECT `%s` " +
                    "FROM `%s`; ", 
                    tabella.vediAttributo(voceTabella).nome(),
                    tabella.nome()
            );
            ArrayList<Object[]> record = db.interrogazioneSQL(
                    sql,
                    new Attributo[]{tabella.vediAttributo(voceTabella)}
            );
            if (record != null) {
                for (Object[] voce : record) {
                    if (voce != null) {
                        if (voce[0] != null) {
                            voci.add(voce[0].toString());
                        }
                    }
                }
            }
            db.chiudi();
            return voci;
        } catch (EccezioneBaseDati ex) {
            return null;
        }
    }
    
}
