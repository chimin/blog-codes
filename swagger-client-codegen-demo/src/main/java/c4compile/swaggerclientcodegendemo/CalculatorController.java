package c4compile.swaggerclientcodegendemo;

import c4compile.swaggerclientcodegendemo.generated.math.api.MathApi;
import c4compile.swaggerclientcodegendemo.generated.math.models.DivideRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CalculatorController {
    private final MathApi mathApi;

    @GetMapping("divide/{dividend}/by/{divisor}")
    public int divide(@PathVariable("dividend") int dividend, @PathVariable("divisor") int divisor) {
        return mathApi.divide(new DivideRequest()
                        .dividend(dividend)
                        .divisor(divisor))
                .getQuotient();
    }
}
