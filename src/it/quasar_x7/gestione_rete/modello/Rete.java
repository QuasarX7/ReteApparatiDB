package it.quasar_x7.gestione_rete.modello;

import it.quasar_x7.gestione_rete.programma.R;
import java.util.ArrayList;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Dr. Domenico della Peruta
 */
public class Rete extends Nodo{

    private final StringProperty dominio;
    private final StringProperty tipo;
    private final StringProperty netmask;
    private final StringProperty gateway;
    
     public Rete(String workgroup,String dominio,String tipo,String gateway,String netmask) {
        super(workgroup);
        this.dominio = new SimpleStringProperty(dominio);
        this.tipo = new SimpleStringProperty(tipo);
        this.gateway = new SimpleStringProperty(gateway);
        this.netmask = new SimpleStringProperty(netmask);
    }

    public String getTipo() {
        return tipo.get();
    }

    public String getNetmask() {
        return netmask.get();
    }

    public String getGateway() {
        return gateway.get();
    }
    
    public String getDominio() {
        return dominio.get();
    }

    @Override
    public int hashCode() {
        int hash = 3;
                
        hash = 67 * hash + Objects.hashCode(this.nome);
        hash = 67 * hash + Objects.hashCode(this.dominio);
        hash = 67 * hash + Objects.hashCode(this.tipo);
        hash = 67 * hash + Objects.hashCode(this.netmask);
        hash = 67 * hash + Objects.hashCode(this.gateway);
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
        final Rete other = (Rete) obj;
        if (!Objects.equals(this.tipo.get(), other.tipo.get())) {
            return false;
        }
        if (!Objects.equals(this.dominio.get(), other.dominio.get())) {
            return false;
        }
        if (!Objects.equals(this.netmask.get(), other.netmask.get())) {
            return false;
        }
        if (!Objects.equals(this.gateway.get(), other.gateway.get())) {
            return false;
        }
        if (!Objects.equals(this.nome.get(), other.nome.get())) {
            return false;
        }
        return true;
    }
     
    
    @Override
     public ArrayList<TextFlow> info(){
        ArrayList<TextFlow> lista = new ArrayList<>();
        
        lista.add(voceInfo(R.Etichette.NOME,getNome()));
        lista.add(voceInfo(R.Etichette.DOMINIO,getDominio()));
        lista.add(voceInfo(R.Etichette.TIPO,getTipo()));
        lista.add(voceInfo(R.Etichette.GATEWAY,getGateway()));
        lista.add(voceInfo(R.Etichette.NETMASK,getNetmask()));
        
        return lista;
    }
     
    static public final Rete STANDALONE = new Rete("Stand-alone","","","","");
    
}
