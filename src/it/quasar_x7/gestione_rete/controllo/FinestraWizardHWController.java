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
import javafx.scene.control.Button;
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
	    

	    @FXML
	    private Button pulsanteSchedaMadre;
	    
	    
	    @FXML
	    private CheckBox selezionaSchedaMadre;

	    @FXML
	    private ChoiceBox<String> menuModelloSchedaMadre;

	    @FXML
	    private ChoiceBox<String> menuMatricolaSchedaMadre;
	    

	    @FXML
	    private Button pulsanteUnitaCentrale;
	    
	    @FXML
	    private ChoiceBox<?> menuMatricolaUPS;

	    
	    @FXML
	    private ChoiceBox<?> menuModelloTastiera;

	    @FXML
	    private Button pulsanteProcessore;

	    @FXML
	    private Button pulsanteUPS;

	    @FXML
	    private ChoiceBox<?> menuModelloMouse;

	    @FXML
	    private Button pulsanteStampante;

	    @FXML
	    private ChoiceBox<?> menuModelloProcessore;

	    @FXML
	    private ChoiceBox<?> menuMatricolaMonitor;

	    @FXML
	    private ChoiceBox<?> menuMatricolaStampante;

	    @FXML
	    private Button pulsanteSchedaRete;

	    @FXML
	    private ChoiceBox<?> menuModelloStampante;

	    @FXML
	    private ChoiceBox<?> menuMatricolaTastiera;

	    @FXML
	    private ChoiceBox<?> menuMatricolaHDD;

	    @FXML
	    private ChoiceBox<?> menuModelloUPS;

	    @FXML
	    private Button pulsanteMouse;

	    @FXML
	    private ChoiceBox<?> menuMatricolaMouse;

	    
	    @FXML
	    private Button pulsanteSchedaVideo;

	    @FXML
	    private ChoiceBox<?> menuMatricolaRAM;

	    @FXML
	    private ChoiceBox<?> menuModelloHDD;

	    @FXML
	    private ChoiceBox<?> menuMatricolaProcessore;

	    @FXML
	    private ChoiceBox<?> menuModelloRAM;

	    @FXML
	    private ChoiceBox<?> menuModelloMonitor;

	    @FXML
	    private ChoiceBox<?> menuMatricolaSchedaRete;

	   

	    @FXML
	    private ChoiceBox<?> menuMatricolaSchedaVideo;

	    @FXML
	    private Button pulsanteTastiera;

	    @FXML
	    private ChoiceBox<?> menuModelloSchedaVideo;

	    @FXML
	    private Button pulsanteHDD;

	    @FXML
	    private Button pulsanteMonitor;

	    

	    @FXML
	    private Button pulsanteRAM;

	    @FXML
	    private ChoiceBox<?> menuModelloSchedaRete;
	    
	    

	    /**
	     * Initializes the controller class.
	     */
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	
			inizializzaRigaTabella(selezionaUnitaCentrale, true, menuModelloUnitaCentrale, menuMatricolaUnitaCentrale,pulsanteUnitaCentrale);
			inizializzaRigaTabella(selezionaSchedaMadre, false, menuModelloSchedaMadre, menuMatricolaSchedaMadre,pulsanteSchedaMadre);
	    	
	    }  
	    
	    /**
	     * Inizializza i campi di una riga dei campi.
	     * 
	     * @param seleziona
	     * @param abilita
	     * @param menuModello
	     * @param menuMatricola
	     * @param pulsante
	     */
	    private void inizializzaRigaTabella(CheckBox seleziona,boolean abilita,ChoiceBox<String> menuModello,ChoiceBox<String> menuMatricola,Button pulsante) {
	    	seleziona.setSelected(abilita);
	    	menuModello.setDisable(!abilita);
			menuMatricola.setDisable(!abilita);
			pulsante.setDisable(!abilita);
			seleziona.selectedProperty().addListener(
					new ChangeListener<Boolean>() {
						@Override
						public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
							menuModello.setDisable(!newValue);
							menuMatricola.setDisable(!newValue);
							pulsante.setDisable(!newValue);
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
	    
	    private void aggiornaMenuMatricola(String nomeHW,ChoiceBox<String> menuMatricola, ChoiceBox<String> menuModello) {
			Programma.aggiornaMenuMatricolaHW(nomeHW,menuModello.getValue(), menuMatricola);
		}
	    
	    

		/**
		 * Permette di aprire una finestra nuovo HW e inizializzarla con i valori della riga associata.
		 * 
		 * @param nomeHW
		 * @param menuModello
		 * @param menuMatricola
		 */
		private void aggiungiHW(String nomeHW, ChoiceBox<String> menuModello,ChoiceBox<String> menuMatricola) {
			String[] input = new String[]{
        			DatiHardware.marcaMatricola(
	        			new Object[]{
	        				nomeHW,
	        				null,
	        				menuModello.getValue() != null ? menuModello.getValue() : "?",
	        				menuMatricola.getValue() != null ? menuMatricola.getValue() : "?",
	        			}
        			),
        			null,null,null,null
        	};
        	Programma.apriFinestraNuovoHardware(this,input);
			
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
		
		@FXML
	    private void aggiungiHWUnitaCentrale(ActionEvent event) {
	        if(event.getEventType().equals(ActionEvent.ACTION)){
	        	aggiungiHW(R.TipoStandardHW.UNITA_CENTALE,menuModelloUnitaCentrale,menuMatricolaUnitaCentrale);
	        	
	        }
	    }
		
		
		
		@FXML
	    private void aggiornaMenuModelloSchedaMadre(MouseEvent event) {
	        if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuModello(R.TipoStandardHW.SCHEDA_MADRE, menuModelloSchedaMadre);
	        }
	    }
	    
	    @FXML
	    private void aggiornaMenuMatricolaSchedaMadre(MouseEvent event) {
	        if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuMatricola(R.TipoStandardHW.SCHEDA_MADRE,menuMatricolaSchedaMadre, menuModelloSchedaMadre);
	        }
	    }
		
		@FXML
	    private void aggiungiHWSchedaMadre(ActionEvent event) {
	        if(event.getEventType().equals(ActionEvent.ACTION)){
	        	aggiungiHW(R.TipoStandardHW.SCHEDA_MADRE,menuModelloSchedaMadre,menuMatricolaSchedaMadre);
	        	
	        }
	    }
		
		

	    

	    @FXML
	    void aggiungiHWProcessore(ActionEvent event) {

	    }

	    @FXML
	    void aggiungiHWRAM(ActionEvent event) {

	    }

	    @FXML
	    void aggiungiHWHDD(ActionEvent event) {

	    }

	    @FXML
	    void aggiungiHWSchedaVideo(ActionEvent event) {

	    }

	    @FXML
	    void aggiungiHWSchedaRete(ActionEvent event) {

	    }

	    @FXML
	    void aggiungiHWMonitor(ActionEvent event) {

	    }

	    @FXML
	    void aggiungiHWMouse(ActionEvent event) {

	    }

	    @FXML
	    void aggiungiHWTastiera(ActionEvent event) {

	    }

	    @FXML
	    void aggiungiHWUPS(ActionEvent event) {

	    }

	    @FXML
	    void aggiungiHWStampante(ActionEvent event) {

	    }

	   
	    @FXML
	    void aggiornaMenuModelloProcessore(ActionEvent event) {

	    }

	    @FXML
	    void aggiornaMenuModelloRAM(ActionEvent event) {

	    }

	    @FXML
	    void aggiornaMenuModelloHDD(ActionEvent event) {

	    }

	    @FXML
	    void aggiornaMenuModelloSchedaVideo(ActionEvent event) {

	    }

	    @FXML
	    void aggiornaMenuModelloSchedaRete(ActionEvent event) {

	    }

	    @FXML
	    void aggiornaMenuModelloMonitor(ActionEvent event) {

	    }

	    @FXML
	    void aggiornaMenuModelloMouse(ActionEvent event) {

	    }

	    @FXML
	    void aggiornaMenuModelloTastiera(ActionEvent event) {

	    }

	    @FXML
	    void aggiornaMenuModelloUPS(ActionEvent event) {

	    }

	    @FXML
	    void aggiornaMenuModelloStampante(ActionEvent event) {

	    }


	    @FXML
	    void aggiornaMenuMatricolaProcessore(ActionEvent event) {

	    }

	    @FXML
	    void aggiornaMenuMatricolaRAM(ActionEvent event) {

	    }

	    @FXML
	    void aggiornaMenuMatricolaHDD(ActionEvent event) {

	    }

	    @FXML
	    void aggiornaMenuMatricolaSchedaVideo(ActionEvent event) {

	    }

	    @FXML
	    void aggiornaMenuMatricolaSchedaRete(ActionEvent event) {

	    }

	    @FXML
	    void aggiornaMenuMatricolaMonitor(ActionEvent event) {

	    }

	    @FXML
	    void aggiornaMenuMatricolaMouse(ActionEvent event) {

	    }

	    @FXML
	    void aggiornaMenuMatricolaTastiera(ActionEvent event) {

	    }

	    @FXML
	    void aggiornaMenuMatricolaUPS(ActionEvent event) {

	    }

	    @FXML
	    void aggiornaMenuMatricolaStampante(ActionEvent event) {

	    }

	    
		
		
	}
