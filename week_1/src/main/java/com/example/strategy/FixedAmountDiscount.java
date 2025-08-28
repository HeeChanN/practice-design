package com.example.strategy;

import java.math.BigDecimal;

public class FixedAmountDiscount implements DiscountStrategy {

    private final BigDecimal amount;

    public FixedAmountDiscount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("amount must be >= 0");
        }
        this.amount = amount;
    }
    @Override
    public BigDecimal discount(BigDecimal originalPrice) {
        if (originalPrice == null || originalPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("originalPrice must be >= 0");
        }
        return amount.min(originalPrice);
    }
}
