package br.edu.infnet.biblioteca_irlan_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaIrlanApiApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BibliotecaIrlanApiApplication.class, args);
        Loader loader = new Loader();
        loader.run(args);
	}

}
