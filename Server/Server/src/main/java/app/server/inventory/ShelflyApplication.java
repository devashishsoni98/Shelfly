//package app.server.inventory;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.scheduling.annotation.EnableScheduling;
//
//@SpringBootApplication
//@EnableScheduling
//public class NexEventApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(NexEventApplication.class, args);
//	}
//
//
//}
package app.server.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication // Enables auto-configuration and component scanning
@EnableScheduling // Enables scheduling support
public class ShelflyApplication {

	public static void main(String[] args) {
		// Launches the Spring Boot application
		SpringApplication.run(ShelflyApplication.class, args);
	}
}
