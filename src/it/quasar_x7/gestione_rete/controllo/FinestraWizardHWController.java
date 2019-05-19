package it.quasar_x7.gestione_rete.controllo;

import static it.quasar_x7.gestione_rete.programma.Programma.dati;

import java.net.URL;
import java.util.ResourceBundle;

import it.quasar_x7.gestione_rete.Dati.DatiHardware;
import it.quasar_x7.gestione_rete.Dati.DatiHardwareApparato;
import it.quasar_x7.gestione_rete.programma.Programma;
import it.quasar_x7.gestione_rete.programma.R;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;




	/**
	 * FXML Controller class
	 *
	 * @author Dott. Domenico della Peruta
	 */
	public class FinestraWizardHWController implements Initializable {

	    static public Scene scenaCorrente = null;
	    
	    protected DatiHardwareApparato datiHardwareApparato = (DatiHardwareApparato) dati.get(DatiHardwareApparato.NOME_TABELLA);
	    
	    @FXML
	    private CheckBox selezionaUnitaCentrale;

	    @FXML
	    private ChoiceBox<String> menuModelloUnitaCentrale;

	    @FXML
	    private ChoiceBox<String> menuMatricolaUnitaCentrale;

	    /**
	     * Initializes the controller class.
	     */
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	
			inizializzaRigaTabella(selezionaUnitaCentrale, false, menuModelloUnitaCentrale, menuMatricolaUnitaCentrale);
	    	aggiornaMenuModello(R.TipoStandardHW.UNITA_CENTALE, menuModelloUnitaCentrale);
	    }  
	    
	    private void inizializzaRigaTabella(CheckBox seleziona,boolean abilita,ChoiceBox<String> menuModello,ChoiceBox<String> menuMatricola) {
	    	seleziona.setSelected(abilita);
	    	menuModello.setDisable(!abilita);
			menuMatricola.setDisable(!abilita);
			seleziona.selectedProperty().addListener(
					new ChangeListener<Boolean>() {
						@Override
						public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
							menuModello.setDisable(!newValue);
							menuMatricola.setDisable(!newValue);
						}
					}
		    );
	    }
	    
	    @FXML
	    private void salva(ActionEvent event) {
	        if(event.getEventType().equals(ActionEvent.ACTION)) {
	        	
	        }
	            
	    }

	    
	    

	    @FXML
	    private void chiusuraSenzaSalvare(ActionEvent event) {
	        if(event.getEventType().equals(ActionEvent.ACTION)){
	            Programma.chiusuraFinestra(this, scenaCorrente);
	            
	        }
	    }
	    
	    @FXML
	    private void aggiungiNuovoHardware(ActionEvent event) {
	        if(event.getEventType().equals(ActionEvent.ACTION)){
	            Programma.apriListaHardware(this);
	        }
	    }
	    
	    private void aggiornaMenuModello(String nomeHardware, ChoiceBox<String> nomeModello) {
	    	Programma.aggiornaMenuModelloHW(nomeHardware,nomeModello);
	    }
	    
	    
	    @FXML
	    private void aggiornaMenuModelloUnitaCentrale(MouseEvent event) {
	        if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuModello(R.TipoStandardHW.UNITA_CENTALE, menuModelloUnitaCentrale);
	        }
	    }
	    
	    @FXML
	    private void aggiornaMenuMatricolaUnitaCentrale(MouseEvent event) {
	        if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuMatricola(R.TipoStandardHW.UNITA_CENTALE,menuMatricolaUnitaCentrale, menuModelloUnitaCentrale);
	        }
	    }
	    
	    

		private void aggiornaMenuMatricola(String nomeHW,ChoiceBox<String> menuMatricola, ChoiceBox<String> menuModello) {
			Programma.aggiornaMenuMatricolaHW(nomeHW,menuModello.getValue(), menuMatricola);
		}
	    
		
		@FXML
	    private void pulsanteUnitaCentrale(ActionEvent event) {
	        if(event.getEventType().equals(ActionEvent.ACTION)){
	        	String[] input = new String[]{
	        			DatiHardware.marcaMatricola(
		        			new Object[]{
		        				R.TipoStandardHW.UNITA_CENTALE,
		        				null,
		        				menuModelloUnitaCentrale.getValue() != null ? menuModelloUnitaCentrale.getValue() : "?",
		        				menuMatricolaUnitaCentrale.getValue() != null ? menuMatricolaUnitaCentrale.getValue() : "?",
		        			}
	        			),
	        			null,null,null,null
	        	};
	        	Programma.apriFinestraNuovoHardware(this,input);
	        }
	    }
	}
