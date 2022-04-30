package com.lab2.petitionservice;

import com.lab2.petitionservice.BL.Abstraction.PetitionService;
import com.lab2.petitionservice.BL.Implementation.PetitionServiceImpl;
import com.lab2.petitionservice.DAL.Abstraction.PetitionRepository;
import com.lab2.petitionservice.DAL.Implementation.PetitionRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


    @Bean
    public PetitionRepository petitionRepository(){
        return new PetitionRepositoryImpl();
    }

    @Bean                                                                       //can be replaced by adding annotation @Component to PetitionServiceImpl
    public PetitionService petitionService(){
        return new PetitionServiceImpl(petitionRepository());
    }


}
