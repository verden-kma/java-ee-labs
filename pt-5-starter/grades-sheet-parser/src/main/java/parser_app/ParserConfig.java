package parser_app;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackageClasses = ParserConfig.class)
public class ParserConfig {

    @Bean
    @ConditionalOnProperty(value = "parse.approach", havingValue="dom", matchIfMissing = true)
    IParser getDomParser() {
        return new DOMParser();
    }

    @Bean
    @ConditionalOnProperty(value = "parse.approach", havingValue = "stax")
    IParser getStaxParser() {
        return new StAXParser();
    }
}
