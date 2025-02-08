package metiers;

import entities.Logement;
import entities.RendezVous;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RendezVousBusiness {
    private List<RendezVous> listeRendezVous;
    private LogementBusiness logementMetier;

    // Constructor initializing the list and the LogementBusiness instance
    public RendezVousBusiness() {
        this.listeRendezVous = new ArrayList<>();
        this.logementMetier = new LogementBusiness(); // Initialize the business layer
    }

    // Method to add a RendezVous
    public boolean addRendezVous(RendezVous rendezVous) {
        int refLogement = rendezVous.getLogementReference();
        Logement logement = logementMetier.getLogementsByReference(refLogement);
        if (logement != null) {
            rendezVous.setLogementReference(logement.getReference());
            return listeRendezVous.add(rendezVous);
        }
        return false;

    }

    // Getter for the list of RendezVous
    public List<RendezVous> getListeRendezVous() {
        return listeRendezVous;
    }

    // Setter for the list of RendezVous
    public void setListeRendezVous(List<RendezVous> listeRendezVous) {
        this.listeRendezVous = listeRendezVous;
    }

    // Method to get RendezVous by Logement reference
    public List<RendezVous> getListeRendezVousByLogementReference(int reference) {
        List<RendezVous> liste = new ArrayList<>();
        for (RendezVous r : listeRendezVous) {
            if (r.getLogementReference() == reference) {
                liste.add(r); // Add RendezVous to list if it matches the Logement reference
            }
        }
        return liste;
    }

    // Method to get a specific RendezVous by its ID
    public RendezVous getRendezVousById(int id) {
        for (RendezVous r : listeRendezVous) {
            if (r.getId() == id) {
                return r; // Return the RendezVous if ID matches
            }
        }
        return null; // If no matching RendezVous is found
    }

    // Method to delete a RendezVous by its ID
    public boolean deleteRendezVous(int id) {
        Iterator<RendezVous> iterator = listeRendezVous.iterator();
        while (iterator.hasNext()) {
            RendezVous r = iterator.next();
            if (r.getId() == id) {
                iterator.remove(); // Remove the RendezVous from the list
                return true; // Return true after successful removal
            }
        }
        return false; // Return false if no RendezVous was removed
    }

    // Method to update a RendezVous by its ID
    public boolean updateRendezVous(int idRendezVous, RendezVous updatedRendezVous) {
        for (int i = 0; i < listeRendezVous.size(); i++) {
            RendezVous r = listeRendezVous.get(i);
            if (r.getId() == idRendezVous) {
                // Update the Logement reference first
                Logement logement = logementMetier.getLogementsByReference(updatedRendezVous.getLogementReference());
                if (logement != null) {
                    updatedRendezVous.setLogementReference(logement.getReference()); // Correctly set the Logement reference
                    listeRendezVous.set(i, updatedRendezVous); // Set the updated RendezVous in the list
                    return true; // Return true after successful update
                }
            }
        }
        return false; // Return false if no matching RendezVous found or Logement is invalid
    }
}
