package it.quasar_x7.gestione_rete.controllo;

import it.quasar_x7.gestione_rete.Dati.DatiDB;
import it.quasar_x7.gestione_rete.Dati.DatiHardware;
import it.quasar_x7.gestione_rete.Dati.DatiHardwareApparato;
import it.quasar_x7.gestione_rete.Dati.DatiSoftware;
import it.quasar_x7.gestione_rete.Dati.DatiSoftwareApparato;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Dr. Domenico della Peruta
 */
public class FinestraHardwareApparatoController implements Initializable {

    public static Scene scenaCorrente = null;

    public static String nomeApparato = null;
    public static FinestraApparatoController finestraApparato = null;
     
    
    @FXML
    private ChoiceBox<String> nomeHardware;

    @FXML
    private ChoiceBox<String> modello;
    
    @FXML
    private ChoiceBox<String> matricola;

    @FXML
    private TextField apparato;
    
    private final DatiHardware datiHW = (DatiHardware)dati.get(DatiHardware.NOME_TABELLA);
    private final DatiHardwareApparato datiHardwareApparato = (DatiHardwareApparato)dati.get(DatiHardwareApparato.NOME_TABELLA);
        
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(nomeApparato != null)
            apparato.setText(nomeApparato);
        
        aggiornaMenuNomeHardware();
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
            Programma.chiusuraFinestra(this, scenaCorrente);
            finestraApparato = null;
            nomeApparato = null;
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
            Programma.apriListaHardware(this);
        }
    }

    @FXML
    private void aggiornaMenuMatricola(MouseEvent event) {
        if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
        	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
            	matricola.getItems().clear();
                if(nomeHardware.getValue() != null && modello.getValue() != null){
                    TreeSet<String> modelli = datiHW.listaMatricola(nomeHardware.getValue(),modello.getValue());
                    if(modelli != null){
                        matricola.getItems().addAll(modelli);
                    }
                }
            }
        }
    }
    
    @FXML
    private void aggiornaMenuModello(MouseEvent event) {
        if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
        	modello.getItems().clear();
            if(nomeHardware.getValue() != null){
                TreeSet<String> modelli = datiHW.listaModello(nomeHardware.getValue());
                if(modelli != null){
                    modello.getItems().addAll(modelli);
                }
            }
        }
    }

    @FXML
    private void salva(ActionEvent event) {
    	/*
        if(event.getEventType().equals(ActionEvent.ACTION)){
            if(!apparato.getText().isEmpty() && nomeSoftware.getValue() != null){
                Object[] record = new Object[]{
                    apparato.getText(),
                    nomeSoftware.getValue(),
                    licenzaSoftware.getValue() == null ? "" : licenzaSoftware.getValue()
                };
                if(!datiSoftwareApparato.aggiungi(record)){
                    Finestra.finestraAvviso(
                            this, 
                            String.format(
                                    R.Messaggi.ERRORE_SALVATAGGIO,
                                    nomeSoftware.getValue()+" di "+apparato.getText(),
                                    DatiDB.stampa(record)
                            )
                    );
                }else{
                    if(finestraApparato != null){
                        finestraApparato.aggiornaTabellaSW();
                    }
                    chiusuraSenzaSalvare(event);
                }
            }
        }else{
            Finestra.finestraAvviso(this,R.Messaggi.ERRORE_CAMPI_FONDAMENTALI);
        }
        */
    }

    
}
