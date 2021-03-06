package it.quasar_x7.gestione_rete.controllo;

import it.quasar_x7.gestione_rete.Dati.DatiApparato;
import it.quasar_x7.gestione_rete.Dati.DatiCasaHardware;
import it.quasar_x7.gestione_rete.Dati.DatiCasaSoftware;
import it.quasar_x7.gestione_rete.Dati.DatiConnessioneSwitch;
import it.quasar_x7.gestione_rete.Dati.DatiHardware;
import it.quasar_x7.gestione_rete.Dati.DatiHardwareApparato;
import it.quasar_x7.gestione_rete.Dati.DatiIntervento;
import it.quasar_x7.gestione_rete.Dati.DatiLogin;
import it.quasar_x7.gestione_rete.Dati.DatiPosizione;
import it.quasar_x7.gestione_rete.Dati.DatiResponsabileSito;
import it.quasar_x7.gestione_rete.Dati.DatiRete;
import it.quasar_x7.gestione_rete.Dati.DatiRuolo;
import it.quasar_x7.gestione_rete.Dati.DatiSoftware;
import it.quasar_x7.gestione_rete.Dati.DatiSoftwareApparato;
import it.quasar_x7.gestione_rete.Dati.DatiStato;
import it.quasar_x7.gestione_rete.Dati.DatiSwitch;
import it.quasar_x7.gestione_rete.Dati.DatiTipoApparato;
import it.quasar_x7.gestione_rete.Dati.DatiTipoHardware;
import it.quasar_x7.gestione_rete.Dati.DatiTipoSoftware;
import it.quasar_x7.gestione_rete.Dati.DatiUtilizzatore;
import it.quasar_x7.gestione_rete.modello.Apparato;
import it.quasar_x7.gestione_rete.modello.Hardware;
import it.quasar_x7.gestione_rete.modello.HardwareApparato;
import it.quasar_x7.gestione_rete.modello.Intervento;
import it.quasar_x7.gestione_rete.modello.Ufficio;
import it.quasar_x7.gestione_rete.modello.Utilizzatore;
import it.quasar_x7.gestione_rete.modello.Voce;
import it.quasar_x7.gestione_rete.modello.Nodo;
import it.quasar_x7.gestione_rete.modello.Responsabile;
import it.quasar_x7.gestione_rete.modello.Rete;
import it.quasar_x7.gestione_rete.modello.Software;
import it.quasar_x7.gestione_rete.modello.SoftwareApparato;
import it.quasar_x7.gestione_rete.modello.Switch;
import it.quasar_x7.gestione_rete.modello.ConnessioneSwitch;
import it.quasar_x7.gestione_rete.programma.Programma;
import static it.quasar_x7.gestione_rete.programma.Programma.dati;
import it.quasar_x7.gestione_rete.programma.R;
import it.quasar_x7.controllo.FinestraGestioneUtentiController;
import it.quasar_x7.java.BaseDati.EccezioneBaseDati;
import it.quasar_x7.java.utile.DataOraria;
import it.quasar_x7.javafx.CampoTesto;
import it.quasar_x7.javafx.Finestra;
import it.quasar_x7.javafx.TipoFile;
import it.quasar_x7.javafx.finestre.controllo.ConfermaController;
import it.quasar_x7.javafx.finestre.controllo.InputController;
import it.quasar_x7.javafx.finestre.controllo.InputController.Codice;
import it.quasar_x7.modello.LivelloAccesso;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeSet;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;
/**
 * Classe che implementa il comportamento della 'FinestraPrincipale,fxml' .
 *
 * @author Dr Domenico della Peruta
 */
public class FinestraPrincipaleController implements Initializable {

	abstract class AzioneMenu{
		public void apparato(Apparato nodo){}
        public void locale(Ufficio nodo){}
        public void rete(Rete nodo){}
        public void responsabile(Responsabile nodo){}
        public void info(Voce nodo){}
        public void listaVuota(){}
        public void listaSoftware(SoftwareApparato nodo){}
        public void listaHardware(HardwareApparato nodo){}
        public void software(SoftwareApparato nodoPadre,Software nodo){}
        public void hardware(HardwareApparato nodoPadre,Hardware nodo){}
        public void utilizzatore(Utilizzatore nodo){}
        public void connessioneSwitch(Apparato nodoPadre,ConnessioneSwitch nodo){}
        public void switchRete(Switch nodo){}
        public void helpDesk(Intervento nodo){}
	}
	
    static public TreeItem<Nodo> rete = new TreeItem<> (new Nodo("Lista apparati"));
    
    private final TreeItem<Nodo> reteSelezionata = new TreeItem<> (new Nodo("Lista ricerca"));
    
    static public TreeItem<Nodo> reteSwitch = new TreeItem<> (new Nodo("Lista switch"));
    
    static public TreeItem<Nodo> reteInterventi = new TreeItem<> (new Nodo("Lista interventi"));
    
     
    @FXML
    private Label titolo;
    
    @FXML
    private ContextMenu menuPannello;
    
    @FXML
    private TreeView<Nodo> listaApparati;
    
    @FXML
    private TreeView<Nodo> listaSwitch;
    
    @FXML
    private TitledPane pannelloListaHelpDesk;

    @FXML
    private TreeView<Nodo> listaHelpDesk;
    
    @FXML
    private TitledPane pannelloListaHost;
    
    @FXML
    private TitledPane pannelloListaSwitch;
    
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
    
    @FXML
    private BarChart<String, Long> diagramma;

    @FXML
    private CategoryAxis diagrammaGruppoApparati;

    @FXML
    private NumberAxis diagrammaNumeroApparati;
    
    @FXML
    private Button pulsanteChiusura;

    

	private TreeView<Nodo> listaSelezionata = null;

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
    private final  DatiConnessioneSwitch datiSwitchApparato = (DatiConnessioneSwitch)dati.get(DatiConnessioneSwitch.NOME_TABELLA);
    private final  DatiPosizione datiPosizione = (DatiPosizione)dati.get(DatiPosizione.NOME_TABELLA);
    private final  DatiRete datiRete = (DatiRete)dati.get(DatiRete.NOME_TABELLA);
    private final  DatiResponsabileSito datiResponsabile = (DatiResponsabileSito)dati.get(DatiResponsabileSito.NOME_TABELLA);
    private final  DatiSoftwareApparato datiSoftwareApparato = (DatiSoftwareApparato)dati.get(DatiSoftwareApparato.NOME_TABELLA);
    private final  DatiHardwareApparato datiHardwareApparato = (DatiHardwareApparato)dati.get(DatiHardwareApparato.NOME_TABELLA);
    private final  DatiIntervento datiIntervento = (DatiIntervento)dati.get(DatiIntervento.NOME_TABELLA);

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	Finestra.infoFinestreAperte(pulsanteChiusura);
    	
    	menuTipoRicerca.getItems().add(R.TipoRicerca.NOMINATIVO);
        menuTipoRicerca.getItems().add(R.TipoRicerca.ACCOUNT);
        menuTipoRicerca.getItems().add(R.TipoRicerca.IP);
        menuTipoRicerca.getItems().add(R.TipoRicerca.MAC);
        menuTipoRicerca.getItems().add(R.TipoRicerca.APPARATO);
        menuTipoRicerca.getItems().add(R.TipoRicerca.SW);
        menuTipoRicerca.getItems().add(R.TipoRicerca.HW);
        menuTipoRicerca.getItems().add(R.TipoRicerca.POSIZIONE);
        menuTipoRicerca.getItems().add(R.TipoRicerca.RESPONSABILE);
        
        pannelloLaterale.setExpandedPane(pannelloListaHost);
        pannelloListaHost.setExpanded(true);
        pannelloListaSwitch.setExpanded(true);
        titolo.setText(
                String.format(
                        "Benvenuto %s [%s] ",
                        datiLogin.livello(FinestraGestioneUtentiController.utente),
                        FinestraGestioneUtentiController.utente
                )
        );
        
        listaHelpDesk.setShowRoot(false);
        listaApparati.setShowRoot(false);
        listaRicercaInfo.setShowRoot(false);
        // modalità di costruzione dell'albero
        
        Programma.aspettoListaAlbero(rete,listaApparati,datiStato);
        Programma.creaListaApparato(rete,datiApparato.listaApparati());
       
        Programma.aspettoListaAlbero(reteSwitch,listaSwitch,datiStato);
        Programma.creaListaSwitch(reteSwitch);
        
        Programma.aspettoListaAlbero(reteInterventi,listaHelpDesk,datiStato);
        Programma.crealistaHelpDesk(reteInterventi);
        
        Programma.aspettoListaAlbero(reteSelezionata,listaRicercaInfo,datiStato);
        
        creaDiagramma();
        visualizzaDiagramma(true);
    }  
    
    private void creaDiagramma() {
    	diagramma.setTitle(R.Etichette.DIAGRAMMA);
    	diagrammaGruppoApparati.setLabel(R.Etichette.POSIZIONE);       
    	diagrammaNumeroApparati.setLabel(R.Etichette.FINESTRA_LISTA_APPARATI);
    	
    	
    	HashMap<String, HashMap<String, HashMap<String, ArrayList<Apparato>>>> lista = datiApparato.listaApparati();
    	
    	for(String nomeDominio : lista.keySet()){
            HashMap<String,HashMap<String, ArrayList<Apparato>>> dominio = lista.get(nomeDominio);
            XYChart.Series<String,Long> serie = new Series<String, Long>();
        	serie.setName(nomeDominio);
            
            if(dominio != null){
                for(String nomeResponsabile : dominio.keySet()){
                	HashMap<String, ArrayList<Apparato>> responsabile = dominio.get(nomeResponsabile);
                    long nApparati = 0;
                	for(String nomeUfficio : responsabile.keySet()){
                    	ArrayList<Apparato> ufficio = responsabile.get(nomeUfficio);
                    	nApparati +=ufficio.size();
                    }
                	serie.getData().add(new XYChart.Data<String, Long>(nomeResponsabile, nApparati));
                    
                    
                }
            }
            diagramma.getData().add(serie);
        
        }
    	
    }
    
    private void visualizzaDiagramma(boolean vedi) {
    	elencoInfo.setVisible(!vedi);
        listaRicercaInfo.setVisible(!vedi);
    }
    
    /**
     * Crea nuovo apparato dal menu principale.
     * @param event
     */
    @FXML
    private void creaNuovoApparato(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
        	Programma.autorizzaCaricamentoFinestra(this,R.FXML.FINESTRA_APPARATO,false);
        }
    }
    
    

    @FXML
    private void apriListaApparati(ActionEvent event) {
    	if (event.getEventType().equals(ActionEvent.ACTION)) {
    		Programma.apriListaApparati(this);
    	}
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

    /**
     * Crea nuovo utilizzatore apparato dal menu principale.
     * @param event
     */
    @FXML
    private void creaNuovoUtilizzatore(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
        	Programma.autorizzaCaricamentoFinestra(this,R.FXML.FINESTRA_UTILIZZATORE,false);
        }
    }

    /**
     * Crea nuovo hardware dal menu principale.
     * @param event
     */
    @FXML
    private void creaNuovoHardware(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
        	Programma.autorizzaCaricamentoFinestra(this,R.FXML.FINESTRA_HW,false);
        }
        
    }

    /**
     * Crea nuova connessione switch
     * @param event
     */
    @FXML
    private void creaNuovaConnessioneSwitch(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
        	Programma.autorizzaCaricamentoFinestra(this,R.FXML.FINESTRA_SWITCH,false);
        }
    }

    
    /**
     * Crea nuovo software dal menu principale.
     * @param event
     */
    @FXML
    private void creaNuovoSoftware(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
        	Programma.autorizzaCaricamentoFinestra(this,R.FXML.FINESTRA_SW,false);
        }
    }


    @FXML
    private void modificaPassword(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
    		Finestra.caricaFinestra(this, R.FXML.FINESTRA_PASSWORD);
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
    
    /**
     * Crea nuovo responsabile del sito tramite il menu principale
     * @param event
     */
    @FXML
    private void creaNuovoResponsabile(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
        	Programma.autorizzaCaricamentoFinestra(this,R.FXML.FINESTRA_RESPONSABILE,false);
        }
    }
    
    
    @FXML
    private void apriListaPosizione(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            Programma.apriListaPosizione(this);
        }
    }
    
    /**
     * Crea la posizione del sito tramite il menu principale
     * @param event
     */
    @FXML
    private void creaNuovaPosizione(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
        	Programma.autorizzaCaricamentoFinestra(this,R.FXML.FINESTRA_POSIZIONE,false);
        }
    }
    
    
    /**
     * Aggiungi nuova rete dal menu principale.
     * @param event
     */
    @FXML
    private void aggiungiNuovaRete(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
        	Programma.autorizzaCaricamentoFinestra(this,R.FXML.FINESTRA_RETE,false);
        }
    }
    
    /**
     * Aggiungi nuovo intervento helpdesk tramite il menu principale
     * @param event
     */
    @FXML
    private void aggiungiNuovoIntervento(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
        	Programma.autorizzaCaricamentoFinestra(this,R.FXML.FINESTRA_INTERVENTO,false);
        }
    }
    
    @FXML
    private void apriListaReti(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            Programma.apriListaReti(this);
        }
    }
    
    @FXML
    private void apriListaInterventi(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            Programma.apriListaInterventi(this);
        }
    }
    
    
    /**
     * Apre la finestra che associa un apparato ad un dato software precedentemente inserito.
     * 
     * @param event Voce del menu principale
     */
    @FXML
    private void associaSoftwareApparato(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
        	Programma.autorizzaCaricamentoFinestra(this,R.FXML.FINESTRA_SW_APPARATO,false);
        }
    }
    /**
     * Apre la finestra che associa un apparato ad un dato hardware precedentemente inserito.
     * 
     * @param event Voce del menu principale 
     */
    @FXML
    private void associaHardwareApparato(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
        	Programma.autorizzaCaricamentoFinestra(this,R.FXML.FINESTRA_HW_APPARATO,false);
        }
    }
    
    /**
     * Permette la visualizzazione del menu di modifica della lista ad albero.
     * 
     * @param event clic mouse sul pannello ad albero laterale
     */
    @FXML
    private void visualizzaMenuListaRete(MouseEvent event){
        if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
            visualizzaMenu(event);
            aggiornaListaInfo(event,listaApparati);
            aggiornaListaRicercaInfo(event,listaApparati);
            event.consume();
        }
    }
    
    @FXML
    private void visualizzaMenuListaHelpDesk(MouseEvent event){
        if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
        	visualizzaMenu(event);
            aggiornaListaInfo(event,listaHelpDesk);
            aggiornaListaRicercaInfo(event,listaHelpDesk);
            event.consume();
        }
    }
    
    @FXML
    private void visualizzaMenuListaSwitch(MouseEvent event){
        if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
            visualizzaMenu(event);
            aggiornaListaInfo(event,listaSwitch);
            aggiornaListaRicercaInfo(event,listaSwitch);
            event.consume();
        }
    }
    
    /**
     * 
     * @param event clic mouse sul pannello 'listaRicercaInfo'
     */
    @FXML
    private void cliccaPanelloRicercaListaApparati(MouseEvent event){
        if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
        	visualizzaMenu(event);
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
                visualizzaDiagramma(false);
            }
            
        }
    }
    /**
     * Aggiorna l'elenco ad albero della "listaRicercaInfo" con un elemento selezionato dal pannello "lista".
     * @param event 
     * @param lista
     */
    private void aggiornaListaRicercaInfo(MouseEvent event,TreeView<Nodo> lista){
        if(event.getButton().equals(MouseButton.PRIMARY)){
        	listaRicercaInfo.getRoot().getChildren().clear();
            TreeItem<Nodo> nodo = lista.getSelectionModel().getSelectedItem();
            if(nodo != null){
                reteSelezionata.getChildren().clear();
                reteSelezionata.getChildren().add(Programma.copia(nodo));
            }
            
        }
    }
    
    /**
     * Esegue un azione menu prevista dalla selezione.
     * 
     * @param azione
     */
    private void selezionaMenuAlbero(AzioneMenu azione) {
    	if(listaSelezionata != null) {
	    	TreeItem<Nodo> nodo = listaSelezionata.getSelectionModel().getSelectedItem();
	    	
	    	if(nodo != null){
	            if(nodo.getValue() instanceof Apparato){
	                azione.apparato((Apparato) nodo.getValue());
	            }else if(nodo.getValue() instanceof Ufficio){
	            	azione.locale((Ufficio) nodo.getValue());
	            }else if(nodo.getValue() instanceof Rete){
	            	azione.rete((Rete) nodo.getValue());
	            }else if(nodo.getValue() instanceof Responsabile) {
	            	azione.responsabile((Responsabile) nodo.getValue());
	            }else if(nodo.getValue() instanceof SoftwareApparato) {
	            	azione.listaSoftware((SoftwareApparato) nodo.getValue());
	            }else if(nodo.getValue() instanceof HardwareApparato) {
	            	azione.listaHardware((HardwareApparato) nodo.getValue());
	            }else if(nodo.getValue() instanceof Hardware) {
	            	azione.hardware((HardwareApparato)nodo.getParent().getValue(),(Hardware) nodo.getValue());
	            }else if(nodo.getValue() instanceof Software) {
	            	azione.software((SoftwareApparato)nodo.getParent().getValue(),(Software) nodo.getValue());
	            }else if(nodo.getValue() instanceof Utilizzatore) {
	            	azione.utilizzatore((Utilizzatore) nodo.getValue());
	            }else if(nodo.getValue() instanceof ConnessioneSwitch) {
	            	azione.connessioneSwitch((Apparato)nodo.getParent().getValue(),(ConnessioneSwitch) nodo.getValue());
	            }else if(nodo.getValue() instanceof Intervento) {
	            	azione.helpDesk((Intervento) nodo.getValue());
	            }else if(nodo.getValue() instanceof Switch) {
	            	azione.switchRete((Switch) nodo.getValue());
	            
	            }else if(nodo.getValue() instanceof Voce) {
	            	azione.info((Voce)nodo.getValue());
	            }
	            
	        }else {
	        	azione.listaVuota();
	        }
    	}
    }
    
    /**
     * Permette di inizializzare la vista del menu, a seconda del tipo di nodo selezionato.
     * Questo metodo è permette anche di inizializzare la variabile 'listaSelezionata'.
     * @param event
     */
    private void visualizzaMenu(MouseEvent event){
        if((event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) || event.getButton().equals(MouseButton.SECONDARY) ){
            if(event.getSource() instanceof TreeView){
            	listaSelezionata  = (TreeView<Nodo>) event.getSource();
                selezionaMenuAlbero(new AzioneMenu(){

                	final int TITOLO_MENU = 0;
                	final int VOCE_AGGIUNGI = 1;
                	final int VOCE_WIZARD   = 2;
                	final int VOCE_MODIFICA = 3;
                	final int VOCE_ELIMINA  = 4;
                	
                	private void inizializzaVoci(String tipoNodo,Nodo nodo) {
                		
                		menuPannello.getItems().get(TITOLO_MENU).setText(tipoNodo.toUpperCase());
                		
                		if(!nodo.getNome().equals(R.ChiaviDati.NESSUNA_RETE) && !nodo.getNome().equals(R.ChiaviDati.NESSUN_NOME) && !nodo.getNome().equals(R.ChiaviDati.NESSUN_GRUPPO) && !nodo.getNome().equals(R.ChiaviDati.NESSUN_SWITCH)) {
                			
                			
                			if(nodo instanceof Software || nodo instanceof Hardware) {
                				menuPannello.getItems().get(VOCE_AGGIUNGI).setVisible(false);
            				}else {
            					menuPannello.getItems().get(VOCE_AGGIUNGI).setVisible(true);
                        		menuPannello.getItems().get(VOCE_AGGIUNGI).setText(String.format("Aggiungi nuovo %s",tipoNodo.toLowerCase()));
            				}
                			
                			if(nodo instanceof SoftwareApparato || nodo instanceof HardwareApparato) {
                				menuPannello.getItems().get(VOCE_WIZARD).setVisible(true);
                				menuPannello.getItems().get(VOCE_WIZARD).setText(String.format("Wizard di «%s»",nodo.getNome()));
                				menuPannello.getItems().get(VOCE_MODIFICA).setVisible(false);
    							menuPannello.getItems().get(VOCE_ELIMINA).setVisible(false);
                			}else {
                				
                				menuPannello.getItems().get(VOCE_WIZARD).setVisible(false);
                				menuPannello.getItems().get(VOCE_MODIFICA).setVisible(true);
    							menuPannello.getItems().get(VOCE_ELIMINA).setVisible(true);
    							menuPannello.getItems().get(VOCE_MODIFICA).setText(String.format("Modifica «%s»",nodo.getNome()));
    							menuPannello.getItems().get(VOCE_ELIMINA).setText(String.format("Elimina «%s»",nodo.getNome()));
                			}
                			
                		}else {
                			menuPannello.getItems().get(VOCE_WIZARD).setVisible(false);
							menuPannello.getItems().get(VOCE_MODIFICA).setVisible(false);
							menuPannello.getItems().get(VOCE_ELIMINA).setVisible(false);
                		}
                	}
    				@Override
    				public void apparato(Apparato nodo) {
    					inizializzaVoci(R.Etichette.APPARATO, nodo);
    				}

    				@Override
    				public void locale(Ufficio nodo) {
    					inizializzaVoci("Locale", nodo);
    				}

    				@Override
    				public void rete(Rete nodo) {
    					inizializzaVoci("Rete", nodo);
    				}

    				@Override
    				public void responsabile(Responsabile nodo) {
    					inizializzaVoci("Responsabile", nodo);
    				}
    				
    				@Override
    				public void listaSoftware(SoftwareApparato nodo) {
    					String titolo = String.format("software `%s`",nodo.getNome());
    					inizializzaVoci(titolo, nodo);
    				}
    				
    				@Override
    				public void listaHardware(HardwareApparato nodo) {
    					String titolo = String.format("hardware `%s`",nodo.getNome());
    					inizializzaVoci(titolo, nodo);
    				}
    				
    				@Override
    				public void software(SoftwareApparato nodoPadre,Software nodo) {
    					inizializzaVoci(R.Etichette.SW,nodo);
    				}
    				
    				@Override
    				public void hardware(HardwareApparato nodoPadre,Hardware nodo) {
    					inizializzaVoci(R.Etichette.HW,nodo);
    				}
    				
    				@Override
    				public void utilizzatore(Utilizzatore nodo) {
    					inizializzaVoci(R.Etichette.UTILIZZATORE,nodo);
    				}
    				
    				@Override
    				public void connessioneSwitch(Apparato nodoPadre,ConnessioneSwitch nodo) {
    					inizializzaVoci(R.Etichette.SWITCH,nodo);
    				}
    				
    				@Override
    				public void switchRete(Switch nodo) {
    					inizializzaVoci(R.Etichette.SWITCH,nodo);
    				}
    				
    				@Override
    				public void helpDesk(Intervento nodo) {
    					inizializzaVoci(R.Etichette.INTERVENTO,nodo);
    				}
    				
					@Override
					public void info(Voce nodo) {
						menuPannello.getItems().get(TITOLO_MENU).setText(nodo.getNome());
						
						menuPannello.getItems().get(TITOLO_MENU).setVisible(true);
						menuPannello.getItems().get(VOCE_AGGIUNGI).setVisible(false);
						menuPannello.getItems().get(VOCE_WIZARD).setVisible(false);
						menuPannello.getItems().get(VOCE_MODIFICA).setVisible(false);
						menuPannello.getItems().get(VOCE_ELIMINA).setVisible(false);
					}
					
					@Override
					public void listaVuota() {
						String titolo = "";
						if(listaApparati.equals(listaSelezionata)) {
							titolo = R.Etichette.APPARATO;
						}else if(listaSwitch.equals(listaSelezionata)) {
							titolo = R.Etichette.SWITCH;
						}else if(listaHelpDesk.equals(listaSelezionata)) {
							titolo = R.Etichette.INTERVENTO;
						}
					    menuPannello.getItems().get(TITOLO_MENU).setText(titolo);
						
						menuPannello.getItems().get(TITOLO_MENU).setVisible(true);
						menuPannello.getItems().get(VOCE_AGGIUNGI).setVisible(true);
						menuPannello.getItems().get(VOCE_WIZARD).setVisible(false);
						menuPannello.getItems().get(VOCE_MODIFICA).setVisible(false);
						menuPannello.getItems().get(VOCE_ELIMINA).setVisible(false);
					}
                });
                
                //visualizza anche quando si fa doppio clic...
                menuPannello.show((Node) event.getSource(), event.getScreenX(), event.getScreenY());
            }
        }
        event.consume();
    }
    
    
    @FXML
    private void menuAggiungi(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
        	String livello = Programma.livelloAccesso();
        	if(!livello.equals(LivelloAccesso.OSSERVATORE)) {
	        
	            elencoInfo.getItems().clear();
	            visualizzaDiagramma(true);
	            if(!listaSelezionata.equals(listaRicercaInfo))
	            	listaRicercaInfo.getRoot().getChildren().clear();
	            
	            selezionaMenuAlbero(new AzioneMenu(){
	
					@Override
					public void apparato(Apparato nodo) {
						creaNuovoApparato(event);
					}
	
					@Override
					public void locale(Ufficio nodo) {
						creaNuovaPosizione(event);
					}
	
					@Override
					public void rete(Rete nodo) {
						aggiungiNuovaRete(event);
					}
	
					@Override
					public void responsabile(Responsabile nodo) {
						creaNuovoResponsabile(event);
					}
					
					@Override
					public void listaSoftware(SoftwareApparato nodo) {
						ceaNuovoSoftwareApparato(nodo);
					}
					
					@Override
					public void listaHardware(HardwareApparato nodo) {
						ceaNuovoHardwareApparato(nodo);
					}
					
					@Override
					public void utilizzatore(Utilizzatore nodo) {
						creaNuovoUtilizzatore(event);
					}
					
					@Override
					public void connessioneSwitch(Apparato nodoPadre,ConnessioneSwitch nodo) {
						associaSwitch(nodoPadre,nodo);
					}
					
					@Override
					public void switchRete(Switch nodo) {
						Finestra.finestraInput(
								this,
								R.Domanda.INPUT_SWITCH,
								new Codice() {
									@Override
									public boolean esegui(String risposta) {
										if(risposta != null)
											if(risposta.length() > 0) {
												boolean conferma = datiSwitch.aggiungi(new Object[] {risposta});
												Programma.aggiornaListeNodi();
												return conferma;
											}
										return false;
									}
								});
					}
					
					@Override
					public void helpDesk(Intervento nodo) {
						creaNuovoIntervento();
					}
					
					@Override
					public void listaVuota() {
						if(listaApparati.equals(listaSelezionata)) {
							creaNuovoApparato(event);
						}else if(listaSwitch.equals(listaSelezionata)) {
							associaSwitch(null,null);
						}else if(listaHelpDesk.equals(listaSelezionata)) {
							creaNuovoIntervento();
						}
					}
	
				});
        	}else
        		Finestra.finestraAvviso(this, String.format(R.Messaggi.ERRORE_AUTORIZZAZIONE,livello));

        }
    }
    
    private void creaNuovoIntervento() {
    	Finestra.caricaFinestra(this, R.FXML.FINESTRA_INTERVENTO);
    }
    
    private void associaSwitch(Apparato nodoPadre,ConnessioneSwitch nodo) {
    	if(nodo != null && nodoPadre != null){
    		FinestraSwitchController.input = new String[] {nodoPadre.getNome(), nodo.getNome(), nodo.getPorta()};
        }
    	Finestra.caricaFinestra(this, R.FXML.FINESTRA_SWITCH);
    }
    
    private void ceaNuovoHardwareApparato(HardwareApparato nodo) {
    	if(nodo != null){
            FinestraHardwareApparatoController.apparato = nodo.getNome();
            Finestra.caricaFinestra(this, R.FXML.FINESTRA_HW_APPARATO);
        }
    }
    
    private void ceaNuovoSoftwareApparato(SoftwareApparato nodo) {
    	if(nodo != null){
            FinestraSoftwareApparatoController.apparato = nodo.getNome();
            Finestra.caricaFinestra(this, R.FXML.FINESTRA_SW_APPARATO);
        }
    }
    
    @FXML
    private void menuWizard(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
        	String livello = Programma.livelloAccesso();
        	if(!livello.equals(LivelloAccesso.OSSERVATORE)) {
	        
	        	elencoInfo.getItems().clear();
	        	visualizzaDiagramma(true);
	        	if(!listaSelezionata.equals(listaRicercaInfo))
	            	listaRicercaInfo.getRoot().getChildren().clear();
	            selezionaMenuAlbero(new AzioneMenu(){
	
					@Override
					public void listaSoftware(SoftwareApparato nodo) {
						if(nodo != null){
			                FinestraWizardSWController.apparato = nodo.getNome();
			                Finestra.caricaFinestra(this, R.FXML.FINESTRA_WIZARD_SW_APPARATO);
			                
			            }
					}
					
					@Override
					public void listaHardware(HardwareApparato nodo) {
						if(nodo != null){
			                FinestraWizardHWController.apparato = nodo.getNome();
			                Finestra.caricaFinestra(this, R.FXML.FINESTRA_WIZARD_HW_APPARATO);
			                
			            }
					}
	            });
        	}else
        		Finestra.finestraAvviso(this, String.format(R.Messaggi.ERRORE_AUTORIZZAZIONE,livello));

        }
    }
    


    @FXML
    private void menuModifica(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
        	String livello = Programma.livelloAccesso();
        	if(!livello.equals(LivelloAccesso.OSSERVATORE)) {
	        
		        elencoInfo.getItems().clear();
		        visualizzaDiagramma(true);
		        if(!listaSelezionata.equals(listaRicercaInfo))
		        	listaRicercaInfo.getRoot().getChildren().clear();
		        
		        selezionaMenuAlbero(new AzioneMenu(){
		
					@Override
					public void apparato(Apparato nodo) {
						FinestraApparatoController.input = new String[]{
		                    nodo.getNome(),
		                    nodo.getTipo(),
		                    nodo.getGruppo(),
		                    nodo.getIp(),
		                    nodo.getMacPC(),
		                    nodo.getMacVOIP(),
		                    nodo.getPosizione(),
		                    nodo.getUtente(),
		                    nodo.getInternet() == null ? null : nodo.getInternet() == true ? R.Conferma.SI : R.Conferma.NO,
		                    String.valueOf(nodo.getSigillo()),
		                    String.valueOf(nodo.getScheda()),
		                    nodo.getPassword(),
		                    nodo.getStato()
		                };
		                creaNuovoApparato(event);
					}
		
					@Override
					public void locale(Ufficio nodo) {
						FinestraPosizioneController.input = new String[]{
		                    nodo.getNome(),
		                    nodo.getResponsabile()
		                };
		                creaNuovaPosizione(event);
					}
		
					@Override
					public void rete(Rete nodo) {
						if(!nodo.equals(Rete.STANDALONE)){
		                    FinestraReteController.input = new String[]{
		                        nodo.getNome(),
		                        nodo.getDominio(),
		                        nodo.getTipo(),
		                        nodo.getGateway(),
		                        nodo.getNetmask()
		                    };
		                    aggiungiNuovaRete(event);
		                }
					}
		
					@Override
					public void responsabile(Responsabile nodo) {
						FinestraResponsabileController.input = new String[] {
		            			nodo.getNome(),//responsabile (nome incarico)
		            			nodo.getNominativo()
		            	};
		            	creaNuovoResponsabile(event);
					}
					
					@Override
					public void hardware(HardwareApparato nodoPadre,Hardware nodo) {
						if(nodo != null && nodoPadre != null) {
		                	FinestraHardwareApparatoController.hardware = nodo.getNome();
		                	FinestraHardwareApparatoController.modello = nodo.getModello();
		                	FinestraHardwareApparatoController.matricola = nodo.getMatricola();
		                }
						ceaNuovoHardwareApparato(nodoPadre);
					}
					
					@Override
					public void software(SoftwareApparato nodoPadre,Software nodo) {
						if(nodo != null && nodoPadre != null) {
		                	FinestraSoftwareApparatoController.software = nodo.getNome();
		                	FinestraSoftwareApparatoController.licenza = nodo.getLicenza();
		                }
						ceaNuovoSoftwareApparato(nodoPadre);
					}
					
					@Override
					public void utilizzatore(Utilizzatore nodo) {
						if(nodo != null) {
		                	FinestraUtilizzatoreController.input = new String[] {
		                			nodo.getAccount(),
		                			nodo.getNome(),
		                			nodo.getMail()
		                	};
		                }
						creaNuovoUtilizzatore(event);
					}
					
					@Override
					public void connessioneSwitch(Apparato nodoPadre,ConnessioneSwitch nodo) {
						associaSwitch(nodoPadre,nodo);
					}
					
					@Override
					public void switchRete(Switch nodo) {
						Finestra.finestraInput(
								this,
								R.Domanda.INPUT_SWITCH,
								nodo.getNome(),
								new Codice() {
									@Override
									public boolean esegui(String risposta) {
										if(risposta != null)
											if(risposta.length() > 0) {
												datiSwitch.elimina(new Object[] {nodo.getNome()});
												datiSwitchApparato.aggiorna(DatiConnessioneSwitch.VOCE_SWITCH, nodo.getNome(), risposta);
												datiSwitch.aggiungi(new Object[] {risposta});
												Programma.aggiornaListeNodi();
												return true;
											}
										return false;
									}
								});
					}
					
					@Override
					public void helpDesk(Intervento nodo) {
						if(nodo != null) {
		                	FinestraInterventoController.input = new String[] {
		                			nodo.getNome(),
		                			nodo.getData() != null ? nodo.getData().stampaGiorno('/') : null,
		                			nodo.getApparato(),
		                			nodo.getMotivo(),
		                			nodo.getAzione(),
		                			nodo.getEsito()
		                	};
		                }
						creaNuovoIntervento();
					}
		
				});
        	}else {
        		Finestra.finestraAvviso(this, String.format(R.Messaggi.ERRORE_AUTORIZZAZIONE,livello));
        	}
            
        }
    }

    @FXML
    private void menuElimina(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
        	String livello = Programma.livelloAccesso();
        	if(!livello.equals(LivelloAccesso.OSSERVATORE)) {
	            elencoInfo.getItems().clear();
	            visualizzaDiagramma(true);
	            if(!listaSelezionata.equals(listaRicercaInfo))
	            	listaRicercaInfo.getRoot().getChildren().clear();
	            
	            selezionaMenuAlbero(new AzioneMenu(){
	
					@Override
					public void apparato(Apparato nodo) {
						Finestra.finestraConferma(
								this, 
								String.format(R.Domanda.CONFERMA_ELIMINAZIONE_APPARATO,nodo.getNome()), 
								new ConfermaController.Codice() {
									@Override
									public void esegui() {
					                	datiApparato.elimina(new Object[] {nodo.getNome()});
					                	Programma.aggiornaListeNodi();
									}
								}
						);
					}
		
					@Override
					public void locale(Ufficio nodo) {
						Finestra.finestraConferma(
								this, 
								String.format(R.Domanda.CONFERMA_ELIMINAZIONE_UFFICIO, nodo.getNome()), 
								new ConfermaController.Codice() {
									@Override
									public void esegui() {
										datiPosizione.elimina(new Object[] {nodo.getNome()});
					                	datiApparato.aggiorna(DatiApparato.VOCE_TABELLA_POSIZIONE, nodo.getNome(), "");
							            
					                	Programma.aggiornaListeNodi();// aggiorna lista laterale ad albero
									}
						});
					}
		
					@Override
					public void rete(Rete nodo) {
						Finestra.finestraConferma(
								this, 
								String.format(R.Domanda.CONFERMA_ELIMINAZIONE_RETE,nodo.getNome()), 
								new ConfermaController.Codice() {
									@Override
									public void esegui() {
										datiRete.elimina(new Object[] {nodo.getNome()});
					                	datiApparato.aggiorna(DatiApparato.VOCE_TABELLA_WG, nodo.getNome(), "");
							            
					                	Programma.aggiornaListeNodi();// aggiorna lista laterale ad albero
									}
						});
					}
		
					@Override
					public void responsabile(Responsabile nodo) {
						Finestra.finestraConferma(
								this, 
								String.format(R.Domanda.CONFERMA_ELIMINAZIONE_RESPONSABILE,nodo.getNome()), 
								new ConfermaController.Codice() {
									@Override
									public void esegui() {
										datiResponsabile.elimina(new Object[] {nodo.getNome()});
					                	datiPosizione.aggiorna(DatiPosizione.VOCE_TABELLA_RESPONSABILE, nodo.getNome(), "");
							            
					                	Programma.aggiornaListeNodi();// aggiorna lista laterale ad albero
									}
						});
					}
					
					@Override
					public void hardware(HardwareApparato nodoPadre,Hardware nodo) {
						if(nodo != null && nodoPadre != null) {
							Finestra.finestraConferma(
									this, 
									String.format(R.Domanda.CONFERMA_ELIMINAZIONE,nodo.toString()), 
									new ConfermaController.Codice() {
										@Override
										public void esegui() {
											datiHardwareApparato.elimina(new Object[]{nodoPadre.apparato(),nodo.getNome(),nodo.getModello(),nodo.getMatricola()});
								            Programma.aggiornaListeNodi();// aggiorna lista laterale ad albero
										}
							});
		                }
					}
					
					@Override
					public void software(SoftwareApparato nodoPadre,Software nodo) {
						if(nodo != null && nodoPadre != null) {
							Finestra.finestraConferma(
									this, 
									String.format(R.Domanda.CONFERMA_ELIMINAZIONE,nodo.toString()), 
									new ConfermaController.Codice() {
										@Override
										public void esegui() {
											datiSoftwareApparato.elimina(new Object[]{nodoPadre.apparato(),nodo.getNome(),nodo.getLicenza()});
								            Programma.aggiornaListeNodi();// aggiorna lista laterale ad albero
										}
							});
		                }
					}
	
					@Override
					public void utilizzatore(Utilizzatore nodo) {
						if(nodo != null) {
							Finestra.finestraConferma(
									this, 
									String.format(R.Domanda.CONFERMA_ELIMINAZIONE,nodo.toString()), 
									new ConfermaController.Codice() {
										@Override
										public void esegui() {
											datiApparato.aggiorna(DatiApparato.VOCE_TABELLA_UTENTE, nodo.getAccount(), "");
								            Programma.aggiornaListeNodi();// aggiorna lista laterale ad albero
										}
							});
		                }
					}
					
					@Override
					public void connessioneSwitch(Apparato nodoPadre,ConnessioneSwitch nodo) {
						if(nodo != null && nodoPadre != null) {
							Finestra.finestraConferma(
									this, 
									String.format(R.Domanda.CONFERMA_ELIMINAZIONE,nodo.toString()), 
									new ConfermaController.Codice() {
										@Override
										public void esegui() {
											datiSwitchApparato.elimina(new Object[] {nodoPadre.getNome()});
											datiApparato.aggiorna(DatiApparato.VOCE_TABELLA_SWITCH, nodo.getNome(), "");
								            Programma.aggiornaListeNodi();// aggiorna lista laterale ad albero
										}
							});
		                }
					}
					
					@Override
					public void switchRete(Switch nodo) {
						if(nodo != null) {
							Finestra.finestraConferma(
									this, 
									String.format(R.Domanda.CONFERMA_ELIMINAZIONE,nodo.toString()), 
									new ConfermaController.Codice() {
										@Override
										public void esegui() {
											datiSwitch.elimina(new Object[] {nodo.getNome()});
											TreeSet<String>nomiApparati = datiSwitchApparato.ricercaOrdinata(1, nodo.getNome(), 0);
											for(String apparato: nomiApparati) {
												datiSwitchApparato.elimina(new Object[] {apparato,nodo.getNome()});
											}
											Programma.aggiornaListeNodi();// aggiorna liste laterali ad albero
										}
							});
		                }
					}
					
					@Override
					public void helpDesk(Intervento nodo) {
						if(nodo != null) {
							Finestra.finestraConferma(
									this, 
									String.format(R.Domanda.CONFERMA_ELIMINAZIONE,nodo.toString()), 
									new ConfermaController.Codice() {
										@Override
										public void esegui() {
											datiIntervento.elimina(new Object[] {nodo.getNome()});
											Programma.aggiornaListeNodi();// aggiorna liste laterali ad albero
										}
							});
		                }
					}
					
				});
        	}else {
        		Finestra.finestraAvviso(this, String.format(R.Messaggi.ERRORE_AUTORIZZAZIONE,livello));
        	}
        }
    }


    private String voce(String tipo,String nome){
        return String.format("%s: [%s]", tipo,nome);
    }
    

    /**
     * Effettua l'importazione dei dati dal file "excel" di backup.
     * 
     * @param event
     */
    @FXML
    private void caricaFile(ActionEvent event){
        if (event.getEventType().equals(ActionEvent.ACTION)) {
        	String livello = Programma.livelloAccesso();
        	if(livello.equals(LivelloAccesso.AMMINISTRATORE))
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
        	else
        		Finestra.finestraAvviso(this, String.format(R.Messaggi.ERRORE_AUTORIZZAZIONE,livello));
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
                    visualizzaDiagramma(false);
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
                CampoTesto.inizializzaCampo(compoRicerca);
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
                    
                }else if(tipoRicerca.equals(R.TipoRicerca.MAC)){
                    CampoTesto.indirizzoMAC(compoRicerca);
                    
                    
                }else if(tipoRicerca.equals(R.TipoRicerca.POSIZIONE)){
                	CampoTesto.autoCompletamento(
                            compoRicerca, 
                            ((DatiPosizione)dati.get(DatiPosizione.NOME_TABELLA)).lista()
                    );
                    
                    
                }else if(tipoRicerca.equals(R.TipoRicerca.RESPONSABILE)){
                	CampoTesto.autoCompletamento(
                            compoRicerca, 
                            ((DatiResponsabileSito)dati.get(DatiResponsabileSito.NOME_TABELLA)).lista()
                    );
                    
                    
                }else if(tipoRicerca.equals(R.TipoRicerca.APPARATO)){
                    CampoTesto.autoCompletamento(
                            compoRicerca, 
                            ((DatiApparato)dati.get(DatiApparato.NOME_TABELLA)).nomiApparati()
                    );
                    
                }else if(tipoRicerca.equals(R.TipoRicerca.SW)){
                    CampoTesto.autoCompletamento(
                            compoRicerca, 
                            ((DatiSoftware)dati.get(DatiSoftware.NOME_TABELLA)).listaNomi()
                    );
                    
                }else if(tipoRicerca.equals(R.TipoRicerca.HW)){
                    final int MODELLI = 2;
                	CampoTesto.autoCompletamento(
                            compoRicerca, 
                            ((DatiHardware)dati.get(DatiHardware.NOME_TABELLA)).listaOrdinata(MODELLI)
                    );
                }
            }
        }
    }
    
    
    /**
     * 
     * @param event	clic sul menu principale (File > Scheda Apparato in PDF)
     */
    @FXML
    private void creaFileSchedaApparato(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION)){
        	
        	Object finestraPrincipale = this;
        	Finestra.finestraInput(
					this, 
        			R.Etichette.APPARATO, 
        			datiApparato.nomiApparati(), 
        			new Codice() {
						@Override
						public boolean esegui(String risposta) {
							boolean esisteApparato = false;
							for(String apparato : datiApparato.nomiApparati())
								if(apparato.equals(risposta)) {
                            		esisteApparato = true;
                            		break;
                            	}
							if(!esisteApparato)
								return false; // chiusura con verifica dell'input
							
							
							// [*] 
							// impostazioni per effettuare la successione due finestre dialogo
							Finestra.vistaCorrente.setScene(Finestra.finestraPrincipale);
							Finestra.eliminaUltimaFinestraRegistrata();
							Finestra.eliminaUltimaFinestraRegistrata();
							InputController.verificaInput = false;
							// [*]
							
							
							Finestra.finestraSalvaFile(
									finestraPrincipale, 
				                    risposta + " " + DataOraria.creaDataOggi().stampaGiorno()+ TipoFile.PDF , 
				                    TipoFile.PDF,
				                    (Path path) -> {
				                        
				                        return new Task() {
				                            @Override
				                            protected  Object call() throws Exception {
				                                String info = "Inizio creazione file....";
				                                updateMessage(info);
				                                updateProgress(0, 2);
				                                File file = path.toFile();
				                                if(file != null){
				                                    info = "file: "+file.getAbsolutePath() + "\n"+ info;
				                                    updateMessage(info);
				                                    Programma.creaSchedaApparatoPDF(file.getAbsolutePath(),risposta);
				                                    info = "File creato!"+ "\n"+ info;
				                                    updateMessage(info);
				                                    updateProgress(1, 2);
				                                    
				                                    if (Desktop.isDesktopSupported()) {
				                                        try {
				                                            Desktop.getDesktop().open(file);
				                                            info = "Apertura file..."+ "\n"+ info;
				                                        } catch (IOException ex) {
				                                        	info = "Errire aprire il file..."+ "\n"+ info;
				                                        }
				                                    }
				                                    updateMessage(info);
				                                    updateProgress(2, 2);
				                                    
				                                }
				                                return true;
				                            }
				                        };
				                    }
				            );
										  // evita la chiusura anticipata della seconda finestra dialogo
							return false; // N.B.: 
										  // [*]
										  // InputController.verificaInput = false;
										  // Finestra.vistaCorrente.setScene(InputController.scenaCorrente);
						}
        				
        			}
        			);
        	
        	
        }
    }
    
    
    /**
     * Apertura finestra delle impostazioni di stampa PDF.
     * 
     * @param event click sul menu principale (voce File)
     */
    @FXML
    private void apriImpostazioniReportPDF(ActionEvent event) {
        if(event.getEventType().equals(ActionEvent.ACTION)){
        	Programma.autorizzaCaricamentoFinestra(this,R.FXML.FINESTRA_IMPOSTAZIONI,false);
        }
    }
}
