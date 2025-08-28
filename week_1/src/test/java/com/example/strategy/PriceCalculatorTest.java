package com.example.strategy;
import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
class PriceCalculatorTest {
    @Test
    void baseline_calculator_behaves_as_expected() {
        BaselineDiscountCalculator calc = new BaselineDiscountCalculator(BaselineDiscountCalculator.Mode.RATE);
        calc.setRate(new BigDecimal("0.10"));
        assertThat(calc.finalPrice(new BigDecimal("10000"))).isEqualByComparingTo("9000.00");
        calc = new BaselineDiscountCalculator(BaselineDiscountCalculator.Mode.FIXED);
        calc.setAmount(new BigDecimal("3000"));
        assertThat(calc.finalPrice(new BigDecimal("10000"))).isEqualByComparingTo("7000");
        calc = new BaselineDiscountCalculator(BaselineDiscountCalculator.Mode.THRESHOLD);
        calc.setAmount(new BigDecimal("10000"));
        assertThat(calc.finalPrice(new BigDecimal("10000"))).isEqualByComparingTo("7000");
    }
    @Test
    void strategy_can_swap_behavior_without_changing_client_code() {
        PriceCalculator calculator = new PriceCalculator(new RateDiscount(new BigDecimal("0.10")));
        assertThat(calculator.finalPrice(new BigDecimal("10000"))).isEqualByComparingTo("9000.00");
        calculator.setStrategy(new FixedAmountDiscount(new BigDecimal("3000")));
        assertThat(calculator.finalPrice(new BigDecimal("10000"))).isEqualByComparingTo("7000");
    }

    @Test
    void threshold_rate_discount_applies_only_when_price_is_over_threshold() {
        DiscountStrategy ds = new ThresholdRateDiscount(new BigDecimal("10000"), new BigDecimal("0.10"));
        PriceCalculator calc = new PriceCalculator(ds);


        assertThat(calc.finalPrice(new BigDecimal("9000"))).isEqualByComparingTo("9000");
        assertThat(calc.finalPrice(new BigDecimal("12000"))).isEqualByComparingTo("10800.00");
    }
}
