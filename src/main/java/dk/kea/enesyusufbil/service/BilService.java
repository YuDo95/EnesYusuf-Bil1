package dk.kea.enesyusufbil.service;

import dk.kea.enesyusufbil.model.Bil;
import dk.kea.enesyusufbil.repository.BilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BilService {

    private final BilRepository bilRepository;

    @Autowired
    public BilService(BilRepository bilRepository)
    {
        this.bilRepository = bilRepository;
    }


    public List<Bil> getAllBiler() {return bilRepository.findAll();}


    public void createBil(Bil bil) {bilRepository.create(bil);}


    public void updateBil(Bil bil)
    {
        bilRepository.update(bil);
    }


    public void deleteBil(int vognnummer)
    {
        bilRepository.delete(vognnummer);
    }


     /* Denne metode kunne bruges, hvis vi vil have en/flere separate html til de enkelte biler
    public Bil getBilById(int vognnummer)
    {
        Bil bil = bilRepository.findById(vognnummer);
        if (bil == null) {
            throw new IllegalArgumentException("Bil not found with vognnummer: " + vognnummer);
        }
        return bil;
    } */

}