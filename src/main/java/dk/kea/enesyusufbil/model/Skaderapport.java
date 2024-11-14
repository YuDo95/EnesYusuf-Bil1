package dk.kea.enesyusufbil.model;

import java.time.LocalDate;
import java.util.List;

public class Skaderapport {

    private int id;
    private int vognnummer;
    private int userId;
    private List<Skade> skadeList;
    private Integer xtraKm; // Can be null
    private double totalSkadePris;
    private LocalDate createdAt;
    private LocalDate updatedAt;


    public Skaderapport() {
    }

    public Skaderapport(LocalDate createdAt, int id, double totalSkadePris, List<Skade> skadeList, LocalDate updatedAt, int userId, int vognnummer, Integer xtraKm) {
        this.createdAt = createdAt;
        this.id = id;
        this.totalSkadePris = totalSkadePris;
        this.skadeList = skadeList;
        this.updatedAt = updatedAt;
        this.userId = userId;
        this.vognnummer = vognnummer;
        this.xtraKm = xtraKm;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVognnummer() {
        return vognnummer;
    }

    public void setVognnummer(int vognnummer) {
        this.vognnummer = vognnummer;
    }

    public int getUserId() {
        return userId;
    }

    public List<Skade> getSkadeList() {return skadeList;}

    public void setSkadeList(List<Skade> skadeList) {this.skadeList = skadeList;}

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getXtraKm() {
        return xtraKm;
    }

    public void setXtraKm(Integer xtraKm) {
        this.xtraKm = xtraKm;
    }

    public double getTotalSkadePris() {
        return totalSkadePris;
    }

    public void setTotalSkadePris(double totalSkadePris) {
        this.totalSkadePris = totalSkadePris;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

}

