package dk.kea.enesyusufbil.repository;

import java.util.List;

public interface BilRepository {

    List<Bil> findAll();


    void update(Bil bil);


    void delete(int vognnummer);


    void create(Bil bil);


    /* Bliver ikke brugt (endnu)
    Bil findById(int vognnummer); */
}
