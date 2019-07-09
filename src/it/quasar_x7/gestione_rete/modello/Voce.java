package it.quasar_x7.gestione_rete.modello;

import java.util.ArrayList;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Dr. Domenico della Peruta
 */
public class Voce extends Nodo{
    
    protected StringProperty tipo;
    
    
    
    public Voce(String tipo, String nome) {
        super(nome);
        this.tipo = new SimpleStringProperty(tipo);
    }
    
    public String getTipo(){
        return this.tipo.get();
    }

    @Override
    public String toString() {
        return String.format("%s: \t«%s»",getTipo(),getNome());
    }
    
    @Override
    public ArrayList<TextFlow> info(){
        final String separatore = ":\t ";
        ArrayList<TextFlow> lista = new ArrayList<>();
        TextFlow testo = new TextFlow();
        
        Text etichettaTipo = new Text(tipo.get()+separatore);
        etichettaTipo.setFont(Font.font("Arial", 13));
        
        Text etichettaValore = new Text(nome.get());
        etichettaValore.setFont(Font.font("Arial black", 13));
        
        
        testo.getChildren().addAll(etichettaTipo,etichettaValore);
        lista.add(testo);
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
        final Voce other = (Voce) obj;
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
    
 

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.tipo);
        hash = 89 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    
    
}
