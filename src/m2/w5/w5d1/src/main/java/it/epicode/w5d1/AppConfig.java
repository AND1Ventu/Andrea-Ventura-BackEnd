package it.epicode.w5d1;

import it.epicode.w5d1.bean.Persona;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;

@Configuration
public class AppConfig {

    @Bean("p1")
    @Scope("singleton")
    public Persona getPersona(){
        Persona p = new Persona();
        p.setNome("Silvia");
        p.setCognome("Romero");
        p.setDataNascita(LocalDate.of(2020, 4, 5));
        return p;
    }

    @Bean("p2")
    @Scope("prototype")
    public Persona getPersona2(){
        Persona p = new Persona();
        p.setNome("Luca");
        p.setCognome("Romero");
        p.setDataNascita(LocalDate.of(2010, 4, 5));
        return p;
    }

}
