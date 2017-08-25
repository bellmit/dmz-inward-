import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.IOException;

/**
 * @author dmz
 * @date 2017/8/17
 */
@Configuration
@ComponentScan(basePackages = {"com.dmz.basic"},includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION,value = Document.class)})
public class PropertiesConfig {

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() throws IOException {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        propertyPlaceholderConfigurer.setLocations(resolver.getResource("classpath:data.properties"));
        return propertyPlaceholderConfigurer;
    }

}
