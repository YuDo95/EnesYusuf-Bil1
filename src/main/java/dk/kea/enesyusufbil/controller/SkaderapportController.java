package dk.kea.enesyusufbil.controller;

import dk.kea.enesyusufbil.model.Bil;
import dk.kea.enesyusufbil.model.Skaderapport;
import dk.kea.enesyusufbil.model.User;
import dk.kea.enesyusufbil.service.BilService;
import dk.kea.enesyusufbil.service.SkaderapportService;
import dk.kea.enesyusufbil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/skaderapporter")
public class SkaderapportController {

    private final SkaderapportService skaderapportService;
    private final UserService userService;
    private final BilService bilService;

    @Autowired
    public SkaderapportController(SkaderapportService skaderapportService, UserService userService, BilService bilService) {
        this.skaderapportService = skaderapportService;
        this.userService = userService;
        this.bilService = bilService;
    }

    // Henter alle skaderapporter, biler og brugere fra databasen,
    // og tilføjer dem som attributter til modellen for visning på skaderapport-siden.
    @GetMapping
    public String getAllSkaderapporter(Model model) {
        List<Skaderapport> skaderapporter = skaderapportService.getAllSkaderapporter();
        List<Bil> biler = bilService.getAllBiler();
        List<User> users = userService.getAllUsers();

        model.addAttribute("biler", biler);
        model.addAttribute("users", users);
        model.addAttribute("skaderapporter", skaderapporter);

        return "skaderapporter"; // Thymeleaf template for at vise alle skaderapporter
    }

    // Henter en specifik skaderapport baseret på `id` og tilføjer den til modellen
    // for visning af detaljer på skaderapport-siden.
    @GetMapping("/{id}")
    public String getSkaderapportById(@PathVariable int id, Model model) {
        Skaderapport skaderapport = skaderapportService.getSkaderapportById(id);
        model.addAttribute("skaderapport", skaderapport);
        return "skaderapporter"; // Thymeleaf template for at vise detaljer om en specifik skaderapport
    }

    // Opretter en ny skaderapport og gemmer den i databasen.
    // Efter oprettelsen omdirigeres til skaderapport-siden, hvor alle skaderapporter vises.
    @PostMapping("/create")
    public String createSkaderapport(@ModelAttribute Skaderapport skaderapport) {
        skaderapportService.createSkaderapport(skaderapport);
        return "redirect:/skaderapporter"; // Omdirigerer til skaderapport-siden efter oprettelse
    }

    // Opdaterer en eksisterende skaderapport baseret på `id`.
    // Anvender `SkaderapportService` til at gemme ændringerne og omdirigerer derefter til skaderapport-siden.
    @PostMapping("/update")
    public String updateSkaderapport(@RequestParam int id, @ModelAttribute Skaderapport skaderapport) {
        skaderapport.setId(id);
        skaderapportService.updateSkaderapport(skaderapport);
        return "redirect:/skaderapporter"; // Omdirigerer til skaderapport-siden efter opdatering
    }

    // Sletter en skaderapport fra databasen baseret på `id`.
    // Efter sletning omdirigeres til skaderapport-siden, hvor alle skaderapporter vises.
    @PostMapping("/delete")
    public String deleteSkaderapport(@RequestParam int id) {
        skaderapportService.deleteSkaderapport(id);
        return "redirect:/skaderapporter"; // Omdirigerer til skaderapport-siden efter sletning
    }
}
