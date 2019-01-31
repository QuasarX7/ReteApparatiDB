package it.quasar_x7.gestione_rete.controllo;

import it.quasar_x7.gestione_rete.Dati.DatiApparato;
import it.quasar_x7.gestione_rete.Dati.DatiConnessioneSwitch;
import it.quasar_x7.gestione_rete.Dati.DatiSwitch;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * 
 *
 * @author Dr. Domenico della Peruta
 */
public class FinestraSwitchController implements Initializable {

        
    static public Scene scenaCorrente = null;
    static public String[] input = null;
    public static TabellaController tabella = null;
    
    protected DatiConnessioneSwitch datiSwitch = (DatiConnessioneSwitch) dati.get(DatiConnessioneSwitch.NOME_TABELLA);
    protected DatiApparato datiApparato = (DatiApparato) dati.get(DatiApparato.NOME_TABELLA);
    protected DatiSwitch datiTipoSwitch = (DatiSwitch) dati.get(DatiSwitch.NOME_TABELLA);
        
    @FXML
    private ChoiceBox<String> _switch;
    @FXML
    private ChoiceBox<String> apparato;

    @FXML
    private TextField porta;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CampoTesto.aggiungiLimiteTesto(porta, 10);
        CampoTesto.maiuscolo(porta);
        
        ArrayList<String> sw = datiTipoSwitch.lista();
        if(sw != null)
            _switch.getItems().addAll(sw);
        
        ArrayList<String> app = datiApparato.nomiApparati();
        if(app != null)
            apparato.getItems().addAll(app);
        
        if(input != null){
            if(input[0] != null)
                apparato.setValue(input[0]);
            if(input[1] != null)
                _switch.setValue(input[1]);
            if(input[2] != null)
                porta.setText(input[2]);
        }
    }    
    
    

    
    @FXML
    private void salva(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            
            Programma.salva(
                    this,
                    //condizione
                    apparato.getSelectionModel().getSelectedIndex() >= 0 && _switch.getSelectionModel().getSelectedIndex() >= 0 && !porta.getText().isEmpty(),
                    // DB
                    datiSwitch,
                    // eventuali dati da modificare
                    input, 
                    // dati da salvare
                    new Object[]{
                        apparato.getValue(),
                        _switch.getValue(),
                        porta.getText()
                    },
                    event, 
                    // apertura della finestra a tabella e salvataggio
                    (ActionEvent evento,String chiave)->{
                        if(tabella != null){
                            ArrayList<String> riga = new ArrayList<>();
                            riga.add(apparato.getValue());
                            riga.add(_switch.getValue());
                            riga.add(porta.getText());
                            
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

    @FXML
    private void chiusuraSenzaSalvare(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            Programma.chiusuraFinestra(this, scenaCorrente);
            input = null;
            tabella = null;
        }
    }
    
   
    @FXML
    private void aggiornaListaApparati(MouseEvent event) {
        if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
            apparato.getItems().clear();
            ArrayList<String> app = datiApparato.nomiApparati();
            if(app != null)
                apparato.getItems().addAll(app);
        }
        
    }

    @FXML
    private void aggiungiNuovoApparato(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION)){
            FinestraApparatoController.scenaCorrente = Finestra.scenaCorrente();
            Finestra.caricaFinestra(this, R.FXML.FINESTRA_APPARATO);
        }
    }

    @FXML
    private void aggiornaListaSwitch(MouseEvent event) {
        if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
            _switch.getItems().clear();
            ArrayList<String> sw = datiTipoSwitch.lista();
            if(sw != null)
                _switch.getItems().addAll(sw);

        }
    }

    @FXML
    private void aggiungiNuovoSwitch(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            Programma.apriSempliceLista(
                    this, 
                    (DatiSwitch) dati.get(DatiSwitch.NOME_TABELLA),
                    R.Etichette.FINESTRA_SWITCH,
                    R.Etichette.NOME_SWITCH,
                    R.Messaggi.SOSTITUZIONE_SWITCH
            );
        }
    }
}
