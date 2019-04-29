package it.quasar_x7.gestione_rete.controllo;

import it.quasar_x7.gestione_rete.Dati.DatiApparato;
import it.quasar_x7.gestione_rete.Dati.DatiHardwareApparato;
import it.quasar_x7.gestione_rete.Dati.DatiPosizione;
import it.quasar_x7.gestione_rete.Dati.DatiRete;
import it.quasar_x7.gestione_rete.Dati.DatiSoftware;
import it.quasar_x7.gestione_rete.Dati.DatiSoftwareApparato;
import it.quasar_x7.gestione_rete.Dati.DatiStato;
import it.quasar_x7.gestione_rete.Dati.DatiSwitch;
import it.quasar_x7.gestione_rete.Dati.DatiTipoApparato;
import it.quasar_x7.gestione_rete.Dati.DatiUtilizzatore;
import it.quasar_x7.gestione_rete.modello.Hardware;
import it.quasar_x7.gestione_rete.modello.Software;
import it.quasar_x7.gestione_rete.modello.Utilizzatore;
import it.quasar_x7.gestione_rete.programma.Programma;
import static it.quasar_x7.gestione_rete.programma.Programma.dati;
import it.quasar_x7.gestione_rete.programma.R;
import it.quasar_x7.javafx.CampoTesto;
import it.quasar_x7.javafx.Finestra;
import it.quasar_x7.javafx.Maschera;
import it.quasar_x7.javafx.finestre.controllo.TabellaController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Dr. Domenico della Peruta
 */
public class FinestraApparatoController implements Initializable {

	public static TabellaController tabella = null;
    static public Scene scenaCorrente = null;
    static public String[] input = null;
    
    public static ObservableList<Software> listaSW = FXCollections.observableArrayList();
    public static ObservableList<Hardware> listaHW = FXCollections.observableArrayList();
    
    
    @FXML
    private ContextMenu menuSW;
    
    @FXML
    private ContextMenu menuHW;
    
    @FXML
    private TableView<Software> tabellaSoftware;
    
    @FXML
    private TableColumn<Software, String> colonnaNomeSW;
    
    @FXML
    private TableColumn<Software, String> colonnaNomeHW;
    
    @FXML
    private TableColumn<Software, String> colonnaLicenzaSW;
    
    @FXML
    private TableColumn<Software, String> colonnaTipoSW;
    
    @FXML
    private TableColumn<Software, String> colonnaCasaSW;
    
    @FXML
    private TableColumn<Software, String> colonnaCasaHW;
    
    @FXML
    private TableColumn<Software, String> colonnaNucHW;
    
    @FXML
    private TableColumn<Software, String> colonnaStatoHW;
    
    @FXML
    private TableColumn<Software, String> colonnaModelloHW;
    
    @FXML
    private TableColumn<Software, String> colonnaMatricolaHW;
    
    
    @FXML
    private TableView<Hardware> tabellaHardware;
        
    @FXML
    private CheckBox internet;
    
    @FXML
    private ChoiceBox<Utilizzatore> utilizzatore;

    @FXML
    private TextField sigillo;
    
    @FXML
    private TextField password;
    
    @FXML
    private TextField macPC;
    
    @FXML
    private TextField macVOIP;

    @FXML
    private ChoiceBox<String> rete;
    
    @FXML
    private ChoiceBox<String> stato;
    
   

    @FXML
    private ChoiceBox<String> tipo;

    @FXML
    private TextField ip;

    @FXML
    private ChoiceBox<String> posizione;

    @FXML
    private TextField nome;
    
    protected DatiTipoApparato datiTipoApparato = (DatiTipoApparato) dati.get(DatiTipoApparato.NOME_TABELLA);
    protected DatiApparato datiApparato = (DatiApparato) dati.get(DatiApparato.NOME_TABELLA);
    
    protected DatiUtilizzatore datiUtilizzatore = (DatiUtilizzatore)dati.get(DatiUtilizzatore.NOME_TABELLA);
    protected DatiPosizione datiPosizione = (DatiPosizione)dati.get(DatiPosizione.NOME_TABELLA);
    protected DatiRete datiRete = (DatiRete)dati.get(DatiRete.NOME_TABELLA);
    protected DatiStato datiStato = (DatiStato)dati.get(DatiStato.NOME_TABELLA);
    protected DatiSwitch datiSwitch = (DatiSwitch)dati.get(DatiSwitch.NOME_TABELLA);
    protected DatiSoftwareApparato datiSoftwareApparato = (DatiSoftwareApparato)dati.get(DatiSoftwareApparato.NOME_TABELLA);
    protected DatiHardwareApparato datiHardwareApparato = (DatiHardwareApparato)dati.get(DatiHardwareApparato.NOME_TABELLA);
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CampoTesto.soloCaratteri(nome, 30, "@.\\-_òàùèéìç1234567890");
        CampoTesto.indirizzoIP(ip);
        CampoTesto.soloNumeri(sigillo, 5);
        final Maschera indirizzoMAC = new Maschera(Maschera.MAC, '_');
        CampoTesto.aggiungiMascheraInput(macPC, indirizzoMAC);
        CampoTesto.aggiungiMascheraInput(macVOIP,indirizzoMAC);
        
        ArrayList<Utilizzatore> utilizzatori = datiUtilizzatore.lista();
        if(utilizzatori != null)
            utilizzatore.getItems().addAll(utilizzatori);
        
        ArrayList<String> posizioni = datiPosizione.lista();
        if(posizioni != null)
            posizione.getItems().addAll(posizioni);
        
        ArrayList<String> reti = datiRete.lista();
        if(reti != null)
            rete.getItems().addAll(reti);
        
        ArrayList<String> tipiApparati = datiTipoApparato.lista();
        if(tipiApparati != null)
            tipo.getItems().addAll(tipiApparati);
        
        ArrayList<String> stati = datiStato.listaSemplice();
        if(stati != null)
            stato.getItems().addAll(stati);
        
        
        crezioneTabellaSW();
        crezioneTabellaHW();
        
        if(input != null){
        	/* modalita MODIFICA */
        	
            if(input[0] != null)
                nome.setText(input[0]);
            
            if(input[1] != null)
                tipo.setValue(input[1]);
            
            if(input[2] != null)
                rete.setValue(input[2]);
            
            if(input[3] != null)
                ip.setText(input[3]);
            
            if(input[4] != null)
                macPC.setText(input[4]);
            
            if(input[5] != null)
                macVOIP.setText(input[5]);
            
            if(input[6] != null)
                posizione.setValue(input[6]);
            
            
            if(input[7] != null){
                utilizzatore.setValue(datiUtilizzatore.info(input[7]));
            }
            
            if(input[8] != null)
                internet.setSelected(input[8].equals(R.Conferma.SI));
            
            
            if(input[9] != null)
                sigillo.setText(input[9]);
            
            if(input[10] != null)
                password.setText(input[10]);
            
            if(input[11] != null)
                stato.setValue(input[11]);
            
            aggiornaTabellaSW();
            aggiornaTabellaHW();
        }else {
        	/* modalita AGGIUNGI */
        	
        	//caricamento eventuale software/ hardware associato....
        	nome.focusedProperty().addListener(
                    (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                        if (newValue) {//acquisisci focus
                            listaSW.clear();
                            listaHW.clear();
                        } else { //perde focus
                            aggiornaTabellaSW();
                            aggiornaTabellaHW();
                        }
                    }
            );
        }
    }  
    
    private void crezioneTabellaSW(){
        colonnaNomeSW.setCellValueFactory(new PropertyValueFactory<>(R.Modello.SW.NOME));
        colonnaLicenzaSW.setCellValueFactory(new PropertyValueFactory<>(R.Modello.SW.LICENZA));
        
        colonnaTipoSW.setCellValueFactory(new PropertyValueFactory<>(R.Modello.SW.TIPO));
        colonnaCasaSW.setCellValueFactory(new PropertyValueFactory<>(R.Modello.SW.CASA));
        
        tabellaSoftware.setItems(listaSW);
    }
    
    private void crezioneTabellaHW(){
        colonnaNomeHW.setCellValueFactory(new PropertyValueFactory<>(R.Modello.HW.NOME));
        colonnaModelloHW.setCellValueFactory(new PropertyValueFactory<>(R.Modello.HW.MODELLO));
        colonnaMatricolaHW.setCellValueFactory(new PropertyValueFactory<>(R.Modello.HW.MATRICOLA));
        
        colonnaCasaHW.setCellValueFactory(new PropertyValueFactory<>(R.Modello.HW.CASA));
        colonnaNucHW.setCellValueFactory(new PropertyValueFactory<>(R.Modello.HW.NUC));
        colonnaTipoSW.setCellValueFactory(new PropertyValueFactory<>(R.Modello.HW.STATO));
        
        tabellaHardware.setItems(listaHW);
    }
   

    @FXML
    private void chiusuraSenzaSalvare(ActionEvent event) {
        Programma.chiusuraFinestra(this, scenaCorrente);
        input = null;
        tabella = null;
    }

    @FXML
    private void salva(ActionEvent event) {
        if(!nome.getText().isEmpty()){
           
            Object[] record = new Object[]{
                nome.getText(),
                tipo.getValue() != null ? tipo.getValue() : "",
                rete.getValue() != null ? rete.getValue() : "",
                ip.getText(),
                macPC.getText(),
                macVOIP.getText(),
                posizione.getValue() != null ? posizione.getValue() : "",
                utilizzatore.getValue() != null ? utilizzatore.getValue().getAccount() : "",
                //_switch.getValue() != null ? _switch.getValue() : "",
                internet.isSelected(),
                sigillo.getText(),
                password.getText(),
                stato.getValue() != null ? stato.getValue() : ""
            };
            
            if(input != null){ // modalità modifica
            	modificaSW();
            	modificaHW();
                if(datiApparato.modifica(new Object[]{input[0]},record)){
                	aggiornaTabella(record);
                	aggiornaPannelloAlbero();
                    chiusuraSenzaSalvare(event);
                }else{
                    Finestra.finestraAvviso(
                            this, 
                            String.format(R.Messaggi.ERRORE_DUPLICAZIONE,input[0])
                    ); 
                }
            }else{ // modalità aggiunta
                if(!datiApparato.aggiungi(record)){
                    Finestra.finestraAvviso(
                            this, 
                            String.format(
                                    R.Messaggi.ERRORE_SALVATAGGIO,
                                    nome.getText(),
                                    DatiApparato.stampa(record)
                            )
                    );
                }else{
                	aggiornaTabella(record);
                	aggiornaPannelloAlbero();
                    chiusuraSenzaSalvare(event);
                }
                
            }
            
            
        }else{
            Finestra.finestraAvviso(this,R.Messaggi.ERRORE_CAMPI_FONDAMENTALI);
        }
    }
    
    /**
     * aggiorna eventuale finestra a tabella
     * @param record 
     */
    private void aggiornaTabella(Object[] record) {
    	if(tabella != null){
            if(input[0] != null)
                tabella.modificaRiga(input[0],converti(record));
            else
                tabella.aggiungiRiga(converti(record));
        }
    }
    
    /**
     * Aggiorna pannello sinistro ad albero degli apparati di rete
     */
    private void aggiornaPannelloAlbero() {
    	if(FinestraPrincipaleController.rete != null){
            Programma.creaListaApparato(FinestraPrincipaleController.rete,datiApparato.listaApparati());
        }
    }
    
    private ArrayList<String> converti(Object[] record){
        ArrayList<String> nuovaRiga = new ArrayList<>();
        for(Object voce:record){
            if(voce != null)
                nuovaRiga.add(voce.toString());
            else
                nuovaRiga.add("");
        }
        return nuovaRiga;
    }
    
    /**
     * Aggiarna il cambiamento del nome dell'apparato anche ai suoi componenti software associati.
     */
    private void modificaSW(){
    	if(!input[0].equals(nome.getText())) {
    		ArrayList<Software> listaSW = datiSoftwareApparato.listaSW(input[0]);
    		for(Software sw:listaSW) {
    			datiSoftwareApparato.modifica(
    					new Object[]{input[0],		sw.getNome(),sw.getLicenza()}, 
    					new Object[]{nome.getText(),sw.getNome(),sw.getLicenza()}
    			);
    		}
    		
    	}
    }
    
    /**
     * Aggiarna il cambiamento del nome dell'apparato anche ai suoi componenti hardware associati.
     */
    private void modificaHW(){
    	if(!input[0].equals(nome.getText())) {
    		ArrayList<Hardware> listaHW = datiHardwareApparato.listaHW(input[0]);
    		for(Hardware hw:listaHW) {
    			datiHardwareApparato.modifica(
    					new Object[]{input[0],		hw.getNome(),hw.getModello(),hw.getMatricola()}, 
    					new Object[]{nome.getText(),hw.getNome(),hw.getModello(),hw.getMatricola()}
    			);
    		}
    	}
    }

    @FXML
    private void aggiornaTipo(MouseEvent event) {
        tipo.getItems().clear();
        ArrayList<String> tipiApparati = datiTipoApparato.lista();
        if(tipiApparati != null)
            tipo.getItems().addAll(tipiApparati);
    }

    @FXML
    private void aggiungiNuovoTipoApparato(ActionEvent event) {
        Programma.apriSempliceLista(
                this,
                datiTipoApparato,
                R.Etichette.FINESTRA_LISTA_TIPO_APPARATO,
                R.Etichette.TIPO_APPARATO,
                R.Messaggi.SOSTITUZIONE_TIPO_APPARATO
        );
    }
    
    @FXML
    private void aggiungiNuovoSwitch(ActionEvent event) {
        Programma.apriListaConnessioniSwitch(this);
    }
    
    @FXML
    private void aggiungiNuovoStato(ActionEvent event) {
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
    private void aggiornaUtilizzatore(MouseEvent event) {
        utilizzatore.getItems().clear();
        ArrayList<Utilizzatore> utilizzatori = datiUtilizzatore.lista();
        if(utilizzatori != null)
            utilizzatore.getItems().addAll(utilizzatori);
    }
    

    
    @FXML
    private void aggiornaStato(MouseEvent event) {
        stato.getItems().clear();
        ArrayList<String> stati = datiStato.listaSemplice();
        if(stati != null)
            stato.getItems().addAll(stati);
    }

    @FXML
    private void aggiungiNuovoUtilizzatore(ActionEvent event) {
        Programma.apriListaUtilizzatori(this);
    }

    @FXML
    private void aggiornaRete(MouseEvent event) {
        rete.getItems().clear();
        ArrayList<String> reti = datiRete.lista();
        if(reti != null)
            rete.getItems().addAll(reti);
        
    }

    @FXML
    private void aggiornaPosizione(MouseEvent event) {
        posizione.getItems().clear();
        ArrayList<String> posizioni = datiPosizione.lista();
        if(posizioni != null)
            posizione.getItems().addAll(posizioni);
        
    }

    @FXML
    private void aggiungiNuovaRete(ActionEvent event) {
        Programma.apriListaReti(this);
    }

    @FXML
    private void aggiungiNuovaPosizione(ActionEvent event) {
        Programma.apriListaPosizione(this);
    }
    
    @FXML
    private void visualizzaMenuSW(MouseEvent event){
        if((event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) || event.getButton().equals(MouseButton.SECONDARY) ){
            if(event.getSource() instanceof TableView){
                TableView pannello = (TableView) event.getSource();
                menuSW.show(pannello, event.getScreenX(), event.getScreenY());
            }
        }
        event.consume();
    }
    
    @FXML
    private void visualizzaMenuHW(MouseEvent event){
        if((event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) || event.getButton().equals(MouseButton.SECONDARY) ){
            if(event.getSource() instanceof TableView){
            	TableView pannello = (TableView) event.getSource();
                menuHW.show(pannello, event.getScreenX(), event.getScreenY());
            }
        }
        event.consume();
    }
    
    @FXML
    private void menuAggiungiHW(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION)){
            if(!nome.getText().isEmpty()){
                 FinestraHardwareApparatoController.scenaCorrente = Finestra.scenaCorrente();
                 FinestraHardwareApparatoController.apparato = nome.getText();
                 FinestraHardwareApparatoController.finestraApparato = this;
                Finestra.caricaFinestra(this, R.FXML.FINESTRA_HW_APPARATO);
            }else{
                Finestra.finestraAvviso(this,R.Messaggi.ERRORE_CAMPI_FONDAMENTALI);
            }
        }
    }
    
    @FXML
    private void menuModificaHW(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION)){
        	if(!nome.getText().isEmpty()){
                FinestraHardwareApparatoController.scenaCorrente = Finestra.scenaCorrente();
                FinestraHardwareApparatoController.apparato = nome.getText();
                FinestraHardwareApparatoController.finestraApparato = this;
                Hardware hw = tabellaHardware.getSelectionModel().getSelectedItem();
                if(hw != null) {
                	FinestraHardwareApparatoController.hardware = hw.getNome();
                	FinestraHardwareApparatoController.modello = hw.getModello();
                	FinestraHardwareApparatoController.matricola = hw.getMatricola();
                }
                Finestra.caricaFinestra(this, R.FXML.FINESTRA_HW_APPARATO);
            }else{
                Finestra.finestraAvviso(this,R.Messaggi.ERRORE_CAMPI_FONDAMENTALI);
            }
        }
    }

    @FXML
    private void menuEliminaHW(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION)){
        	Hardware hw = tabellaHardware.getSelectionModel().getSelectedItem();
            if(hw != null) {
            	datiHardwareApparato.elimina(new Object[]{nome.getText(),hw.getNome(),hw.getModello(),hw.getMatricola()});
            }
            aggiornaTabellaHW();
        }
    }
    
    @FXML
    private void menuAggiungiSW(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION)){
            if(!nome.getText().isEmpty()){
                FinestraSoftwareApparatoController.scenaCorrente = Finestra.scenaCorrente();
                FinestraSoftwareApparatoController.apparato = nome.getText();
                FinestraSoftwareApparatoController.finestraApparato = this;
                Finestra.caricaFinestra(this, R.FXML.FINESTRA_SW_APPARATO);
            }else{
                Finestra.finestraAvviso(this,R.Messaggi.ERRORE_CAMPI_FONDAMENTALI);
            }
        }
    }
    
    @FXML
    private void menuModificaSW(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION)){
        	if(!nome.getText().isEmpty()){
                FinestraSoftwareApparatoController.scenaCorrente = Finestra.scenaCorrente();
                FinestraSoftwareApparatoController.apparato = nome.getText();
                FinestraSoftwareApparatoController.finestraApparato = this;
                Software sw = tabellaSoftware.getSelectionModel().getSelectedItem();
                if(sw != null) {
                	FinestraSoftwareApparatoController.software = sw.getNome();
                	FinestraSoftwareApparatoController.licenza = sw.getLicenza();
                }
                Finestra.caricaFinestra(this, R.FXML.FINESTRA_SW_APPARATO);
            }else{
                Finestra.finestraAvviso(this,R.Messaggi.ERRORE_CAMPI_FONDAMENTALI);
            }
        }
    }

    @FXML
    private void menuEliminaSW(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION)){
        	Software sw = tabellaSoftware.getSelectionModel().getSelectedItem();
            if(sw != null) {
            	datiSoftwareApparato.elimina(new Object[]{nome.getText(),sw.getNome(),sw.getLicenza()});
            }
            aggiornaTabellaSW();
        }
    }
    
    public void aggiornaTabellaSW(){
        listaSW.clear();
        ArrayList<Software> lista = datiSoftwareApparato.listaSW(nome.getText());
        if(lista != null)
            for(Software sw:lista)
                listaSW.add(sw);
    }

    public void aggiornaTabellaHW() {
        listaHW.clear();
        ArrayList<Hardware> lista = datiHardwareApparato.listaHW(nome.getText());
        if(lista != null)
            for(Hardware hw:lista)
                listaHW.add(hw);
    }
}
