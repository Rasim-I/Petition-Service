package com.lab2.petitionservice.Controllers;

public class PetitionWebModel {

    private String Title;
    private String Text;
    private String Author;

    public String getTitle() {
        return Title;
    }

    public String getText() {
        return Text;
    }

    public String getAuthor() {
        return Author;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setText(String text) {
        Text = text;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public PetitionWebModel(String Title, String Text, String Author){
        this.Title = Title;
        this.Text = Text;
        this.Author = Author;
    }
}
