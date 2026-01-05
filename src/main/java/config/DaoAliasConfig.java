package config;

import dao.DaoApi;
import dao.IDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoAliasConfig {

    // Uncommenting this bean would create a conflict if 'dao' is also defined
    // elsewhere
    // or it serves as a specific Override if this config is loaded.

    // @Bean(name = "dao")
    // public IDao daoAlias(DaoApi target) {
    // return target;
    // }
}
