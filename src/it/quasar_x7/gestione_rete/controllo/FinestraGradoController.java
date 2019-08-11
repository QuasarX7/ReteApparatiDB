package it.quasar_x7.gestione_rete.controllo;

import it.quasar_x7.gestione_rete.Dati.DatiDB;
import it.quasar_x7.gestione_rete.Dati.DatiGrado;
import it.quasar_x7.gestione_rete.Dati.DatiRuolo;
import it.quasar_x7.gestione_rete.programma.Programma;
import static it.quasar_x7.gestione_rete.programma.Programma.dati;
import it.quasar_x7.gestione_rete.programma.R;
import it.quasar_x7.javafx.CampoTesto;
import it.quasar_x7.javafx.Finestra;
import it.quasar_x7.javafx.finestre.controllo.TabellaController;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Dr. Domenico della Peruta
 */
public class FinestraGradoController  implements Initializable {

    public static TabellaController tabella = null;
    public static String[] input = null;
   
    @FXML
    private ChoiceBox<String> ruolo;

    @FXML
    private TextField sigla;

    @FXML
    private TextField nome;

    @FXML
    private TextField id;
    
    @FXML
    private Button pulsanteChiusura;

    
    protected DatiDB datiGrado = dati.get(DatiGrado.NOME_TABELLA);
    protected DatiRuolo datiRuolo = (DatiRuolo)dati.get(DatiRuolo.NOME_TABELLA);
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Finestra.infoFinestreAperte(pulsanteChiusura);
        ruolo.getItems().addAll(datiRuolo.lista());
        CampoTesto.soloNumeri(id, 5);
        
        if(input != null)
            if(input.length == datiGrado.attributi().size()){
                if(input[0] != null)
                    nome.setText(input[0]);
                if(input[1] != null)
                    ruolo.setValue(input[1]);
                if(input[2] != null)
                    sigla.setText(input[2]);
                if(input[3] != null)
                    id.setText(input[3]);
            }
        
    } 
    
    @FXML
    protected void aggiornaRuolo(MouseEvent event) {
    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        ruolo.getItems().clear();
	        ruolo.getItems().addAll(datiRuolo.lista());
    	}
    }

    @FXML
    protected void chiusuraSenzaSalvare(ActionEvent event) {
    	if(event.getEventType().equals(ActionEvent.ACTION)){
	        Programma.chiusuraFinestra(this);
	        input = null;
	        tabella = null;
    	}
    }

    @FXML
    protected void aggiungiRuolo(ActionEvent event) {
    	if(event.getEventType().equals(ActionEvent.ACTION)){
	        Programma.apriSempliceLista(
	                this,
	                datiRuolo,
	                R.Etichette.FINESTRA_LISTA_CATEGORIA_GRADO,
	                R.Etichette.RUOLO,
	                R.Messaggi.SOSTITUZIONE_RUOLO
	        );
    	}
    }

    @FXML
    protected void salva(ActionEvent event) {
    	if(event.getEventType().equals(ActionEvent.ACTION)){
	        Integer indice = null;
	        try{
	            indice = new Integer(id.getText());
	        }catch(NumberFormatException e){}
	        
	        
	        Object[] record = new Object[]{
	            nome.getText(),
	            ruolo.getValue(),
	            sigla.getText(),
	            indice
	        };
	        Programma.salva(
	                this, 
	                !nome.getText().isEmpty(), 
	                datiGrado, 
	                input, 
	                record, 
	                event, 
	                // metodo di creazione della finestra a tabella
	                (ActionEvent evento, String chiave) -> {
	                    if(tabella != null){
	                        if(chiave != null)
	                            tabella.modificaRiga(chiave,converti(record));
	                        else
	                            tabella.aggiungiRiga(converti(record));
	                    }
	                    chiusuraSenzaSalvare(evento);
	                }
	        );
    	}
    }
    
    private ArrayList<String> converti(Object[] record){
        ArrayList<String> nuovaRiga = new ArrayList<>();
        for(Object voce:record){
            if(voce != null)
                nuovaRiga.add(voce.toString());
            else
                nuovaRiga.add("");
        }
        return nuovaRiga;
    }
       
    
}
