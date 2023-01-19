package com.retailer.rewards.model;

import java.time.Month;
import java.util.Map;
import lombok.Data;

@Data
public class RewardsResponse {
    private String customerId;
    private int totalPoints;
    private Map<String, Integer> monthWisePoints;
}
