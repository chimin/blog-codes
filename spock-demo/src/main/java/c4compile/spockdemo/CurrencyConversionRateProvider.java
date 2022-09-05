package c4compile.spockdemo;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class CurrencyConversionRateProvider {
    private final RestTemplate restTemplate = new RestTemplateBuilder()
            .rootUri("http://some-currency-conversion-website.com")
            .build();

    public BigDecimal getConversionRate(String currency) {
        return new BigDecimal(restTemplate.getForObject("/currency-rate/{currency}", String.class, currency));
    }
}
