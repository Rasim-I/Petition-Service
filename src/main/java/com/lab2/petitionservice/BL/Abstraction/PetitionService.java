package com.lab2.petitionservice.BL.Abstraction;

import com.lab2.petitionservice.BL.Models.Petition;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface PetitionService {
     List<Petition> getListPetitions();
     Petition getPetition(String petitionId);
     void CreatePetition(String petitionTitle, String petitionText, String petitionAuthorName);
     void RemovePetition(UUID PetitionId);
     void VoteForPetition(UUID PetitionId, String User);
}
