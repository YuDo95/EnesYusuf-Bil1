package dk.kea.enesyusufbil.controller;

import dk.kea.enesyusufbil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService)
    {
        this.userService = userService;
    }


    @GetMapping()
    public String showLoginForm()
    {
        return "login";
    }


    @PostMapping()
    public String login(@RequestParam String user_email,
                        @RequestParam String user_password, Model model) {
        try {
            if (userService.login(user_email, user_password)) {
                return "redirect:/home";
            } else {
                model.addAttribute("error", "Forkert email eller password.");
                return "login";
            }
        } catch (Exception e) {
            model.addAttribute("error", "An unexpected error occurred: " + e.getMessage());
            return "login";
        }
    }
}
