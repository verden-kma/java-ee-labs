package manager_app;

import manager_app.parse_resolver.DomParseMode;
import manager_app.parse_resolver.IParseModeResolver;
import manager_app.parse_resolver.StAXParseMode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import parser_app.DOMParser;
import parser_app.StAXParser;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

//    @Bean
//    @ConditionalOnBean(DOMParser.class)
//    IParseModeResolver getDOMResolver() {
//        return new DomParseMode();
//    }

//    @Bean
//    @ConditionalOnBean(StAXParser.class)
//    IParseModeResolver getStAXResolver() {
//        return new StAXParseMode();
//    }
}
