package it.quasar_x7.gestione_rete.controllo;

import it.quasar_x7.gestione_rete.Dati.DatiDB;
import it.quasar_x7.gestione_rete.Dati.DatiGrado;
import it.quasar_x7.gestione_rete.Dati.DatiPosizione;
import it.quasar_x7.gestione_rete.Dati.DatiResponsabileSito;
import it.quasar_x7.gestione_rete.programma.Programma;
import static it.quasar_x7.gestione_rete.programma.Programma.dati;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Dr. Domenico della PERUTA
 */
public class FinestraResponsabileController implements Initializable {

    public static TabellaController tabella = null;
    public static Scene scenaCorrente = null;
    public static String[] input = null;
   
    
    @FXML
    private ChoiceBox<String> grado;

    @FXML
    private TextField cognome;

    @FXML
    private TextField nome;

    @FXML
    private TextField responsabile;
    
    protected DatiGrado datiGrado = (DatiGrado) dati.get(DatiGrado.NOME_TABELLA);
    protected DatiDB datiResponsabileSito = dati.get(DatiResponsabileSito.NOME_TABELLA);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        grado.getItems().addAll(datiGrado.lista(true));
        CampoTesto.maiuscolo(cognome);
        CampoTesto.soloCaratteri(cognome, 40, " '");
        CampoTesto.primoMaiuscolo(nome);
        CampoTesto.soloCaratteri(nome, 50, " ',àèéòìçù");
        if(input != null){
            if(input[1] != null){
                String[] lista = DatiResponsabileSito.nominativo(input[1]);
                if(lista.length == 3){
                    grado.setValue(lista[0]);
                    cognome.setText(lista[1]);
                    nome.setText(lista[2]);
                }
            }
            if(input[0] != null)
                responsabile.setText(input[0]);
        }
    }    

    @FXML
    private void salva(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION)){
        
            Programma.salva(
                    this, 
                    !responsabile.getText().isEmpty(), 
                    datiResponsabileSito, 
                    input, 
                    new Object[]{
                        responsabile.getText(),
                        grado.getValue(),
                        cognome.getText(),
                        nome.getText()
                    }, 
                    event, 
                    (ActionEvent evento,String chiave)->{
                        if(tabella != null){
                            ArrayList<String> riga = new ArrayList<>();
                            riga.add(responsabile.getText());
                            riga.add(
                                    DatiResponsabileSito.nominativo(
                                    new Object[]{
                                        responsabile.getText(),
                                        grado.getValue(),
                                        cognome.getText(),
                                        nome.getText()
                                    }
                                )
                            );

                            if(chiave != null)
                                tabella.modificaRiga(chiave,riga);
                            else
                                tabella.aggiungiRiga(riga);
                        }
                     // aggiorna tutti gli apparati 
                        
                        boolean procedi = Programma.salvaModifichePosizione(
                                this, 
                                DatiPosizione.VOCE_TABELLA_RESPONSABILE, 
                                input, 0, 
                                responsabile
                        );
                        if(procedi)
                            chiusuraSenzaSalvare(evento);
                    }
            );
        }
    }

    @FXML
    private void aggiornaGrado(MouseEvent event) {
    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        grado.getItems().clear();
	        grado.getItems().addAll(datiGrado.lista(true));
    	}
    }

    @FXML
    private void aggiungiNuovoGrado(ActionEvent event) {
    	if(event.getEventType().equals(ActionEvent.ACTION)){
    		Programma.listaGradi(this);
    	}
    }

    @FXML
    private void chiusuraSenzaSalvare(ActionEvent event) {
    	if(event.getEventType().equals(ActionEvent.ACTION)){
	        Programma.chiusuraFinestra(this, scenaCorrente);
	        input = null;
	        tabella= null;
    	}
    }
 
}
