package com.lab2.petitionservice.DAL.Implementation;

import com.lab2.petitionservice.BL.Models.Petition;
import com.lab2.petitionservice.DAL.Abstraction.PetitionRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@Scope("singleton")
public class PetitionRepositoryImpl implements PetitionRepository {

    public List<Petition> GetAll() {                                                                            //TODO change to GetALL() and move to RepositoryImpl
        return Petitions;
    }

    private static List<Petition> Petitions;

    public void Create(Petition petition){
        Petitions.add(petition);
    }

    public Petition Get(UUID petitionId) {                                                                      //TODO move to RepositoryImpl

        Petition petition = Petitions.stream()
                .filter(x -> x.getId().equals(petitionId))
                .findAny()
                .orElse(null);

        return petition;
    }

    public void Update(Petition petition){                                                                      //TODO move to RepositoryImpl
        Petition petitionToChange = Petitions.stream()
                .filter(x -> x.getId().equals(petition.getId()))
                .findAny()
                .orElse(null);

        Petitions.remove(petitionToChange);
        Petitions.add(petition);
    }

    public static void InitDatabase() {
        Petitions = new ArrayList<>();
        Petition petition1 = new Petition("Petition to continue hating russians", "Joe D.",
                "text text texttext text texttext text texttext text text" +
                        "text text texttext text texttext text texttext text texttext text texttext text text" +
                        "text text texttext text texttext text texttext text texttext text texttext text text" +
                        "text text texttext text texttext text texttext text texttext text texttext text text" +
                        "");
        Petition petition2 = new Petition("Petition to make a bike road on Street 42", "Arnold H.", "text2text2text2");
        Petition petition3 = new Petition("Petition to build a fountain in St. Peter park", "Mary C.", "text3 text3 text3");
        Petitions.add(petition1);
        Petitions.add(petition2);
        Petitions.add(petition3);
    }

    public void Remove(UUID petitionId) {                                                                       //TODO move to RepositoryImpl
        Petitions.removeIf(x -> x.getId().equals(petitionId));
        System.out.println("---------List size after remove method: " + GetAll().size() + "-----------");


    }

}
