package sk.tmconsulting.eshoptricka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sk.tmconsulting.eshoptricka.model.Objednavka;
import sk.tmconsulting.eshoptricka.model.Pouzivatel;
import sk.tmconsulting.eshoptricka.service.ObjednavkaService;
import sk.tmconsulting.eshoptricka.service.PouzivatelService;

@Controller
public class EshopTrickaController {
    @Autowired
    ObjednavkaService objednavkaServiceObjekt;
    @Autowired
    PouzivatelService pouzivatelServiceObjekt;

    @GetMapping("/")
    public String uvodnaStranka() {
        return "index";
    }

    @GetMapping("/pridaj-objednavku")
    public String pridajNovuObjednavku(Model model) {
        Objednavka objednavkaObjekt = new Objednavka();
        model.addAttribute("objednavkaObjektThymeleaf", objednavkaObjekt);
        return "pridaj-novu-objednavku";
    }
    @PostMapping("uloz-objednavku")
        public String ulozObjednavku(@ModelAttribute("objednavkaObjektThymeleaf") Objednavka objednavkaObjekt) {
        objednavkaServiceObjekt.ulozObjednavku(objednavkaObjekt);
        return "uloz-novu-objednavku";
    }

    @PostMapping("/uloz-upravenu-objednavku")
    public String ulozUpravenuObjednavku(@ModelAttribute("objednavkaObjektThymeleaf") Objednavka objednavkaObjekt) {
        objednavkaServiceObjekt.aktualizujObjednavku(objednavkaObjekt);
        return "uloz-upravenu-objednavku";
    }

    @GetMapping("zobraz-vsetky-objednavky")
    public String zobrazObjednavky(Model model) {
        model.addAttribute("vsetkyObjednavkyThymeleaf", objednavkaServiceObjekt.ziskajVsetkyObjednavky());
        return "zobraz-vsetky-objednavky";
    }
    @GetMapping("/uprav-objednavku/{id}")
    public String upravObjednavku(@PathVariable(value="id") Long id, Model model) {
    Objednavka objednavkaObjektPodlaId = objednavkaServiceObjekt.ziskajObjPodlaId(id);
    model.addAttribute("objednavkaPodlaIdThymeleaf", objednavkaObjektPodlaId);
    return "uprav-objednavku";
    }

    @GetMapping ("/odstran-objednavku/{id}")
    public String odstranObjednavku(@PathVariable(value = "id") Long id) {
        objednavkaServiceObjekt.odstranObjednavku(id);
        return "objednavka-odstranena";
    }
    @GetMapping ("/login")
    public String loginFormular() {
        return "login";
    }
    /*@GetMapping("/logout")
    public String logoutFormular() {
        return "redirect:/"; // Presmerovanie na hlavnu stranku}
    }*/

    @GetMapping ("/pridaj-noveho-pouzivatela")
    public String pridajNovehoPouzivatela(Model model) {
        Pouzivatel pouzivatelObjekt = new Pouzivatel();
        model.addAttribute("pouzivatelObjektThymeleaf", pouzivatelObjekt);
        return "pridaj-noveho-pouzivatela";
    }

    @PostMapping("uloz-pouzivatela")
    public String ulozPouzivatela(@ModelAttribute("pouzivatelObjektThymeleaf") Pouzivatel pouzivatelObjekt) {
        pouzivatelServiceObjekt.ulozPouzivatela(pouzivatelObjekt);
        return "uloz-noveho-pouzivatela";
    }

}
