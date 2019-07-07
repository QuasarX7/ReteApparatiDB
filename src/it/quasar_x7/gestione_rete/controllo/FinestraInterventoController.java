package it.quasar_x7.gestione_rete.controllo;

import it.quasar_x7.gestione_rete.Dati.DatiApparato;
import it.quasar_x7.gestione_rete.Dati.DatiIntervento;
import it.quasar_x7.gestione_rete.Dati.DatiUtilizzatore;
import it.quasar_x7.gestione_rete.modello.Utilizzatore;
import it.quasar_x7.gestione_rete.programma.Programma;
import static it.quasar_x7.gestione_rete.programma.Programma.dati;
import it.quasar_x7.gestione_rete.programma.R;
import it.quasar_x7.java.utile.Colore;
import it.quasar_x7.java.utile.DataOraria;
import it.quasar_x7.java.utile.Errore;
import it.quasar_x7.javafx.CampoTesto;
import it.quasar_x7.javafx.finestre.controllo.TabellaController;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeSet;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Dr. Domenico della Peruta
 */
public class FinestraInterventoController  implements Initializable {

    public static TabellaController tabella = null;
    public static Scene scenaCorrente = null;
    public static String[] input = null;
   
    @FXML
    private TextField ticket;

    @FXML
    private TextField data;

    @FXML
    private TextArea richiesta;

    @FXML
    private TextArea intervento;

    @FXML
    private ChoiceBox<String> menuEsito;

    @FXML
    private ChoiceBox<String> menuApparato;
    
    protected DatiIntervento datiIntervento = (DatiIntervento) dati.get(DatiIntervento.NOME_TABELLA);
    protected DatiApparato datiApparato = (DatiApparato) dati.get(DatiApparato.NOME_TABELLA);
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	CampoTesto.data(data);
    	data.setText(new DataOraria().stampaGiorno('/'));
    	TreeSet<String> host = datiApparato.listaOrdinata(0);
        if(host != null)
            menuApparato.getItems().addAll(host);
       
        menuEsito.getItems().addAll(R.Esito.POSITIVO,R.Esito.NEGATIVO,R.Esito.SOSPESO);
        
        if(input != null) {
        	if(input[0] != null)
                ticket.setText(input[0]);
            
            if(input[1] != null) {
            	try {
	            	new DataOraria(input[1]);//verifica correttezza input
	                data.setText(input[1]);
            	}catch(Errore e) {
            	}
            }
            
            if(input[2] != null)
                menuApparato.setValue(input[2]);
            
            if(input[3] != null)
                richiesta.setText(input[3]);
            
            if(input[4] != null)
                intervento.setText(input[4]);
            
            if(input[5] != null)
                menuEsito.setValue(input[5]);
        }
            
        
    } 
    
    
    @FXML
    protected void chiusuraSenzaSalvare(ActionEvent event) {
        Programma.chiusuraFinestra(this, scenaCorrente);
        Programma.aggiornaListeNodi();
        input = null;
        tabella = null;
    }

    

    @FXML
    protected void salva(ActionEvent event) {
    	
        
        Object[] record = new Object[]{
            ticket.getText(),
            data.getText(),
            menuApparato.getValue() != null ? menuApparato.getValue() : "",
            richiesta.getText(),
            intervento.getText(),
            menuEsito.getValue() != null ? menuEsito.getValue() : "",
        };
        Programma.salva(
                this, 
                !ticket.getText().isEmpty(), 
                datiIntervento, 
                input, 
                record, 
                event, 
                // metodo di creazione della finestra a tabella
                (ActionEvent evento, String chiave) -> {
                    if(tabella != null){
                    	ArrayList<String> riga = new ArrayList<>();
                    	for(Object s: record)
                    		riga.add((String)s);
                        
                        if(chiave != null)
                            tabella.modificaRiga(chiave,riga);
                        else
                            tabella.aggiungiRiga(riga);
                    }
                    chiusuraSenzaSalvare(evento);
                }
        );
       
    }
    
   
    
}
