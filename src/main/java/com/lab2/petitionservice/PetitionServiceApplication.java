package com.lab2.petitionservice;

import com.lab2.petitionservice.DAL.Implementation.PetitionRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetitionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetitionServiceApplication.class, args);
        PetitionRepositoryImpl.InitDatabase();

    }

}
