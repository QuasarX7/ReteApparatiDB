package it.quasar_x7.gestione_rete.controllo;

import it.quasar_x7.gestione_rete.Dati.DatiDB;
import it.quasar_x7.gestione_rete.Dati.DatiGrado;
import it.quasar_x7.gestione_rete.Dati.DatiResponsabileSito;
import it.quasar_x7.gestione_rete.programma.Programma;
import static it.quasar_x7.gestione_rete.programma.Programma.dati;
import it.quasar_x7.gestione_rete.programma.R;
import it.quasar_x7.javafx.CampoTesto;
import it.quasar_x7.javafx.Finestra;
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
    void salva(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION)){
        /*
        if(!responsabile.getText().isEmpty()){
            Object[] record = new Object[]{
                responsabile.getText(),
                grado.getValue(),
                cognome.getText(),
                nome.getText()
            };
            if(input != null){ // modalità modifica
                
                if(input[0] != null){
                    Object[] chiave = new Object[]{//chiave vecchi dati
                        input[0]
                    };

                    if(datiResponsabileSito.modifica(chiave,record)){
                        visualizzaTabella(event, input[0]);
                    }else{
                        Finestra.finestraAvviso(
                                this, 
                                String.format(R.Messaggi.ERRORE_DUPLICAZIONE,input[0])
                        ); 
                    }
                }
            }else{// modalità aggiunta
                if(!datiResponsabileSito.aggiungi(record)){
                    Finestra.finestraAvviso(
                            this, 
                            String.format(
                                    R.Messaggi.ERRORE_SALVATAGGIO,
                                    responsabile.getText(),
                                    DatiResponsabileSito.stampa(record)
                            )
                    );
                } else{
                    visualizzaTabella(event);
                }
            }
        }else{
            Finestra.finestraAvviso(this,R.Messaggi.ERRORE_CAMPI_FONDAMENTALI);
        }
        */
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
                            chiusuraSenzaSalvare(evento);
                    }
            );
        }
    }

    @FXML
    void aggiornaGrado(MouseEvent event) {
        grado.getItems().clear();
        grado.getItems().addAll(datiGrado.lista(true));
    }

    @FXML
    void aggiungiNuovoGrado(ActionEvent event) {
        Programma.listaGradi(this);
    }

    @FXML
    void chiusuraSenzaSalvare(ActionEvent event) {
        Programma.chiusuraFinestra(this, scenaCorrente);
        input = null;
        tabella= null;
    }
    
    private void visualizzaTabella(ActionEvent event){
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
            tabella.aggiungiRiga(riga);
        }
        chiusuraSenzaSalvare(event);
    }
    
    
    private void visualizzaTabella(ActionEvent event, String chiave){
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
            tabella.modificaRiga(chiave, riga);
            
        }
        chiusuraSenzaSalvare(event);
    }
    
}
