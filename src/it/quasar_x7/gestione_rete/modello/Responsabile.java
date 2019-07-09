package it.quasar_x7.gestione_rete.modello;

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
public class Responsabile extends Nodo{
    
    private StringProperty nominativo = null;
    
    
    public Responsabile(String responsabile,String nominativo) {
        super(responsabile);
        this.nominativo = new SimpleStringProperty(nominativo);
    }

    public String getNominativo() {
        return nominativo.get();
    }

    @Override
    public ArrayList<TextFlow> info(){
       ArrayList<TextFlow> lista = new ArrayList<>();
       
       lista.add(voceInfo(R.Etichette.RESPONSABILE,getNome()));
       lista.add(voceInfo(R.Etichette.NOMINATIVO,getNominativo()));
       
       return lista;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.nominativo);
        hash = 41 * hash + Objects.hashCode(this.nome);
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
        final Responsabile other = (Responsabile) obj;
        if (!Objects.equals(this.nominativo.get(), other.nominativo.get())) {
            return false;
        }
        if (!Objects.equals(this.nome.get(), other.nome.get())) {
            return false;
        }
        return true;
    }
    
    
    
}
