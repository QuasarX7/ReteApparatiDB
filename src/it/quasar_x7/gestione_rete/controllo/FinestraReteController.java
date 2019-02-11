package it.quasar_x7.gestione_rete.controllo;

import it.quasar_x7.gestione_rete.Dati.DatiApparato;
import it.quasar_x7.gestione_rete.Dati.DatiRete;
import it.quasar_x7.gestione_rete.Dati.DatiTipoRete;
import it.quasar_x7.gestione_rete.programma.Programma;
import static it.quasar_x7.gestione_rete.programma.Programma.dati;
import it.quasar_x7.gestione_rete.programma.R;
import it.quasar_x7.javafx.CampoTesto;
import it.quasar_x7.javafx.Finestra;
import it.quasar_x7.javafx.finestre.controllo.BarraProgressiController;
import it.quasar_x7.javafx.finestre.controllo.TabellaController;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
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
public class FinestraReteController implements Initializable {

    static public Scene scenaCorrente = null;
    static public String[] input = null;
    public static TabellaController tabella = null;
    
    
    @FXML
    private ChoiceBox<String> tipoRete;

    @FXML
    private TextField workgroup;

    @FXML
    private TextField netmask;
    
    @FXML
    private TextField gateway;

    @FXML
    private TextField dominio;

    protected DatiTipoRete datiTipoRete = (DatiTipoRete) dati.get(DatiTipoRete.NOME_TABELLA);
    protected DatiRete datiRete = (DatiRete) dati.get(DatiRete.NOME_TABELLA);
        
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        CampoTesto.minuscolo(dominio);
        CampoTesto.soloCaratteri(dominio, 60, ".1234567890@òàèéìç\\_\\-ù");
        CampoTesto.indirizzoIP(netmask);
        CampoTesto.indirizzoIP(gateway);
        tipoRete.getItems().addAll(datiTipoRete.lista());
        
        if(input != null){
            if(input[0] != null){
                workgroup.setText(input[0]);
            }
            if(input[1] != null)
                dominio.setText(input[1]);
           
            if(input[2] != null)
                tipoRete.setValue(input[2]);
             
            if(input[3] != null)
                gateway.setText(input[3]);
            
            if(input[4] != null)
                netmask.setText(input[4]);
            
        }
    } 

    @FXML
    private void aggiornaTipoRete(MouseEvent event) {
        if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
            tipoRete.getItems().clear();
            tipoRete.getItems().addAll(datiTipoRete.lista());
        }
    }

    @FXML
    private void aggiungiNuovoTipoRete(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION))
            Programma.apriSempliceLista(
                    this, 
                    datiTipoRete, 
                    R.Etichette.FINESTRA_TIPO_RETE, 
                    R.Etichette.TIPO_RETE, 
                    R.Messaggi.SOSTITUZIONE_TIPO_RETE
            );
    }

    @FXML
    private void chiusuraSenzaSalvare(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION)){
            Programma.chiusuraFinestra(this, scenaCorrente);
            input = null;
            tabella = null;
        }
    }

    @FXML
    private void salva(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION)){
            
           
            Programma.salva(
                    this, 
                    !workgroup.getText().isEmpty(), 
                    datiRete, 
                    input, 
                    new Object[]{
                        workgroup.getText(),
                        dominio.getText(),
                        tipoRete.getValue(),
                        gateway.getText(),
                        netmask.getText()
                    }, 
                    event, 
                    (ActionEvent evento,String chiave)->{
                        if(tabella != null){
                            ArrayList<String> riga = new ArrayList<>();
                            riga.add(workgroup.getText());
                            riga.add(dominio.getText());
                            riga.add(tipoRete.getValue());
                            riga.add(gateway.getText());
                            riga.add(netmask.getText());

                            if(chiave != null)
                                tabella.modificaRiga(chiave,riga);
                            else
                                tabella.aggiungiRiga(riga);
                        }
                        
                        // aggiorna tutti gli apparati della rete modificata
                        boolean procedi = Programma.salvaModificheApparato(
                                this, 
                                DatiApparato.VOCE_TABELLA_WG, 
                                input, 0, 
                                workgroup
                        );
                        if(procedi)
                            chiusuraSenzaSalvare(evento);
                        
                         
                    }
            );


        }
    }
    
  
}
