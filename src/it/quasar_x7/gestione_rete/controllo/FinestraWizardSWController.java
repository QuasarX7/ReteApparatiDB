package it.quasar_x7.gestione_rete.controllo;

import static it.quasar_x7.gestione_rete.programma.Programma.dati;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.quasar_x7.gestione_rete.Dati.DatiDB;
import it.quasar_x7.gestione_rete.Dati.DatiHardware;
import it.quasar_x7.gestione_rete.Dati.DatiHardwareApparato;
import it.quasar_x7.gestione_rete.Dati.DatiSoftware;
import it.quasar_x7.gestione_rete.Dati.DatiSoftwareApparato;
import it.quasar_x7.gestione_rete.modello.Software;
import it.quasar_x7.gestione_rete.programma.Programma;
import it.quasar_x7.gestione_rete.programma.R;
import it.quasar_x7.javafx.Finestra;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;




	/**
	 * FXML Controller class
	 *
	 * @author Dott. Domenico della Peruta
	 */
	public class FinestraWizardSWController implements Initializable {

	    static public Scene scenaCorrente = null;

		public static String apparato = null;

		public static FinestraApparatoController finestraApparato = null;
	    
		protected DatiSoftwareApparato datiSoftwareApparato = (DatiSoftwareApparato) dati.get(DatiSoftwareApparato.NOME_TABELLA);
		protected DatiSoftware datiSoftware = (DatiSoftware) dati.get(DatiSoftware.NOME_TABELLA);
		  
	    public static ObservableList<Software> listaSW = FXCollections.observableArrayList();
	    
	    
	    @FXML
	    private TableColumn<Software, String> colonnaLicenza;

	    @FXML
	    private TableColumn<Software, String> colonnaSoftware;

	    @FXML
	    private TableView<Software> tabella;

	    /**
	     * Initializes the controller class.
	     */
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	crezioneTabellaSW();
	    	aggiornaTabellaSW();
	    }  
	    
	   
	    
	    
	    
	    @FXML
	    private void salva(ActionEvent event) {
	        if(event.getEventType().equals(ActionEvent.ACTION)){
	        
	        	if(apparato != null) {
	        			
		        	
	        	}else {
	        		Finestra.finestraAvviso(this, R.Messaggi.APPARATO_NON_DEFINITO);
	        	}
	            
	        }
	        
	    }
	    
	    private void crezioneTabellaSW(){
	        colonnaSoftware.setCellValueFactory(new PropertyValueFactory<>(R.Modello.SW.NOME));
	        colonnaLicenza.setCellValueFactory(new PropertyValueFactory<>(R.Modello.SW.LICENZA));
	        
	        tabella.setItems(listaSW);
	    }
	    
	    public void aggiornaTabellaSW(){
	        listaSW.clear();
	        ArrayList<Software> lista = datiSoftware.listaSoftwarePredefinito();
	        
	        if(lista != null) {
	            for(Software sw:lista)
	                listaSW.add(sw);
	        }
	    }


	    @FXML
	    private void chiusuraSenzaSalvare(ActionEvent event) {
	        if(event.getEventType().equals(ActionEvent.ACTION)){
	            Programma.chiusuraFinestra(this, scenaCorrente);
	            finestraApparato = null;
	            apparato = null;
	        }
	    }
	    
	}