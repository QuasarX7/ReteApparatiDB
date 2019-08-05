package it.quasar_x7.gestione_rete.programma;

import it.quasar_x7.gestione_rete.Dati.Dati;
import it.quasar_x7.gestione_rete.Dati.DatiApparato;
import it.quasar_x7.gestione_rete.Dati.DatiCasaHardware;
import it.quasar_x7.gestione_rete.Dati.DatiCasaSoftware;
import it.quasar_x7.gestione_rete.Dati.DatiConnessioneSwitch;
import it.quasar_x7.gestione_rete.Dati.DatiDB;
import it.quasar_x7.gestione_rete.Dati.DatiGrado;
import it.quasar_x7.gestione_rete.Dati.DatiHardware;
import it.quasar_x7.gestione_rete.Dati.DatiHardwareApparato;
import it.quasar_x7.gestione_rete.Dati.DatiImpostazioni;
import it.quasar_x7.gestione_rete.Dati.DatiIntervento;
import it.quasar_x7.gestione_rete.Dati.DatiLista;
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
import it.quasar_x7.gestione_rete.Dati.DatiTipoRete;
import it.quasar_x7.gestione_rete.Dati.DatiTipoSoftware;
import it.quasar_x7.gestione_rete.Dati.DatiUtilizzatore;
import it.quasar_x7.gestione_rete.controllo.FinestraLoginController;
import it.quasar_x7.gestione_rete.controllo.FinestraApparatoController;
import it.quasar_x7.gestione_rete.controllo.FinestraGradoController;
import it.quasar_x7.gestione_rete.controllo.FinestraHardwareController;
import it.quasar_x7.gestione_rete.controllo.FinestraInterventoController;
import it.quasar_x7.gestione_rete.controllo.FinestraPosizioneController;

import static it.quasar_x7.gestione_rete.controllo.FinestraPrincipaleController.rete;
import static it.quasar_x7.gestione_rete.controllo.FinestraPrincipaleController.reteInterventi;
import static it.quasar_x7.gestione_rete.controllo.FinestraPrincipaleController.reteSwitch;

import it.quasar_x7.gestione_rete.controllo.FinestraResponsabileController;
import it.quasar_x7.gestione_rete.controllo.FinestraReteController;
import it.quasar_x7.gestione_rete.controllo.FinestraSoftwareController;
import it.quasar_x7.gestione_rete.controllo.FinestraSwitchController;
import it.quasar_x7.gestione_rete.controllo.FinestraUtilizzatoreController;
import it.quasar_x7.gestione_rete.modello.Apparato;
import it.quasar_x7.gestione_rete.modello.Hardware;
import it.quasar_x7.gestione_rete.modello.HardwareApparato;
import it.quasar_x7.gestione_rete.modello.Intervento;
import it.quasar_x7.gestione_rete.modello.Nodo;
import it.quasar_x7.gestione_rete.modello.Responsabile;
import it.quasar_x7.gestione_rete.modello.Rete;
import it.quasar_x7.gestione_rete.modello.Software;
import it.quasar_x7.gestione_rete.modello.SoftwareApparato;
import it.quasar_x7.gestione_rete.modello.Switch;
import it.quasar_x7.gestione_rete.modello.ConnessioneSwitch;
import it.quasar_x7.gestione_rete.modello.Ufficio;
import it.quasar_x7.gestione_rete.modello.Utilizzatore;
import it.quasar_x7.gestione_rete.modello.Voce;
import it.quasar_x7.controllo.FinestraGestioneUtentiController;
import it.quasar_x7.java.BaseDati.EccezioneBaseDati;
import it.quasar_x7.java.utile.CellaPDF;
import it.quasar_x7.java.utile.DataOraria;
import it.quasar_x7.java.utile.FilePDF;
import it.quasar_x7.javafx.CampoTesto.Colore;
import it.quasar_x7.javafx.Finestra;
import it.quasar_x7.javafx.finestre.controllo.BarraProgressiController;
import it.quasar_x7.javafx.finestre.controllo.ListaController.Codice;
import it.quasar_x7.javafx.finestre.controllo.TabellaController;
import it.quasar_x7.javafx.finestre.modello.VoceListaColore;
import it.quasar_x7.javafx.finestre.modello.VoceSempliceLista;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

/**
 *
 * @author Dott. Domenico della PERUTA
 */
public class Programma extends Application {

    public static HashMap<String,DatiDB> dati = new HashMap<>();

    
    private static void inizializzaDati() {
        dati.put(DatiLogin.NOME_TABELLA, new DatiLogin());
        dati.put(DatiGrado.NOME_TABELLA, new DatiGrado());
        dati.put(DatiHardware.NOME_TABELLA, new DatiHardware());
        dati.put(DatiUtilizzatore.NOME_TABELLA, new DatiUtilizzatore());
        dati.put(DatiCasaHardware.NOME_TABELLA, new DatiCasaHardware());
        dati.put(DatiCasaSoftware.NOME_TABELLA, new DatiCasaSoftware());
        dati.put(DatiSoftware.NOME_TABELLA, new DatiSoftware());
        dati.put(DatiRuolo.NOME_TABELLA, new DatiRuolo());
        dati.put(DatiTipoHardware.NOME_TABELLA, new DatiTipoHardware());
        dati.put(DatiTipoSoftware.NOME_TABELLA, new DatiTipoSoftware());
        dati.put(DatiResponsabileSito.NOME_TABELLA, new DatiResponsabileSito());
        dati.put(DatiPosizione.NOME_TABELLA, new DatiPosizione());
        dati.put(DatiRete.NOME_TABELLA, new DatiRete());
        dati.put(DatiTipoRete.NOME_TABELLA, new DatiTipoRete());
        dati.put(DatiApparato.NOME_TABELLA, new DatiApparato());
        dati.put(DatiTipoApparato.NOME_TABELLA, new DatiTipoApparato());
        dati.put(DatiStato.NOME_TABELLA, new DatiStato());
        dati.put(DatiSwitch.NOME_TABELLA, new DatiSwitch());
        dati.put(DatiSoftwareApparato.NOME_TABELLA, new DatiSoftwareApparato());
        dati.put(DatiHardwareApparato.NOME_TABELLA, new DatiHardwareApparato());
        dati.put(DatiConnessioneSwitch.NOME_TABELLA, new DatiConnessioneSwitch());
        dati.put(DatiImpostazioni.NOME_TABELLA, new DatiImpostazioni());
        dati.put(DatiIntervento.NOME_TABELLA, new DatiIntervento());
    }

    

    
    
       
    
    @Override
    public void start(Stage stage) throws IOException {
        Locale.setDefault(Locale.ITALY);
        Finestra.vistaCorrente = stage;
        Finestra.vistaCorrente.setResizable(false);
        Finestra.vistaCorrente.initStyle(StageStyle.TRANSPARENT);
        Finestra.vistaCorrente.setOnCloseRequest(
                (WindowEvent t) -> {
                    Platform.exit();
                    System.exit(0);
                }
        );
        login();
    }
    
    
    
    /**
     * Metodo che permette il caricamento della finestra di login.
     * 
     * @throws IOException si verifica quando il file FXML (delle finestre Dialogo) 
     * non è caricato correttamente.
     */
    private void login() throws IOException{
       
        if(this.esisteDB()){
            if(testDB()){
                DatiLogin datiLogin = (DatiLogin) dati.get(DatiLogin.NOME_TABELLA);
                ArrayList<String> listaUtenti = datiLogin.tuttiUtenti();
                if(listaUtenti != null){
                    if(listaUtenti.size() > 0){
                        FinestraLoginController.listaMenu = listaUtenti;
                        Finestra.caricaFinestra(this, R.FXML.LOGIN);
                    }else{
                        it.quasar_x7.swing.Finestra.creaFinestra(
                                new it.quasar_x7.swing.vista.FinestraGestioneUtenti(datiLogin,true)
                        );
                    }
                }else{
                    try {
                        datiLogin.creaTabella();
                        it.quasar_x7.swing.Finestra.creaFinestra(
                                new it.quasar_x7.swing.vista.FinestraGestioneUtenti(datiLogin,true)
                        );
                    } catch (EccezioneBaseDati ex) {
                            Finestra.finestraDebug(this, ex);

                    }
                }
            }
        }else{
            Finestra.finestraConferma(
                    this, 
                    R.Domanda.CREAZIONE_DB, 
                    () -> {
                        creaDB();
                    }
            );
        }
    }
    
    private boolean esisteDB(){
        Path path = Paths.get(R.File.DB);
        return Files.exists(path);
    }
    /**
     * Crea eventuali tabelle inesistenti o danneggiate
     */
    private boolean testDB(){
        for(String chiave:dati.keySet()){
            if(!dati.get(chiave).esiste()){
                Finestra.finestraConferma(
                        this, 
                        String.format(
                                R.Domanda.CREAZIONE_TABELLA,
                                chiave
                        ),
                        () -> {
                            try {
                                dati.get(chiave).creaTabella();
                            } catch (EccezioneBaseDati ex) {
                                Logger.getLogger(Programma.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                );
                return false;
            }
        }
        return true;
    }
    
    public static void creaDB(){
        try {
            for(String chiave:dati.keySet()){
                dati.get(chiave).creaTabella();
            }
        } catch (EccezioneBaseDati ex) {
            Logger.getLogger(Programma.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FinestraGestioneUtentiController.utente = R.Utente.ANONIMO;
        inizializzaDati();
        launch(args);
    }
    
    
    
    /**
     * Apre la finestra a tabella per la gestione dei gradi/qualifica di un nodoUtilizzatore.
     * @param controller 
     */
    public static void listaGradi(Object controller) {
        
        DatiDB datiGrado = dati.get(DatiGrado.NOME_TABELLA);
        ArrayList<Integer> dim = new ArrayList<>();
        dim.add(390);
        dim.add(250);
        dim.add(190);
        dim.add(70);
        
        Finestra.finestraTabella(
                controller, 
                R.Etichette.FINESTRA_LISTA_GRADO, 
                datiGrado.attributi(), 
                dim, 
                datiGrado.tabella(), 
                false, 
                new TabellaController.Codice(){
                    @Override
                    public void aggiungi(TabellaController delega) {
                        FinestraGradoController.tabella = delega;
                        Finestra.caricaFinestra(this, R.FXML.FINESTRA_GRADO);
                        
                    }

                    @Override
                    public boolean elimina(TabellaController delega, String primaCella) {
                        String[] riga = delega.rigaSelezionata();
                        if(riga != null){
                            if(riga[0] != null){
                                if( !datiGrado.elimina(new Object[]{riga[0]}) ){
                                    Finestra.finestraAvviso(this, String.format(R.Messaggi.ERRORE_ELIMINAZIONE_RIGA,riga[0]));
                                    return false;
                                }
                                return true;
                            }
                        }
                        return false;
                    }

                    @Override
                    public void modifica(TabellaController delega, String primaCella) {
                        FinestraGradoController.input = delega.rigaSelezionata();
                       
                        FinestraGradoController.tabella = delega;
                        Finestra.caricaFinestra(this, R.FXML.FINESTRA_GRADO);
                    }
                   
                }
        );
        
    }
    
      
    public static void apriListaSoftware(Object controller) {
        
        DatiDB datiSoftware = dati.get(DatiSoftware.NOME_TABELLA);
        ArrayList<Integer> dim = new ArrayList<>();
        dim.add(500);// nome + licenza
        dim.add(150);// tipo
        dim.add(150);// casa
        dim.add(400);// note
        dim.add(100);// predefinita
        
        ArrayList<String> colonne = new ArrayList<>();
        colonne.add(R.Etichette.NOME_VERSIONE_LICENZA);
        colonne.add(R.Etichette.TIPO);
        colonne.add(R.Etichette.CASA);
        colonne.add(R.Etichette.NOTE);
        colonne.add(R.Etichette.PREDEFINITO);
        
        Finestra.finestraTabella(
                controller, 
                R.Etichette.FINESTRA_SW, 
                colonne, 
                dim, 
                datiSoftware.tabella(), 
                false, 
                new TabellaController.Codice(){
                    @Override
                    public void aggiungi(TabellaController delega) {
                        FinestraSoftwareController.tabella = delega;
                        Finestra.caricaFinestra(this, R.FXML.FINESTRA_SW);
                    }

                    @Override
                    public boolean elimina(TabellaController delega, String primaCella) {
                        return eliminaRiga(datiSoftware,this,delega);
                    }

                    @Override
                    public void modifica(TabellaController delega, String primaCella) {
                        String[] inputTab = delega.rigaSelezionata();
                        if(inputTab != null){
                            String[] nomeLicenza = DatiSoftware.scomponiNomeLicenza(inputTab[0]);
                            String nome = nomeLicenza[0];
                            String licenza = nomeLicenza.length == 2 ? nomeLicenza[1] : "";
                            FinestraSoftwareController.input = new String[]{
                                nome,
                                inputTab[1],
                                inputTab[2],
                                licenza,
                                inputTab[3],
                                inputTab[4]
                            };
                    }
                       
                        FinestraSoftwareController.tabella = delega;
                        Finestra.caricaFinestra(this, R.FXML.FINESTRA_SW);
                    }

                }
        );
    }
    
    
    public static void apriListaApparati(Object controller) {
        
        DatiDB datiApparati = dati.get(DatiApparato.NOME_TABELLA);
        ArrayList<Integer> dim = new ArrayList<>();
        dim.add(100);// nome
        dim.add(100);// tipo
        dim.add(100);// rete
        dim.add(100);// ip
        dim.add(150);// mac
        dim.add(150);// mac voip
        dim.add(100);// posizione
        dim.add(250);// utilizzatore
        dim.add(70);// internet
        dim.add(70);// sigillo
        dim.add(100);// pasword
        dim.add(70);// stato
        
        
        ArrayList<String> colonne = new ArrayList<>();
        colonne.add(R.Etichette.APPARATO);
        colonne.add(R.Etichette.TIPO);
        colonne.add(R.Etichette.DOMINIO);
        colonne.add(R.Etichette.IP);
        colonne.add(R.Etichette.MAC_PC);
        colonne.add(R.Etichette.MAC_VOIP);
        colonne.add(R.Etichette.POSIZIONE);
        colonne.add(R.Etichette.UTILIZZATORE);
        colonne.add(R.Etichette.INTERNET);
        colonne.add(R.Etichette.SIGILLO);
        colonne.add(R.Etichette.PASSWORD);
        colonne.add(R.Etichette.STATO);
        
        Finestra.finestraTabella(
                controller, 
                R.Etichette.FINESTRA_LISTA_APPARATI, 
                colonne, 
                dim, 
                datiApparati.tabella(), 
                false, 
                new TabellaController.Codice(){
                    @Override
                    public void aggiungi(TabellaController delega) {
                        FinestraApparatoController.tabella = delega;
                        Finestra.caricaFinestra(this, R.FXML.FINESTRA_APPARATO);
                    }

                    @Override
                    public boolean elimina(TabellaController delega, String primaCella) {
                        return eliminaRiga(datiApparati,this,delega);
                    }

                    @Override
                    public void modifica(TabellaController delega, String primaCella) {
                        String[] inputTab = delega.rigaSelezionata();
                        if(inputTab != null){
                            FinestraApparatoController.input = inputTab;
                        }
                       
                        FinestraApparatoController.tabella = delega;
                        Finestra.caricaFinestra(this, R.FXML.FINESTRA_APPARATO);
                    }

                }
        );
    }

    
    public static void apriListaHardware(Object controller) {
        
        DatiDB datiHardware = dati.get(DatiHardware.NOME_TABELLA);
        ArrayList<Integer> dim = new ArrayList<>();
        dim.add(550);// marca matricola
        dim.add(150);// casa
        dim.add(100); // NUC
        dim.add(90);// stato
        dim.add(400);// note
        
        ArrayList<String> colonne = new ArrayList<>();
        colonne.add(R.Etichette.MARCA_MATRICOLA);
        colonne.add(R.Etichette.CASA);
        colonne.add(R.Etichette.NUC);
        colonne.add(R.Etichette.STATO);
        colonne.add(R.Etichette.NOTE);
        
        Finestra.finestraTabella(
                controller, 
                R.Etichette.FINESTRA_HW, 
                colonne, 
                dim, 
                datiHardware.tabella(), 
                false, 
                new TabellaController.Codice(){
                    @Override
                    public void aggiungi(TabellaController delega) {
                        FinestraHardwareController.tabella = delega;
                        Finestra.caricaFinestra(this, R.FXML.FINESTRA_HW);
                    }

                    @Override
                    public boolean elimina(TabellaController delega, String primaCella) {
                        return eliminaRiga(datiHardware,this,delega);
                    }

                    @Override
                    public void modifica(TabellaController delega, String primaCella) {
                        FinestraHardwareController.input = delega.rigaSelezionata();
                       
                        FinestraHardwareController.tabella = delega;
                        Finestra.caricaFinestra(this, R.FXML.FINESTRA_HW);
                    }

                }
        );
    }
    
    
    
    public static void apriListaReti(Object controller) {
        
        DatiDB datiRete = dati.get(DatiRete.NOME_TABELLA);
        ArrayList<Integer> dim = new ArrayList<>();
        dim.add(200);// workgroup
        dim.add(320);// dominio
        dim.add(130);// tipo
        dim.add(120); // gateway
        dim.add(120); // netmask
        
       ArrayList<String> colonne = new ArrayList<>();
        colonne.add(R.Etichette.WORKGROUP);
        colonne.add(R.Etichette.DOMINIO);
        colonne.add(R.Etichette.TIPO);
        colonne.add(R.Etichette.GATEWAY);
        colonne.add(R.Etichette.NETMASK);
        
        Finestra.finestraTabella(
                controller, 
                R.Etichette.FINESTRA_RETE, 
                colonne, 
                dim, 
                datiRete.tabella(), 
                false, 
                new TabellaController.Codice(){
                    @Override
                    public void aggiungi(TabellaController delega) {
                        FinestraReteController.tabella = delega;
                        Finestra.caricaFinestra(this, R.FXML.FINESTRA_RETE);
                    }

                    @Override
                    public boolean elimina(TabellaController delega, String primaCella) {
                        return eliminaRiga(datiRete,this,delega);
                    }

                    @Override
                    public void modifica(TabellaController delega, String primaCella) {
                        FinestraReteController.input = delega.rigaSelezionata();
                       
                        FinestraReteController.tabella = delega;
                        Finestra.caricaFinestra(this, R.FXML.FINESTRA_RETE);
                    }

                }
        );
    }
    
    public static void apriListaResponsabile(Object controller) {
        DatiResponsabileSito datiResponsabileSito = (DatiResponsabileSito)dati.get(DatiResponsabileSito.NOME_TABELLA);
        ArrayList<Integer> dim = new ArrayList<>();
        dim.add(500);
        dim.add(400);
        ArrayList<String> colonne = new ArrayList<>();
        colonne.add(R.Etichette.RESPONSABILE);
        colonne.add(R.Etichette.NOMINATIVO);
        
        Finestra.finestraTabella(
                controller, 
                R.Etichette.FINESTRA_RESPONSABILE, 
                colonne, 
                dim, 
                datiResponsabileSito.tabella(), 
                false, 
                new TabellaController.Codice(){
                    @Override
                    public void aggiungi(TabellaController delega) {
                        FinestraResponsabileController.tabella = delega;
                        Finestra.caricaFinestra(this, R.FXML.FINESTRA_RESPONSABILE);
                        
                    }

                    @Override
                    public boolean elimina(TabellaController delega, String primaCella) {
                        return eliminaRiga(datiResponsabileSito,this,delega);
                    }

                    @Override
                    public void modifica(TabellaController delega, String primaCella) {
                        FinestraResponsabileController.input = delega.rigaSelezionata();
                       
                        FinestraResponsabileController.tabella = delega;
                        Finestra.caricaFinestra(this, R.FXML.FINESTRA_RESPONSABILE);
                    }
                  
                }
        );
         
    }
    
    

    
    public static void apriListaPosizione(Object controller) {
        DatiPosizione datiPosizione = (DatiPosizione)dati.get(DatiPosizione.NOME_TABELLA);
        ArrayList<Integer> dim = new ArrayList<>();
        dim.add(500);
        dim.add(400);
        ArrayList<String> colonne = new ArrayList<>();
        colonne.add(R.Etichette.POSIZIONE);
        colonne.add(R.Etichette.RESPONSABILE);
        
        Finestra.finestraTabella(
                controller, 
                R.Etichette.FINESTRA_POSIZIONE, 
                colonne, 
                dim, 
                datiPosizione.tabella(), 
                false, 
                new TabellaController.Codice(){
                    @Override
                    public void aggiungi(TabellaController delega) {
                        FinestraPosizioneController.tabella = delega;
                        Finestra.caricaFinestra(this, R.FXML.FINESTRA_POSIZIONE);
                    }

                    @Override
                    public boolean elimina(TabellaController delega, String primaCella) {
                        return eliminaRiga(datiPosizione,this,delega);
                    }

                    @Override
                    public void modifica(TabellaController delega, String primaCella) {
                        FinestraPosizioneController.input = delega.rigaSelezionata();
                       
                        FinestraPosizioneController.tabella = delega;
                        Finestra.caricaFinestra(this, R.FXML.FINESTRA_POSIZIONE);
                    }
                  
                }
        );
        
    }
    
    public static void apriListaConnessioniSwitch(Object controller) {
        DatiConnessioneSwitch datiSwitch = (DatiConnessioneSwitch)dati.get(DatiConnessioneSwitch.NOME_TABELLA);
        ArrayList<Integer> dim = new ArrayList<>();
        dim.add(300);
        dim.add(300);
        dim.add(100);
        ArrayList<String> colonne = new ArrayList<>();
        colonne.add(R.Etichette.APPARATO);
        colonne.add(R.Etichette.SWITCH);
        colonne.add(R.Etichette.PORTA);
        
        Finestra.finestraTabella(
                controller, 
                R.Etichette.FINESTRA_SWITCH, 
                colonne, 
                dim, 
                datiSwitch.tabella(), 
                false, 
                new TabellaController.Codice(){
                    @Override
                    public void aggiungi(TabellaController delega) {
                        FinestraSwitchController.tabella = delega;
                        Finestra.caricaFinestra(this, R.FXML.FINESTRA_SWITCH);
                    }

                    @Override
                    public boolean elimina(TabellaController delega, String primaCella) {
                        return eliminaRiga(datiSwitch,this,delega);
                    }

                    @Override
                    public void modifica(TabellaController delega, String primaCella) {
                        FinestraSwitchController.input = delega.rigaSelezionata();
                       
                        FinestraSwitchController.tabella = delega;
                        Finestra.caricaFinestra(this, R.FXML.FINESTRA_SWITCH);
                    }
                  
                }
        );
        
    }
    
    public static void apriListaInterventi(Object controller) {
    	
    	DatiIntervento datiIntervento = (DatiIntervento)dati.get(DatiIntervento.NOME_TABELLA);
    	
    	ArrayList<Integer> dim = new ArrayList<>();
        dim.add(200);
        dim.add(100);
        dim.add(150);
        dim.add(300);
        dim.add(300);
        dim.add(150);
        
        ArrayList<String> colonne = new ArrayList<>();
        colonne.add(R.Etichette.TICKET);
        colonne.add(R.Etichette.DATA);
        colonne.add(R.Etichette.APPARATO);
        colonne.add(R.Etichette.RICHIESTA);
        colonne.add(R.Etichette.INTERVENTO);
        colonne.add(R.Etichette.ESITO);
        
        Finestra.finestraTabella(
                controller, 
                R.Etichette.FINESTRA_INTERVENTO, 
                colonne, 
                dim, 
                datiIntervento.tabella(), 
                false, 
                new TabellaController.Codice(){
                    @Override
                    public void aggiungi(TabellaController delega) {
                        FinestraInterventoController.tabella = delega;
                        Finestra.caricaFinestra(this, R.FXML.FINESTRA_INTERVENTO);
                        
                    }

                    @Override
                    public boolean elimina(TabellaController delega, String primaCella) {
                        return eliminaRiga(datiIntervento,this,delega);
                    }

                    @Override
                    public void modifica(TabellaController delega, String primaCella) {
                        FinestraInterventoController.input = delega.rigaSelezionata();
                        aggiungi(delega);
                    }
                
                }
        );
	}
    
    public static void apriListaUtilizzatori(Object controller) {
       
        DatiUtilizzatore datiUtilizzatore = (DatiUtilizzatore) dati.get(DatiUtilizzatore.NOME_TABELLA);
        
        ArrayList<Integer> dim = new ArrayList<>();
        dim.add(200);
        dim.add(400);
        dim.add(300);
        ArrayList<String> colonne = new ArrayList<>();
        colonne.add(R.Etichette.ACCOUNT);
        colonne.add(R.Etichette.NOMINATIVO);
        colonne.add(R.Etichette.MAIL);
  
        Finestra.finestraTabella(
                controller, 
                R.Etichette.FINESTRA_UTILIZZATORI, 
                colonne, 
                dim, 
                datiUtilizzatore.tabella(), 
                false, 
                new TabellaController.Codice(){
                    @Override
                    public void aggiungi(TabellaController delega) {
                        FinestraUtilizzatoreController.tabella = delega;
                        Finestra.caricaFinestra(this, R.FXML.FINESTRA_UTILIZZATORE);
                        
                    }

                    @Override
                    public boolean elimina(TabellaController delega, String primaCella) {
                        return eliminaRiga(datiUtilizzatore,this,delega);
                    }

                    @Override
                    public void modifica(TabellaController delega, String primaCella) {
                        FinestraUtilizzatoreController.input = delega.rigaSelezionata();
                       
                        FinestraUtilizzatoreController.tabella = delega;
                        Finestra.caricaFinestra(this, R.FXML.FINESTRA_UTILIZZATORE);
                    }
                
                }
        );
    }
    
    
    static private boolean eliminaRiga(DatiDB db,Object controller,TabellaController delega){
        int riga = delega.indiceRigaSelezionata();
        if(riga >= 0){
            if( !db.elimina(riga) ){
                Finestra.finestraAvviso(
                        controller, 
                        String.format(
                                R.Messaggi.ERRORE_ELIMINAZIONE_RIGA,
                                delega.rigaSelezionata()[0]
                        )
                );
                return false;
            }
            return true;
        }
        return false;
    }
    
    /**
     * Apre una semplice finestra con una semplice lista, usata per la gestione
     * dell'input dei campi a menu.
     * 
     * @param controller
     * @param db
     * @param titolo
     * @param domandaAggiungiInput
     * @param domandaModificaInput 
     */
    public static void apriSempliceLista(Object controller,DatiLista db, String titolo,String domandaAggiungiInput,String domandaModificaInput){
         
        if(db.lista() == null){
            try {
                db.creaTabella();
            } catch (EccezioneBaseDati ex) {
                Finestra.finestraDebug(controller, ex);
            }
        }

        Finestra.finestraListaSempice(
                controller,
                titolo,
                db.lista(),
                new Codice() {
                    @Override
                    public void aggiungiRiga(ObservableList<VoceSempliceLista> lista) {
                        Finestra.finestraInput(
                                this,
                                domandaAggiungiInput,
                                db.lista(),
                                (String risposta) -> {
                                    if(db.aggiungi(new Object[]{risposta})){
                                        lista.add(new VoceSempliceLista(risposta));
                                        return true;
                                    }
                                    return false;
                                }
                        );
                    }

                    @Override
                    public boolean eliminaRiga(String riga) {
                        return db.elimina(new Object[]{riga});
                    }

                    @Override
                    public void modifica(String riga,int id, ObservableList<VoceSempliceLista> lista) {

                        Finestra.finestraInput(
                                this,
                                String.format(domandaModificaInput,riga),
                                riga,
                                (String risposta) -> {
                                    if(db.elimina(new Object[]{riga})){
                                        lista.remove(id);
                                        if(db.aggiungi(new Object[]{risposta})){
                                            lista.add(new VoceSempliceLista(risposta));
                                            return true;
                                        }
                                    }
                                    return false;
                                }
                        );

                    }
                }
        );
        
    }
    
    
    
    public static void apriListaStato(Object controller,Dati db,ArrayList<Object[]> lista, String titolo,String domandaAggiungiInput,String domandaModificaInput){
         
        if(lista == null){
            try {
                db.creaTabella();
            } catch (EccezioneBaseDati ex) {
                Finestra.finestraDebug(controller, ex);
            }
        }

        Finestra.finestraListaColorata(
                controller,
                titolo,
                lista,
                new Codice() {
                    @Override
                    public void aggiungiRiga(ObservableList<VoceSempliceLista> lista) {
                        Finestra.finestraInputColore(
                                this,
                                domandaAggiungiInput,
                                (String risposta) -> {
                                    VoceListaColore voce = new VoceListaColore(risposta);
                                    if(db.aggiungi(new Object[]{voce.getVoce(),VoceListaColore.coloreWeb(voce.getColore())})){
                                        if(lista != null){
                                            lista.add(voce);
                                            return true;
                                        }
                                    }
                                    return false;
                                }
                        );
                    }

                    @Override
                    public boolean eliminaRiga(String riga) {
                        String stato = (String)VoceListaColore.estraiInfo(riga)[1];
                        return db.elimina(new Object[]{stato});
                    }

                    @Override
                    public void modifica(String riga,int id, ObservableList<VoceSempliceLista> lista) {
                        String stato = (String)VoceListaColore.estraiInfo(riga)[1];
                        Finestra.finestraInputColore(
                                this,
                                String.format(domandaModificaInput,stato),
                                riga, // contiene i valori iniziali di input (colore e stringa stato)
                                (String risposta) -> {
                                    VoceListaColore voce = new VoceListaColore(risposta);
                                    if(db.elimina(new Object[]{stato})){
                                        lista.remove(id);
                                        if(db.aggiungi(new Object[]{voce.getVoce(),VoceListaColore.coloreWeb(voce.getColore())})){
                                            lista.add(voce);
                                            return true;
                                        }
                                    }
                                    return false;
                                }
                        );

                    }
                }
        );
    }

    
    /**
     * Chiusura standard delle finestre.
     * 
     * @param controller
     * @param scenaCorrente 
     */
    static public void chiusuraFinestra(Object controller){
        Finestra.ricaricaFinestra(controller);
    }
    
    /**
     * Copia un node di un elenco ad albero
     * @param nodo
     * @return 
     */
    public static TreeItem<Nodo> copia(TreeItem<Nodo> nodo){
        if(nodo != null){
            TreeItem<Nodo> copia;
            ImageView immagine = (ImageView)nodo.getGraphic();
            if(immagine != null){
                Image icona = immagine.getImage();
                copia= new TreeItem<>(nodo.getValue(),new ImageView(icona));
            }else{
                copia= new TreeItem<>(nodo.getValue()); 
            }
            //copia.setExpanded(true);
            ObservableList<TreeItem<Nodo>> lista = nodo.getChildren();
            if(lista != null){
                for(TreeItem<Nodo> sottonodo:lista){
                    copia.getChildren().add(copia(sottonodo));//ricorsivo
                }
            }
            return copia;
        }
        return null;
    }
    /**
     * 
     * @param rete
     * @param valore
     * @return 
     */
    public static TreeItem<Nodo> cercaListaApparato(TreeItem<Nodo> rete, String valore,String tipoRicerca){
        TreeItem<Nodo> risultato = new TreeItem<>(new Nodo(String.format("Ricerca %s: «%s»",tipoRicerca, valore)));
        
        for(TreeItem<Nodo> dominio : rete.getChildren()){
            for(TreeItem<Nodo> responsabile : dominio.getChildren()){
            	if(tipoRicerca.equals(R.TipoRicerca.RESPONSABILE)){
            		if(responsabile.getValue() instanceof Responsabile) {
                        Responsabile capo = (Responsabile) responsabile.getValue();
                        if(capo.getNome().toLowerCase().equals(valore.toLowerCase()))
                        	risultato.getChildren().add(Programma.copia(responsabile));
            		}
            	}else {
	                for(TreeItem<Nodo> posizione : responsabile.getChildren()){
	                    for(TreeItem<Nodo> host : posizione.getChildren()){
	                        if(host.getValue() instanceof Apparato){
	                            Apparato apparato = (Apparato) host.getValue();
	                            
	                            if(tipoRicerca.equals(R.TipoRicerca.NOMINATIVO)){
	                                String nominativo = ((DatiUtilizzatore)(dati.get(DatiUtilizzatore.NOME_TABELLA))).trovaNominativo( apparato.getUtente());
	                                if(nominativo.toLowerCase().contains(valore.toLowerCase()))
	                                    risultato.getChildren().add(Programma.copia(host));
	                                
	                            }else if(tipoRicerca.equals(R.TipoRicerca.ACCOUNT)){
	                                if(apparato.getUtente().equals(valore))
	                                    risultato.getChildren().add(Programma.copia(host));
	                                
	                            }else if(tipoRicerca.equals(R.TipoRicerca.IP)){
	                                if(apparato.getIp().equals(valore))
	                                    risultato.getChildren().add(Programma.copia(host));
	                                
	                            }else if(tipoRicerca.equals(R.TipoRicerca.APPARATO)){
	                                if(apparato.getNome().equals(valore))
	                                    risultato.getChildren().add(Programma.copia(host));
	                                
	                            }else if(tipoRicerca.equals(R.TipoRicerca.POSIZIONE)){
	                                if(apparato.getPosizione().equals(valore))
	                                    risultato.getChildren().add(Programma.copia(host));
	                                
	                            }else if(tipoRicerca.equals(R.TipoRicerca.MAC)){
	                                if(apparato.getMacPC().equals(valore) || apparato.getMacVOIP().equals(valore))
	                                    risultato.getChildren().add(Programma.copia(host));
	                            
	                            }else if(tipoRicerca.equals(R.TipoRicerca.SW)){
	                            	for(TreeItem<Nodo> nodo : host.getChildren()){
	                            		if(nodo.getValue() instanceof SoftwareApparato) {
	                            			for(TreeItem<Nodo> sw:nodo.getChildren()) {
	                            				if(sw.getValue() instanceof Software) {
	                            					String nome = ((Software)sw.getValue()).getNome().toLowerCase();
	                            					if(nome.equals(valore.toLowerCase())) {
	                            						risultato.getChildren().add(Programma.copia(host));
	                            						break;
	                            					}
	                            				}
	                            			}
	                            		}
	                            	}
	                            
	                            }else if(tipoRicerca.equals(R.TipoRicerca.HW)){
	                            	for(TreeItem<Nodo> nodo : host.getChildren()){
	                            		if(nodo.getValue() instanceof HardwareApparato) {
	                            			for(TreeItem<Nodo> hw: nodo.getChildren()) {
	                            				if(hw.getValue() instanceof Hardware) {
	                            					String modello = ((Hardware)hw.getValue()).getModello().toLowerCase();
	                            					if(modello.equals(valore.toLowerCase())) {
	                            						risultato.getChildren().add(Programma.copia(host));
	                            						break;
	                            					}
	                            				}
	                            			}
	                            		}
	                            	}
	                            }
	                        }
	                    }
	                }
            	}
            }
        }
        return risultato;
    }
    
    /**
     * Crea ed inizializza la lista ad albero degli apparati.
     * 
     * @param rete
     * @param lista 
     */
    public static void creaListaApparato(TreeItem<Nodo> rete, HashMap<String,HashMap<String, HashMap<String, ArrayList<Apparato>>>> lista) {
        if(rete != null){
            rete.getChildren().clear();
            for(String nomeDominio : lista.keySet()){
                HashMap<String,HashMap<String, ArrayList<Apparato>>> dominio = lista.get(nomeDominio);
                
                Node immagineRete = new ImageView(new Image(R.Icona.RETE,80,80,true,true));
                DatiRete datiRete = (DatiRete)dati.get(DatiRete.NOME_TABELLA);
                TreeItem<Nodo> gruppoDominio = new TreeItem<>(datiRete.info(nomeDominio),immagineRete);
                rete.getChildren().add(gruppoDominio);
                gruppoDominio.setExpanded(true);
                
                if(dominio != null){
                    for(String nomeResponsabile : dominio.keySet()){
                        HashMap<String, ArrayList<Apparato>> responsabile = dominio.get(nomeResponsabile);
                        Node immagineResponsabile = new ImageView(new Image(R.Icona.UFFICIO,80,80,true,true));
                        DatiResponsabileSito datiResponsabile = (DatiResponsabileSito)dati.get(DatiResponsabileSito.NOME_TABELLA);
                        String nominativo = datiResponsabile.nominativoResponsabileSito(nomeResponsabile);
                        TreeItem<Nodo> nodoResponsabile = new TreeItem<>(new Responsabile(nomeResponsabile,nominativo),immagineResponsabile);
                        gruppoDominio.getChildren().add(nodoResponsabile);
                        nodoResponsabile.setExpanded(true);
                        
                        for(String nomeUfficio : responsabile.keySet()){
                            ArrayList<Apparato> ufficio = responsabile.get(nomeUfficio);
                            Node immaginePosizione = new ImageView(new Image(R.Icona.POSIZIONE,20,20,true,true));
                            DatiPosizione datiPosizione = (DatiPosizione)dati.get(DatiPosizione.NOME_TABELLA);
                            TreeItem<Nodo> gruppoUfficio = new TreeItem<>(datiPosizione.info(nomeUfficio),immaginePosizione);
                            nodoResponsabile.getChildren().add(gruppoUfficio);
                            gruppoUfficio.setExpanded(true);
                            if(ufficio != null){
                                for(Apparato apparato : ufficio){
                                    TreeItem<Nodo> pc = nodoApparato(apparato);
                                    gruppoUfficio.getChildren().add(pc);
                                }
                            }
                        }
                    }
                }
            
            }
            
        }
    }


    
    /**
     * Metodo statico di salvattaggio per le finestre di input legate a finestre a tabella.
     * Esempio:
     * 
     * <pre>
     * <code>
     * ....
     *  @FXML
     *  protected void salva(ActionEvent event) {
     *  .......
     *  
     *      Object[] record = new Object[]{
     *          nome.getText(),
     *          ruolo.getValue(),
     *          sigla.getText(),
     *          ....
     *      };
     *      Programma.salva(
     *              this, 
     *              !nome.getText().isEmpty(), 
     *              datiGrado, 
     *              input, 
     *              record, 
     *              event, 
     *              // metodo di creazione della finestra a tabella
     *              (ActionEvent evento, String chiave) -> {
     *                  if(tabella != null){
     *                      if(chiave != null)
     *                          tabella.modificaRiga(chiave,converti(record));
     *                      else
     *                          tabella.aggiungiRiga(converti(record));
     *                  }
     *                  chiusuraSenzaSalvare(evento);
     *              }
     *      );
     *  }
     * </code>
     * </pre>
     * 
     * @param controller
     * @param condizione
     * @param db
     * @param vecchiValori       Vecchio record dati. 
     * 							 NB.: l'ordine dei valori deve corrispondere all'ordine 'nuoviValori'
     * @param nuoviValori
     * @param event
     * @param visualizzaTabella  Metodo che gestisce la chiusura della finestra e il caricamento della
     *                           finestra a tabella. Ha due argomenti: l'evento e la stringa chiave della tabella.
     */
    public static void salva(Object controller, boolean condizione, DatiDB db, String[] vecchiValori,Object[] nuoviValori, ActionEvent event, BiConsumer<ActionEvent, String> visualizzaTabella) {
        Finestra.finestraBarraProgressi(
                controller, 
                new BarraProgressiController.Codice() {
                    boolean modifica = false;
                    boolean aggiungi = false;
                    boolean erroreAggiunta = false;
                    boolean erroreModifica = false;        
                    boolean erroreCampi = false;        
                    
                    @Override
                    public Task<Object> creaTask() {

                        return new Task<Object>() {
                            
                            String info = "";
                            final long maxEventi = 10;
                            final int tempo = 50;

                            private void stampa(long indice,String rigaInfo,boolean aggiorna){
                                updateProgress(indice,maxEventi);
                                updateMessage(rigaInfo+info);
                                try {
                                    Thread.sleep(tempo);
                                } catch (InterruptedException ex) {
                                }
                                if(aggiorna)
                                    info = rigaInfo+info;
                            }
                            
                            @Override
                            protected Object call() {
                                modifica = aggiungi = false;
                                erroreCampi = erroreAggiunta = erroreModifica = false;    
                    
                                stampa(0, "[0] inizio...", true);
                                stampa(1, "[1] condizione....\n", false);
                                if(condizione){
                                    stampa(2, "[1] condizione\tOK!\n", true);
                                    stampa(3, "[2] modalità: ...\n", false);
                                
                                    if(vecchiValori != null){ // modalità modifica
                                        stampa(4, "[2] modalità:\t MODIFICA\n", true);
                                        Object[] chiave = DatiDB.chiave(db, vecchiValori);
                                        stampa(5, "[3] modifica ...\n", false);
                                        if(db.modifica(chiave,nuoviValori)){
                                            modifica = true;
                                            stampa(6, "[3] modifica\t OK!\n", true);
                                        }else{
                                            // le modifiche non sono statte effettuate perché ....
                                            
                                            // controllo esistenza di un record simile
                                                // esiste un valore già presente 
                                            if(db.elimina(chiave)){
                                                stampa(7, "[3a] eliminazione valore già esistente!\n", true);
                                            }
                                            // controllo ed elimina eventuali record simili
                                            if(db.elimina(DatiDB.chiave(db, nuoviValori))){ // elimina eventuali valori simili 
                                                stampa(8, "[3b] eliminazione valore simile già esistente!\n", true);
                                            }
                                            
                                            if(db.aggiungi(nuoviValori)){
                                                //visualizzaTabella.accept(event, DatiDB.stampaChiave(db,vecchiValori));
                                                stampa(9, "[3c] aggiunta record modificato: OK!\n", true);
                                                modifica = true;
                                            }else{
                                                stampa(9, "[3c] modificato: ERRORE!\n", true);
                                                erroreModifica = true; 
                                            }
                                        }
                                        
                                    }else{ // modalità aggiunta
                                        stampa(9, "[2] modalità:\t AGGIUNGA\n", true);
                                        if(!db.aggiungi(nuoviValori)){
                                            stampa(10, "[3] aggiunci nuovo record: ERRORE!\n", true);
                                            erroreAggiunta = true;
                                    
                                            
                                        } else{
                                            stampa(10, "[3] aggiunci nuovo record: OK!\n", true);
                                            aggiungi= true;
                                        }
                                    }
                                }else{
                                    stampa(10, "[1] condizione\tERRORE!\n", true);
                                    erroreCampi = true; 

                                } 
                                stampa(11, "[10] fine...\n", true);
                                   
                                return true;
                            }
                        };
                    }

                    @Override
                    public void esci() {
                        if(modifica){
                            visualizzaTabella.accept(event, DatiDB.stampaChiave(db,vecchiValori));
                            Programma.aggiornaListeNodi();
                        }else if(aggiungi){
                            visualizzaTabella.accept(event,null);
                            Programma.aggiornaListeNodi();
                        }else{
                            Finestra.finestraAvviso(controller, String.format(R.Messaggi.ERRORE_SALVATAGGIO,DatiDB.stampaChiave(db, vecchiValori),DatiDB.stampa(nuoviValori))); 
                                                
                        }
                        if(erroreModifica)
                            Finestra.finestraAvviso(controller, String.format(R.Messaggi.ERRORE_MODIFICHE,DatiDB.stampaChiave(db, vecchiValori),DatiDB.stampa(nuoviValori))); 
                        else if(erroreAggiunta)
                            Finestra.finestraAvviso(
                                    controller,
                                    String.format(R.Messaggi.ERRORE_SALVATAGGIO,
                                            DatiDB.stampaChiave(db, nuoviValori),
                                            DatiDB.stampa(nuoviValori)
                                    )
                            );
                        else if(erroreCampi)
                            Finestra.finestraAvviso(controller,R.Messaggi.ERRORE_CAMPI_FONDAMENTALI);
                                               
                        
                    }
                }
        );
        
    }

    
    public static void aggiornaListeNodi(){
        DatiApparato datiApparato = (DatiApparato) dati.get(DatiApparato.NOME_TABELLA);
        Programma.creaListaApparato(rete,datiApparato.listaApparati());
        Programma.creaListaSwitch(reteSwitch);
        Programma.crealistaHelpDesk(reteInterventi);
    }
    
    /**
     * Metodo statico che sostituisce determinati valori (modificati) di una colonna della tabella Apparato.
     * 
     * @param controller
     * @param colonnaDB
     * @param input
     * @param indice
     * @param campo
     * @return
     */
    public static boolean salvaModificheApparato(Object controller,String colonnaDB, String[] input, int indice, TextField campo){
        return Programma.salvaModificheTabella(controller, DatiApparato.NOME_TABELLA, colonnaDB, input, indice, campo, R.Messaggi.IMMOSSIBILE_AGGIORNARE_APPARATI);    
    }
    /**
     * Metodo statico che sostituisce determinati valori (modificati) di una colonna della tabella Posizione.
     * 
     * @param controller
     * @param colonnaDB
     * @param input
     * @param indice
     * @param campo
     * @return
     */
    public static boolean salvaModifichePosizione(Object controller,String colonnaDB, String[] input, int indice, TextField campo){
        return Programma.salvaModificheTabella(controller, DatiPosizione.NOME_TABELLA, colonnaDB, input, indice, campo, R.Messaggi.IMMOSSIBILE_AGGIORNARE_POSIZIONE);    
    }
    
    private static boolean salvaModificheTabella(Object controller,String tabellaDB, String colonnaDB, String[] input, int indice, TextField campo,String messaggio){
        boolean procedi = true;
        if(input != null)
            if(input[indice] != null)
                if(! input[indice].equals(campo.getText())){
                    procedi = dati.get(tabellaDB).aggiorna(colonnaDB,input[indice], campo.getText());
                }
        if(procedi)
            return true;
        else{
            Finestra.finestraAvviso(controller, 
                String.format(messaggio,input[indice],campo.getText())
            );
            return false;
        }
    }
    
    
    public static void creaSchedaApparatoPDF(String nomeFile,String apparato) {
    	
    	Apparato datiApparato = ((DatiApparato)dati.get(DatiApparato.NOME_TABELLA)).info(apparato);
    	
    	if(datiApparato == null)
    		return ; // chiudi 
    	
    	DatiImpostazioni datiImpostazioni  = ((DatiImpostazioni)dati.get(DatiImpostazioni.NOME_TABELLA));
		
    	final String BARRA = "______________________________________________________________________________";
    	FilePDF file = new FilePDF(nomeFile);
    	file.aggiungi(datiImpostazioni.valore(DatiImpostazioni.INTESTAZIONE_ENTE),FilePDF.TIMES,18,FilePDF.GROSSETTO,FilePDF.ALLINEAMENTO_CENTRO,FilePDF.NERO);
    	file.aggiungi(datiImpostazioni.valore(DatiImpostazioni.INTESTAZIONE_UFFICIO),FilePDF.TIMES,12,FilePDF.CORSIVO,FilePDF.ALLINEAMENTO_CENTRO,FilePDF.NERO);
    	
    	file.aggiungi(String.format("Caserta, %s", DataOraria.creaDataOggi().stampaGiorno('/')),FilePDF.TIMES_CORSIVO,12,FilePDF.CORSIVO,FilePDF.ALLINEAMENTO_DESTRA,FilePDF.NERO);
    	
    	file.aggiungi(String.format("\nSCHEDA APPARATO N° %d",888),FilePDF.HELVETICA_GROSSETTO,12,FilePDF.CORSIVO,FilePDF.ALLINEAMENTO_SINISTRA,FilePDF.NERO);
    	file.aggiungi(String.format("SIGILLO N° %d\n",datiApparato.getSigillo()),FilePDF.HELVETICA,12,FilePDF.CORSIVO,FilePDF.ALLINEAMENTO_SINISTRA,FilePDF.NERO);
    	
    	file.aggiungi(BARRA,FilePDF.HELVETICA,12,FilePDF.CORSIVO,FilePDF.ALLINEAMENTO_SINISTRA,FilePDF.NERO);
    	
    	
		ArrayList<CellaPDF> tabellaInfo = new ArrayList<CellaPDF>();
		
		tabellaInfo.add(new CellaPDF("Posizione:",FilePDF.GROSSETTO));
		tabellaInfo.add(new CellaPDF(datiApparato.getPosizione()));
		
		tabellaInfo.add(new CellaPDF("Nome PC:",FilePDF.GROSSETTO));
		tabellaInfo.add(new CellaPDF(apparato));
		
		DatiPosizione datiPosizione  = ((DatiPosizione)dati.get(DatiPosizione.NOME_TABELLA));
		Ufficio posizione = datiPosizione.info(datiApparato.getPosizione());
		
		tabellaInfo.add(new CellaPDF("Responsabile Sito:",FilePDF.GROSSETTO));
		tabellaInfo.add(new CellaPDF(posizione != null ? posizione.getResponsabile() : " "));
		
		tabellaInfo.add(new CellaPDF("WorkGroup:",FilePDF.GROSSETTO));
		tabellaInfo.add(new CellaPDF(datiApparato.getGruppo()));
		
		DatiUtilizzatore datiUtilizzatore  = ((DatiUtilizzatore)dati.get(DatiUtilizzatore.NOME_TABELLA));
		Utilizzatore utilizzatore = datiUtilizzatore.info(datiApparato.getUtente());
		
		tabellaInfo.add(new CellaPDF("Utilizzatore:",FilePDF.GROSSETTO));
		tabellaInfo.add(new CellaPDF( utilizzatore != null ? utilizzatore.getNome() : "" ));
		
		DatiRete datiRete  = ((DatiRete)dati.get(DatiRete.NOME_TABELLA));
		Rete rete = datiRete.info(datiApparato.getGruppo());
		
		tabellaInfo.add(new CellaPDF("Dominio Internet:",FilePDF.GROSSETTO));
		tabellaInfo.add(new CellaPDF(rete != null ? rete.getDominio() : " "));
		
		
		tabellaInfo.add(new CellaPDF("Posta Elettronica:",FilePDF.GROSSETTO));
		tabellaInfo.add(new CellaPDF( utilizzatore != null ? utilizzatore.getMail() : "" ));
		
		tabellaInfo.add(new CellaPDF("Tipo Rete:",FilePDF.GROSSETTO));
		tabellaInfo.add(new CellaPDF(rete != null ? rete.getTipo() : " "));
		
		tabellaInfo.add(new CellaPDF(" ",FilePDF.GROSSETTO));
		tabellaInfo.add(new CellaPDF(" "));
		
		tabellaInfo.add(new CellaPDF("Indirizzo IP:",FilePDF.GROSSETTO));
		tabellaInfo.add(new CellaPDF(datiApparato.getIp()));
		
		file.aggiungiTabella(tabellaInfo, new float[]{14,54,12,20});
		
		file.aggiungi(BARRA,FilePDF.HELVETICA,12,FilePDF.CORSIVO,FilePDF.ALLINEAMENTO_SINISTRA,FilePDF.NERO);
    	file.aggiungi("Configurazione Hardware dell'Apparato",FilePDF.HELVETICA,14,FilePDF.GROSSETTO_CORSIVO,FilePDF.ALLINEAMENTO_CENTRO,FilePDF.NERO);
		
		ArrayList<CellaPDF> tabellaHW = new ArrayList<CellaPDF>();
		
		tabellaHW.add(new CellaPDF("Tipo",FilePDF.GROSSETTO));
		tabellaHW.add(new CellaPDF("Casa",FilePDF.GROSSETTO));
		tabellaHW.add(new CellaPDF("Modello",FilePDF.GROSSETTO));
		tabellaHW.add(new CellaPDF("Matricola",FilePDF.GROSSETTO));
		tabellaHW.add(new CellaPDF("NUC",FilePDF.GROSSETTO));
		tabellaHW.add(new CellaPDF("Note",FilePDF.GROSSETTO));
		
		DatiHardwareApparato datiHW  = ((DatiHardwareApparato)dati.get(DatiHardwareApparato.NOME_TABELLA));
		ArrayList<Hardware> listaHW = datiHW.listaHW(apparato);
		for(Hardware hw:listaHW) {
			tabellaHW.add(new CellaPDF(hw.getNome(),FilePDF.NORMALE,8));
			tabellaHW.add(new CellaPDF(hw.getCasa(),FilePDF.NORMALE,8));
			tabellaHW.add(new CellaPDF(hw.getModello(),FilePDF.NORMALE,8));
			tabellaHW.add(new CellaPDF(hw.getMatricola(),FilePDF.NORMALE,8));
			tabellaHW.add(new CellaPDF(hw.getNUC(),FilePDF.NORMALE,8));//NUC
			tabellaHW.add(new CellaPDF(hw.getNote(),FilePDF.NORMALE,8));//note
		}
		
		file.aggiungiTabella(tabellaHW, new float[]{10,10,10,10,10,10});
		
		file.aggiungi(BARRA,FilePDF.HELVETICA,12,FilePDF.CORSIVO,FilePDF.ALLINEAMENTO_SINISTRA,FilePDF.NERO);
    	file.aggiungi("Software Installato",FilePDF.HELVETICA,14,FilePDF.GROSSETTO_CORSIVO,FilePDF.ALLINEAMENTO_CENTRO,FilePDF.NERO);
		
		ArrayList<CellaPDF> tabellaSW = new ArrayList<CellaPDF>();
		
		tabellaSW.add(new CellaPDF("Tipo",FilePDF.GROSSETTO));
		tabellaSW.add(new CellaPDF("Casa",FilePDF.GROSSETTO));
		tabellaSW.add(new CellaPDF("Versione",FilePDF.GROSSETTO));
		tabellaSW.add(new CellaPDF("Licenza",FilePDF.GROSSETTO));
		tabellaSW.add(new CellaPDF("Note",FilePDF.GROSSETTO));
		
		DatiSoftwareApparato datiSW  = ((DatiSoftwareApparato)dati.get(DatiSoftwareApparato.NOME_TABELLA));
		ArrayList<Software> listaSW = datiSW.listaSW(apparato);
		for(Software sw:listaSW) {
			tabellaSW.add(new CellaPDF(sw.getTipo(),FilePDF.NORMALE,8));
			tabellaSW.add(new CellaPDF(sw.getCasa(),FilePDF.NORMALE,8));
			tabellaSW.add(new CellaPDF(sw.getNome(),FilePDF.NORMALE,8));
			tabellaSW.add(new CellaPDF(sw.getLicenza(),FilePDF.NORMALE,8));
			tabellaSW.add(new CellaPDF(sw.getNote(),FilePDF.NORMALE,8));
		}
		
		file.aggiungiTabella(tabellaSW, new float[]{10,10,10,40,10});
		
		file.aggiungi(BARRA,FilePDF.HELVETICA,12,FilePDF.CORSIVO,FilePDF.ALLINEAMENTO_SINISTRA,FilePDF.NERO);
		
		//file.aggiungi("\n\n\n ");
		
		ArrayList<CellaPDF> areaFirme = new ArrayList<CellaPDF>();
		
		String titoloFirma1 = "", nomeFirma1 = "";
		String riga = datiImpostazioni.valore(DatiImpostazioni.FIRMA1);
		if(riga.equals(DatiImpostazioni.FIRMA_RIGA1)) {
			titoloFirma1 = datiImpostazioni.valore(DatiImpostazioni.FIRMA1_QUALIFICA1);
			nomeFirma1 = datiImpostazioni.valore(DatiImpostazioni.FIRMA1_NOME1);
		}else if(riga.equals(DatiImpostazioni.FIRMA_RIGA2)) {
			titoloFirma1 = datiImpostazioni.valore(DatiImpostazioni.FIRMA1_QUALIFICA2);
			nomeFirma1 = datiImpostazioni.valore(DatiImpostazioni.FIRMA1_NOME2);
		}
		
		String titoloFirma2 = "", nomeFirma2 = "";
		riga = datiImpostazioni.valore(DatiImpostazioni.FIRMA2);
		if(riga.equals(DatiImpostazioni.FIRMA_RIGA1)) {
			titoloFirma2 = datiImpostazioni.valore(DatiImpostazioni.FIRMA2_QUALIFICA1);
			nomeFirma2 = datiImpostazioni.valore(DatiImpostazioni.FIRMA2_NOME1);
		}else if(riga.equals(DatiImpostazioni.FIRMA_RIGA2)) {
			titoloFirma2 = datiImpostazioni.valore(DatiImpostazioni.FIRMA2_QUALIFICA2);
			nomeFirma2 = datiImpostazioni.valore(DatiImpostazioni.FIRMA2_NOME2);
		}
		
		areaFirme.add(new CellaPDF(" "));areaFirme.add(new CellaPDF(" "));
		areaFirme.add(new CellaPDF(" "));areaFirme.add(new CellaPDF(" "));
		areaFirme.add(new CellaPDF(titoloFirma1,10,FilePDF.TIMES, FilePDF.GROSSETTO,false,FilePDF.ALLINEAMENTO_CENTRO));
		areaFirme.add(new CellaPDF("L'UTILIZZATORE",10,FilePDF.TIMES, FilePDF.GROSSETTO,false,FilePDF.ALLINEAMENTO_CENTRO));
		areaFirme.add(new CellaPDF(nomeFirma1,10,FilePDF.TIMES, FilePDF.CORSIVO,false,FilePDF.ALLINEAMENTO_CENTRO));
		areaFirme.add(new CellaPDF(utilizzatore != null ? utilizzatore.getNome() : "" ,10,FilePDF.TIMES, FilePDF.CORSIVO,false,FilePDF.ALLINEAMENTO_CENTRO));
		areaFirme.add(new CellaPDF(" "));areaFirme.add(new CellaPDF(" "));
		areaFirme.add(new CellaPDF(" "));areaFirme.add(new CellaPDF(" "));
		areaFirme.add(new CellaPDF(titoloFirma2,10,FilePDF.TIMES, FilePDF.GROSSETTO,false,FilePDF.ALLINEAMENTO_CENTRO));
		areaFirme.add(new CellaPDF(" "));
		areaFirme.add(new CellaPDF(nomeFirma2,10,FilePDF.TIMES, FilePDF.CORSIVO,false,FilePDF.ALLINEAMENTO_CENTRO));
		areaFirme.add(new CellaPDF(" "));
		
		file.aggiungiTabella(areaFirme, new float[]{60,40});
		
		
		tabellaInfo.add(new CellaPDF("Posizione:",FilePDF.GROSSETTO));
		
    	file.chiudi();
    }





    /**
     * Metodo statico che inizializza un campo a menu con l'elenco dei modello relativi a un dato hardware.
     * 
     * @param nomeHardware
     * @param nomeModello
     */
	public static void aggiornaMenuModelloHW(String nomeHardware, ChoiceBox<String> nomeModello) {
		nomeModello.getItems().clear();
        if(nomeHardware != null){
            TreeSet<String> modelli = ((DatiHardware)dati.get(DatiHardware.NOME_TABELLA)).listaModello(nomeHardware);
            if(modelli != null){
                nomeModello.getItems().addAll(modelli);
            }
        }
		
	}







	public static void aggiornaMenuMatricolaHW(String nomeHardware, String nomeModello,ChoiceBox<String> listaMatricole) {
		listaMatricole.getItems().clear();
        if(nomeHardware != null && nomeModello != null){
            TreeSet<String> modelli = ((DatiHardware)dati.get(DatiHardware.NOME_TABELLA)).listaMatricola(nomeHardware,nomeModello);
            if(modelli != null){
            	listaMatricole.getItems().addAll(modelli);
            }
        }
		
	}







	public static void apriFinestraNuovoHardware(Object finestra, String[] input) {
		FinestraHardwareController.input = input;
        Finestra.caricaFinestra(finestra, R.FXML.FINESTRA_HW);
	}






	/**
	 * Permette di salvare un nuovo componente hw di un apparato.
	 * 
	 * @param controller		se null non compare la finestra di avviso errore
	 * @param nomeApparato
	 * @param nomeHardware
	 * @param nomeModello
	 * @param nomeMatricola
	 * @return
	 */
	public static boolean aggiungiNuovoHardware(Object controller, String nomeApparato, String nomeHardware,ChoiceBox<String> nomeModello, ChoiceBox<String> nomeMatricola) {
		
        Object[] record = new Object[]{
            nomeApparato,
            nomeHardware,
            nomeModello.getValue() == null ? "" : nomeModello.getValue(),
            nomeMatricola.getValue() == null ? "" : nomeMatricola.getValue()
        };
        
        Object[] hw = new Object[]{
            nomeHardware,
            null,
            nomeModello.getValue() == null ? "" : nomeModello.getValue(),
            nomeMatricola.getValue() == null ? "" : nomeMatricola.getValue()
        };

        if(((DatiHardware)dati.get(DatiHardware.NOME_TABELLA)).cerca(hw)) {
	        if(((DatiHardwareApparato)dati.get(DatiHardwareApparato.NOME_TABELLA)).aggiungi(record)){
	            return true;
	        }
        }
        if(controller != null)
        	Finestra.finestraAvviso(
        		controller, 
                String.format(
                        R.Messaggi.ERRORE_SALVATAGGIO,
                        nomeHardware+" di "+nomeApparato,
                        DatiDB.stampa(record)
                )
            );
        return false;
	}




	/**
	 * Crea un nodo di una lista ad albero che rappresenta un apparato.
	 * 
	 * @param apparato
	 * @return
	 */
	private static TreeItem<Nodo> nodoApparato(Apparato apparato){
		Node immaginePC = new ImageView(new Image(R.Icona.PC,20,20,true,true));
        TreeItem<Nodo> pc = new TreeItem<>(apparato,immaginePC);
        
        TreeItem<Nodo> tipo = new TreeItem<>(new Voce(R.Etichette.TIPO,apparato.getTipo()));
        pc.getChildren().add(tipo);

        TreeItem<Nodo> ip = new TreeItem<>(new Voce(R.Etichette.IP,apparato.getIp()));
        pc.getChildren().add(ip);

        TreeItem<Nodo> mac = new TreeItem<>(new Voce(R.Etichette.MAC_PC,apparato.getMacPC()));
        pc.getChildren().add(mac);

        TreeItem<Nodo> mac2 = new TreeItem<>(new Voce(R.Etichette.MAC_VOIP,apparato.getMacVOIP()));
        pc.getChildren().add(mac2);

        TreeItem<Nodo> password = new TreeItem<>(new Voce(R.Etichette.PASSWORD,apparato.getPassword()));
        pc.getChildren().add(password);

        Node immagineAccount = new ImageView(new Image(R.Icona.UTILIZZATORE,20,20,true,true));
        DatiUtilizzatore datiUtilizzatore = (DatiUtilizzatore) dati.get(DatiUtilizzatore.NOME_TABELLA);
        Utilizzatore utente = datiUtilizzatore.info(apparato.getUtente());
        if(utente != null){
            TreeItem<Nodo> nodoUtilizzatore = new TreeItem<>(utente,immagineAccount);
            pc.getChildren().add(nodoUtilizzatore);
            nodoUtilizzatore.getChildren().add(new TreeItem<>(new Voce(R.Etichette.ACCOUNT,utente.getAccount())));
            nodoUtilizzatore.getChildren().add(new TreeItem<>(new Voce(R.Etichette.MAIL,utente.getMail())));

        }

        TreeItem<Nodo> nodoSwitch = nodoConnessioneSwitch(apparato);
        pc.getChildren().add(nodoSwitch);
        
        HardwareApparato hwApp = new HardwareApparato(apparato.getNome(),dati);
        Node immagineHW = new ImageView(new Image(R.Icona.HW,20,20,true,true));
        TreeItem<Nodo> nodoHW = new TreeItem<>(hwApp,immagineHW);
        pc.getChildren().add(nodoHW);
        
        for(Hardware hw : hwApp.getHardware()){
            TreeItem<Nodo> componente = new TreeItem<>(hw);
            nodoHW.getChildren().add(componente);
        }
        	

        SoftwareApparato swApp = new SoftwareApparato(apparato.getNome(),dati);
        Node immagineSW = new ImageView(new Image(R.Icona.SW,20,20,true,true));
        TreeItem<Nodo> nodoSW = new TreeItem<>(swApp,immagineSW);
        pc.getChildren().add(nodoSW);

        for(Software sw : swApp.getSoftware()){
            TreeItem<Nodo> applicazione = new TreeItem<>(sw);
            nodoSW.getChildren().add(applicazione);
        }

        pc.setExpanded(false);
        return pc;
	}

	/**
	 * Crea un nodo di una lista ad albero che rappresenta una connessione allo switch.
	 * 
	 * @param apparato
	 * @return
	 */
	private static TreeItem<Nodo> nodoConnessioneSwitch(Apparato apparato){
		DatiConnessioneSwitch datiSw = (DatiConnessioneSwitch)dati.get(DatiConnessioneSwitch.NOME_TABELLA);
        Node immagineSw = new ImageView(new Image(R.Icona.SWITCH,20,20,true,true));
        ConnessioneSwitch _switch = datiSw.creaSwitch(apparato.getNome());
        TreeItem<Nodo> nodoSwitch = new TreeItem<>(_switch,immagineSw);
        
        if(!_switch.getNome().equals(R.ChiaviDati.NESSUN_SWITCH))
        	nodoSwitch.getChildren().add(new TreeItem<>(new Voce(R.Etichette.PORTA,_switch.getPorta())));
        return nodoSwitch;
	}


	public static void creaListaSwitch(TreeItem<Nodo> rete) {
		if(rete != null){
            rete.getChildren().clear();
            DatiSwitch datiSw = (DatiSwitch)dati.get(DatiSwitch.NOME_TABELLA);
            for(String nomeSw: datiSw.listaOrdinata(0)) {
            	Switch sw = new Switch(nomeSw,dati);
            	Node immagineSw = new ImageView(new Image(R.Icona.SWITCH,35,35,true,true));
                TreeItem<Nodo> nodoSwitch = new TreeItem<>(sw,immagineSw);
                rete.getChildren().add(nodoSwitch);
                for(Apparato apparato: sw.getApparati()) {
                	nodoSwitch.getChildren().add(nodoApparato(apparato));
                }
            }
		}
	}

	
	public static void crealistaHelpDesk(TreeItem<Nodo> listaInterventi) {
		if(listaInterventi != null){
			listaInterventi.getChildren().clear();
			DatiApparato datiApparato = (DatiApparato)dati.get(DatiApparato.NOME_TABELLA);
			DatiIntervento datiHelpDesk = (DatiIntervento)dati.get(DatiIntervento.NOME_TABELLA);
            for( Intervento helpDesk: datiHelpDesk.listaHelpDesk()) {
            	Node immagine = new ImageView(new Image(R.Icona.HELPDESK,20,20,true,true));
                TreeItem<Nodo> nodo = new TreeItem<>(helpDesk,immagine);
                nodo.getChildren().add(new TreeItem<>(new Voce(R.Etichette.RICHIESTA,helpDesk.getMotivo())));
                nodo.getChildren().add(new TreeItem<>(new Voce(R.Etichette.INTERVENTO,helpDesk.getAzione())));
                Apparato apparato = datiApparato.info(helpDesk.getApparato());
                if(apparato != null)
                	nodo.getChildren().add(nodoApparato(apparato));
                nodo.getChildren().add(new TreeItem<>(new Voce(R.Etichette.ESITO,helpDesk.getEsito())));
                listaInterventi.getChildren().add(nodo);
                
            }
		}
	}



	


	/**
	 * Inizializza l'aspetto della lista ad albero.
	 * 
	 * @param rete
	 * @param lista
	 * @param datiStato
	 */
	public static void aspettoListaAlbero(TreeItem<Nodo> rete, TreeView<Nodo> lista, DatiStato datiStato) {
		Callback<TreeView<Nodo>, TreeCell<Nodo>> costruzioneListaAlbero = tv -> new TreeCell<Nodo>() {
	            @Override
	            protected void updateItem(Nodo item, boolean empty) {
	                super.updateItem(item, empty);
	                setStyle("-fx-text-fill: black;");
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
	                    }else if(item instanceof Rete || item instanceof Ufficio || item instanceof Responsabile || item instanceof Switch ){
	                        setFont(Font.font("Arial Black", 12));
	                    }else if(item instanceof ConnessioneSwitch || item instanceof Utilizzatore || item instanceof SoftwareApparato || item instanceof HardwareApparato || item instanceof Intervento){
	                        setFont(Font.font("Arial", FontWeight.BOLD, 12));
	                        
	                      //colora gli interventi di 'help desk' in base all'esito
	                        if(item instanceof Intervento) {
	                        	String colore = Colore.ARANCIONE;
	                        	if(((Intervento) item).getEsito().equals(R.Esito.POSITIVO)){
	                        		colore = Colore.VERDE;
	                        	}else if(((Intervento) item).getEsito().equals(R.Esito.NEGATIVO)){
	                        		colore = Colore.ROSSO;
	                        	}
	                        	setStyle(colore);
	                        }
	                        
	                    }else{
	                        setFont(Font.font("Arial Narrow",12));
	                    }
	                }
	            }
	    };
	    
	    lista.setCellFactory(costruzioneListaAlbero);
	    lista.setRoot(rete);
	}







	







	
    
}
