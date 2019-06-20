package it.quasar_x7.gestione_rete.controllo;

import static it.quasar_x7.gestione_rete.programma.Programma.dati;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.quasar_x7.gestione_rete.Dati.DatiDB;
import it.quasar_x7.gestione_rete.Dati.DatiHardware;
import it.quasar_x7.gestione_rete.Dati.DatiHardwareApparato;
import it.quasar_x7.gestione_rete.Dati.DatiSoftwareApparato;
import it.quasar_x7.gestione_rete.programma.Programma;
import it.quasar_x7.gestione_rete.programma.R;
import it.quasar_x7.javafx.Finestra;
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

		public static String apparato = null;

		public static FinestraApparatoController finestraApparato = null;
	    
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
	    private ChoiceBox<String> menuMatricolaUPS;

	    
	    @FXML
	    private ChoiceBox<String> menuModelloTastiera;

	    @FXML
	    private Button pulsanteProcessore;

	    @FXML
	    private Button pulsanteUPS;

	    @FXML
	    private ChoiceBox<String> menuModelloMouse;

	    @FXML
	    private Button pulsanteStampante;

	    @FXML
	    private ChoiceBox<String> menuModelloProcessore;

	    @FXML
	    private ChoiceBox<String> menuMatricolaMonitor;

	    @FXML
	    private ChoiceBox<String> menuMatricolaStampante;

	    @FXML
	    private Button pulsanteSchedaRete;

	    @FXML
	    private ChoiceBox<String> menuModelloStampante;

	    @FXML
	    private ChoiceBox<String> menuMatricolaTastiera;

	    @FXML
	    private ChoiceBox<String> menuMatricolaHDD;

	    @FXML
	    private ChoiceBox<String> menuModelloUPS;

	    @FXML
	    private Button pulsanteMouse;

	    @FXML
	    private ChoiceBox<String> menuMatricolaMouse;

	    
	    @FXML
	    private Button pulsanteSchedaVideo;

	    @FXML
	    private ChoiceBox<String> menuMatricolaRAM;

	    @FXML
	    private ChoiceBox<String> menuModelloHDD;

	    @FXML
	    private ChoiceBox<String> menuMatricolaProcessore;

	    @FXML
	    private ChoiceBox<String> menuModelloRAM;

	    @FXML
	    private ChoiceBox<String> menuModelloMonitor;

	    @FXML
	    private ChoiceBox<String> menuMatricolaSchedaRete;

	   

	    @FXML
	    private ChoiceBox<String> menuMatricolaSchedaVideo;

	    @FXML
	    private Button pulsanteTastiera;

	    @FXML
	    private ChoiceBox<String> menuModelloSchedaVideo;

	    @FXML
	    private Button pulsanteHDD;

	    @FXML
	    private Button pulsanteMonitor;

	    

	    @FXML
	    private Button pulsanteRAM;

	    @FXML
	    private ChoiceBox<String> menuModelloSchedaRete;

	    @FXML
	    private CheckBox selezionaProcessore;

	    @FXML
	    private CheckBox selezionaMouse;

    @FXML
	    private CheckBox selezionaHDD;

	    @FXML
	    private CheckBox selezionaSchedaRete;


	    @FXML
	    private CheckBox selezionaUPS;

	    @FXML
	    private CheckBox selezionaMonitor;

	    @FXML
	    private CheckBox selezionaStampante;

	    @FXML
	    private CheckBox selezionaSchedaVideo;

	    @FXML
	    private CheckBox selezionaRAM;

	    @FXML
	    private CheckBox selezionaTastiera;

	    /**
	     * Initializes the controller class.
	     */
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	
			inizializzaRigaTabella(selezionaUnitaCentrale, true, menuModelloUnitaCentrale, menuMatricolaUnitaCentrale,pulsanteUnitaCentrale);
			inizializzaRigaTabella(selezionaSchedaMadre, false, menuModelloSchedaMadre, menuMatricolaSchedaMadre,pulsanteSchedaMadre);
			inizializzaRigaTabella(selezionaProcessore, true, menuModelloProcessore, menuMatricolaProcessore,pulsanteProcessore);
			inizializzaRigaTabella(selezionaRAM, true, menuModelloRAM, menuMatricolaRAM,pulsanteRAM);
			inizializzaRigaTabella(selezionaHDD, true, menuModelloHDD, menuMatricolaHDD,pulsanteHDD);
			inizializzaRigaTabella(selezionaSchedaVideo, false, menuModelloSchedaVideo, menuMatricolaSchedaVideo,pulsanteSchedaVideo);
			inizializzaRigaTabella(selezionaSchedaRete, false, menuModelloSchedaRete, menuMatricolaSchedaRete,pulsanteSchedaRete);
			inizializzaRigaTabella(selezionaMonitor, true, menuModelloMonitor, menuMatricolaMonitor,pulsanteMonitor);
			inizializzaRigaTabella(selezionaMouse, true, menuModelloMouse, menuMatricolaMouse,pulsanteMouse);
			inizializzaRigaTabella(selezionaTastiera, true, menuModelloTastiera, menuMatricolaTastiera,pulsanteTastiera);
			inizializzaRigaTabella(selezionaUPS, true, menuModelloUPS, menuMatricolaUPS,pulsanteUPS);
			inizializzaRigaTabella(selezionaStampante, false, menuModelloStampante, menuMatricolaStampante,pulsanteStampante);
	    	
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
	        if(event.getEventType().equals(ActionEvent.ACTION)){
	        
	        	
	        	ArrayList<String> errori = new ArrayList<>();
	        	
	        	if(apparato != null) {
	        		datiHardwareApparato.elimataAlcuniRecord(String.format(" `%s` = '%s' ", DatiHardwareApparato.VOCE_APPARATO,apparato));
	        		
	        		aggiungiNuovoHardware(R.TipoStandardHW.HDD,selezionaHDD,menuModelloHDD,menuMatricolaHDD,errori);
	        		aggiungiNuovoHardware(R.TipoStandardHW.MONITOR,selezionaMonitor,menuModelloMonitor,menuMatricolaMonitor,errori);
	        		aggiungiNuovoHardware(R.TipoStandardHW.MOUSE,selezionaMouse,menuModelloMouse,menuMatricolaMouse,errori);
	        		aggiungiNuovoHardware(R.TipoStandardHW.PROCESSORE,selezionaProcessore,menuModelloProcessore,menuMatricolaProcessore,errori);
	        		aggiungiNuovoHardware(R.TipoStandardHW.RAM,selezionaRAM,menuModelloRAM,menuMatricolaRAM,errori);
	        		aggiungiNuovoHardware(R.TipoStandardHW.SCHEDA_MADRE,selezionaSchedaMadre,menuModelloSchedaMadre,menuMatricolaSchedaMadre,errori);
	        		aggiungiNuovoHardware(R.TipoStandardHW.SCHEDA_RETE,selezionaSchedaRete,menuModelloSchedaRete,menuMatricolaSchedaRete,errori);
	        		aggiungiNuovoHardware(R.TipoStandardHW.SCHEDA_VIDEO,selezionaSchedaVideo,menuModelloSchedaVideo,menuMatricolaSchedaVideo,errori);
	        		aggiungiNuovoHardware(R.TipoStandardHW.STAMPANTE,selezionaStampante,menuModelloStampante,menuMatricolaStampante,errori);
	        		aggiungiNuovoHardware(R.TipoStandardHW.TASTIERA,selezionaTastiera,menuModelloTastiera,menuMatricolaTastiera,errori);
	        		aggiungiNuovoHardware(R.TipoStandardHW.UNITA_CENTALE,selezionaUnitaCentrale,menuModelloUnitaCentrale,menuMatricolaUnitaCentrale,errori);
	        		aggiungiNuovoHardware(R.TipoStandardHW.UPS,selezionaUPS,menuModelloUPS,menuMatricolaUPS,errori);
	        		
		        	if(errori.size() == 0) {
	                    if(finestraApparato != null){
	                        finestraApparato.aggiornaTabellaHW();
	                    }
	                    chiusuraSenzaSalvare(event);
		        	}else {
		        		String hw = "";
		        		for(String s: errori) {
		        			hw += s + "\n";
		        		}
		        		Finestra.finestraAvviso(this, String.format(R.Messaggi.IMMOSSIBILE_AGGIORNARE_HW,hw));
		        	}
	        	}else {
	        		Finestra.finestraAvviso(this, R.Messaggi.APPARATO_NON_DEFINITO);
	        	}
	            
	        }
	        
	    }

	    private void aggiungiNuovoHardware(String hardware, CheckBox seleziona,ChoiceBox<String> menuModello, ChoiceBox<String> menuMatricola,ArrayList<String> errori) {
	    	if(verificaHW(hardware,seleziona)){
	    		if(menuModello.getValue() == null && menuMatricola.getValue() == null) {
					Object[] hardwareIndefinito = new Object[]{ 
		                hardware,
		                "",
		                "",
		                "",
		                "",
		                "",
		                ""
		            };
					((DatiHardware)dati.get(DatiHardware.NOME_TABELLA)).aggiungi(hardwareIndefinito);
				}
    			if(!Programma.aggiungiNuovoHardware(null,apparato,hardware,menuModello,menuMatricola)) {
    				errori.add(hardware);
    			}
	    	}
			
		}

		private boolean verificaHW(String tipo, CheckBox seleziona) {
	    	if(seleziona.isSelected()) {
	    		datiHardwareApparato.elimina(tipo);
	    		return true;
	    	}
	    	return false;
	    	
	    }
	    

	    @FXML
	    private void chiusuraSenzaSalvare(ActionEvent event) {
	        if(event.getEventType().equals(ActionEvent.ACTION)){
	            Programma.chiusuraFinestra(this, scenaCorrente);
	            Programma.aggiornaListeNodi();
	            finestraApparato = null;
	            apparato = null;
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
	    private void aggiungiHWProcessore(ActionEvent event) {
	    	if(event.getEventType().equals(ActionEvent.ACTION)){
	        	aggiungiHW(R.TipoStandardHW.PROCESSORE,menuModelloProcessore,menuMatricolaProcessore);
	        }
	    }
	    
	    @FXML
	    private void aggiornaMenuModelloProcessore(MouseEvent event) {
	    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuModello(R.TipoStandardHW.PROCESSORE, menuModelloProcessore);
	        }
	    }
	    
	    @FXML
	    private void aggiornaMenuMatricolaProcessore(MouseEvent event) {
	    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuMatricola(R.TipoStandardHW.PROCESSORE,menuMatricolaProcessore, menuModelloProcessore);
	        }
	    }

	    @FXML
	    private void aggiungiHWRAM(ActionEvent event) {
	    	if(event.getEventType().equals(ActionEvent.ACTION)){
	        	aggiungiHW(R.TipoStandardHW.RAM,menuModelloRAM,menuMatricolaRAM);
	        }
	    }
	    
	    @FXML
	    private void aggiornaMenuMatricolaRAM(MouseEvent event) {
	    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuMatricola(R.TipoStandardHW.RAM,menuMatricolaRAM, menuModelloRAM);
	        }
	    }
	    
	    @FXML
	    private void aggiornaMenuModelloRAM(MouseEvent event) {
	    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuModello(R.TipoStandardHW.RAM, menuModelloRAM);
	        }
	    }

	    @FXML
	    private void aggiungiHWHDD(ActionEvent event) {
	    	if(event.getEventType().equals(ActionEvent.ACTION)){
	        	aggiungiHW(R.TipoStandardHW.HDD,menuModelloHDD,menuMatricolaHDD);
	        }
	    }
	    
	    @FXML
	    private void aggiornaMenuModelloHDD(MouseEvent event) {
	    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuModello(R.TipoStandardHW.HDD, menuModelloHDD);
	        }
	    }

	    @FXML
	    private void aggiornaMenuMatricolaHDD(MouseEvent event) {
	    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuMatricola(R.TipoStandardHW.HDD,menuMatricolaHDD, menuModelloHDD);
	        }
	    }

	    
	    
	    @FXML
	    private void aggiornaMenuModelloSchedaVideo(MouseEvent event) {
	    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuModello(R.TipoStandardHW.SCHEDA_VIDEO, menuModelloSchedaVideo);
	        }
	    }
	    
	    @FXML
	    private void aggiungiHWSchedaVideo(ActionEvent event) {
	    	if(event.getEventType().equals(ActionEvent.ACTION)){
	        	aggiungiHW(R.TipoStandardHW.SCHEDA_VIDEO,menuModelloSchedaVideo,menuMatricolaSchedaVideo);
	        }
	    }

	    @FXML
	    private void aggiornaMenuMatricolaSchedaVideo(MouseEvent event) {
	    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuMatricola(R.TipoStandardHW.SCHEDA_VIDEO,menuMatricolaSchedaVideo, menuModelloSchedaVideo);
	        }
	    }

	    @FXML
	    private void aggiungiHWSchedaRete(ActionEvent event) {
	    	if(event.getEventType().equals(ActionEvent.ACTION)){
	        	aggiungiHW(R.TipoStandardHW.SCHEDA_RETE,menuModelloSchedaRete,menuMatricolaSchedaRete);
	        }
	    }
	    
	    @FXML
	    private void aggiornaMenuModelloSchedaRete(MouseEvent event) {
	    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuModello(R.TipoStandardHW.SCHEDA_RETE, menuModelloSchedaRete);
	        }
	    }
	    
	    
	    @FXML
	    private void aggiornaMenuMatricolaSchedaRete(MouseEvent event) {
	    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuMatricola(R.TipoStandardHW.SCHEDA_RETE,menuMatricolaSchedaRete, menuModelloSchedaRete);
	        }
	    }


	    @FXML
	    private void aggiungiHWMonitor(ActionEvent event) {
	    	if(event.getEventType().equals(ActionEvent.ACTION)){
	        	aggiungiHW(R.TipoStandardHW.MONITOR,menuModelloMonitor,menuMatricolaMonitor);
	        }
	    }
	    
	    @FXML
	    private void aggiornaMenuModelloMonitor(MouseEvent event) {
	    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuModello(R.TipoStandardHW.MONITOR, menuModelloMonitor);
	        }
	    }

	    @FXML
	    private void aggiornaMenuMatricolaMonitor(MouseEvent event) {
	    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuMatricola(R.TipoStandardHW.MONITOR,menuMatricolaMonitor, menuModelloMonitor);
	        }
	    }

	    

	    @FXML
	    private void aggiungiHWMouse(ActionEvent event) {
	    	if(event.getEventType().equals(ActionEvent.ACTION)){
	        	aggiungiHW(R.TipoStandardHW.MOUSE,menuModelloMouse,menuMatricolaMouse);
	        }
	    }

	    @FXML
	    private void aggiornaMenuModelloMouse(MouseEvent event) {
	    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuModello(R.TipoStandardHW.MOUSE, menuModelloMouse);
	        }
	    }
	    

	    @FXML
	    private void aggiornaMenuMatricolaMouse(MouseEvent event) {
	    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuMatricola(R.TipoStandardHW.MOUSE,menuMatricolaMouse, menuModelloMouse);
	        }
	    }
	    
	    
	    
	    @FXML
	    private void aggiungiHWTastiera(ActionEvent event) {
	    	if(event.getEventType().equals(ActionEvent.ACTION)){
	        	aggiungiHW(R.TipoStandardHW.TASTIERA,menuModelloTastiera,menuMatricolaTastiera);
	        }
	    }
	    
	    @FXML
	    private void aggiornaMenuModelloTastiera(MouseEvent event) {
	    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuModello(R.TipoStandardHW.TASTIERA, menuModelloTastiera);
	        }
	    }
	    
	    @FXML
	    private void aggiornaMenuMatricolaTastiera(MouseEvent event) {
	    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuMatricola(R.TipoStandardHW.TASTIERA,menuMatricolaTastiera, menuModelloTastiera);
	        }
	    }
	    

	    @FXML
	    private void aggiungiHWUPS(ActionEvent event) {
	    	if(event.getEventType().equals(ActionEvent.ACTION)){
	        	aggiungiHW(R.TipoStandardHW.UPS,menuModelloUPS,menuMatricolaUPS);
	        }
	    }
	    
	    @FXML
	    private void aggiornaMenuModelloUPS(MouseEvent event) {
	    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuModello(R.TipoStandardHW.UPS, menuModelloUPS);
	        }
	    }
	    

	    @FXML
	    private void aggiornaMenuMatricolaUPS(MouseEvent event) {
	    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuMatricola(R.TipoStandardHW.UPS,menuMatricolaUPS, menuModelloUPS);
	        }
	    }
	    

	    @FXML
	    private void aggiungiHWStampante(ActionEvent event) {
	    	if(event.getEventType().equals(ActionEvent.ACTION)){
	        	aggiungiHW(R.TipoStandardHW.STAMPANTE,menuModelloStampante,menuMatricolaStampante);
	        }
	    }

	    @FXML
	    private void aggiornaMenuModelloStampante(MouseEvent event) {
	    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuModello(R.TipoStandardHW.STAMPANTE, menuModelloStampante);
	        }
	    }

	    @FXML
	    private void aggiornaMenuMatricolaStampante(MouseEvent event) {
	    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        	aggiornaMenuMatricola(R.TipoStandardHW.STAMPANTE,menuMatricolaStampante, menuModelloStampante);
	        }
	    }

	    
		
		
	}
