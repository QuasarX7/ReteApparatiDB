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
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;




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
	    private TableColumn<Software, Boolean> colonnaPredefinito;
	    
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
	    
	   
	    private void inizializzaColonnaPredefinito() {
	    	
	    	colonnaPredefinito.setCellValueFactory(new Callback<CellDataFeatures<Software, Boolean>, ObservableValue<Boolean>>() {
	 
	            @Override
	            public ObservableValue<Boolean> call(CellDataFeatures<Software, Boolean> param) {
	                Software sw = param.getValue();
	 
	                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(sw.getPredefinito());
	 
	                booleanProp.addListener(new ChangeListener<Boolean>() {
	 
	                    @Override
	                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,Boolean newValue) {
	                        sw.setPredefinito(newValue);
	                        datiSoftware.predefinito(sw.getNome(),sw.getLicenza(),newValue);
	                    }
	                });
	                return booleanProp;
	            }
	        });
	 
	    	 colonnaPredefinito.setCellFactory(new Callback<TableColumn<Software, Boolean>, TableCell<Software, Boolean>>() {
		            @Override
		            public TableCell<Software, Boolean> call(TableColumn<Software, Boolean> p) {
		                CheckBoxTableCell<Software, Boolean> cell = new CheckBoxTableCell<Software, Boolean>();
		                cell.setAlignment(Pos.CENTER);
		                return cell;
		            }
	        });
	    }
	    
	    
	    @FXML
	    private void salva(ActionEvent event) {
	        if(event.getEventType().equals(ActionEvent.ACTION)){
	        
	        	if(apparato != null) {
	        		datiSoftwareApparato.elimataAlcuniRecord(String.format(" `%s` = '%s' ", DatiSoftwareApparato.VOCE_APPARATO,apparato));
	        		for(Software sw:listaSW) {
	        			if(sw.getPredefinito()) {
		        			Object[] record = new Object[]{
	                            apparato,
	                            sw.getNome(),
	                            sw.getLicenza()
	                        };
							if(!datiSoftwareApparato.aggiungi(record)) {
								Finestra.finestraAvviso(
										this, 
										String.format(
												R.Messaggi.ERRORE_SALVATAGGIO,
												sw.getNome(),DatiDB.stampa(record)
										)
								);
								return;
							}
	        			}
	        		}
	        		if(finestraApparato != null){
                        finestraApparato.aggiornaTabellaSW();
                    }
	        		chiusuraSenzaSalvare(event);
	        	}else {
	        		Finestra.finestraAvviso(this, R.Messaggi.APPARATO_NON_DEFINITO);
	        	}
	            
	        }
	        
	    }
	    
	    private void crezioneTabellaSW(){
	        colonnaSoftware.setCellValueFactory(new PropertyValueFactory<>(R.Modello.SW.NOME));
	        colonnaLicenza.setCellValueFactory(new PropertyValueFactory<>(R.Modello.SW.LICENZA));
	        colonnaPredefinito.setCellValueFactory(new PropertyValueFactory<>(R.Modello.SW.PREDEFINITO));

	        inizializzaColonnaPredefinito();
	        
	    	tabella.setEditable(true);
	        tabella.setItems(listaSW);
	    }
	    
	    public void aggiornaTabellaSW(){
	        listaSW.clear();
	        ArrayList<Software> lista = datiSoftware.listaSoftware();
	        
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