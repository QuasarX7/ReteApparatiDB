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
public class ConnessioneSwitch extends Nodo{
    
    protected StringProperty porta;
    
    
    
    public ConnessioneSwitch(String nome, String porta) {
        super(nome);
        this.porta = new SimpleStringProperty(porta);
    }
    
    public String getPorta(){
        return this.porta.get();
    }

    @Override
    public String toString() {
    	return String.format("Switch `%s`", getNome());
    }
    
    @Override
    public ArrayList<TextFlow> info(){
        ArrayList<TextFlow> lista = new ArrayList<>();
        lista.add(voceInfo(R.Etichette.SWITCH,getNome()));
        lista.add(voceInfo(R.Etichette.PORTA,getPorta()));
        
        return lista;
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
        final ConnessioneSwitch other = (ConnessioneSwitch) obj;
        if (!Objects.equals(this.porta.get(), other.porta.get())) {
            return false;
        }
        if (!Objects.equals(this.nome.get(), other.nome.get())) {
            return false;
        }
        return true;
    }
    
 

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.porta);
        return hash;
    }

    
    
}
