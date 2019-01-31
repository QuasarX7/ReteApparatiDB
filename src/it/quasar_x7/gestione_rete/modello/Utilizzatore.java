package it.quasar_x7.gestione_rete.modello;

import it.quasar_x7.gestione_rete.Dati.DatiUtilizzatore;
import it.quasar_x7.gestione_rete.programma.R;
import java.util.ArrayList;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Dr. Domenico della Peruta
 */
public class Utilizzatore extends Nodo{
    
    protected StringProperty account = null;
    protected StringProperty mail = null;
    
    public Utilizzatore(String nome,String account, String mail) {
        super(nome);
        this.account = new SimpleStringProperty(account);
        this.mail = new SimpleStringProperty(mail);
    }

    public String getAccount() {
        return account.get();
    }

    public String getMail() {
        return mail.get();
    }

    @Override
    public String toString() {
        return String.format("%s",nome.get());
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.account);
        hash = 97 * hash + Objects.hashCode(this.mail);
        
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utilizzatore other = (Utilizzatore) obj;
        if (!Objects.equals(this.account.get(), other.account.get())) {
            return false;
        }
        if (!Objects.equals(this.mail.get(), other.mail.get())) {
            return false;
        }
        if (!Objects.equals(this.nome.get(), other.nome.get())) {
            return false;
        }
        return true;
    }

    
    @Override
    public ArrayList<TextFlow> info(){
        ArrayList<TextFlow> lista = new ArrayList<>();
        String[] nominativo = DatiUtilizzatore.nominativo(getNome());
        if(nominativo != null)
            if(nominativo.length == 3){
                lista.add(voceInfo(R.Etichette.GRADO,nominativo[0]));
                lista.add(voceInfo(R.Etichette.COGNOME,nominativo[1]));
                lista.add(voceInfo(R.Etichette.NOME,nominativo[2]));
            }
        lista.add(voceInfo(R.Etichette.MAIL,getMail()));
        lista.add(voceInfo(R.Etichette.ACCOUNT,getAccount()));
        
        return lista;
    }
    
}
