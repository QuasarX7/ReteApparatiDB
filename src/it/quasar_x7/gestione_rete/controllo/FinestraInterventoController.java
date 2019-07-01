package it.quasar_x7.gestione_rete.controllo;

import it.quasar_x7.gestione_rete.Dati.DatiIntervento;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    private ChoiceBox<?> menuEsito;

    @FXML
    private ChoiceBox<?> menuApparato;
    
    protected DatiIntervento datiIntervento = (DatiIntervento) dati.get(DatiIntervento.NOME_TABELLA);
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        if(input != null) {
        	
        }
            
        
    } 
    
    
    @FXML
    protected void chiusuraSenzaSalvare(ActionEvent event) {
        Programma.chiusuraFinestra(this, scenaCorrente);
        input = null;
        tabella = null;
    }

    

    @FXML
    protected void salva(ActionEvent event) {
    	/*
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
        */
    }
    
   
    
}
