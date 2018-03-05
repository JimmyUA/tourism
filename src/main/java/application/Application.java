package application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EntityScan("application.entity")
public class Application {


	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder()
			.sources(Application.class)
			.run(args);

	}

}
