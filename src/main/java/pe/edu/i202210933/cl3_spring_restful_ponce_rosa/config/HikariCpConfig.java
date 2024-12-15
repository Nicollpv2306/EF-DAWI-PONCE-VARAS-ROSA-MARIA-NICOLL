package pe.edu.i202210933.cl3_spring_restful_ponce_rosa.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HikariCpConfig {
    @Value("${DB_FABRIC_URL}")
    private String dbFabricUrl;
    @Value("${DB_FABRIC_USER}")
    private String dbFabricUser;
    @Value("${DB_FABRIC_PASS}")
    private String dbFabricPass;
    @Value("${DB_FABRIC_DRIVER}")
    private String dbFabricDriver;

    @Bean
    public HikariDataSource hikariDataSource() {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(dbFabricUrl);
        config.setUsername(dbFabricUser);
        config.setPassword(dbFabricPass);
        config.setDriverClassName(dbFabricDriver);

        config.setMaximumPoolSize(20);
        config.setMinimumIdle(5);
        config.setIdleTimeout(300000);
        config.setConnectionTimeout(30000);

        System.out.println("###### HikariCP initialized ######");
        return new HikariDataSource(config);

    }

}
