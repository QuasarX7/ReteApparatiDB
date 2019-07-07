package it.quasar_x7.gestione_rete.modello;

import it.quasar_x7.java.utile.DataOraria;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Dr. Domenico della Peruta
 */
public class Intervento extends Nodo{

    private ObjectProperty<DataOraria> data = null;
    private StringProperty apparato = null;
    private StringProperty motivo = null;
    private StringProperty azione = null;
    private StringProperty esito = null;
    
    
    public Intervento(String ticket, DataOraria data, String apparato,String motivo,String azione,String esito) {
        super(ticket);
        this.data = new SimpleObjectProperty<DataOraria>(data);
        this.apparato = new SimpleStringProperty(apparato);
        this.motivo = new SimpleStringProperty(motivo);
        this.azione = new SimpleStringProperty(azione);
        this.esito = new SimpleStringProperty(esito);
    }

    public DataOraria getData() {
		return data.get();
	}

    public String getMotivo() {
		return motivo.get();
	}
    
    public String getApparato() {
		return apparato.get();
	}
	
	public String getAzione() {
		return azione.get();
	}
	
	public String getEsito() {
		return esito.get();
	}
	
	@Override
	public String toString() {
		return String.format("%s\t%s",data.get().stampaGiorno(),nome.get());
	}

	
    
}
