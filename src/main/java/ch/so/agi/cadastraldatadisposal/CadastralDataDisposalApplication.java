package ch.so.agi.cadastraldatadisposal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.web.filter.ForwardedHeaderFilter;
import org.springframework.nativex.hint.TypeAccess;

@TypeHint(
        types = {ch.so.agi.cadastraldatadisposal.models.Dataset.class},
        access= {TypeAccess.DECLARED_METHODS, 
              TypeAccess.DECLARED_FIELDS, 
              TypeAccess.DECLARED_CONSTRUCTORS, 
              TypeAccess.PUBLIC_METHODS,
              TypeAccess.PUBLIC_FIELDS,
              TypeAccess.PUBLIC_CONSTRUCTORS}               
)
@Configuration
@SpringBootApplication
public class CadastralDataDisposalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastralDataDisposalApplication.class, args);
	}
	
    @Bean
    ForwardedHeaderFilter forwardedHeaderFilter() {
        return new ForwardedHeaderFilter();
    }  
}
