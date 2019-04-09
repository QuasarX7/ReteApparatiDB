package it.quasar_x7.gestione_rete.controllo;

import it.quasar_x7.gestione_rete.Dati.DatiDB;
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
public class FinestraSoftwareApparatoController implements Initializable {

    public static Scene scenaCorrente = null;

    public static String nomeApparato = null;
    public static FinestraApparatoController finestraApparato = null;
     
    
    @FXML
    private ChoiceBox<String> nomeSoftware;

    @FXML
    private ChoiceBox<String> licenzaSoftware;

    @FXML
    private TextField apparato;
    
    private final DatiSoftware datiSW = (DatiSoftware)dati.get(DatiSoftware.NOME_TABELLA);
    private final DatiSoftwareApparato datiSoftwareApparato = (DatiSoftwareApparato)dati.get(DatiSoftwareApparato.NOME_TABELLA);
        
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(nomeApparato != null)
            apparato.setText(nomeApparato);
        
        
        aggiornaMenuNomeSoftware();
    } 
    
    private void aggiornaMenuNomeSoftware() {
    	nomeSoftware.getItems().clear();
    	TreeSet<String> listaSW = datiSW.listaNomi();
        if(listaSW != null)
            nomeSoftware.getItems().addAll(listaSW);
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
    private void aggiornaMenuNomeSoftware(MouseEvent event) {
        if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
        	aggiornaMenuNomeSoftware();
        }
    }

    @FXML
    private void aggiungiNuovoSoftware(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION)){
            Programma.apriListaSoftware(this);
        }
    }

    @FXML
    private void aggiornaMenuLicenza(MouseEvent event) {
        if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
            licenzaSoftware.getItems().clear();
            if(nomeSoftware.getValue() != null){
                TreeSet<String> licenze = datiSW.listaLicenza(nomeSoftware.getValue());
                if(licenze != null){
                    licenzaSoftware.getItems().addAll(licenze);
                }
            }
        }
    }

    @FXML
    private void salva(ActionEvent event) {
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
    }

    
}
