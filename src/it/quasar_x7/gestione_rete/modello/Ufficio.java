package it.quasar_x7.gestione_rete.modello;

import it.quasar_x7.gestione_rete.Dati.DatiResponsabileSito;
import static it.quasar_x7.gestione_rete.programma.Programma.dati;
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
public class Ufficio extends Nodo{

    private StringProperty responsabile = null;

    
    public Ufficio(String nome, String responsabile) {
        super(nome);
        this.responsabile = new SimpleStringProperty(responsabile);
    }
    
    
    public String getResponsabile() {
        return responsabile.get();
    }

    @Override
     public ArrayList<TextFlow> info(){
        ArrayList<TextFlow> lista = new ArrayList<>();
        DatiResponsabileSito datiResponsabile = (DatiResponsabileSito)dati.get(DatiResponsabileSito.NOME_TABELLA);
        
        lista.add(voceInfo(R.Etichette.NOME,getNome()));
        lista.add(voceInfo(R.Etichette.RESPONSABILE,getResponsabile()));
        lista.add(voceInfo(R.Etichette.NOME_RESPONSABILE,datiResponsabile.nominativoResponsabileSito(getResponsabile())));
        
        return lista;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.responsabile);
        hash = 97 * hash + Objects.hashCode(this.nome);
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
        final Ufficio other = (Ufficio) obj;
        if (!Objects.equals(this.responsabile.get(), other.responsabile.get())) {
            return false;
        }
        if (!Objects.equals(this.nome.get(), other.nome.get())) {
            return false;
        }
        return true;
    }
     
     
    
}
