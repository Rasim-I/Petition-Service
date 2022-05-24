package com.lab2.petitionservice.Controllers;

import com.lab2.petitionservice.BL.Abstraction.PetitionService;
import com.lab2.petitionservice.BL.Models.Petition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import java.beans.BeanProperty;
import java.lang.reflect.Array;
import java.util.*;

@Controller
public class PetitionController {

    @Autowired
    public PetitionService PetitionService;

    public List<Petition> Petitions(){   //Because without it intellij sense can't see fields of Petition
        return PetitionService.getListPetitions();
    }

    @GetMapping("/")
    public String MainPage(Model model, boolean isOperationFailed){
        model.addAttribute("petitions", Petitions());
        model.addAttribute("isOperationFailed", isOperationFailed);
        return "main";
    }

    @GetMapping("/create")
    String CreatePetition(){
        return "createPetition";
    }

    @PostMapping("/create")  //return Created Petition ID
    ModelAndView CreatePetition(Model model, @RequestParam String petitionTitle, @RequestParam String petitionText, @RequestParam String petitionAuthorName){
        PetitionService.CreatePetition(petitionTitle, petitionText, petitionAuthorName);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;//new ModelAndView("redirect:/");
    }

    @GetMapping("/vote")
    ModelAndView Vote(Model model, @RequestParam UUID petitionId, @RequestParam String voteUsername){
        PetitionService.VoteForPetition(petitionId, voteUsername);

        return new ModelAndView("redirect:/");
    }

    @PostMapping("/remove")                                                                                 //delete mapping
    ModelAndView RemovePetition(Model model, @RequestParam UUID petitionId, @RequestParam String userPassword){
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        if(userPassword.equals("12345")){
            PetitionService.RemovePetition(petitionId);
            System.out.println("--------------"+petitionId+"----------------");
        }
        else
        {
            modelAndView.addObject("isOperationFailed", true);
        }
        return modelAndView;
        //return MainPage(model);
    }

    @GetMapping("/petition")
    String PetitionPage(Model model, @RequestParam String petitionId){
        //PetitionService.getPetition(ID);
        Petition petition = PetitionService.getPetition(petitionId);
        model.addAttribute("petition", petition);
        return "petition";
    }

}
