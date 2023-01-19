package com.retailer.rewards;

import com.retailer.rewards.model.Transaction;
import com.retailer.rewards.service.RewardsService;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class RewardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RewardsApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(RewardsService repository) {
        return args -> {
            repository.save(new Transaction("CUST-001", new BigDecimal("120"), LocalDate.of(2021, 1, 10)));
            repository.save(new Transaction("CUST-001", new BigDecimal("80"), LocalDate.of(2021, 2, 10)));
            repository.save(new Transaction("CUST-001", new BigDecimal("150"), LocalDate.of(2021, 3, 10)));
            repository.save(new Transaction("CUST-002", new BigDecimal("200"), LocalDate.of(2021, 1, 20)));
            repository.save(new Transaction("CUST-002", new BigDecimal("120"), LocalDate.of(2021, 2, 20)));
            repository.save(new Transaction("CUST-002", new BigDecimal("180"), LocalDate.of(2021, 3, 20)));

            repository.save(new Transaction("CUST-001", new BigDecimal("120"), LocalDate.of(2022, 5, 10)));
            repository.save(new Transaction("CUST-001", new BigDecimal("80"), LocalDate.of(2022, 6, 10)));
            repository.save(new Transaction("CUST-001", new BigDecimal("150"), LocalDate.of(2022, 7, 10)));
            repository.save(new Transaction("CUST-002", new BigDecimal("200"), LocalDate.of(2022, 5, 20)));
            repository.save(new Transaction("CUST-002", new BigDecimal("120"), LocalDate.of(2022, 6, 20)));
            repository.save(new Transaction("CUST-002", new BigDecimal("180"), LocalDate.of(2022, 7, 20)));
        };
    }

}
