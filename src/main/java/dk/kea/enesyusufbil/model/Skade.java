package dk.kea.enesyusufbil.model;

public class Skade {

    private int id;
    private String type;
    private double pris;


    public Skade() {
    }

    public Skade(int id, double pris, String type) {
        this.id = id;
        this.pris = pris;
        this.type = type;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    @Override
    public String toString() {
        return "Skade{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", pris=" + pris +
                '}';
    }
}
