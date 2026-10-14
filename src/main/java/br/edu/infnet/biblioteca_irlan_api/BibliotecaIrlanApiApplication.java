package br.edu.infnet.biblioteca_irlan_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BibliotecaIrlanApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaIrlanApiApplication.class, args);
	}

}
