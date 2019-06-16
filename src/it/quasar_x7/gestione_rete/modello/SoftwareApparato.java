package it.quasar_x7.gestione_rete.modello;

import java.util.ArrayList;
import java.util.HashMap;

import it.quasar_x7.gestione_rete.Dati.DatiDB;
import it.quasar_x7.gestione_rete.Dati.DatiSoftwareApparato;
import it.quasar_x7.gestione_rete.programma.R;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class SoftwareApparato extends Nodo{

	protected ObservableList<Software> software = FXCollections.observableArrayList();
	
	public SoftwareApparato(String nomeApparato,HashMap<String,DatiDB> dati) {
		super(nomeApparato);
		ArrayList<Software> swApp = ((DatiSoftwareApparato)dati.get(DatiSoftwareApparato.NOME_TABELLA)).listaSW(nomeApparato);
        if(swApp != null)
        	software.addAll(swApp);
	}

	public ObservableList<Software> getSoftware() {
		return software;
	}
	
	@Override
    public String toString() {
        return String.format("%s `%s`",R.Etichette.SW,getNome());
    }

	@Override
	public ArrayList<TextFlow> info(){
        ArrayList<TextFlow> lista = new ArrayList<>();
        TextFlow testo = new TextFlow();
        Text etichetta = new Text(toString());
        etichetta.setFont(Font.font("Arial black", 16));
        testo.getChildren().add(etichetta);
        lista.add(testo);
        for(Software sw: software) {
        	lista.add(voceInfo(sw.getNome(), sw.getLicenza()));
        }
        return lista;
    }
	
	public String apparato() {
		return getNome();
	}
}
