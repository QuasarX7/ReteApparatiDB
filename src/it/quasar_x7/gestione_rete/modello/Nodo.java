package it.quasar_x7.gestione_rete.modello;

import java.util.ArrayList;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;

/**
 * Rappesenta un generico elemento grafico della tabella ad albero degli apparati.
 * 
 * @author Dr. Domenico della Peruta
 */
public class Nodo implements Cloneable{
    
    protected StringProperty nome;
    
    public Nodo(String nome){
        this.nome = new SimpleStringProperty(nome);
    }
    
    public String getNome(){
        return this.nome.get();
    }

    @Override
    public String toString() {
        return getNome();
    }
    
    public ArrayList<TextFlow> info(){
        ArrayList<TextFlow> lista = new ArrayList<>();
        TextFlow testo = new TextFlow();
        Text etichetta = new Text(nome.get());
        etichetta.setFont(Font.font("Arial black", 16));
        testo.getChildren().add(etichetta);
        lista.add(testo);
        return lista;
    }
    
    protected TextFlow voceInfo(String infoEtichetta, String infoValore){
        final String separatore = ":\t ";
        
        TextFlow testo = new TextFlow();
        
        Text etichetta = new Text(infoEtichetta+separatore);
        etichetta.setFont(Font.font("Arial", 13));
        
        Text valore = new Text(infoValore);
        valore.setFont(Font.font("Arial black", 13));
        
        testo.getChildren().addAll(etichetta,valore);
        return testo;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.nome);
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
        final Nodo other = (Nodo) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
  
    

}
