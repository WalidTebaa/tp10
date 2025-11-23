package ma.projet.restclient.entities;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import javax.xml.bind.annotation.XmlElement;

@Root(name = "item", strict = false)
public class Compte {
    @Element(name = "id")
    private Long _id;

    @Element(name = "solde")
    private double _solde;

    @Element(name = "type")
    private String _type;

    @Element(name = "dateCreation")
    private String _createdAt;

    // Constructeur par défaut requis par le framework
    public Compte() {}

    // Constructeur pratique
    public Compte(Long id, double solde, String type, String dateCreation) {
        this._id = id;
        this._solde = solde;
        this._type = type;
        this._createdAt = dateCreation;
    }

    // Setters (noms publics inchangés pour compatibilité)
    public void setId(Long id) {
        this._id = id;
    }

    public void setSolde(double solde) {
        this._solde = solde;
    }

    public void setType(String type) {
        this._type = type;
    }

    public void setDateCreation(String dateCreation) {
        this._createdAt = dateCreation;
    }


    // Getters (annotations conservées)
    @XmlElement(name = "id")
    public Long getId() { return _id; }

    @XmlElement(name = "solde")
    public double getSolde() { return _solde; }

    @XmlElement(name = "type")
    public String getType() { return _type; }

    @XmlElement(name = "dateCreation")
    public String getDateCreation() { return _createdAt; }

    // toString modifié pour style différent
    @Override
    public String toString() {
        return String.format("Compte(id=%s, type=%s, solde=%.2f, created=%s)",
                _id, _type, _solde, _createdAt);
    }
}