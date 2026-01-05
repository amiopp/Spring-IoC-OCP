package config;

import dao.IDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Map;

@Configuration
@PropertySource("classpath:app.properties")
public class PropertyDrivenConfig {

    private final Map<String, IDao> candidates;

    public PropertyDrivenConfig(Map<String, IDao> candidates) {
        this.candidates = candidates;
    }

    @Value("${dao.target:dao}")
    private String target;

    // Defines the primary "dao" bean based on the property
    @Bean(name = "dao")
    @DependsOn("propertySourcesPlaceholderConfigurer")
    public IDao selectedDao() {
        IDao bean = candidates.get(target);
        if (bean == null) {
            // Note: If profiles are active, only beans matching that profile are in
            // 'candidates'.
            // If no profiles are active, ensure 'candidates' contains all desired
            // implementations.
            throw new IllegalArgumentException(
                    "Unknown implementation: " + target + " (Available: " + candidates.keySet() + ")");
        }
        return bean;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
