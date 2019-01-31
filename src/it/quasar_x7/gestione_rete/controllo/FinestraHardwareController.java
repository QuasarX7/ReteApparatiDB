package it.quasar_x7.gestione_rete.controllo;

import it.quasar_x7.gestione_rete.Dati.DatiCasaHardware;
import it.quasar_x7.gestione_rete.Dati.DatiDB;
import it.quasar_x7.gestione_rete.Dati.DatiHardware;
import it.quasar_x7.gestione_rete.Dati.DatiStato;
import it.quasar_x7.gestione_rete.Dati.DatiTipoHardware;
import it.quasar_x7.gestione_rete.programma.Programma;
import static it.quasar_x7.gestione_rete.programma.Programma.dati;
import it.quasar_x7.gestione_rete.programma.R;
import it.quasar_x7.java.BaseDati.EccezioneBaseDati;
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
                if(lista.length == 2){
                    modello.setText(lista[0]);
                    matricola.setText(lista[1]);
                }
            }
            if(input[1] != null)
                menuTipo.setValue(input[1]);
           
            if(input[2] != null)
                menuCasa.setValue(input[2]);
             
            if(input[3] != null)
                NUC.setText(input[3]);
             
            if(input[4] != null)
                menuStato.setValue(input[4]);
            
            if(input[5] != null)
                note.setText(input[5]);
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
        
        if(!modello.getText().isEmpty() && !matricola.getText().isEmpty()){
            Object[] record = new Object[]{
                menuTipo.getValue(),
                menuCasa.getValue(),
                modello.getText(),
                matricola.getText(),
                NUC.getText(),
                menuStato.getValue(),
                note.getText()
            };
            if(input != null){ // modalità di modifica
                String[] lista = DatiHardware.marcaMatricola(input[0]);
                if(lista.length == 2){
                    Object[] chiave = new Object[]{//chiave vecchi dati
                        lista[0],
                        lista[1]
                    };

                    if(datiHardware.modifica(chiave,record)){
                        visualizzaTabella(event,input[0],record);
                    }else{
                        Finestra.finestraAvviso(
                                this, 
                                String.format(R.Messaggi.ERRORE_DUPLICAZIONE,
                                        modello.getText()+" "+matricola.getText()
                                )
                        ); 
                    }
                }

            }else{ // modalità aggiungi
                if(!datiHardware.aggiungi(record)){
                    Finestra.finestraAvviso(
                            this, 
                            String.format(
                                    R.Messaggi.ERRORE_SALVATAGGIO,
                                    modello.getText()+" "+matricola.getText(),
                                    DatiDB.stampa(record)
                            )
                    );
                }else{
                    visualizzaTabella(event,record);
                }
            }  
            
        }else{
            Finestra.finestraAvviso(this,R.Messaggi.ERRORE_CAMPI_FONDAMENTALI);
        }
    }

    private void visualizzaTabella(ActionEvent event, Object[] record) {
        if(tabella != null){
            ArrayList<String> riga = new ArrayList<>();
            riga.add(DatiHardware.marcaMatricola(record));
            riga.add((String) record[0]);
            riga.add((String) record[1]);
            riga.add((String) record[4]);
            riga.add((String) record[5]);
            riga.add((String) record[6]);
            tabella.aggiungiRiga(riga);
        }
        chiusuraSenzaSalvare(event);
    }

    private void visualizzaTabella(ActionEvent event, String chiave, Object[] record){
        if(tabella != null){
            ArrayList<String> riga = new ArrayList<>();
            riga.add(DatiHardware.marcaMatricola(record));
            riga.add((String) record[0]);
            riga.add((String) record[1]);
            riga.add((String) record[4]);
            riga.add((String) record[5]);
            riga.add((String) record[6]);
            
            tabella.modificaRiga(chiave, riga);
            
        }
        chiusuraSenzaSalvare(event);
    }
    
}
