package com.soda.nse.batch.config;
import com.soda.nse.batch.model.Users;
import com.soda.nse.batch.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class MongoConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {

        return strings -> {
            userRepository.save(new Users(1, "Shanmugam", "dev", 3000L));
            userRepository.save(new Users(2, "Anitha", "dev", 8000L));
            userRepository.save(new Users(3, "Sam", "dev", 6000L));
            userRepository.save(new Users(4, "Rayan", "dev", 5000L));
            userRepository.save(new Users(5, "Raghu", "dev", 4000L));

        };
    }
}
