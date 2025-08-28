package com.example.strategy;
import java.math.BigDecimal;
import java.math.RoundingMode;
public class BaselineDiscountCalculator {

    public enum Mode { RATE, FIXED, THRESHOLD }

    private Mode mode;

    private BigDecimal rate = BigDecimal.ZERO;
    private BigDecimal amount = BigDecimal.ZERO;

    public BaselineDiscountCalculator(Mode mode) { this.mode = mode; }

    public void setRate(BigDecimal rate) { this.rate = rate; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public BigDecimal finalPrice(BigDecimal originalPrice) {

        if (originalPrice == null || originalPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("originalPrice must be >= 0");
        }

        BigDecimal discount = BigDecimal.ZERO;

        if (mode == Mode.RATE) {
            discount = originalPrice.multiply(rate).setScale(2, RoundingMode.HALF_UP);

        }
        else if (mode == Mode.FIXED) {
            discount = amount.min(originalPrice);
        }

        BigDecimal finalPrice = originalPrice.subtract(discount);
        return finalPrice.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : finalPrice;
    }

}
