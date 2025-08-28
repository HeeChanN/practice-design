package com.example.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RateDiscount implements DiscountStrategy {
    private final BigDecimal rate;
    public RateDiscount(BigDecimal rate) {
        if (rate == null || rate.compareTo(BigDecimal.ZERO) < 0 || rate.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException("rate must be in [0,1]");
        }
        this.rate = rate;
    }
    @Override
    public BigDecimal discount(BigDecimal originalPrice) {
        if (originalPrice == null || originalPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("originalPrice must be >= 0");
        }
        return originalPrice.multiply(rate).setScale(2, RoundingMode.HALF_UP);
    }
}
