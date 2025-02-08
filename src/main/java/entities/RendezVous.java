package entities;

public class RendezVous {
    private int id;
    private String date;
    private String heure;
    private int logementReference;
    private String numTel;

    // Default constructor for Jersey deserialization
    public RendezVous() {}

    // Constructor that takes a Logement object and sets reference
    public RendezVous(int id, String date, String heure, Logement logement, String numTel) {
        this.id = id;
        this.date = date;
        this.heure = heure;
        this.logementReference = logement.getReference();
        this.numTel = numTel;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getHeure() { return heure; }
    public void setHeure(String heure) { this.heure = heure; }
    public int getLogementReference() { return logementReference; }
    public void setLogementReference(int logementReference) { this.logementReference = logementReference; }
    public String getNumTel() { return numTel; }
    public void setNumTel(String numTel) { this.numTel = numTel; }
}
