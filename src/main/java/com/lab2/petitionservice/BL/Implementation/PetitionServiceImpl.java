package com.lab2.petitionservice.BL.Implementation;

import com.lab2.petitionservice.BL.Abstraction.PetitionService;
import com.lab2.petitionservice.BL.Models.Petition;
import com.lab2.petitionservice.BL.Models.Vote;
import com.lab2.petitionservice.DAL.Abstraction.PetitionRepository;
import com.lab2.petitionservice.DAL.Implementation.PetitionRepositoryImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


public class PetitionServiceImpl implements PetitionService {

    PetitionRepository petitionRepository;

    public PetitionServiceImpl(PetitionRepository petitionRepository){
        this.petitionRepository = petitionRepository;
    }

    public List<Petition> getListPetitions() {
        return petitionRepository.GetAll();
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
