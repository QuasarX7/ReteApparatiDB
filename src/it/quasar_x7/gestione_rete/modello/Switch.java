package it.quasar_x7.gestione_rete.modello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import it.quasar_x7.gestione_rete.Dati.DatiApparato;
import it.quasar_x7.gestione_rete.Dati.DatiConnessioneSwitch;
import it.quasar_x7.gestione_rete.Dati.DatiDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Switch extends Nodo {

	protected ObservableList<Apparato> apparati = FXCollections.observableArrayList();
	
	public Switch(String nome,HashMap<String,DatiDB> dati) {
		super(nome);
		 TreeSet<String> app = ((DatiConnessioneSwitch)dati.get(DatiConnessioneSwitch.NOME_TABELLA)).listaNomiApparati(nome);
        if(app != null) {
        	for(String nomeApparato: app) {
        		Apparato info = ((DatiApparato)dati.get(DatiApparato.NOME_TABELLA)).info(nomeApparato);
        		if(info != null)
        			apparati.add(info);
        	}
        }
	}
	
	@Override
    public String toString() {
    	return String.format("Switch `%s`", getNome());
    }
	
	@Override
    public ArrayList<TextFlow> info(){
        ArrayList<TextFlow> lista = new ArrayList<>();
        TextFlow testo = new TextFlow();
        Text etichetta = new Text(toString());
        etichetta.setFont(Font.font("Arial black", 16));
        testo.getChildren().add(etichetta);
        lista.add(testo);
        for(Apparato apparato: apparati) 
        	lista.add(voceInfo(apparato.getNome(),  apparato.getPosizione(), apparato.getUtente()));
        
        return lista;
    }

	public ObservableList<Apparato> getApparati() {
		return apparati;
	}
}
