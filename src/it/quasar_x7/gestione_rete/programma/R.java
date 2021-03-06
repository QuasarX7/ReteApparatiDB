package it.quasar_x7.gestione_rete.programma;



/**
 * 
 * @author Dr Domenico della Peruta
 */
public class R {

    public static String[] Stato = new String[]{
        "BUONO","OBSOLETO","INEFFICIENTE","GUASTO"
    };

    public class TipoRicerca {
        static public final String NOMINATIVO 	= "UTILIZZATORE";
        static public final String ACCOUNT 		= "ACCOUNT";
        static public final String IP 			= "IP";
        static public final String APPARATO 	= "APPARATO";
        static public final String MAC 			= "MAC";
		public static final String SW 			= "NOME SOFTWARE";
		public static final String HW 			= "MODELLO HARDWARE";
		public static final String POSIZIONE 	= "POSIZIONE";
		public static final String RESPONSABILE = "RESPONSABILE";
    }
  
    
    public class Modello{
        public class SW{
            static public final String NOME 		= "nome";
            static public final String LICENZA 		= "licenza";
            static public final String TIPO 		= "tipo";
            static public final String CASA 		= "casa";
            static public final String NOTE 		= "note";
			public static final String PREDEFINITO 	= "predefinito";
        }
        public class HW{
            static public final String NOME 		= "nome";
            static public final String MATRICOLA 	= "matricola";
            static public final String MODELLO 		= "modello";
            static public final String NUC 			= "NUC";
            static public final String STATO 		= "stato";
            static public final String CASA 		= "casa";
            static public final String NOTE 		= "note";
        }
    }
    
    public class FXML{
        static private final String PATH 					= "/it/quasar_x7/gestione_rete/vista/";
        
        static public final String LOGIN 					= PATH + "FinestraLogin.fxml";
        static public final String FINESTRA_PRINCIPALE 		= PATH + "FinestraPrincipale.fxml";
        static public final String FINESTRA_HW 				= PATH + "FinestraHardware.fxml";
        static public final String FINESTRA_SW 				= PATH + "FinestraSoftware.fxml";
        static public final String FINESTRA_GRADO 			= PATH + "FinestraGrado.fxml";
        public static final String FINESTRA_UTILIZZATORE 	= PATH + "FinestraUtilizzatore.fxml";
        public static final String FINESTRA_RESPONSABILE 	= PATH + "FinestraResponsabile.fxml";
        public static final String FINESTRA_POSIZIONE 		= PATH + "FinestraPosizione.fxml";
        public static final String FINESTRA_RETE 			= PATH + "FinestraRete.fxml";
        public static final String FINESTRA_APPARATO 		= PATH + "FinestraApparato.fxml";
        public static final String FINESTRA_SWITCH 			= PATH + "FinestraSwitch.fxml";
        public static final String FINESTRA_SW_APPARATO 	= PATH + "FinestraSoftwareApparato.fxml";
        public static final String FINESTRA_HW_APPARATO 	= PATH + "FinestraHardwareApparato.fxml";
        public static final String FINESTRA_IMPOSTAZIONI 	= PATH + "FinestraImpostazioni.fxml";
        public static final String FINESTRA_PASSWORD 		= PATH + "FinestraPassword.fxml";
        public static final String FINESTRA_INTERVENTO 		= PATH + "FinestraIntervento.fxml";
        public static final String FINESTRA_WIZARD_HW_APPARATO = PATH + "FinestraWizardHW.fxml";
        public static final String FINESTRA_WIZARD_SW_APPARATO = PATH + "FinestraWizardSW.fxml";
    }
    
    public class File{
        static public final String DB = "dati.db";
    }
    
    public class Icona{
        static private final String PATH = "/it/quasar_x7/gestione_rete/Risorse/";
        
        public static final String RETE    		= PATH + "rete_2.png";
        public static final String PC      		= PATH + "pc.png";
        public static final String UFFICIO 		= PATH + "ufficio.png";
        public static final String HW      		= PATH + "hardware.png";
        public static final String SW      		= PATH + "software.png";
        public static final String UTILIZZATORE = PATH + "utilizzatore.png";
        public static final String SWITCH 		= PATH + "switch.png";
        public static final String POSIZIONE 	= PATH + "posizione.png";

		public static final String HELPDESK 	= PATH + "modifica.png";
    
    }
    
    public class Etichette{
    	static public final String FINESTRA_LISTA_APPARATI      = "Apparati";
        static public final String FINESTRA_LISTA_CASA_SW      = "Case Software";
        static public final String FINESTRA_LISTA_CASA_HW      = "Case Hardware";
        static public final String FINESTRA_LISTA_TIPO_HW      = "Tipo Hardware";
        static public final String FINESTRA_LISTA_TIPO_SW      = "Tipo Software";
        static public final String FINESTRA_LISTA_TIPO_APPARATO= "Tipo di Apparato";
        static public final String FINESTRA_LISTA_STATO = "Stato";
        
        static public final String FINESTRA_TIPO_RETE          = "Tipo di Rete";
        
        static public final String FINESTRA_LISTA_CATEGORIA_GRADO = "Categoria";
        
        static public final String NOME_SWITCH  = "switch: ";
        
        static public final String CASA_SW       = "nome casa: ";
        static public final String CASA_HW       = "nome casa: ";
        static public final String TIPO_HW       = "tipo hardware: ";
        static public final String TIPO_SW       = "tipo software: ";
        static public final String TIPO_APPARATO = "tipo di apparato: ";
        static public final String TIPO_STATO	 = "stato: ";
        
        static public final String TIPO_RETE     = "tipo di rete: ";
        
        static public final String RUOLO         = "ruolo: ";
        public static final String FINESTRA_LISTA_GRADO = "Lista Qualifica/Gradi";
        
        public static final String FINESTRA_UTILIZZATORI = "Lista Utilizzatori";
        public static final String FINESTRA_HW	 = "Lista Hardware";
        public static final String FINESTRA_SW	 = "Lista Software";
        
        public static final String NOMINATIVO 	 	= "Nominativo";
        public static final String MAIL 			= "Posta elettronica";
        public static final String MARCA_MATRICOLA	= "Tipo ˙ Modello ˙ Matricola";
        public static final String MATRICOLA 		= "Matricola";
        public static final String MODELLO 			= "Modello";
        public static final String CASA 			= "Casa";
        public static final String TIPO 			= "Tipo";
        public static final String NUC 				= "NUC";
        public static final String STATO 			= "Stato";
        public static final String NOTE 			= "Note";
        public static final String NOME_VERSIONE 	= "Nome/versione";
        public static final String NOME_VERSIONE_LICENZA = "Nome/versione - licenza";
        public static final String LICENZA 				 = "Licenza";
        public static final String RESPONSABILE 		 = "Responsabile";
        public static final String FINESTRA_RESPONSABILE = "Lista Responsabili dei Siti";
        public static final String FINESTRA_POSIZIONE 	 = "Lista Posizioni";
        public static final String FINESTRA_SWITCH 		 = "Lista Switch";
        public static final String POSIZIONE 			 = "Posizione";
        public static final String WORKGROUP 		= "Workgroup";
        public static final String SWITCH 		= "Switch";
        public static final String PORTA 		= "Porta";
        public static final String DOMINIO 		= "Dominio";
        public static final String NETMASK 		= "Netmask";
        public static final String FINESTRA_RETE = "Lista Reti";
        public static final String HW 			= "Hardware";
        public static final String SW 			= "Software";
        public static final String ACCOUNT 		= "Account";
        public static final String UTILIZZATORE = "Utilizzatore";
        public static final String IP 			= "IP";
        public static final String MAC_PC 		= "MAC PC";
        public static final String MAC_VOIP 	= "MAC VOIP";
        public static final String PASSWORD 	= "Password";
        public static final String NOME 		= "Nome";
        public static final String INTERNET 	= "Internet";
        public static final String SIGILLO 		= "Sigillo";
        public static final String SCHEDA 		= "Scheda";
        public static final String GATEWAY 		= "Gateway";
        public static final String NOME_RESPONSABILE = "Nome Resp.";
        public static final String GRADO 		= "Grado/Qualifica";
        public static final String COGNOME 		= "Cognome";
        public static final String APPARATO 	= "Apparato";
		public static final String PREDEFINITO  = "Predefinito";
		public static final String TICKET 		= "Ticket";
		public static final String DATA 		= "Data";
		public static final String RICHIESTA 	= "Richiesta";
		public static final String INTERVENTO 	= "Intervento";
		public static final String ESITO 		= "Esito";
		public static final String FINESTRA_INTERVENTO = "Lista Interventi";
		public static final String DIAGRAMMA 	= "Distribuzione Apparati";
        
        
        
    }
    
    public class TipoStandardHW{
        public static final String UNITA_CENTALE 	= "Unità Centrale";
        public static final String SCHEDA_MADRE 	= "Scheda Madre";
        public static final String PROCESSORE 		= "Processore";
        public static final String RAM 				= "RAM";
        public static final String HDD 				= "HDD";
        public static final String SCHEDA_VIDEO 	= "Scheda Video";
        public static final String SCHEDA_RETE 		= "Scheda Rete";
        public static final String MONITOR 			= "Monitor";
        public static final String MOUSE 			= "Mouse";
        public static final String TASTIERA 		= "Tastiera";
        public static final String UPS 				= "UPS";
        public static final String STAMPANTE 		= "Stampante";
        
    }
    
    public class Conferma{
        public static final String SI = "Sì";
        public static final String NO = "No";
    }
    
    public class Esito{
        public static final String POSITIVO = "Positivo";
        public static final String NEGATIVO = "Negativo";
        public static final String SOSPESO  = "Da definire";
    }
    
    public class Messaggi{
    	static public final String SELEZIONA_UTENTE 			= "Seleziona utente!";
        static public final String FILE_INESISTENTE 			= "Il file «"+File.DB+"» non esiste, ne verrà creato uno nuovo!";
        static public final String TABELLA_LOGIN_INESISTENTE 	= "La tabella «utente» nella base di dati «dati.db» non esiste, ne verrà creata una nuova!";
        static public final String SOSTITUZIONE_CASA_HW 		= "Inserisci il nome della casa produttrice di hardware che sostituirà «%s».";
        static public final String SOSTITUZIONE_CASA_SW 		= "Inserisci il nome della casa produttrice di software che sostituirà «%s».";
        static public final String SOSTITUZIONE_TIPO_APPARATO 	= "Inserisci il nome del tipo di apparato che sostituirà «%s».";
        static public final String SOSTITUZIONE_STATO 			= "Inserisci il nome dello stato che sostituirà «%s».";
        static public final String SOSTITUZIONE_TIPO_HW 		= "Inserisci il nome del tipo di hardware che sostituirà «%s».";
        static public final String SOSTITUZIONE_TIPO_SW 		= "Inserisci il nome del tipo di software che sostituirà «%s».";
        static public final String SOSTITUZIONE_TIPO_RETE 		= "Inserisci il nome del tipo di rete che sostituirà «%s».";
        static public final String SOSTITUZIONE_SWITCH 			= "Inserisci il nome dello switch che sostituirà «%s».";
        
        static public final String SOSTITUZIONE_RUOLO 			= "Inserisci il nome del tipo della categoria che sostituirà «%s».";
        public static final String ERRORE_SALVATAGGIO_GRADO 	= "Errore nel salvataggio della nuova Qualifica/Grado !";
        public static final String ERRORE_ELIMINAZIONE_RIGA 	= "Impossibile eliminare «%s»";
        public static final String ERRORE_SALVATAGGIO 			= "Impossibile salvare «%s» %s";
        public static final String ERRORE_PASSWORD			 	= "Password errata!!!";
        public static final String ERRORE_MODIFICHE 			= "Impossibile modificare «%s» %s";
        public static final String ERRORE_DUPLICAZIONE 			= "Impossibile salvare «%s», dato già essistente...";
        public static final String ERRORE_CAMPI_FONDAMENTALI 	= "Errore, i campi con etichetta di color ARANCIONE sono fondamentali e non possono essere vuoti!";
        public static final String IMMOSSIBILE_AGGIORNARE_APPARATI 	= "ERRORE:\n Non è stato possiblile sostituire la voce «%s» con «%s» nella tabella apparato !";
		public static final String IMMOSSIBILE_AGGIORNARE_POSIZIONE = "ERRORE:\n Non è stato possiblile sostituire la voce «%s» con «%s» nella tabella posizione !";
		public static final String IMMOSSIBILE_AGGIORNARE_HW 	= "ERRORE:\n Non è stato possiblile aggiungere il seguente hardware:\n%s";
		public static final String APPARATO_NON_DEFINITO 		= "ERRORE:\n Apparato non definito!!!";
		public static final String ERRORE_AUTORIZZAZIONE        = "ERRORE: l'utente «%s» non è autorizzato a effettuare l'operazione richiesta!";
	    
    }
    
    public class ChiaviDati{
        static public final String NESSUNA_RETE  	= "Stand-alone";
        static public final String NESSUN_GRUPPO 	= "";
        static public final String NESSUN_NOME 		= "";
		public static final String NESSUN_SWITCH 	= "";
    }
    
    public class Domanda{
        static public final String CONFERMA_CHIUSURA_PROGRAMMA      = "Sei sicuro di voler uscire dal programma?";
        static public final String CREAZIONE_DB      				= "Non esiste nessuna Base di Dati, vuoi crearne una nuova?\n\nSe [Sì] Il programma si chiuderà dopo l'inizializzazione della nuova base di dati.\nAlla successiva apertura, verrà chiesta la creazione dell'utente amministratore....";
        static public final String CREAZIONE_TABELLA      			= "La tabella «%s» non esiste oppure è danneggiata, vuoi crearne una nuova?";
        static public final String SOSTITUISCI      				= "E' già presente un record simile a «%s», vuoi modificarlo?";
        static public final String CONFERMA_ELIMINAZIONE_APPARATO   = "Sei sicuro di voler eliminare l'apparato `%s`?";
        static public final String CONFERMA_ELIMINAZIONE_UFFICIO    = "Sei sicuro di voler eliminare il locale `%s`?";
		public static final String CONFERMA_ELIMINAZIONE_RESPONSABILE = "Sei sicuro di voler eliminare il responsabile `%s`?";
		public static final String CONFERMA_ELIMINAZIONE_RETE 		= "Sei sicuro di voler eliminare la rete `%s`?";
		public static final String CONFERMA_ELIMINAZIONE 			= "Sei sicuro di voler eliminare `%s`?";
		public static final String INPUT_SWITCH 					= "Switch:";
        
    }
    
    
    public class Utente{
        static public final String ROOT 	= "Administrator";
        static public final String ANONIMO 	= "Anonimo";
    }
    
    
}
