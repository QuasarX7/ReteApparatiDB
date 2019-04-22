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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

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
    
    
    
    public static Scene scenaCorrente = null;
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
    } 
   

    @FXML
    protected void chiusuraSenzaSalvare(ActionEvent event) {
        Programma.chiusuraFinestra(this, scenaCorrente);
        input = null;
    }


    @FXML
    protected void salva(ActionEvent event) {
        //if(!campoEnte.getText().isEmpty() && !campoUfficio.getText().isEmpty()) {
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
	    	
	    	chiusuraSenzaSalvare(event);
        //}
    }
    
    
       
    
}
