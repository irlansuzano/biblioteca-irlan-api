package br.edu.infnet.biblioteca_irlan_api;

import br.edu.infnet.biblioteca_irlan_api.domain.*;
import br.edu.infnet.biblioteca_irlan_api.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class BibliotecaIrlanApiApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BibliotecaIrlanApiApplication.class, args);
    }
}
