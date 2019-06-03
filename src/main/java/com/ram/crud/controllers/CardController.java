package com.ram.crud.controllers;

import com.ram.crud.entities.Card;
import com.ram.crud.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CardController {

    private final CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    
    @GetMapping("/signup")
    public String showSignUpForm(Card card) {
        return "add-card";
    }
    
    @PostMapping("/addcard")
    public String addUser(@Valid Card card, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-card";
        }
        cardRepository.save(card);
        model.addAttribute("cards", cardRepository.findAll());
        return "index";
    }

    @GetMapping("/upload")
    public String upload(Card card) {
        return "upload";
    }

    @PostMapping("/fileUpload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                    RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }


}
