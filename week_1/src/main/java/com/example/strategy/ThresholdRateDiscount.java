package com.example.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ThresholdRateDiscount implements DiscountStrategy{

    private final BigDecimal threshold;
    private final BigDecimal rate;

    public ThresholdRateDiscount(BigDecimal threshold, BigDecimal rate) {
        if(threshold == null || threshold.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("threshold must be >= 0");
        }
        if(rate == null || rate.compareTo(BigDecimal.ZERO) < 0 || rate.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException("rate must be in [0,1]");
        }
        this.threshold = threshold;
        this.rate = rate;
    }

    @Override
    public BigDecimal discount(BigDecimal originalPrice) {
        if (originalPrice == null || originalPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("originalPrice must be >= 0");
        }
        if (originalPrice.compareTo(threshold) < 0) {
            return BigDecimal.ZERO;
        }
        return originalPrice.multiply(rate).setScale(2, RoundingMode.HALF_UP);
    }
}
