package br.edu.infnet.receitafacil.ReceitaFacil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ReceitaFacilApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceitaFacilApplication.class, args);
	}
}
