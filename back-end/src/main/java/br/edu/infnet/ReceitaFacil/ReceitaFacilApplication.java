package br.edu.infnet.ReceitaFacil;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class ReceitaFacilApplication {

	public static void main(String[] args) throws IOException {
		FirebaseOptions options = null;
		try {
			String GOOGLE_APPLICATION_CREDENTIALS = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
			ServiceAccountCredentials credentials = ServiceAccountCredentials.fromStream(new ByteArrayInputStream(GOOGLE_APPLICATION_CREDENTIALS.getBytes(StandardCharsets.UTF_8)));
			
			options = FirebaseOptions.builder()
				.setCredentials(credentials)
				.build();
			} catch(Exception e) {
				String filePath = "./src/main/resources/GOOGLE_APPLICATION_CREDENTIALS.json";
				options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(new FileInputStream(filePath)))
					.build();
			}

		try {
			FirebaseApp.getInstance();
			System.out.println("Firebase instance found...");
		} catch (IllegalStateException  e) {
			FirebaseApp.initializeApp(options);
			System.out.println("Firebase initialized...");
		}

		SpringApplication.run(ReceitaFacilApplication.class, args);
	}
}
