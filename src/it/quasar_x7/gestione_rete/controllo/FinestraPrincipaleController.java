package it.quasar_x7.gestione_rete.controllo;

import it.quasar_x7.gestione_rete.Dati.DatiApparato;
import it.quasar_x7.gestione_rete.Dati.DatiCasaHardware;
import it.quasar_x7.gestione_rete.Dati.DatiCasaSoftware;
import it.quasar_x7.gestione_rete.Dati.DatiLogin;
import it.quasar_x7.gestione_rete.Dati.DatiRuolo;
import it.quasar_x7.gestione_rete.Dati.DatiStato;
import it.quasar_x7.gestione_rete.Dati.DatiSwitch;
import it.quasar_x7.gestione_rete.Dati.DatiTipoApparato;
import it.quasar_x7.gestione_rete.Dati.DatiTipoHardware;
import it.quasar_x7.gestione_rete.Dati.DatiTipoSoftware;
import it.quasar_x7.gestione_rete.Dati.DatiUtilizzatore;
import it.quasar_x7.gestione_rete.modello.Apparato;
import it.quasar_x7.gestione_rete.modello.Hardware;
import it.quasar_x7.gestione_rete.modello.Ufficio;
import it.quasar_x7.gestione_rete.modello.Nodo;
import it.quasar_x7.gestione_rete.modello.Responsabile;
import it.quasar_x7.gestione_rete.modello.Rete;
import it.quasar_x7.gestione_rete.modello.Software;
import it.quasar_x7.gestione_rete.programma.Programma;
import static it.quasar_x7.gestione_rete.programma.Programma.dati;
import it.quasar_x7.gestione_rete.programma.R;
import it.quasar_x7.controllo.FinestraGestioneUtentiController;
import it.quasar_x7.java.BaseDati.EccezioneBaseDati;
import it.quasar_x7.java.utile.DataOraria;
import it.quasar_x7.javafx.CampoTesto;
import it.quasar_x7.javafx.Finestra;
import it.quasar_x7.javafx.TipoFile;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Dr Domenico della Peruta
 */
public class FinestraPrincipaleController implements Initializable {

    static public TreeItem<Nodo> rete = new TreeItem<> (new Nodo("Lista apparati"));
    
    private final TreeItem<Nodo> reteSelezionata = new TreeItem<> (new Nodo("Lista ricerca"));
    
    @FXML
    private Label titolo;
    
    @FXML
    private ContextMenu menuPannello;
    
    @FXML
    private TreeView<Nodo> listaApparati;
    
    
    @FXML
    private TitledPane pannelloListaHost;
    
    @FXML
    private Accordion pannelloLaterale;
    
    
    @FXML
    private ListView<TextFlow> elencoInfo;
    
    @FXML
    private TreeView<Nodo> listaRicercaInfo;
        
    
    @FXML
    private TextField compoRicerca;
    
    @FXML
    private ChoiceBox<String> menuTipoRicerca;

    private final  DatiTipoApparato datiTipoApparato = (DatiTipoApparato) dati.get(DatiTipoApparato.NOME_TABELLA);
    private final  DatiLogin datiLogin = (DatiLogin) dati.get(DatiLogin.NOME_TABELLA);
    private final  DatiCasaHardware datiCasaHardware = (DatiCasaHardware) dati.get(DatiCasaHardware.NOME_TABELLA);
    private final  DatiCasaSoftware datiCasaSoftware = (DatiCasaSoftware) dati.get(DatiCasaSoftware.NOME_TABELLA); 
    private final  DatiRuolo datiRuolo = (DatiRuolo) dati.get(DatiRuolo.NOME_TABELLA);
    private final  DatiTipoHardware datiTipoHardware = (DatiTipoHardware)dati.get(DatiTipoHardware.NOME_TABELLA);
    private final  DatiTipoSoftware datiTipoSoftware = (DatiTipoSoftware)dati.get(DatiTipoSoftware.NOME_TABELLA);
    private final  DatiApparato datiApparato = (DatiApparato)dati.get(DatiApparato.NOME_TABELLA);
    private final  DatiStato datiStato = (DatiStato)dati.get(DatiStato.NOME_TABELLA);
    private final  DatiSwitch datiSwitch = (DatiSwitch)dati.get(DatiSwitch.NOME_TABELLA);
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        menuTipoRicerca.getItems().add(R.TipoRicerca.NOMINATIVO);
        menuTipoRicerca.getItems().add(R.TipoRicerca.ACCOUNT);
        menuTipoRicerca.getItems().add(R.TipoRicerca.IP);
        menuTipoRicerca.getItems().add(R.TipoRicerca.APPARATO);
        
        pannelloLaterale.setExpandedPane(pannelloListaHost);
        pannelloListaHost.setExpanded(true);
        titolo.setText(
                String.format(
                        "Benvenuto %s [%s] ",
                        datiLogin.livello(FinestraGestioneUtentiController.utente),
                        FinestraGestioneUtentiController.utente
                )
        );
        
        listaApparati.setShowRoot(false);
        this.listaRicercaInfo.setShowRoot(false);
        // modalità di costruzione dell'albero
        Callback<TreeView<Nodo>, TreeCell<Nodo>> costruzioneListaAlbero = tv -> new TreeCell<Nodo>() {
                @Override
                protected void updateItem(Nodo item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        setText(getItem() == null ? "" : getItem().toString());
                        setGraphic(getTreeItem().getGraphic());
                        if(item instanceof Apparato){
                            setFont(Font.font("Arial Black", 16));
                            String stato = ((Apparato)item).getStato();
                            if(stato != null){
                                //colora gli apparari in base allo stato gli apparati
                                String colore = datiStato.colore(stato);
                                if(colore != null){
                                    setStyle(String.format("-fx-text-fill: %s;",colore));
                                    return;
                                }
                            }
                            setStyle("-fx-text-fill: black;");

                        }else if(item instanceof Rete || item instanceof Ufficio || item instanceof Responsabile){
                            setFont(Font.font("Arial Black", 12));
                        }else if(item instanceof Software || item instanceof Hardware){
                            if(item.getNome().equals(R.Etichette.SW) || item.getNome().equals(R.Etichette.HW))
                                setFont(Font.font("Arial", FontWeight.BOLD, 12));
                            else
                                setFont(Font.font("Arial Narrow",12));
                        }else{
                            setFont(Font.font("Arial Narrow",12));
                        }
                    }
                }
        };
        listaApparati.setCellFactory(costruzioneListaAlbero);
        listaApparati.setRoot(rete);
        Programma.creaListaApparato(rete,datiApparato.listaApparati());
        
        listaRicercaInfo.setRoot(reteSelezionata);
        listaRicercaInfo.setCellFactory(costruzioneListaAlbero);
    }   
    
    
    @FXML
    private void creaNuovoApparato(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            FinestraApparatoController.scenaCorrente = Finestra.scenaCorrente();
            Finestra.caricaFinestra(this, R.FXML.FINESTRA_APPARATO);
        }
    }

    @FXML
    private void apriListaApparati(ActionEvent event) {

    }
    
    @FXML
    private void apriListaTipoApparato(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            Programma.apriSempliceLista(
                    this,
                    datiTipoApparato,
                    R.Etichette.FINESTRA_LISTA_TIPO_APPARATO,
                    R.Etichette.TIPO_APPARATO,
                    R.Messaggi.SOSTITUZIONE_TIPO_APPARATO
            );
        }
    }

    @FXML
    private void creaNuovoUtilizzatore(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            FinestraUtilizzatoreController.scenaCorrente = Finestra.scenaCorrente();
            Finestra.caricaFinestra(this, R.FXML.FINESTRA_UTILIZZATORE);
        }
    }

    @FXML
    private void creaNuovoHardware(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            FinestraHardwareController.scenaCorrente = Finestra.scenaCorrente();
            Finestra.caricaFinestra(this, R.FXML.FINESTRA_HW);
        }
        
    }

    @FXML
    private void creaNuovaConnessioneSwitch(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            FinestraSwitchController.scenaCorrente = Finestra.scenaCorrente();
            Finestra.caricaFinestra(this, R.FXML.FINESTRA_SWITCH);
        }
    }
    
    @FXML
    private void creaNuovoSoftware(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            FinestraSoftwareController.scenaCorrente = Finestra.scenaCorrente();
            Finestra.caricaFinestra(this, R.FXML.FINESTRA_SW);
        }
    }


    @FXML
    private void modificaPassword(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            //todo
        }
    }

    @FXML
    private void apriGestioneUtenti(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            it.quasar_x7.swing.Finestra.creaFinestra(
                    new it.quasar_x7.swing.vista.FinestraGestioneUtenti(datiLogin,false)
            );
        }
    }

    @FXML
    private void apriListaPosizioneMilitare(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            Programma.apriListaPosizione(this);
        }
    }
    
    @FXML
    private void apriListaHardware(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            Programma.apriListaHardware(this);
        }
    }
    
    @FXML
    private void apriListaSwitch(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            Programma.apriSempliceLista(
                    this,
                    datiSwitch,
                    R.Etichette.FINESTRA_SWITCH,
                    R.Etichette.NOME_SWITCH,
                    R.Messaggi.SOSTITUZIONE_SWITCH
            );
        }
    }
    
    @FXML
    private void apriListaConnessioniSwitch(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            Programma.apriListaConnessioniSwitch(this);
        }
    }
    
    @FXML
    private void apriListaSoftware(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            Programma.apriListaSoftware(this);
        }
    }
    
    @FXML
    private void apriListaUtilizzatori(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            Programma.apriListaUtilizzatori(this);
        }
    }
    
    
    @FXML
    private void chiusuraSenzaSalvare(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            Finestra.finestraConferma(
                    this,
                    R.Domanda.CONFERMA_CHIUSURA_PROGRAMMA, 
                    () -> {
                        Platform.exit();
                        System.exit(0);
                    }
            );
        }
    }
    
    
    @FXML
    private void listaCaseProdutriciSW(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) 
            Programma.apriSempliceLista(
                    this,
                    datiCasaSoftware,
                    R.Etichette.FINESTRA_LISTA_CASA_SW,
                    R.Etichette.CASA_SW,
                    R.Messaggi.SOSTITUZIONE_CASA_SW
            );
    }
    
    
    @FXML
    private void listaCaseProdutriciHW(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) 
            Programma.apriSempliceLista(
                    this,
                    datiCasaHardware,
                    R.Etichette.FINESTRA_LISTA_CASA_HW,
                    R.Etichette.CASA_HW,
                    R.Messaggi.SOSTITUZIONE_CASA_HW
            );
    }
    
    
    @FXML
    private void listaTipoHardware(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) 
            Programma.apriSempliceLista(
                    this,
                    datiTipoHardware,
                    R.Etichette.FINESTRA_LISTA_TIPO_HW,
                    R.Etichette.TIPO_HW,
                    R.Messaggi.SOSTITUZIONE_TIPO_HW
            );
    }
    
    @FXML
    private void listaTipoSoftware(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            Programma.apriSempliceLista(
                    this,
                    datiTipoSoftware,
                    R.Etichette.FINESTRA_LISTA_TIPO_SW,
                    R.Etichette.TIPO_SW,
                    R.Messaggi.SOSTITUZIONE_TIPO_SW
            );
        }
    }
    
    
    @FXML
    private void listaGradi(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            Programma.listaGradi(this);
        }
    }
    
    @FXML
    private void listaRuoli(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            Programma.apriSempliceLista(
                    this,
                    datiRuolo,
                    R.Etichette.FINESTRA_LISTA_CATEGORIA_GRADO,
                    R.Etichette.RUOLO,
                    R.Messaggi.SOSTITUZIONE_RUOLO
            );
        }
    }
    
    
    @FXML
    private void apriListaResponsabile(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            Programma.apriListaResponsabile(this);
        }
    }
    
    
    @FXML
    private void creaNuovoResponsabile(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            FinestraResponsabileController.scenaCorrente = Finestra.scenaCorrente();
            Finestra.caricaFinestra(this, R.FXML.FINESTRA_RESPONSABILE);
        }
    }
    
    
    @FXML
    private void apriListaPosizione(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            Programma.apriListaPosizione(this);
        }
    }
    
    
    @FXML
    private void creaNuovaPosizione(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            FinestraPosizioneController.scenaCorrente = Finestra.scenaCorrente();
            Finestra.caricaFinestra(this, R.FXML.FINESTRA_POSIZIONE);
        }
    }
    
    
    @FXML
    private void aggiungiNuovaRete(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            FinestraReteController.scenaCorrente = Finestra.scenaCorrente();
            Finestra.caricaFinestra(this, R.FXML.FINESTRA_RETE);
        }
    }
    
    @FXML
    private void apriListaReti(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            Programma.apriListaReti(this);
        }
    }
    
    
    @FXML
    private void cliccaPanelloListaApparati(MouseEvent event){
        if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
            visualizzaMenu(event);
            aggiornaListaInfo(event,listaApparati);
            aggiornaListaRicercaInfo(event);
            event.consume();
        }
    }
    
    @FXML
    private void cliccaPanelloRicercaListaApparati(MouseEvent event){
        if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
            aggiornaListaInfo(event,listaRicercaInfo);
            event.consume();
        }
    }
    
    private void aggiornaListaInfo(MouseEvent event,TreeView<Nodo> pannello){
        if(event.getButton().equals(MouseButton.PRIMARY)){
            elencoInfo.getItems().clear();
            TreeItem<Nodo> nodo = pannello.getSelectionModel().getSelectedItem();
            if(nodo != null){
                Nodo selezione = nodo.getValue();
                elencoInfo.getItems().addAll(selezione.info());
            }
            
        }
    }
    /**
     * Aggiorna l'elenco ad albero della "listaRicercsInfo" con un elemento selezionato dal pannello "listaApparati".
     * @param event 
     */
    private void aggiornaListaRicercaInfo(MouseEvent event){
        if(event.getButton().equals(MouseButton.PRIMARY)){
            listaRicercaInfo.getRoot().getChildren().clear();
            TreeItem<Nodo> nodo = listaApparati.getSelectionModel().getSelectedItem();
            if(nodo != null){
                reteSelezionata.getChildren().clear();
                reteSelezionata.getChildren().add(Programma.copia(nodo));
            }
            
        }
    }
    
    /* MENU MOUSE PANNELLO LATERALE LISTA APPARATI */
    
    
    private void visualizzaMenu(MouseEvent event){
        if((event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) || event.getButton().equals(MouseButton.SECONDARY) ){
            if(event.getSource() instanceof TreeView){
                TreeView pannello = (TreeView) event.getSource();
                menuPannello.show(pannello, event.getScreenX(), event.getScreenY());
            }
        }
        event.consume();
    }
    
    
    
    @FXML
    private void menuAggiungi(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            elencoInfo.getItems().clear();
            listaRicercaInfo.getRoot().getChildren().clear();
            TreeItem<Nodo> nodo = listaApparati.getSelectionModel().getSelectedItem();
            if(nodo != null){
                if(nodo.getValue() instanceof Apparato){
                    creaNuovoApparato(event);
                }else if(nodo.getValue() instanceof Ufficio){
                    creaNuovaPosizione(event);
                }else if(nodo.getValue() instanceof Rete){
                    aggiungiNuovaRete(event);
                }
            } 
        }
    }

    @FXML
    private void menuModifica(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            elencoInfo.getItems().clear();
            listaRicercaInfo.getRoot().getChildren().clear();
            TreeItem<Nodo> nodo = listaApparati.getSelectionModel().getSelectedItem();
            if(nodo != null){
                if(nodo.getValue() instanceof Apparato){
                    Apparato apparato = (Apparato) nodo.getValue();
                    
                    FinestraApparatoController.input = new String[]{
                        apparato.getNome(),
                        apparato.getTipo(),
                        apparato.getGruppo(),
                        apparato.getIp(),
                        apparato.getMacPC(),
                        apparato.getMacVOIP(),
                        apparato.getPosizione(),
                        apparato.getUtente(),
                        apparato.getInternet() == null ? null : apparato.getInternet() == true ? R.Conferma.SI : R.Conferma.NO,
                        String.valueOf(apparato.getSigillo()),
                        apparato.getPassword(),
                        apparato.getStato()
                    };
                    creaNuovoApparato(event);
                }else if(nodo.getValue() instanceof Ufficio){
                    Ufficio ufficio = (Ufficio) nodo.getValue();
                    
                    FinestraPosizioneController.input = new String[]{
                        ufficio.getNome(),
                        ufficio.getResponsabile()
                    };
                    creaNuovaPosizione(event);
                }else if(nodo.getValue() instanceof Rete){
                    Rete workgroup = (Rete) nodo.getValue();
                    if(!workgroup.equals(Rete.STANDALONE)){
                        FinestraReteController.input = new String[]{
                            workgroup.getNome(),
                            workgroup.getDominio(),
                            workgroup.getTipo(),
                            workgroup.getGateway(),
                            workgroup.getNetmask()
                        };
                        aggiungiNuovaRete(event);
                    }
                }
            } 
        }
    }

    @FXML
    private void menuElimina(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            elencoInfo.getItems().clear();
            listaRicercaInfo.getRoot().getChildren().clear();
            
            TreeItem<Nodo> nodo = listaApparati.getSelectionModel().getSelectedItem();
            if(nodo != null){
                if(nodo.getValue() instanceof Apparato){
                    System.out.println("apparato");
                    //TODO....
                }else if(nodo.getValue() instanceof Ufficio){
                    //TODO...
                    System.out.println("ufficio");
                }else if(nodo.getValue() instanceof Rete){
                    //TODO...
                    System.out.println("rete");
                }
            } 
        }
    }


    private String voce(String tipo,String nome){
        return String.format("%s: [%s]", tipo,nome);
    }

    @FXML
    private void caricaFile(ActionEvent event){
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            final Object finestraPrincipale = this;
            Finestra.finestraCaricaFile(
                    this, 
                    TipoFile.XLS,
                    (Path path) -> {
                        
                        return new Task() {
                            @Override
                            protected  Object call() throws Exception {
                                String info = "Inizio caricamento...";
                                String infoErrore = "";
                                updateMessage(info);
                                File file = path.toFile();
                                if(file != null){
                                    if(file.exists()){
                                        int i=0;
                                        for(String chiave: dati.keySet()){
                                            if(dati.get(chiave).esiste()){
                                                String messaggio = String.format("formattazione tabella\t `%s`\t", chiave);
                                                if(dati.get(chiave).elimataTuttiRecord())
                                                    messaggio += "OK";
                                                else
                                                    messaggio += "Errore";
                                                info = messaggio +"\n"+ info;
                                                updateMessage(info);
                                                messaggio = String.format("caricamento tabella\t`%s`\t", chiave);
                                                if(dati.get(chiave).carica(file)) // crea tabella nel file
                                                    messaggio += "OK";
                                                else{
                                                    messaggio += "Errore";
                                                    if(infoErrore.isEmpty())
                                                        infoErrore = "AVVISO!!!!!:\nSi sono verificati gravi errore nelle caricamento delle seguenti tabelle:\n";
                                                    infoErrore += String.format("-> «%s»\n", chiave);
                                                }
                                                info = messaggio +"\n"+ info;
                                            }
                                            if(++i != dati.keySet().size()){
                                                updateMessage(info);
                                            } else{
                                                if(!infoErrore.isEmpty()){
                                                    updateMessage(infoErrore);
                                                    for(int k=0; k < 20; k++){
                                                        Thread.sleep(1000);
                                                        updateMessage(String.format("..chiusura tra %s\n%s",""+(20-k),infoErrore));
                                                    }
                                                }
                                            }
                                            updateProgress(i, dati.keySet().size());
                                            
                                        }
                                        
                                        Programma.creaListaApparato(rete,datiApparato.listaApparati());
                                        
                                        
                                        
                                    }else{
                                        info = "Errore: file `"+file.getAbsolutePath() + "` non trovato!!! \n";
                                        updateMessage(info);
                                        for(int i=0; i < 10; i++){
                                            Thread.sleep(1000);
                                            updateMessage(String.format("%s\n\nChiusura tra %s",info,""+(10-i)));
                                        }
                                    }
                                    
                                }
                                return true;
                            }
                        };
                        
                        
                    }
            );
        }
    }
    
    
    
    @FXML
    private void salvaFile(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
             
            Finestra.finestraSalvaFile(
                    this, 
                    String.format(
                            "DB Rete %s%s", 
                            DataOraria.creaDataOggi().stampaGiorno(),
                            TipoFile.XLS.estensione()
                    ), 
                    TipoFile.XLS,
                    (Path path) -> {
                        
                        return new Task() {
                            @Override
                            protected  Object call() throws Exception {
                                String info = "Inizio salvataggio...";
                                updateMessage(info);
                                File file = path.toFile();
                                if(file != null){
                                    info = "file: "+file.getAbsolutePath() + "\n"+ info;
                                    updateMessage(info);
                                    
                                    int i=0;
                                    
                                    for(String chiave: dati.keySet()){
                                        if(dati.get(chiave).esiste()){
                                            String messaggio = String.format("creazione tabella `%s` ", chiave);
                                            try{
                                                dati.get(chiave).salva(file); // crea tabella nel file
                                                messaggio += "OK";
                                            }catch(EccezioneBaseDati e){
                                                messaggio += "Errore";
                                            }
                                            info = messaggio +"\n"+ info;
                                        }
                                        updateMessage(info);
                                        updateProgress(++i, dati.keySet().size());
                                    }
                                }
                                return true;
                            }
                        };
                    }
            );
        }
    }
    
    
    /**
     * Effettua la ricerca degli apparati della rete.
     * 
     * @param event         Pulsante 'Cerca'
     */
    @FXML
    private void cercaApparato(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION)){
            if(menuTipoRicerca.getValue() != null){
                TreeItem<Nodo> nodo = Programma.cercaListaApparato(rete, compoRicerca.getText(), menuTipoRicerca.getValue());

                if(nodo != null){
                    nodo.setExpanded(true);
                    reteSelezionata.getChildren().clear();
                    elencoInfo.getItems().clear();
                    reteSelezionata.getChildren().add(nodo);
                }
            }
        }
    }
    
    /**
     * Imposta comportamento 'campoRicerca'.
     * 
     * @param event         Clic sul 'campoRicerca'
     */
    @FXML
    private void aggiornaCampoRicerca(MouseEvent event){
        if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
            if(menuTipoRicerca.getValue() != null){
                String tipoRicerca = menuTipoRicerca.getValue();
                    
                if(tipoRicerca.equals(R.TipoRicerca.NOMINATIVO)){
                    CampoTesto.autoCompletamento(
                            compoRicerca, 
                            ((DatiUtilizzatore)dati.get(DatiUtilizzatore.NOME_TABELLA)).listaCognomi()
                    );
                }else if(tipoRicerca.equals(R.TipoRicerca.ACCOUNT)){
                    CampoTesto.autoCompletamento(
                            compoRicerca, 
                            ((DatiUtilizzatore)dati.get(DatiUtilizzatore.NOME_TABELLA)).listaAccount()
                    );
                }else if(tipoRicerca.equals(R.TipoRicerca.IP)){
                    CampoTesto.indirizzoIP(compoRicerca);
                    
                }else if(tipoRicerca.equals(R.TipoRicerca.APPARATO)){
                    CampoTesto.autoCompletamento(
                            compoRicerca, 
                            ((DatiApparato)dati.get(DatiApparato.NOME_TABELLA)).nomiApparati()
                    );
                    
                }
            }
        }
    }
}
