package c4compile.spockdemo;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CurrencyConverter {
    private final CurrencyConversionRateProvider currencyConversionRateProvider;

    public CurrencyConverter(CurrencyConversionRateProvider currencyConversionRateProvider) {
        this.currencyConversionRateProvider = currencyConversionRateProvider;
    }

    public BigDecimal convert(BigDecimal amount, String fromCurrency, String toCurrency) {
        BigDecimal fromRate = currencyConversionRateProvider.getConversionRate(fromCurrency);
        BigDecimal toRate = currencyConversionRateProvider.getConversionRate(toCurrency);
        return amount.multiply(toRate).divide(fromRate);
    }
}
