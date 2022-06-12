package com.lab2.petitionservice.Controllers;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PetitionWebModel {

    @NotBlank
    @Size(min = 2)
    private String Title;

    @NotNull
    private String Text;

    @NotNull
    @Size(min = 2, max = 20, message = "Your name is too short or too long. Our service does not work with clients who have such names))0)")
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
