package c4compile.swaggercodegendemo;

import c4compile.swaggercodegendemo.generated.api.MathApi;
import c4compile.swaggercodegendemo.generated.models.DivideRequest;
import c4compile.swaggercodegendemo.generated.models.DivideResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController implements MathApi {
    @Override
    public ResponseEntity<DivideResponse> divide(DivideRequest body) {
        return ResponseEntity.ok(new DivideResponse()
                .quotient(body.getDividend() / body.getDivisor())
                .remainder(body.getDividend() % body.getDivisor()));
    }
}
