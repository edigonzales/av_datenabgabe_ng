package ch.so.agi.cadastraldatadisposal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
@SpringBootApplication
public class CadastralDataDisposalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastralDataDisposalApplication.class, args);
	}
}
