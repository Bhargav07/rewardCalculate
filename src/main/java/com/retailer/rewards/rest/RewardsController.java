package com.retailer.rewards.rest;

import com.retailer.rewards.model.RewardsResponse;
import com.retailer.rewards.model.Transaction;
import com.retailer.rewards.service.RewardsService;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RewardsController {

    @Autowired
    private RewardsService rewardsService;


    /**
     * Get rewards for a specific customer.
     *
     * @param customerId the id of the customer
     * @return RewardsResponse containing total points and month wise points earned by the customer
     */

    @GetMapping("/rewards/{customerId}")
    public RewardsResponse getRewards(@PathVariable String customerId) {
        List<Transaction> transactions = rewardsService.findByCustomerId(customerId);
        Map<String, Integer> monthWisePoints = transactions.stream().collect(
            Collectors.groupingBy(t -> t.getTransactionDate().getMonth()+ "-" +t.getTransactionDate().getYear(),
                Collectors.summingInt(Transaction::getPointsEarned)));

        RewardsResponse response = new RewardsResponse();
        response.setTotalPoints(transactions.stream().mapToInt(Transaction::getPointsEarned).sum());
        response.setMonthWisePoints(monthWisePoints);
        response.setCustomerId(customerId);
        return response;
    }

    /**
     * Get rewards for all customers.
     *
     * @return List of RewardsResponse containing total points and month wise points earned by each customer
     */
    @GetMapping("/rewards")
    public List<RewardsResponse> getAllRewards() {
        List<Transaction> transactions = rewardsService.findAll();
        Map<String, Map<String, Integer>> customerWiseMonthWisePoints = transactions.stream().collect(
            Collectors.groupingBy(Transaction::getCustomerId,
                Collectors.groupingBy(t -> t.getTransactionDate().getMonth()+ "-" +t.getTransactionDate().getYear(),
                    Collectors.summingInt(Transaction::getPointsEarned))));
        return customerWiseMonthWisePoints.entrySet().stream().map(e -> {
            RewardsResponse response = new RewardsResponse();
            response.setTotalPoints(e.getValue().values().stream().mapToInt(Integer::intValue).sum());
            response.setMonthWisePoints(e.getValue());
            response.setCustomerId(e.getKey());

            return response;
        }).collect(Collectors.toList());
    }

}
