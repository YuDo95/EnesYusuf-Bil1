package dk.kea.enesyusufbil.controller;

import dk.kea.enesyusufbil.model.User;
import dk.kea.enesyusufbil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Henter alle brugere fra databasen og tilføjer dem som en attribut til modellen.
    // Returnerer "users" view, hvor brugere kan administreres.
    @GetMapping
    public String manageUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users"; // Thymeleaf template til visning af brugerliste
    }

    // Opretter en ny bruger baseret på `User`-objektet.
    // Efter oprettelse omdirigeres brugeren til siden med alle brugere.
    @PostMapping("/create")
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/users"; // Omdirigerer til brugersiden efter oprettelse
    }

    // Opdaterer en eksisterende brugers oplysninger.
    // Kalder `updateUser`-metoden i `UserService` og omdirigerer derefter til brugersiden.
    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/users"; // Omdirigerer til brugersiden efter opdatering
    }

    // Sletter en bruger baseret på `userId` hentet fra forespørgslen.
    // Efter sletning omdirigeres brugeren til siden med alle brugere.
    @PostMapping("/delete")
    public String deleteUser(@RequestParam("selectedUserId") int userId) {
        userService.deleteUser(userId);
        return "redirect:/users"; // Omdirigerer til brugersiden efter sletning
    }
}
