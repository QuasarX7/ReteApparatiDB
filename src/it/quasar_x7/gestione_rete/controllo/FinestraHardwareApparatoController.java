package it.quasar_x7.gestione_rete.controllo;

import it.quasar_x7.gestione_rete.Dati.DatiApparato;
import it.quasar_x7.gestione_rete.Dati.DatiHardware;
import it.quasar_x7.gestione_rete.programma.Programma;
import static it.quasar_x7.gestione_rete.programma.Programma.dati;
import it.quasar_x7.gestione_rete.programma.R;
import it.quasar_x7.javafx.Finestra;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Dr. Domenico della Peruta
 */
public class FinestraHardwareApparatoController implements Initializable {

    
    public static String apparato = null;
    public static String hardware = null;
    public static String modello = null;
    public static String matricola = null;
    
    public static FinestraApparatoController finestraApparato = null;
     
    
    @FXML
    private ChoiceBox<String> nomeHardware;

    @FXML
    private ChoiceBox<String> nomeModello;
    
    @FXML
    private ChoiceBox<String> nomeMatricola;

    @FXML
    private ChoiceBox<String> nomeApparato;
    
    @FXML
    private Button pulsanteChiusura;
    
    private final DatiHardware datiHW = (DatiHardware)dati.get(DatiHardware.NOME_TABELLA);
    private final DatiApparato datiApparato = (DatiApparato)dati.get(DatiApparato.NOME_TABELLA);
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    	Finestra.infoFinestreAperte(pulsanteChiusura);
    	
    	aggiornaMenuNomeHardware();
        
        aggiornaApparato();
        if(apparato != null) {
            nomeApparato.setValue(apparato);
        }
        if(hardware != null) {
        	nomeHardware.setValue(hardware);
        }
        
        if(modello != null) {
        	aggiornaMenuModello();
        	nomeModello.setValue(modello);
        }
        
        if(matricola != null) {
        	aggiornaMenuMatricola();
        	nomeMatricola.setValue(matricola);
        }
        
        
    } 
    
    private void aggiornaApparato() {
    	nomeApparato.getItems().clear();
    	TreeSet<String> listaApparati = datiApparato.listaOrdinata(0);
        if(listaApparati != null)
            nomeApparato.getItems().addAll(listaApparati);
    }
    
    
    
    private void aggiornaMenuNomeHardware() {
    	nomeHardware.getItems().clear();
    	TreeSet<String> listaHW = datiHW.listaNomi();
        if(listaHW != null)
            nomeHardware.getItems().addAll(listaHW);
    }

    @FXML
    private void chiusuraSenzaSalvare(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION)){
            Programma.chiusuraFinestra(this);
            Programma.aggiornaListeNodi();
            finestraApparato = null;
            apparato = null;
            hardware = matricola = modello = null;
        }
    }

    @FXML
    private void aggiornaNomeApparato(MouseEvent event) {
        if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
        	aggiornaApparato();
        }
    }
    
    @FXML
    private void aggiornaMenuNomeHardware(MouseEvent event) {
        if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
        	aggiornaMenuNomeHardware();
        }
    }

    @FXML
    private void aggiungiNuovoHardware(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION)){
        	Programma.apriFinestraNuovoHardware(this,null);
        }
    }

    @FXML
    private void aggiornaMenuMatricola(MouseEvent event) {
        if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
        	aggiornaMenuMatricola();
        }
    }
    
    private void aggiornaMenuMatricola() {
    	Programma.aggiornaMenuMatricolaHW(nomeHardware.getValue(), nomeModello.getValue(),nomeMatricola);
    }
    
    @FXML
    private void aggiornaMenuModello(MouseEvent event) {
        if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
        	aggiornaMenuModello();
        }
    }
    
    private void aggiornaMenuModello() {
    	Programma.aggiornaMenuModelloHW(nomeHardware.getValue(),nomeModello);
    }

    @FXML
    private void salva(ActionEvent event) {
    	
        if(event.getEventType().equals(ActionEvent.ACTION)){
        	if(nomeApparato.getValue() != null && nomeHardware.getValue() != null){
        		
                if(Programma.aggiungiNuovoHardware(this,nomeApparato.getValue(),nomeHardware.getValue(),nomeModello,nomeMatricola)){
                
                    if(finestraApparato != null){
                        finestraApparato.aggiornaTabellaHW();
                    }
                    chiusuraSenzaSalvare(event);
                }
            }else{
                Finestra.finestraAvviso(this,R.Messaggi.ERRORE_CAMPI_FONDAMENTALI);
            }
        }
        
    }

    
}
