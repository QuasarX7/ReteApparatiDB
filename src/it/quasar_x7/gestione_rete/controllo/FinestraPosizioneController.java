package it.quasar_x7.gestione_rete.controllo;

import it.quasar_x7.gestione_rete.Dati.DatiApparato;
import it.quasar_x7.gestione_rete.Dati.DatiPosizione;
import it.quasar_x7.gestione_rete.Dati.DatiResponsabileSito;
import it.quasar_x7.gestione_rete.programma.Programma;
import static it.quasar_x7.gestione_rete.programma.Programma.dati;
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
 * @author Dott. Domenico della Peruta
 */
public class FinestraPosizioneController implements Initializable {

    static public String[] input = null;
    public static TabellaController tabella = null;
    
    protected DatiResponsabileSito datiResponsabileSito = (DatiResponsabileSito) dati.get(DatiResponsabileSito.NOME_TABELLA);
    protected DatiPosizione datiPosizione = (DatiPosizione) dati.get(DatiPosizione.NOME_TABELLA);
    
    @FXML
    private TextField posizione;

    
    @FXML
    private ChoiceBox<String> responsabile;

    @FXML
    private Button pulsanteChiusura;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	Finestra.infoFinestreAperte(pulsanteChiusura);
        CampoTesto.autoCompletamento(posizione, datiPosizione.listaSiti());
        responsabile.getItems().addAll(datiResponsabileSito.lista());
        if(input != null){
            if(input[0] != null){
                posizione.setText(input[0]);
            }
            if(input[1] != null)
                responsabile.setValue(input[1]);
        }
    }   
    
    @FXML
    private void salva(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION))
            Programma.salva(
                    this, 
                    // condizione
                    !posizione.getText().isEmpty(), 
                    // DB
                    datiPosizione, 
                    // eventuale input da modificare
                    input, 
                    // input da salvare
                    new Object[]{
                        posizione.getText(),
                        responsabile.getValue() == null ? "" : responsabile.getValue()
                    }, 
                    event,
                    // apertura finestra a tabella
                    (ActionEvent evento,String chiave)->{
                        if(tabella != null){
                                ArrayList<String> riga = new ArrayList<>();
                                riga.add(posizione.getText());
                                riga.add(responsabile.getValue());
                                if(chiave != null)
                                    tabella.modificaRiga(chiave, riga);
                                else
                                    tabella.aggiungiRiga(riga);
                        }
                        // aggiorna tutti gli apparati
                        boolean procedi = Programma.salvaModificheApparato(
                                this, 
                                DatiApparato.VOCE_TABELLA_POSIZIONE, 
                                input, 0, 
                                posizione
                        );
                        if(procedi)
                            chiusuraSenzaSalvare(evento);
                    }

            );
    }

    @FXML
    void aggiornaResponsabile(MouseEvent event) {
        if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
            responsabile.getItems().clear();
            responsabile.getItems().addAll(datiResponsabileSito.lista());
        }
    }

    @FXML
    void aggiungiNuovoResponsabile(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION))
            Programma.apriListaResponsabile(this);
    }

    

    @FXML
    private void chiusuraSenzaSalvare(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION)){
            Programma.chiusuraFinestra(this);
            input = null;
            tabella= null;
        }
    }
    
}
