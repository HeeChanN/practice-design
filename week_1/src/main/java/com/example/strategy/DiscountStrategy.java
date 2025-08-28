package com.example.strategy;
import java.math.BigDecimal;
public interface DiscountStrategy {
    BigDecimal discount(BigDecimal originalPrice);
}
