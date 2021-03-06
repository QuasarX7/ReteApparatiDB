package it.quasar_x7.gestione_rete.controllo;

import it.quasar_x7.gestione_rete.Dati.DatiCasaSoftware;
import it.quasar_x7.gestione_rete.Dati.DatiSoftware;
import it.quasar_x7.gestione_rete.Dati.DatiTipoSoftware;
import it.quasar_x7.gestione_rete.programma.Programma;
import static it.quasar_x7.gestione_rete.programma.Programma.dati;
import it.quasar_x7.gestione_rete.programma.R;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Dr. Domenico della Peruta
 */
public class FinestraSoftwareController implements Initializable {

    public static String[] input = null;
    public static TabellaController tabella= null;
	public static FinestraWizardSWController finestraWizard=null;

            
    @FXML
    private ChoiceBox<String> menuTipo;

    @FXML
    private ChoiceBox<String> menuCasa;
    
    @FXML
    private TextArea note;

    @FXML
    private TextField versione;

    @FXML
    private TextField licenza;
    
    @FXML
    private CheckBox selezionaPredefinito;
    
    @FXML
    private Button pulsanteChiusura;

    
    protected DatiCasaSoftware datiCasaSoftware = (DatiCasaSoftware)dati.get(DatiCasaSoftware.NOME_TABELLA);
    protected DatiSoftware datiSoftware = (DatiSoftware) dati.get(DatiSoftware.NOME_TABELLA);
    protected DatiTipoSoftware datiTipoSoftware =  (DatiTipoSoftware) dati.get(DatiTipoSoftware.NOME_TABELLA);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	Finestra.infoFinestreAperte(pulsanteChiusura);
    	if(input != null){
            if(input[0] != null){
                versione.setText(input[0]);
            }
            if(input[1] != null)
                menuTipo.setValue(input[1]);
           
            if(input[2] != null)
                menuCasa.setValue(input[2]);
             
            if(input[3] != null)
                licenza.setText(input[3]);
            
            if(input[4] != null)
                note.setText(input[4]);
            
            if(input[5] != null) {
            	if(input[5].equals(R.Conferma.SI) || input[5].equals("true") )
            		selezionaPredefinito.setSelected(true);
            }
            
        }
        ArrayList<String> caseSoftware = datiCasaSoftware.lista();
        if(caseSoftware != null)
            menuCasa.getItems().addAll(caseSoftware);
        
        ArrayList<String> tipoSoftware = datiTipoSoftware.lista();
        if(tipoSoftware != null)
            menuTipo.getItems().addAll(tipoSoftware);
    }    

    @FXML
    protected void chiusuraSenzaSalvare(ActionEvent event) {
    	if(event.getEventType().equals(ActionEvent.ACTION)){
	        Programma.chiusuraFinestra(this);
	        input = null;
	        tabella= null;
	        finestraWizard=null;
    	}
    }

    @FXML
    protected void aggiornaMenuTipo(MouseEvent event) {
    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        menuTipo.getItems().clear();
	        menuTipo.getItems().addAll(datiTipoSoftware.lista());
    	}
    }

    @FXML
    protected void aggiungiNuovoTipo(ActionEvent event) {
    	if(event.getEventType().equals(ActionEvent.ACTION)){
	        Programma.apriSempliceLista(
	                this,
	                datiTipoSoftware,
	                R.Etichette.FINESTRA_LISTA_TIPO_SW,
	                R.Etichette.TIPO_SW,
	                R.Messaggi.SOSTITUZIONE_TIPO_SW
	        );
    	}
    }

    @FXML
    protected void aggiornaMenuCasa(MouseEvent event) {
    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        menuCasa.getItems().clear();
	        menuCasa.getItems().addAll(datiCasaSoftware.lista());
    	}
    }

    @FXML
    protected void aggiungiCasa(ActionEvent event) {
    	if(event.getEventType().equals(ActionEvent.ACTION)){
	        Programma.apriSempliceLista(
	                this,
	                datiCasaSoftware,
	                    R.Etichette.FINESTRA_LISTA_CASA_SW,
	                R.Etichette.CASA_SW,
	                R.Messaggi.SOSTITUZIONE_CASA_SW
	        );
    	}
    }

    @FXML
    protected void salva(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION)) {
        	final boolean predefinito = selezionaPredefinito.isSelected();
            Programma.salva(
                    this, 
                    !versione.getText().isEmpty(), 
                    datiSoftware, 
                    input, 
                    new Object[]{
                        versione.getText(),
                        menuTipo.getValue(),
                        menuCasa.getValue(),
                        licenza.getText(),
                        note.getText(),
                        predefinito
                    }, 
                    event, 
                    (ActionEvent evento, String chiave) -> {
                         if(tabella != null){
                            ArrayList<String> riga = new ArrayList<>();
                            riga.add(
                                    DatiSoftware.componiNomeLicenza(
                                            versione.getText(),
                                            licenza.getText())
                            );
                            riga.add(menuTipo.getValue());
                            riga.add(menuCasa.getValue());
                            riga.add(note.getText());
                            riga.add(selezionaPredefinito.isSelected() ? R.Conferma.SI : R.Conferma.NO);
                            if(chiave != null)
                                tabella.modificaRiga(chiave,riga);
                            else
                                tabella.aggiungiRiga(riga);
                        }
                         finestraWizard.aggiornaTabellaSW();
                        chiusuraSenzaSalvare(evento);
                    }
            );
        }
    }
    
}
