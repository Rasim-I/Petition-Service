package com.lab2.petitionservice.Controllers;

import com.lab2.petitionservice.BL.Abstraction.PetitionService;
import com.lab2.petitionservice.BL.Models.Petition;
import com.lab2.petitionservice.BL.Models.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/petitions")
public class PetitionRestController {

    @Autowired
    PetitionService petitionService;


    @GetMapping
    public Collection<Petition> getAllPetitions(){
        return petitionService.getListPetitions();
    }



    @GetMapping(params = {"page"})
    public Collection<Petition> getPetitionPaginated(@RequestParam(required = false) Integer page,
                                                     @RequestParam(required = false) Integer size,
                                                     HttpServletResponse response){
        if (size == null)
            size = 0;
        return petitionService.getPetitionsPaginated(size, page);
    }

    @GetMapping(params = {"author"})
    public Collection<Petition> getPetitionFiltered(@RequestParam(required = false) String author,
                                                    HttpServletResponse response){
        List<Petition> filteredPetitions;
        filteredPetitions = petitionService.getPetitionsByAuthor(author);
        if (filteredPetitions.size() > 0)
            return filteredPetitions;
        else {
            response.setStatus(404);
            return null;
        }
    }


    @GetMapping("/{petitionId}")
    public Petition getPetition(@PathVariable UUID petitionId, HttpServletResponse response){       //can be ResponseEntity<Petition>
        if (petitionService.getPetition(petitionId.toString()) != null){
            return petitionService.getPetition(petitionId.toString());                              //return ResponseEntity.ok(petition)
        }else{
            response.setStatus(404);
            return null;
        }
    }

    @PostMapping                            //validation doesn't work. don't know why
    public void addPetition(@RequestBody @Valid PetitionWebModel petitionWebModel, HttpServletResponse response){
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
