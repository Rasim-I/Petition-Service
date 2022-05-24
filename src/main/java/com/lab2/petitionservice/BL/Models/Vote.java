package com.lab2.petitionservice.BL.Models;

import java.util.Date;
import java.util.UUID;

public class Vote {

    private final UUID Id;
    private final UUID PetitionId;
    private final String User;
    private final Date DateSubmitted;

    public UUID getId() {
        return Id;
    }

    public UUID getPetitionId() {
        return PetitionId;
    }

    public String getUser() {
        return User;
    }

    public Date getDateSubmitted() {
        return DateSubmitted;
    }

    public Vote(UUID PetitionId, String User){
        Id = UUID.randomUUID();
        this.PetitionId = PetitionId;
        this.User = User;
        DateSubmitted = new Date();

    }

}
