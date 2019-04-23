package it.quasar_x7.gestione_rete.controllo;

import it.quasar_x7.gestione_rete.Dati.DatiCasaHardware;
import it.quasar_x7.gestione_rete.Dati.DatiDB;
import it.quasar_x7.gestione_rete.Dati.DatiHardware;
import it.quasar_x7.gestione_rete.Dati.DatiStato;
import it.quasar_x7.gestione_rete.Dati.DatiTipoHardware;
import it.quasar_x7.gestione_rete.programma.Programma;
import static it.quasar_x7.gestione_rete.programma.Programma.dati;
import it.quasar_x7.gestione_rete.programma.R;
import it.quasar_x7.javafx.finestre.controllo.TabellaController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Dr. Domenico della Peruta
 */
public class FinestraHardwareController implements Initializable {

    public static Scene scenaCorrente = null;
    public static String[] input = null;
    public static TabellaController tabella= null;
    
        
    @FXML
    private ChoiceBox<String> menuTipo;

    @FXML
    private ChoiceBox<String> menuCasa;

   
    @FXML
    private TextArea note;

    @FXML
    private ChoiceBox<String> menuStato;

    @FXML
    private TextField modello;

  

    @FXML
    private TextField NUC;

    @FXML
    private TextField matricola;
    
        
    protected DatiDB datiHardware = dati.get(DatiHardware.NOME_TABELLA);
    protected DatiTipoHardware datiTipoHardware = (DatiTipoHardware) dati.get(DatiTipoHardware.NOME_TABELLA);
    protected DatiCasaHardware datiCasaHardware = (DatiCasaHardware) dati.get(DatiCasaHardware.NOME_TABELLA);
    protected DatiStato datiStato = (DatiStato) dati.get(DatiStato.NOME_TABELLA);
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(input != null){
            if(input[0] != null){
                String[] lista = DatiHardware.marcaMatricola(input[0]);
                if(lista.length == 3){
                	menuTipo.setValue(lista[0]);
                    modello.setText(lista[1]);
                    matricola.setText(lista[2]);
                }
            }
            
            if(input[1] != null)
                menuCasa.setValue(input[1]);
             
            if(input[2] != null)
                NUC.setText(input[2]);
             
            if(input[3] != null)
                menuStato.setValue(input[3]);
            
            if(input[4] != null)
                note.setText(input[4]);
        }
        menuStato.getItems().addAll(R.Stato);
        
        ArrayList<String> caseHardware = datiCasaHardware.lista();
        if(caseHardware != null)
            menuCasa.getItems().addAll(caseHardware);
        
        ArrayList<String> tipoHardware = datiTipoHardware.lista();
        if(tipoHardware != null)
            menuTipo.getItems().addAll(tipoHardware);
        
        ArrayList<String> lista = datiStato.listaSemplice();
        if(lista != null){
            menuStato.getItems().addAll(lista);
        }
        
    }   

    @FXML
    protected void chiusuraSenzaSalvare(ActionEvent event) {
        Programma.chiusuraFinestra(this, scenaCorrente);
        input = null;
        tabella= null;
    }


    @FXML
    protected void aggiungiNuovoTipo(ActionEvent event) {
        Programma.apriSempliceLista(
                this,
                datiTipoHardware,
                R.Etichette.FINESTRA_LISTA_TIPO_HW,
                R.Etichette.TIPO_HW,
                R.Messaggi.SOSTITUZIONE_TIPO_HW
        );
    }
    
    @FXML
    protected void aggiungiStato(ActionEvent event) {
        Programma.apriListaStato(
                this,
                datiStato,
                datiStato.lista(),
                R.Etichette.FINESTRA_LISTA_STATO,
                R.Etichette.TIPO_STATO,
                R.Messaggi.SOSTITUZIONE_STATO
        );
    }

    @FXML
    protected void aggiungiCasa(ActionEvent event) {
        Programma.apriSempliceLista(
                this,
                datiCasaHardware,
                R.Etichette.FINESTRA_LISTA_CASA_HW,
                R.Etichette.CASA_HW,
                R.Messaggi.SOSTITUZIONE_CASA_HW
        );
    }
    
    
    @FXML
    protected void aggiornaMenuTipo(MouseEvent event) {
        menuTipo.getItems().clear();
        menuTipo.getItems().addAll(datiTipoHardware.lista());
    }
    
    @FXML
    protected void aggiornaMenuStato(MouseEvent event) {
        ArrayList<String> lista = datiStato.listaSemplice();
        if(lista != null){
            menuStato.getItems().clear();
            menuStato.getItems().addAll(lista);
        }
    }

    @FXML
    protected void aggiornaMenuCasa(MouseEvent event) {
        menuCasa.getItems().clear();
        menuCasa.getItems().addAll(datiCasaHardware.lista());
    }

    @FXML
    protected void salva(ActionEvent event) {
        
		if(event.getEventType().equals(ActionEvent.ACTION)) {
			Object[] record = new Object[]{ // nuovi valori da salvare
                menuTipo.getValue(),
                menuCasa.getValue(),
                modello.getText(),
                matricola.getText(),
                NUC.getText(),
                menuStato.getValue(),
                note.getText()
            };
    		Programma.salva(
                    this, 
                    // condizione
                    !menuTipo.getValue().isEmpty(), 
                    datiHardware, 
                    input(), // contiene i vecchi valori chiave modificare
                    record, // nvalori da salvare 
                    event, 
                    (ActionEvent evento, String chiave) -> {
                         if(tabella != null){
                            ArrayList<String> riga = new ArrayList<>();
                            riga.add(DatiHardware.marcaMatricola(record));
                            riga.add((String) record[1]);
                            riga.add((String) record[4]);
                            riga.add((String) record[5]);
                            riga.add((String) record[6]);
                            

                            if(chiave != null)
                                tabella.modificaRiga(chiave,riga);
                            else
                                tabella.aggiungiRiga(riga);
                        }
                        chiusuraSenzaSalvare(evento);
                    }
            );
		}
    }
    /**
     * Contiene solo gli degli elementi chiave dell'input in ordine con il record SQL della tabbella hardware
     * @return
     */
    private String[] input() {
    	if(input != null) {
    		String[] chiave = DatiHardware.marcaMatricola(input[0]);
    		return new String[] {
				chiave[0],
                null,
                chiave[1],
                chiave[2],
                null,
                null,
                null	
    		};
    	}
    	return null;
    }

}
