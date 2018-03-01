package application;

import application.persistence.repository.UserRepository;
import com.giffing.wicket.spring.boot.context.condition.ConditionalOnWicket;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EntityScan("application.entity")
public class WicketApplication{


	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder()
			.sources(WicketApplication.class)
			.run(args);

	}

}
