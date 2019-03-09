package ch.so.agi.cadastraldatadisposal;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WelcomePageRedirect implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/").setViewName("redirect:/index.xhtml"); // vs. forward: URL does not change (but POST does not work).
      registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
