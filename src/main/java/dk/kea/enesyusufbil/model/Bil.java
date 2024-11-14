package dk.kea.enesyusufbil.model;

public class Bil {

    private int vognnummer;
    private String stelnummer;
    private String maerke;
    private String model;
    private int aargang;
    private String farve;
    private String registreringsdato;
    private String udleveringslokation;
    private double staalpris;
    private double regAfgift;
    private double co2Udledning;
    private int km;
    private boolean udlejet;
    private String imageUrl;


    public Bil() {
    }


    public Bil(int aargang, double co2Udledning, String farve, int km, String maerke, String model, double regAfgift, String registreringsdato, double staalpris, String stelnummer, String udleveringslokation, int vognnummer, boolean udlejet, String imageUrl) {
        this.aargang = aargang;
        this.co2Udledning = co2Udledning;
        this.farve = farve;
        this.km = km;
        this.maerke = maerke;
        this.model = model;
        this.regAfgift = regAfgift;
        this.registreringsdato = registreringsdato;
        this.staalpris = staalpris;
        this.stelnummer = stelnummer;
        this.udleveringslokation = udleveringslokation;
        this.vognnummer = vognnummer;
        this.udlejet = udlejet;
        this.imageUrl = imageUrl;
    }


    public int getVognnummer() {
        return vognnummer;
    }

    public void setVognnummer(int vognnummer) {
        this.vognnummer = vognnummer;
    }

    public String getStelnummer() {
        return stelnummer;
    }

    public void setStelnummer(String stelnummer) {
        this.stelnummer = stelnummer;
    }

    public String getMaerke() {
        return maerke;
    }

    public void setMaerke(String maerke) {
        this.maerke = maerke;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getAargang() {
        return aargang;
    }

    public void setAargang(int aargang) {
        this.aargang = aargang;
    }

    public String getFarve() {
        return farve;
    }

    public void setFarve(String farve) {
        this.farve = farve;
    }

    public String getRegistreringsdato() {
        return registreringsdato;
    }

    public void setRegistreringsdato(String registreringsdato) {
        this.registreringsdato = registreringsdato;
    }

    public String getUdleveringslokation() {
        return udleveringslokation;
    }

    public void setUdleveringslokation(String udleveringslokation) {
        this.udleveringslokation = udleveringslokation;
    }

    public double getStaalpris() {
        return staalpris;
    }

    public void setStaalpris(double staalpris) {
        this.staalpris = staalpris;
    }

    public double getRegAfgift() {
        return regAfgift;
    }

    public void setRegAfgift(double regAfgift) {
        this.regAfgift = regAfgift;
    }

    public double getCo2Udledning() {
        return co2Udledning;
    }

    public void setCo2Udledning(double co2Udledning) {
        this.co2Udledning = co2Udledning;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public boolean getUdlejet() {return udlejet;}

    public void setUdlejet(boolean udlejet) {this.udlejet = udlejet;}

    public String getImageUrl() {return imageUrl;}

    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}

}
