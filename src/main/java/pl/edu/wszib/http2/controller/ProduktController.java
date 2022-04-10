package pl.edu.wszib.http2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.http2.service.ProduktService;
import pl.edu.wszib.http2.service.exception.NotFoundException;
import pl.edu.wszib.http2.service.model.Filtr;
import pl.edu.wszib.http2.service.model.Produkt;

@Controller
@RequestMapping("/produkt")
public class ProduktController {

    @Autowired
    private ProduktService produktService;

    @GetMapping("/list")
    public String list(Filtr filtr, Model model) {
        if (filtr == null) {
//        Produkt produkt = new Produkt();
//        produkt.setCena(4.5f);
//        produkt.setIlosc(56);
//        produkt.setNazwa("Domestos");
//        produktService.create(produkt);

            model.addAttribute("produkty", produktService.list()); // <- przekaz list
            model.addAttribute("filtr", new Filtr());
        } else {
            model.addAttribute("produkty", produktService.list(filtr)); // <- przekaz list
            model.addAttribute("filtr", new Filtr());
        }
        return "produkt/list-produkt"; //<- nazwa widoku
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("newProdukt", new Produkt()); // domyslna wartosc do tworzenia
        return "produkt/create-produkt"; //<- nazwa widoku
    }

    @PostMapping("/create")
    public String createAction(Produkt produkt, Model model) {
        //kod stworzenia produktu
        produkt = produktService.create(produkt);
        return "redirect:/produkt/get?id=" + produkt.getId();
    }

    @GetMapping("/get")
    public String get(@RequestParam Integer id, Model model) {
        model.addAttribute("produkt", produktService.get(id));
        return "produkt/get-produkt";
    }

    @GetMapping("/update")
    public String update(@RequestParam Integer id, Model model) {
        model.addAttribute("updateProdukt", produktService.get(id)); // domyslna wartosc do tworzenia
        return "produkt/update-produkt"; //<- nazwa widoku
    }

    @PostMapping("/update")
    public String updateAction(Produkt produkt, Model model) {
        //kod stworzenia produktu
        produktService.update(produkt);
        return "redirect:/produkt/get?id=" + produkt.getId();
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Integer id, Model model) {
        model.addAttribute("deleteProdukt", produktService.get(id)); // domyslna wartosc do tworzenia
        return "produkt/delete-produkt"; //<- nazwa widoku
    }

    @PostMapping("/delete")
    public String deleteAction(Produkt produkt, Model model) {
        //kod stworzenia produktu
        produktService.delete(produkt.getId());
        return "redirect:/produkt/list";
    }

    @ExceptionHandler(NotFoundException.class)
    public String notFoundView() {
        return "produkt/404-produkt";
    }
}
