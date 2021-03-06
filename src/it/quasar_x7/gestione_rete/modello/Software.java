package it.quasar_x7.gestione_rete.modello;

import it.quasar_x7.gestione_rete.programma.R;
import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Dr. Domenico della Peruta
 */
public class Software extends Nodo{
    
    protected StringProperty licenza = null;
    protected StringProperty tipo = null;
    protected StringProperty casa = null;
    protected StringProperty note = null;
    protected BooleanProperty predefinito = null;
    
    public Software(String nome, String licenza,String tipo, String casa,String note,Boolean predefinito) {
        super(nome);
        this.licenza = new SimpleStringProperty(licenza);
        this.tipo = new SimpleStringProperty(tipo);
        this.casa = new SimpleStringProperty(casa);
        this.note = new SimpleStringProperty(note);
        this.predefinito = new SimpleBooleanProperty(predefinito);
    }
    
    public Software(String nome, String licenza,String tipo, String casa,String note) {
        this(nome,licenza,tipo,casa,note,false);
    }
    
    public Software(String nome){
        super(nome);
    }

    public Software(Object[] record) {
        this((String)record[0],(String)record[1],(String)record[2],(String)record[3],(String)record[4],(Boolean)record[5]);
    }

    public String getLicenza() {
        return licenza.get();
    }

    public String getTipo() {
        return tipo.get();
    }

    public String getCasa() {
        return casa.get();
    }

    public String getNote() {
        return note.get();
    }
    
    public Boolean getPredefinito() {
        return predefinito.get();
    }
    
    
    @Override
    public ArrayList<TextFlow> info(){
        
        if(licenza == null && tipo == null && casa == null && note == null){
            return super.info();
        }else{
            ArrayList<TextFlow> lista = new ArrayList<>();
        
            lista.add(voceInfo(R.Etichette.NOME,getNome()));
            lista.add(voceInfo(R.Etichette.LICENZA,getLicenza()));
            lista.add(voceInfo(R.Etichette.TIPO,getTipo()));
            lista.add(voceInfo(R.Etichette.CASA,getCasa()));
            lista.add(voceInfo(R.Etichette.NOTE,getNote()));
            return lista;
        }
        
    }

	public void setPredefinito(Boolean valore) {
		predefinito.setValue(valore);
	}
    
	@Override
	public String toString() {
		return String.format("%s%s", nome.get(), licenza != null ? String.format(" `%s`", licenza.get()) : "");
	}
}
