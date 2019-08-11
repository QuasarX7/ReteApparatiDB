package it.quasar_x7.gestione_rete.controllo;

import static it.quasar_x7.gestione_rete.programma.Programma.dati;

import java.net.URL;
import java.util.ResourceBundle;

import it.quasar_x7.controllo.FinestraGestioneUtentiController;
import it.quasar_x7.gestione_rete.Dati.DatiLogin;
import it.quasar_x7.gestione_rete.programma.R;
import it.quasar_x7.java.utile.CifrarioVigeneve;
import it.quasar_x7.javafx.Finestra;
import it.quasar_x7.modello.LivelloAccesso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class FinestraPasswordController implements Initializable {

    static public Scene scenaCorrente = null;
    
    protected DatiLogin datiUtente = (DatiLogin) dati.get(DatiLogin.NOME_TABELLA);
    
    @FXML
    private PasswordField campoPassword;

    @FXML
    private PasswordField campoConfermaPassword;

    @FXML
    private PasswordField campoNuovaPassword;

    @FXML
    private Button pulsanteChiusura;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	Finestra.infoFinestreAperte(pulsanteChiusura);
    	inizializza();
    }   
    
    @FXML
    private void salva(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION)){
        	if(FinestraGestioneUtentiController.utente != null) {
        		Object[] record = datiUtente.accedi(new Object[]{FinestraGestioneUtentiController.utente});
        		if(record != null) {
        			CifrarioVigeneve codifica = new CifrarioVigeneve(LivelloAccesso.CHIAVE_CODIFICA);
        			if(record[1].equals(codifica.cifra(campoPassword.getText()))){
		        		if(campoNuovaPassword.getText().equals(campoConfermaPassword.getText())) {
		        			String password = codifica.cifra(campoNuovaPassword.getText());
		        			datiUtente.modificaPassword(FinestraGestioneUtentiController.utente, password);
		            		Finestra.caricaFinestra(this, R.FXML.FINESTRA_PRINCIPALE);
		                }else{
		                	Finestra.finestraAvviso(this, R.Messaggi.ERRORE_PASSWORD);
		                	inizializza();
		                }
        			}else{
        				Finestra.finestraAvviso(this, R.Messaggi.ERRORE_PASSWORD);
	                	inizializza();
        			}
        		}
            }
        }
            
    }

    private void inizializza() {
    	campoPassword.setText("");
    	campoNuovaPassword.setText("");
    	campoConfermaPassword.setText("");
    }

    

    @FXML
    private void chiusuraSenzaSalvare(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION)){
        	Finestra.caricaFinestra(this, R.FXML.LOGIN);
        }
    }
    
}
