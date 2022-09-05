package c4compile.spockdemo

import spock.lang.Specification

class SomeTest extends Specification {
    def "convert should success"() {
        given:
            final currencyConversionRateProvider = Mock(CurrencyConversionRateProvider) {
                getConversionRate("A") >> BigDecimal.valueOf(2)
                getConversionRate("B") >> BigDecimal.valueOf(3)
            }
            final currencyConverter = new CurrencyConverter(currencyConversionRateProvider)

        when:
            final result = currencyConverter.convert(BigDecimal.valueOf(amount), "A", "B")

        then:
            result == BigDecimal.valueOf(expectedResult)

        where:
            amount | expectedResult
            2      | 3
            4      | 6
    }
}
