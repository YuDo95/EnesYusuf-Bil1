package dk.kea.enesyusufbil.service;

import dk.kea.enesyusufbil.model.Bil;
import dk.kea.enesyusufbil.repository.BilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BilService {

    private final BilRepository bilRepository;

    // Constructor, der injicerer BilRepository for at muliggøre databaseoperationer for Bil-objekter
    @Autowired
    public BilService(BilRepository bilRepository) {this.bilRepository = bilRepository;}

    // Henter en liste over alle Bil-objekter fra databasen
    public List<Bil> getAllBiler() {return bilRepository.findAll();}

    // Opretter en ny bil i databasen ved at kalde create-metoden i BilRepository
    public void createBil(Bil bil) {bilRepository.create(bil);}

    // Opdaterer en eksisterende bil i databasen ved at kalde update-metoden i BilRepository
    public void updateBil(Bil bil) {bilRepository.update(bil);}

    // Sletter en bil fra databasen baseret på bilens vognnummer ved at kalde delete-metoden i BilRepository
    public void deleteBil(int vognnummer) {bilRepository.delete(vognnummer);}

    /* Finder en specifik bil baseret på vognnummer.
       Denne metode kunne være nyttig, hvis vi ønsker at vise detaljer for en enkelt bil i en separat HTML-side.
       Hvis bilen ikke findes, smides en undtagelse med en passende fejlbesked.
    public Bil getBilById(int vognnummer) {
        Bil bil = bilRepository.findById(vognnummer);
        if (bil == null) {
            throw new IllegalArgumentException("Bil not found with vognnummer: " + vognnummer);
        }
        return bil;
    } */

}
