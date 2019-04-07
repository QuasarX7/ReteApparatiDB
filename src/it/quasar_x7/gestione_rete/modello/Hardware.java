package it.quasar_x7.gestione_rete.modello;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Dr. Domenico della Peruta
 */
public class Hardware extends Nodo{
    protected StringProperty modello;
    protected StringProperty matricola;
    protected StringProperty casa;
    protected StringProperty NUC;
    protected StringProperty stato;
    protected StringProperty note;
    
    public Hardware(String tipo, String modello, String matricola, String casa, String NUC, String stato,String note) {
        super(tipo);
        this.modello = new SimpleStringProperty(modello);
        this.matricola = new SimpleStringProperty(matricola);
        
        this.casa = new SimpleStringProperty(casa);
        this.NUC = new SimpleStringProperty(NUC);
        this.stato = new SimpleStringProperty(stato);
        this.note = new SimpleStringProperty(note);
    }

    public Hardware(Object[] record) {
		this((String)record[0],(String)record[1],(String)record[2],(String)record[3],(String)record[4],(String)record[5],(String)record[6]);
	}
    

	public String getModello() {
        return modello.get();
    }

    public String getMatricola() {
        return matricola.get();
    }

    public String getCasa() {
        return casa.get();
    }
    
    public String getNUC() {
        return NUC.get();
    }
    
    public String getStato() {
        return stato.get();
    }

    public String getNote() {
        return note.get();
    }
    
    
    
}
