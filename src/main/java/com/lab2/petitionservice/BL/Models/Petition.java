package com.lab2.petitionservice.BL.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Petition {

    private UUID Id;
    private String Title;
    private String Author;
    private String Text;
    private Date DateCreated;
    private Date DateExpiring;
    private List<Vote> Votes;

    public List<Vote> getVotes(){
        return Votes;
    }

    public void Vote(Vote vote){
        Votes.add(vote);
    }

    public UUID getId() {
        return Id;
    }

    public String getTitle(){
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public String getText() {
        return Text;
    }

    public int getVoteCount() {
        return Votes.size();
    }

    public Date getDateCreated() {
        return DateCreated;
    }

    public Date getDateExpiring() {
        return DateExpiring;
    }



    public Petition(String Title, String Author, String Text){
        Id = UUID.randomUUID();
        this.Title = Title;
        this.Author = Author;
        this.Text = Text;
        DateCreated = new Date();
        //Calendar myCalendar = new GregorianCalendar(2014, 2, 11);

        Date endDate = new Date();
        endDate.setMonth(endDate.getMonth() + 1);
        DateExpiring = endDate;

        Votes = new ArrayList<>();

    }



}
