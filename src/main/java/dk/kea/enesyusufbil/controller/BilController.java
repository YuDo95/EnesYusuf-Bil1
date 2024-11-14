package dk.kea.enesyusufbil.controller;

import dk.kea.enesyusufbil.model.Bil;
import dk.kea.enesyusufbil.service.BilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bil")
public class BilController {

    public BilService bilService;

    @Autowired
    public BilController(BilService bilService) {
        this.bilService = bilService;
    }

    // Henter alle biler fra databasen og tilføjer dem som en attribut til modellen.
    // Returnerer "bil" view, hvor bilerne kan administreres.
    @GetMapping
    public String getAllBiler(Model model) {
        List<Bil> biler = bilService.getAllBiler();
        model.addAttribute("biler", biler);
        return "bil"; // Thymeleaf template til visning af biloversigt
    }

    // Opretter en ny bil baseret på `Bil`-objektet.
    // Efter oprettelse omdirigeres brugeren til siden med alle biler.
    @PostMapping("/create")
    public String createBil(@ModelAttribute Bil bil) {
        bilService.createBil(bil);
        return "redirect:/bil"; // Omdirigerer til bilsiden efter oprettelse
    }

    // Opdaterer en eksisterende bils oplysninger.
    // Kalder `updateBil`-metoden i `BilService` og omdirigerer derefter til bilsiden.
    @PostMapping("/update/{vognnummer}")
    public String updateBil(@ModelAttribute Bil bil) {
        bilService.updateBil(bil);
        return "redirect:/bil"; // Omdirigerer til bilsiden efter opdatering
    }

    // Sletter en bil baseret på `vognnummer` hentet fra forespørgslen.
    // Efter sletning omdirigeres brugeren til siden med alle biler.
    @PostMapping("/delete")
    public String deleteBil(@RequestParam("vognnummer") int vognnummer) {
        bilService.deleteBil(vognnummer);
        return "redirect:/bil"; // Omdirigerer til bilsiden efter sletning
    }

    /* Denne metode kunne bruges, hvis vi vil have en/flere separate HTML-sider til individuelle biler
       Her returneres biloplysninger baseret på vognnummer, hvilket muliggør en detaljeret visning.
    @GetMapping("/{vognnummer}")
    public Bil getBilById(@PathVariable("vognnummer") int vognnummer) {
        return bilService.getBilById(vognnummer);
    } */
}