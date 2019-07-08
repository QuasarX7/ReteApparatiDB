package it.quasar_x7.gestione_rete.modello;

import it.quasar_x7.gestione_rete.Dati.DatiUtilizzatore;
import static it.quasar_x7.gestione_rete.programma.Programma.dati;
import it.quasar_x7.gestione_rete.programma.R;
import java.util.ArrayList;
import java.util.Objects;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Dr. Domenico della Peruta
 */
public class Apparato extends Nodo{
    
    private final StringProperty tipo;
    private final StringProperty gruppo;
    private final StringProperty ip;
    private final StringProperty macPC;
    private final StringProperty macVOIP;
    private final StringProperty posizione;
    private final StringProperty utente;
    private final BooleanProperty internet;
    private final IntegerProperty sigillo;
    private final IntegerProperty scheda;
    private final StringProperty password;
    private final StringProperty stato;
    
   
    
    public Apparato(String nome,String tipo,String gruppo,String ip,String macPC,String macVOIP,String posizione,String utente, boolean internet, int sigillo,int scheda,String password,String stato) {
        super(nome);
        this.tipo = new SimpleStringProperty(tipo);
        this.gruppo = new SimpleStringProperty(gruppo);
        this.ip = new SimpleStringProperty(ip);
        this.macPC = new SimpleStringProperty(macPC);
        this.macVOIP= new SimpleStringProperty(macVOIP);
        this.posizione = new SimpleStringProperty(posizione);
        this.utente = new SimpleStringProperty(utente);
        this.internet = new SimpleBooleanProperty(internet);
        this.sigillo = new SimpleIntegerProperty(sigillo);
        this.scheda = new SimpleIntegerProperty(scheda);
        this.password = new SimpleStringProperty(password);
        this.stato = new SimpleStringProperty(stato);
    }

    public String getTipo() {
        return tipo.get();
    }

    public String getGruppo() {
        return gruppo.get();
    }

    public String getIp() {
        return ip.get();
    }
    
    public String getMacPC() {
        return macPC.get();
    }
    
    public String getMacVOIP() {
        return macVOIP.get();
    }

    public String getPosizione() {
        return posizione.get();
    }

    public String getUtente() {
        return utente.get();
    }
    
   
    
    public Boolean getInternet() {
        return internet.get();
    }

    public int getSigillo() {
        return sigillo.get();
    }
    
    public int getScheda() {
        return scheda.get();
    }
    
    public String getPassword() {
        return password.get();
    }
    
    public String getStato() {
        return stato.get();
    }
    
    @Override
    public ArrayList<TextFlow> info(){
        ArrayList<TextFlow> lista = new ArrayList<>();
        DatiUtilizzatore utilizzatore = (DatiUtilizzatore)dati.get(DatiUtilizzatore.NOME_TABELLA);
        
        lista.add(voceInfo(R.Etichette.NOME,getNome()));
        lista.add(voceInfo(R.Etichette.TIPO,getTipo()));
        lista.add(voceInfo(R.Etichette.WORKGROUP,getGruppo()));
        lista.add(voceInfo(R.Etichette.IP,getIp()));
        lista.add(voceInfo(R.Etichette.MAC_PC,getMacPC()));
        lista.add(voceInfo(R.Etichette.MAC_VOIP,getMacVOIP()));
        lista.add(voceInfo(R.Etichette.POSIZIONE,getPosizione()));
        lista.add(voceInfo(R.Etichette.UTILIZZATORE,utilizzatore.trovaNominativo(getUtente())));
        lista.add(voceInfo(R.Etichette.ACCOUNT,getUtente()));
        lista.add(voceInfo(R.Etichette.INTERNET,getInternet() ? R.Conferma.SI : R.Conferma.NO ));
        lista.add(voceInfo(R.Etichette.SIGILLO,getSigillo()+""));
        lista.add(voceInfo(R.Etichette.SCHEDA,getScheda()+""));
        lista.add(voceInfo(R.Etichette.PASSWORD,getPassword()));
        lista.add(voceInfo(R.Etichette.STATO,getStato()));
        
        return lista;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.tipo);
        hash = 41 * hash + Objects.hashCode(this.gruppo);
        hash = 41 * hash + Objects.hashCode(this.ip);
        hash = 41 * hash + Objects.hashCode(this.macPC);
        hash = 41 * hash + Objects.hashCode(this.macVOIP);
        hash = 41 * hash + Objects.hashCode(this.posizione);
        hash = 41 * hash + Objects.hashCode(this.utente);
        hash = 41 * hash + Objects.hashCode(this.internet);
        hash = 41 * hash + Objects.hashCode(this.sigillo);
        hash = 41 * hash + Objects.hashCode(this.password);
        hash = 41 * hash + Objects.hashCode(this.stato);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Apparato other = (Apparato) obj;
        if (!Objects.equals(this.tipo.get(), other.tipo.get())) {
            return false;
        }
        if (!Objects.equals(this.gruppo.get(), other.gruppo.get())) {
            return false;
        }
        if (!Objects.equals(this.ip.get(), other.ip.get())) {
            return false;
        }
        if (!Objects.equals(this.macPC.get(), other.macPC.get())) {
            return false;
        }
        if (!Objects.equals(this.macVOIP.get(), other.macVOIP.get())) {
            return false;
        }
        if (!Objects.equals(this.posizione.get(), other.posizione.get())) {
            return false;
        }
        if (!Objects.equals(this.utente.get(), other.utente.get())) {
            return false;
        }
        if (!Objects.equals(this.internet.get(), other.internet.get())) {
            return false;
        }
        if (!Objects.equals(this.sigillo.get(), other.sigillo.get())) {
            return false;
        }
        if (!Objects.equals(this.scheda.get(), other.scheda.get())) {
            return false;
        }
        if (!Objects.equals(this.password.get(), other.password.get())) {
            return false;
        }
        if (!Objects.equals(this.stato.get(), other.stato.get())) {
            return false;
        }
        return true;
    }

	
    
    
    
    
}
