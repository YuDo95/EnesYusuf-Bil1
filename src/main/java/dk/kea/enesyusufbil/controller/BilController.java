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
    public BilController(BilService bilService)
    {
        this.bilService = bilService;
    }


    @GetMapping
    public String getAllBiler(Model model)
    {
        List<Bil> biler = bilService.getAllBiler();

        model.addAttribute("biler", biler);

        return "bil";
    }


    @PostMapping("/create")
    public String createBil(@ModelAttribute Bil bil)
    {
        bilService.createBil(bil);

        return "redirect:/bil";
    }


    @PostMapping("/update/{vognnummer}")
    public String updateBil(@ModelAttribute Bil bil)
    {
        bilService.updateBil(bil);

        return "redirect:/bil";
    }


    @PostMapping("/delete")
    public String deleteBil(@RequestParam("vognnummer") int vognnummer)
    {
        bilService.deleteBil(vognnummer);

        return "redirect:/bil";
    }


    /* Denne metode kunne bruges, hvis vi vil have en/flere separate html til de enkelte biler
    @GetMapping("/{vognnummer}")
    public Bil getBilById(@PathVariable("vognnummer") int vognnummer)
    {
        return bilService.getBilById(vognnummer);
    }*/

}