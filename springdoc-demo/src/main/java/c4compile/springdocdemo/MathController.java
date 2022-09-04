package c4compile.springdocdemo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
    /**
     * Divide a number.
     *
     * @param request The dividend and divisor.
     * @return The quotient and remainder.
     */
    @PostMapping(value = "/divide",
            consumes = {"application/json"},
            produces = {"application/json"})
    public DivideResponse divide(@RequestBody DivideRequest request) {
        return new DivideResponse()
                .setQuotient(request.getDividend() / request.getDivisor())
                .setRemainder(request.getDividend() % request.getDivisor());
    }
}
