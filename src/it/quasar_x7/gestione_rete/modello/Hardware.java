package it.quasar_x7.gestione_rete.modello;

import java.util.ArrayList;

import it.quasar_x7.gestione_rete.programma.R;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.text.TextFlow;

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
    
    @Override
    public ArrayList<TextFlow> info(){
        
        if(modello == null && matricola == null && nome == null){
            return super.info();
        }else{
            ArrayList<TextFlow> lista = new ArrayList<>();
        
            lista.add(voceInfo(R.Etichette.NOME,getNome()));
            lista.add(voceInfo(R.Etichette.CASA,getCasa()));
            lista.add(voceInfo(R.Etichette.MODELLO,getModello()));
            lista.add(voceInfo(R.Etichette.MATRICOLA,getMatricola()));
            lista.add(voceInfo(R.Etichette.STATO,getStato()));
            lista.add(voceInfo(R.Etichette.NOTE,getNote()));
            return lista;
        }
        
    }
    
}
