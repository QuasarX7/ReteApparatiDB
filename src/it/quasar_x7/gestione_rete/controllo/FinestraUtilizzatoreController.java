package it.quasar_x7.gestione_rete.controllo;
 
import it.quasar_x7.gestione_rete.Dati.DatiGrado;
import it.quasar_x7.gestione_rete.Dati.DatiUtilizzatore;
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
 * @author Dr. Domenico della Peruta
 */
public class FinestraUtilizzatoreController implements Initializable {
 
    static public Scene scenaCorrente = null;
    static public String[] input = null;
    public static TabellaController tabella = null;
     
     
    @FXML
    private TextField mail;
 
    @FXML
    private TextField cognome;
 
    @FXML
    private TextField nome;
     
    @FXML
    private TextField account;
     
       
    @FXML
    private ChoiceBox<String> grado;
     
     
    protected DatiGrado datiGrado = (DatiGrado) dati.get(DatiGrado.NOME_TABELLA);
    protected DatiUtilizzatore datiUtilizzatore = (DatiUtilizzatore) dati.get(DatiUtilizzatore.NOME_TABELLA);
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        grado.getItems().addAll(datiGrado.lista(true));
        CampoTesto.maiuscolo(cognome);
        CampoTesto.soloCaratteri(cognome, 40, " '");
        CampoTesto.autoCompletamento(cognome, datiUtilizzatore.listaCognomi());
         
        CampoTesto.primoMaiuscolo(nome);
        CampoTesto.soloCaratteri(nome, 50, " ',àèéòìçù");
        CampoTesto.autoCompletamento(nome, datiUtilizzatore.listaNomi());
         
        CampoTesto.soloCaratteri(mail, 70, "@.\\-_1234567890");
        CampoTesto.autoCompletamento(mail, datiUtilizzatore.listaMail());
         
        CampoTesto.soloCaratteri(account, 50,"\\-_1234567890");
        CampoTesto.autoCompletamento(account, datiUtilizzatore.listaAccount());
         
        if(input != null){
            if(input[0] != null){
                account.setText(input[0]);
            }
            if(input[1] != null){
                String[] lista = DatiUtilizzatore.nominativo(input[1]);
                if(lista.length == 3){
                    grado.setValue(lista[0]);
                    cognome.setText(lista[1]);
                    nome.setText(lista[2]);
                }
            }
            if(input[2] != null)
                mail.setText(input[2]);
        }
    }   
 
    @FXML
    void chiusuraSenzaSalvare(ActionEvent event) {
    	if(event.getEventType().equals(ActionEvent.ACTION)){
	        Programma.chiusuraFinestra(this, scenaCorrente);
	        input = null;
	        tabella = null;
    	}
         
    }
     
         
    @FXML
    protected void aggiornaGrado(MouseEvent event) {
    	if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
	        grado.getItems().clear();
	        grado.getItems().addAll(datiGrado.lista(true));
    	}
    }
 
    @FXML
    protected void aggiungiNuovoGrado(ActionEvent event) {
    	if(event.getEventType().equals(ActionEvent.ACTION)){
    		Programma.listaGradi(this);
    	}
    }
 
    @FXML
    protected void salva(ActionEvent event) {
        
        if(event.getEventType().equals(ActionEvent.ACTION)){
            
            Programma.salva(
                    this,
                    //condizione di salvataggio
                    !account.getText().isEmpty() && !cognome.getText().isEmpty() && !nome.getText().isEmpty(),
                     
                    datiUtilizzatore,
                    input,
                    // dati da salvare
                    new Object[]{
                        account.getText(),
                        datiGrado.trovaGradoEsteso(grado.getValue()),
                        cognome.getText(),
                        nome.getText(),
                        mail.getText()
                    },
                    event,
                    // metodo di apertura della finestra a tabella
                    (ActionEvent evento, String chiave) -> {
                        if(tabella != null){
                            ArrayList<String> riga = new ArrayList<>();
                            riga.add(account.getText());
                            riga.add(
                                DatiUtilizzatore.nominativo(
                                    new Object[]{
                                        null,
                                        datiGrado.trovaGradoEsteso(grado.getValue()),
                                        cognome.getText(),
                                        nome.getText(),
                                        null
                                    }
                                )
                            );
                            riga.add(mail.getText());
                            if(chiave != null)
                                tabella.modificaRiga(chiave, riga);
                            else
                                tabella.aggiungiRiga(riga);
 
                        }
                        chiusuraSenzaSalvare(evento);
                    }
            );
            
        }
         
    }
 
}