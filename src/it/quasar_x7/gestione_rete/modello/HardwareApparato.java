package it.quasar_x7.gestione_rete.modello;

import java.util.ArrayList;
import java.util.HashMap;

import it.quasar_x7.gestione_rete.Dati.DatiDB;
import it.quasar_x7.gestione_rete.Dati.DatiHardwareApparato;
import it.quasar_x7.gestione_rete.Dati.DatiSoftwareApparato;
import it.quasar_x7.gestione_rete.programma.R;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class HardwareApparato extends Nodo{

	protected ObservableList<Hardware> hardware = FXCollections.observableArrayList();
	
	public HardwareApparato(String nomeApparato,HashMap<String,DatiDB> dati) {
		super(nomeApparato);
		ArrayList<Hardware> hwApp = ((DatiHardwareApparato)dati.get(DatiHardwareApparato.NOME_TABELLA)).listaHW(nomeApparato);
        if(hwApp != null)
        	hardware.addAll(hwApp);
	}
	
	public String apparato() {
		return getNome();
	}

	public ObservableList<Hardware> getHardware() {
		return hardware;
	}
	
	@Override
    public String toString() {
        return String.format("%s `%s`",R.Etichette.HW,getNome());
    }

	@Override
	public ArrayList<TextFlow> info(){
        ArrayList<TextFlow> lista = new ArrayList<>();
        TextFlow testo = new TextFlow();
        Text etichetta = new Text(toString());
        etichetta.setFont(Font.font("Arial black", 16));
        testo.getChildren().add(etichetta);
        lista.add(testo);
        for(Hardware hw: hardware) {
        	lista.add(voceInfo(hw.getNome(), String.format("%s `%s`", hw.getModello(),hw.getMatricola())));
        }
        return lista;
    }
}
