package it.quasar_x7.gestione_rete.controllo;

import it.quasar_x7.gestione_rete.Dati.DatiImpostazioni;
import it.quasar_x7.gestione_rete.programma.Programma;
import static it.quasar_x7.gestione_rete.programma.Programma.dati;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Dr. Domenico della Peruta
 */
public class FinestraImpostazioniController  implements Initializable {

    @FXML
    private TextField campoEnte;

    @FXML
    private TextField campoUfficio;

    @FXML
    private TextField campoSimbolo;
    
    @FXML
    private RadioButton selezionaFirma2Riga2;

    @FXML
    private RadioButton selezionaFirma2Riga1;
    
    @FXML
    private TextField campoFirma1Nominativo2;

    @FXML
    private TextField campoFirma2Responsabile1;

    @FXML
    private TextField campoFirma1Nominativo1;

    @FXML
    private TextField campoFirma2Responsabile2;
    
    @FXML
    private TextField campoFirma1Responsabile2;

    @FXML
    private TextField campoFirma1Responsabile1;

    @FXML
    private TextField campoFirma2Nominativo2;

    @FXML
    private TextField campoFirma2Nominativo1;

    @FXML
    private RadioButton selezionaFirma1Riga2;

    @FXML
    private RadioButton selezionaFirma1Riga1;
    
    @FXML
    private TextArea testoRetropagina;
    
    @FXML
    private WebView vistaTestoRetropagina;

    private WebEngine webEngine;
    
    public static String[] input = null;
    
    protected DatiImpostazioni datiImpostazioni = (DatiImpostazioni) dati.get(DatiImpostazioni.NOME_TABELLA);
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	ToggleGroup firma1 = new ToggleGroup();
    	selezionaFirma1Riga1.setToggleGroup(firma1);
    	selezionaFirma1Riga2.setToggleGroup(firma1);
    	
    	ToggleGroup firma2 = new ToggleGroup();
    	selezionaFirma2Riga1.setToggleGroup(firma2);
    	selezionaFirma2Riga2.setToggleGroup(firma2);
    
    	inizializzaSelettori(selezionaFirma1Riga1, selezionaFirma1Riga2,DatiImpostazioni.FIRMA1);
    	inizializzaSelettori(selezionaFirma2Riga1, selezionaFirma2Riga2,DatiImpostazioni.FIRMA2);
    	
    	
    	String ente = datiImpostazioni.valore(DatiImpostazioni.INTESTAZIONE_ENTE);
    	campoEnte.setText(ente);
    	String ufficio = datiImpostazioni.valore(DatiImpostazioni.INTESTAZIONE_UFFICIO);
    	campoUfficio.setText(ufficio);
    	
    	String firma1Qualifica1 = datiImpostazioni.valore(DatiImpostazioni.FIRMA1_QUALIFICA1);
    	campoFirma1Responsabile1.setText(firma1Qualifica1);
    	String firma1Nome1 = datiImpostazioni.valore(DatiImpostazioni.FIRMA1_NOME1);
    	campoFirma1Nominativo1.setText(firma1Nome1);
    	String firma1Qualifica2 = datiImpostazioni.valore(DatiImpostazioni.FIRMA1_QUALIFICA2);
    	campoFirma1Responsabile2.setText(firma1Qualifica2);
    	String firma1Nome2 = datiImpostazioni.valore(DatiImpostazioni.FIRMA1_NOME2);
    	campoFirma1Nominativo2.setText(firma1Nome2);
    	
    	String firma2Qualifica1 = datiImpostazioni.valore(DatiImpostazioni.FIRMA2_QUALIFICA1);
    	campoFirma2Responsabile1.setText(firma2Qualifica1);
    	String firma2Nome1 = datiImpostazioni.valore(DatiImpostazioni.FIRMA2_NOME1);
    	campoFirma2Nominativo1.setText(firma2Nome1);
    	String firma2Qualifica2 = datiImpostazioni.valore(DatiImpostazioni.FIRMA2_QUALIFICA2);
    	campoFirma2Responsabile2.setText(firma2Qualifica2);
    	String firma2Nome2 = datiImpostazioni.valore(DatiImpostazioni.FIRMA2_NOME2);
    	campoFirma2Nominativo2.setText(firma2Nome2);
    	
    	String testoPag2 = datiImpostazioni.valore(DatiImpostazioni.TESTO_PAG);
    	testoRetropagina.setText(testoPag2);
    	
    	webEngine = vistaTestoRetropagina.getEngine();
        webEngine.loadContent(testoPag2);
    	
    	
    } 
    
    
    private void inizializzaSelettori(RadioButton selettore1,RadioButton selettore2,String firma) {

    	String rigaFirma = datiImpostazioni.valore(firma);
    	if(rigaFirma.equals(DatiImpostazioni.FIRMA_RIGA1)){
    		selettore1.setSelected(true);
    		selettore2.setSelected(false);
    	}else if(rigaFirma.equals(DatiImpostazioni.FIRMA_RIGA2)){
    		selettore2.setSelected(true);
    		selettore1.setSelected(false);
    	}else {
    		selettore1.setSelected(false);
    		selettore2.setSelected(false);
    	}
    }
   

    @FXML
    protected void chiusuraSenzaSalvare(ActionEvent event) {
    	if(event.getEventType().equals(ActionEvent.ACTION)){
	        Programma.chiusuraFinestra(this);
	        input = null;
    	}
    }

    @FXML
    private void aggiornaVistaPagina(KeyEvent event) {
    	if(event.getEventType().equals(KeyEvent.KEY_RELEASED)){
        	webEngine.loadContent(testoRetropagina.getText());
    	}
    }
    private void salvaSelezioneFirma(RadioButton riga1,RadioButton riga2,String firma) {
    	if(riga1.isSelected()) {
    		datiImpostazioni.valore(firma,DatiImpostazioni.FIRMA_RIGA1);
    	}else if(riga2.isSelected()) { 
    		datiImpostazioni.valore(firma,DatiImpostazioni.FIRMA_RIGA2);
    	}else {
    		datiImpostazioni.valore(firma,"");
    	}
    }
    
    @FXML
    protected void salva(ActionEvent event) {
    	if(event.getEventType().equals(ActionEvent.ACTION)){
    		salvaSelezioneFirma(selezionaFirma1Riga1, selezionaFirma1Riga2, DatiImpostazioni.FIRMA1);
    		salvaSelezioneFirma(selezionaFirma2Riga1, selezionaFirma2Riga2, DatiImpostazioni.FIRMA2);
	    	
	    	datiImpostazioni.valore(DatiImpostazioni.INTESTAZIONE_ENTE,campoEnte.getText());
	    	datiImpostazioni.valore(DatiImpostazioni.INTESTAZIONE_UFFICIO,campoUfficio.getText());
	    	
	    	datiImpostazioni.valore(DatiImpostazioni.FIRMA1_QUALIFICA1,campoFirma1Responsabile1.getText());
	    	datiImpostazioni.valore(DatiImpostazioni.FIRMA1_NOME1,campoFirma1Nominativo1.getText());
	    	datiImpostazioni.valore(DatiImpostazioni.FIRMA1_QUALIFICA2,campoFirma1Responsabile2.getText());
	    	datiImpostazioni.valore(DatiImpostazioni.FIRMA1_NOME2,campoFirma1Nominativo2.getText());
	    	
	    	datiImpostazioni.valore(DatiImpostazioni.FIRMA2_QUALIFICA1,campoFirma2Responsabile1.getText());
	    	datiImpostazioni.valore(DatiImpostazioni.FIRMA2_NOME1,campoFirma2Nominativo1.getText());
	    	datiImpostazioni.valore(DatiImpostazioni.FIRMA2_QUALIFICA2,campoFirma2Responsabile2.getText());
	    	datiImpostazioni.valore(DatiImpostazioni.FIRMA2_NOME2,campoFirma2Nominativo2.getText());
	    	
	    	datiImpostazioni.valore(DatiImpostazioni.TESTO_PAG,testoRetropagina.getText());
	    	
	    	chiusuraSenzaSalvare(event);
    	}
    	
    }
    
    
       
    
}
