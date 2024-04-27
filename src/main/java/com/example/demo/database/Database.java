package com.example.demo.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;

/*
    docker run -d --rm --name mysql-demo-java-spring -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_USER=dqhuyy -e MYSQL_PASSWORD=123456 -e MYSQL_DATABASE=test_db -p 3309:3306 --volume mysql-demo-java-spring:/var/lib/mysql mysql:latest
*/

@Configuration
public class Database {
    // simple logging facade for java
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                // Product product1 = new Product("Mac", 2020, 2400.0, "");
                // Product product2 = new Product("Win", 2020, 2400.0, "");
                // logger.info("insert data" + productRepository.save(product1));
                // logger.info("insert data" + productRepository.save(product2));
            }
        };
    }
}
