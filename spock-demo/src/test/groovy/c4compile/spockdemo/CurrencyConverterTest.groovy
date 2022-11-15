package c4compile.spockdemo

import spock.lang.Specification

class CurrencyConverterTest extends Specification {
    def "convert should success"() {
        given:
            final currencyConversionRateProvider = Mock(CurrencyConversionRateProvider) {
                getConversionRate("usd") >> BigDecimal.valueOf(2)
                getConversionRate("myr") >> BigDecimal.valueOf(3)
            }
            final currencyConverter = new CurrencyConverter(currencyConversionRateProvider)

        when:
            final result = currencyConverter.convert(BigDecimal.valueOf(amount), "usd", "myr")

        then:
            result == BigDecimal.valueOf(expectedResult)

        where:
            amount | expectedResult
            2      | 3
            4      | 6
    }
}
