package ma.projet.restclient.entities;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import java.util.List;

@Root(name = "List", strict = false)
public class CompteList {
    @ElementList(inline = true, entry = "item")
    private List<Compte> items;

    // Retourne la liste des comptes (nom de méthode inchangé pour compatibilité)
    public List<Compte> getComptes() {
        return items;
    }

    public void setComptes(List<Compte> comptes) {
        this.items = comptes;
    }
}