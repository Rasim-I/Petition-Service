package com.lab2.petitionservice.DAL.Abstraction;

import com.lab2.petitionservice.BL.Models.Petition;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Component
public interface PetitionRepository {

     List<Petition> GetAll();
     void Create(Petition petition);
     Petition Get(UUID petitionId);
     void Update(Petition petition);
     void Remove(UUID petitionId);

}
