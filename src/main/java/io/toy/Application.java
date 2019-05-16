package io.toy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
