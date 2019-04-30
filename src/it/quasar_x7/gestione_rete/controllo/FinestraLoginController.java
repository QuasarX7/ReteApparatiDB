package it.quasar_x7.gestione_rete.controllo;

import it.quasar_x7.gestione_rete.Dati.DatiLogin;
import it.quasar_x7.gestione_rete.programma.Programma;
import static it.quasar_x7.gestione_rete.programma.Programma.dati;
import it.quasar_x7.gestione_rete.programma.R;
import it.quasar_x7.controllo.FinestraGestioneUtentiController;
import it.quasar_x7.java.utile.CifrarioVigeneve;
import it.quasar_x7.javafx.Finestra;
import it.quasar_x7.modello.LivelloAccesso;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;

/**
 * FXML Controller class
 *
 * @author Dott. Domenico dellla Peruta
 */
public class FinestraLoginController implements Initializable {

    public static ArrayList<String> listaMenu;

     
    @FXML
    private ChoiceBox<String> menuUtenti;
    
    @FXML
    private PasswordField campoPassword;

    
    
    /**
     * Metodo di inizializzazione della classe.
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        caricaDatiUtente();
    }

    
    private void caricaDatiUtente(){
        if(listaMenu != null){
            this.menuUtenti.getItems().addAll(listaMenu);
        }
    }
    
    
    /**
     * Metodo che gestisce il pulsante "Accedi" che permette di verificare il login
     * ed accedere alla finestra principale.
     * @param event
     */
    @FXML
    public void accedi(ActionEvent event){
    	if(event.getEventType().equals(ActionEvent.ACTION)){
	        String utente = menuUtenti.getValue();
	        if(utente != null){
	            if(controlloPassword(campoPassword.getText(),utente)){
	                FinestraGestioneUtentiController.utente = utente;
	                Finestra.caricaFinestra(this, R.FXML.FINESTRA_PRINCIPALE);
	            }else{
	            	Finestra.finestraAvviso(this, R.Messaggi.ERRORE_PASSWORD);
	            }
	        }
	        campoPassword.setText("");
    	}
    }
    

    /**
     * Metodo che effettua la verifica della password di un utente.
     * 
     * @param password
     * @param utente
     * @return 
     */
    private boolean controlloPassword(String password, String utente) {
        if(utente.length() > 0){
            DatiLogin datiLogin = (DatiLogin) dati.get(DatiLogin.NOME_TABELLA);
            CifrarioVigeneve codifica = new CifrarioVigeneve(LivelloAccesso.CHIAVE_CODIFICA);
            Object[] record = datiLogin.accedi(new Object[]{utente});
            if(record != null){
                if(record[1] != null){
                    if(record[1].equals(codifica.cifra(password))){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Metodo che apre la finestra per la modifica della password.
     * 
     * @param event
     */
    @FXML
    public void modifica(ActionEvent event){
    	if(event.getEventType().equals(ActionEvent.ACTION)){
    		if(menuUtenti.getValue() != null) {
		    	FinestraGestioneUtentiController.utente = menuUtenti.getValue();
		    	Finestra.caricaFinestra(this, R.FXML.FINESTRA_PASSWORD);
    		}else{
    			Finestra.finestraAvviso(this, R.Messaggi.SELEZIONA_UTENTE);
    		}
    	}
    }
    
    
    
    
    /**
     * Chiusura programma.
     * 
     * @param event 
     */
    @FXML
    void chiusuraSenzaSalvare(ActionEvent event) {
    	if(event.getEventType().equals(ActionEvent.ACTION)){
            Platform.exit();
            System.exit(0);
    	}
    }
    
}


