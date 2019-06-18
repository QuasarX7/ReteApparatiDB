package it.quasar_x7.gestione_rete.modello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import it.quasar_x7.gestione_rete.Dati.DatiApparato;
import it.quasar_x7.gestione_rete.Dati.DatiConnessioneSwitch;
import it.quasar_x7.gestione_rete.Dati.DatiDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Switch extends Nodo {

	protected ObservableList<Apparato> apparati = FXCollections.observableArrayList();
	
	public Switch(String nome,HashMap<String,DatiDB> dati) {
		super(nome);
		 TreeSet<String> app = ((DatiConnessioneSwitch)dati.get(DatiConnessioneSwitch.NOME_TABELLA)).listaNomiApparati(nome);
        if(app != null) {
        	for(String nomeApparato: app)
        		apparati.add(((DatiApparato)dati.get(DatiApparato.NOME_TABELLA)).info(nomeApparato));
        	
        }
	}

	public ObservableList<Apparato> getApparati() {
		return apparati;
	}
}
