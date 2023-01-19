package com.retailer.rewards.service;

import com.retailer.rewards.data.TransactionRepository;
import com.retailer.rewards.model.Transaction;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * RewardsService class has one method calculatePoints, which takes in a Transaction object as a parameter.
 * The method checks the transaction amount, if the transaction amount is greater than 100,
 * it will earn 2 points for every dollar spent over $100 plus 50 points. If the transaction amount is between 50 and
 * 100,
 * it will earn 1 point for every dollar spent between $50 and $100.
 * The points earned are then set to the transaction object by calling the setPointsEarned method on transaction object.
 */
@Service
public class RewardsService {
    @Autowired
    private TransactionRepository repository;

    public Transaction save(Transaction transaction) {
        BigDecimal amount = transaction.getTransactionAmount();

        if (amount.compareTo(BigDecimal.valueOf(100)) > 0) {
            int points = amount.subtract(BigDecimal.valueOf(100)).intValue() * 2;
            points += 50;
            transaction.setPointsEarned(points);
        } else if (amount.compareTo(BigDecimal.valueOf(50)) > 0) {
            int points = amount.subtract(BigDecimal.valueOf(50)).intValue();
            transaction.setPointsEarned(points);
        }

        return repository.save(transaction);
    }

    public List<Transaction> findByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId);
    }

    public List<Transaction> findAll() {
        return repository.findAll();
    }
}
