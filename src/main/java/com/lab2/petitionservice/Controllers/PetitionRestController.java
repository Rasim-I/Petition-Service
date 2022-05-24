package com.lab2.petitionservice.Controllers;

import com.lab2.petitionservice.BL.Abstraction.PetitionService;
import com.lab2.petitionservice.BL.Models.Petition;
import com.lab2.petitionservice.BL.Models.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/petitions")
public class PetitionRestController {

    @Autowired
    PetitionService petitionService;

    @GetMapping
    public Collection<Petition> getAllPetitions(){   //pagination
        return petitionService.getListPetitions();
    }

    @GetMapping("/{petitionId}")
    public Petition getPetition(@PathVariable UUID petitionId, HttpServletResponse response){
        if (petitionService.getPetition(petitionId.toString()) != null){
            return petitionService.getPetition(petitionId.toString());
        }else{
            response.setStatus(404);
            return null;
        }
    }

    @PostMapping
    public void addPetition(@RequestBody PetitionWebModel petitionWebModel, HttpServletResponse response){  //Create class PetitionWebModel or something
        try {
            petitionService.CreatePetition(petitionWebModel.getTitle(), petitionWebModel.getText(), petitionWebModel.getAuthor());
            response.setStatus(HttpServletResponse.SC_CREATED);
        }catch (Exception e){
            response.setStatus(500);
        }

    }

    @DeleteMapping("/{petitionId}")
    public void deletePetition(@PathVariable UUID petitionId, HttpServletResponse response){
        if(petitionService.getPetition(petitionId.toString()) != null){
            petitionService.RemovePetition(petitionId);
            response.setStatus(HttpServletResponse.SC_OK);
        }else
            response.setStatus(404);

    }

    @PutMapping("/{petitionId}/vote")
    public void voteForPetition(@PathVariable UUID petitionId, @RequestBody String name, HttpServletResponse response){
        if(petitionService.getPetition(petitionId.toString()) != null && (name != "" && name != null)){
            petitionService.VoteForPetition(petitionId, name);
            response.setStatus(200);
        }
        else if (petitionService.getPetition(petitionId.toString()) == null)
            response.setStatus(404);
        else
            response.setStatus(418);

    }


}
