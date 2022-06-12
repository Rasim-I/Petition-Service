package com.lab2.petitionservice.BL.Implementation;

import com.lab2.petitionservice.BL.Abstraction.PetitionService;
import com.lab2.petitionservice.BL.Models.Petition;
import com.lab2.petitionservice.BL.Models.Vote;
import com.lab2.petitionservice.DAL.Abstraction.PetitionRepository;
import com.lab2.petitionservice.DAL.Implementation.PetitionRepositoryImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class PetitionServiceImpl implements PetitionService {

    PetitionRepository petitionRepository;

    public PetitionServiceImpl(PetitionRepository petitionRepository){
        this.petitionRepository = petitionRepository;
    }

    @Override
    public List<Petition> getListPetitions() {
        return petitionRepository.GetAll();
    }

    public List<Petition> getPetitionsPaginated(int pageSize, int pageNumber){

        if (pageSize == 0)
            pageSize = 2;
        List<Petition> page = new ArrayList<>();

        petitionRepository
                .GetAll()
                .stream()
                .skip((pageNumber - 1)*pageSize)
                .limit(pageSize)
                .forEach(page::add);

        return page;
    }

    @Override
    public List<Petition> getPetitionsByAuthor(String author){

        List<Petition> filtered = new ArrayList<>();

        petitionRepository
                .GetAll()
                .stream()
                .filter(x->x.getAuthor().equals(author))
                .forEach(filtered::add);
        return filtered;
    }

    @Override
    public void VoteForPetition(UUID PetitionId, String User){
        Vote vote = new Vote(PetitionId, User);
        Petition petitionToChange = petitionRepository.Get(PetitionId);
        petitionToChange.Vote(vote);
        petitionRepository.Update(petitionToChange);
    }

    @Override
    public Petition getPetition(String petitionId) {
        UUID PetitionIdUUID;
        try{
            PetitionIdUUID = UUID.fromString(petitionId);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return petitionRepository.Get(PetitionIdUUID);
    }

    @Override
    public void CreatePetition(String petitionTitle, String petitionText, String petitionAuthorName) {
        Petition petition = new Petition(petitionTitle, petitionAuthorName, petitionText);
        petitionRepository.Create(petition);
    }

    @Override
    public void RemovePetition(UUID PetitionId) {
        petitionRepository.Remove(PetitionId);
    }


}
