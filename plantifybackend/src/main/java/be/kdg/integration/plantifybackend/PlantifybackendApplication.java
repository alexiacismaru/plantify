package be.kdg.integration.plantifybackend;

import be.kdg.integration.plantifybackend.domain.hibernate.ArduinoDao;
import be.kdg.integration.plantifybackend.repository.ArduinoRepository;
import be.kdg.integration.plantifybackend.repository.ArduinoRepositoryHibernate;
import be.kdg.integration.plantifybackend.service.ArduinoService;
import be.kdg.integration.plantifybackend.service.ArduinoServiceImplementation;
import be.kdg.integration.plantifybackend.service.PlantService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class PlantifybackendApplication{

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(PlantifybackendApplication.class,args);

		// archiving functionality
		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
		ses.scheduleAtFixedRate(context.getBean(PlantService.class)::updateDBArchive,
				0, 24, TimeUnit.HOURS);




	}
}
