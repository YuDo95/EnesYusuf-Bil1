package dk.kea.enesyusufbil.service;

import dk.kea.enesyusufbil.model.Skaderapport;
import dk.kea.enesyusufbil.repository.SkaderapportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkaderapportService {

    private final SkaderapportRepository skaderapportRepository;

    @Autowired
    public SkaderapportService(SkaderapportRepository skaderapportRepository) {this.skaderapportRepository = skaderapportRepository;}

    // Henter en liste over alle skaderapporter fra databasen.
    // Metoden kalder `findAll`-metoden i `SkaderapportRepository`.
    public List<Skaderapport> getAllSkaderapporter() {return skaderapportRepository.findAll();}

    // Henter en specifik skaderapport baseret på ID.
    // Metoden kalder `findById`-metoden i `SkaderapportRepository` for at finde skaderapporten.
    public Skaderapport getSkaderapportById(int id) {return skaderapportRepository.findById(id);}

    // Opretter en ny skaderapport i databasen.
    // Metoden bruger `save`-metoden i `SkaderapportRepository` til at gemme skaderapporten.
    public void createSkaderapport(Skaderapport skaderapport) {skaderapportRepository.save(skaderapport);}

    // Opdaterer en eksisterende skaderapport i databasen.
    // Metoden kalder `update`-metoden i `SkaderapportRepository` for at opdatere skaderapporten med nye data.
    public void updateSkaderapport(Skaderapport skaderapport) {skaderapportRepository.update(skaderapport);}

    // Sletter en skaderapport fra databasen baseret på ID.
    // Metoden anvender `delete`-metoden i `SkaderapportRepository` til at fjerne skaderapporten.
    public void deleteSkaderapport(int id) {
        skaderapportRepository.delete(id);
    }
}
