package it.quasar_x7.gestione_rete.controllo;

import it.quasar_x7.gestione_rete.Dati.DatiDB;
import it.quasar_x7.gestione_rete.Dati.DatiGrado;
import it.quasar_x7.gestione_rete.Dati.DatiImpostazioni;
import it.quasar_x7.gestione_rete.Dati.DatiRuolo;
import it.quasar_x7.gestione_rete.programma.Programma;
import static it.quasar_x7.gestione_rete.programma.Programma.dati;
import it.quasar_x7.gestione_rete.programma.R;
import it.quasar_x7.javafx.CampoTesto;
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
 * FXML Controller class
 *
 * @author Dr. Domenico della Peruta
 */
public class FinestraImpostazioniController  implements Initializable {

    public static Scene scenaCorrente = null;
    public static String[] input = null;
   
    
    
    protected DatiImpostazioni datiImpostazioni = (DatiImpostazioni) dati.get(DatiImpostazioni.NOME_TABELLA);
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        
    } 
   

    @FXML
    protected void chiusuraSenzaSalvare(ActionEvent event) {
        Programma.chiusuraFinestra(this, scenaCorrente);
        input = null;
    }


    @FXML
    protected void salva(ActionEvent event) {
        
        Object[] record = new Object[]{
            
        };
        Programma.salva(
                this, 
                true, //TODO...
                datiImpostazioni, 
                input, 
                record, 
                event, 
                
                (ActionEvent evento, String chiave) -> {
                   //TODO...
                    chiusuraSenzaSalvare(evento);
                }
        );
    }
    
    
       
    
}
