package dk.kea.enesyusufbil.controller;

import dk.kea.enesyusufbil.model.Bil;
import dk.kea.enesyusufbil.model.Lejeaftale;
import dk.kea.enesyusufbil.model.User;
import dk.kea.enesyusufbil.service.BilService;
import dk.kea.enesyusufbil.service.LejeaftaleService;
import dk.kea.enesyusufbil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/lejeaftaler")
public class LejeaftaleController {

    private final LejeaftaleService lejeaftaleService;
    private final BilService bilService;
    private final UserService userService;

    // Constructor, der injicerer LejeaftaleService, BilService og UserService til brug i controlleren
    @Autowired
    public LejeaftaleController(LejeaftaleService lejeaftaleService, BilService bilService, UserService userService) {
        this.lejeaftaleService = lejeaftaleService;
        this.bilService = bilService;
        this.userService = userService;
    }

    // Henter og viser en liste af alle lejeaftaler, biler og brugere
    // Viser disse data på "lejeaftaler"-siden
    @GetMapping
    public String getAllLejeaftaler(Model model) {
        List<Lejeaftale> lejeaftaler = lejeaftaleService.getAllLejeaftale();
        List<Bil> biler = bilService.getAllBiler();
        List<User> users = userService.getAllUsers();

        model.addAttribute("biler", biler);
        model.addAttribute("users", users);
        model.addAttribute("lejeaftaler", lejeaftaler);

        return "lejeaftaler";
    }

    // Opretter en ny lejeaftale baseret på de indsendte data
    // Sætter vognnummer og bruger-ID fra forespørgslen og gemmer lejeaftalen
    @PostMapping("/create")
    public String createLejeaftale(@ModelAttribute Lejeaftale lejeaftale,
                                   @RequestParam("vognnummer") int vognnummer,
                                   @RequestParam("users") int userId) {
        lejeaftale.setBilVognnummer(vognnummer);
        lejeaftale.setUserId(userId);
        lejeaftale.setSlutKm(null); // Sætter slut-kilometer som null ved oprettelse
        lejeaftale.setOverkoerteKm(false); // Sætter overkørte kilometer til false

        // Gemmer lejeaftalen ved hjælp af LejeaftaleService
        lejeaftaleService.createLejeaftale(lejeaftale);

        return "redirect:/lejeaftaler"; // Viderestiller til listen over lejeaftaler
    }

    // Opdaterer en eksisterende lejeaftale baseret på lejeaftale-ID'et
    @PostMapping("/update/{id}")
    public String updateLejeaftale(@PathVariable("id") int lejeaftaleId,
                                   @ModelAttribute Lejeaftale lejeaftale) {
        lejeaftale.setLejeaftaleId(lejeaftaleId); // Sætter ID'et for lejeaftalen, der skal opdateres
        lejeaftaleService.updateLejeaftale(lejeaftale); // Opdaterer lejeaftalen

        return "redirect:/lejeaftaler"; // Viderestiller til listen over lejeaftaler
    }

    // Sletter en lejeaftale baseret på dens ID
    @PostMapping("/delete")
    public String deleteLejeaftale(@RequestParam("id") int lejeaftale_id) {
        lejeaftaleService.deleteLejeaftale(lejeaftale_id); // Sletter lejeaftalen

        return "redirect:/lejeaftaler"; // Viderestiller til listen over lejeaftaler
    }

}
