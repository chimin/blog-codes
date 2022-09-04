package c4compile.springdocdemo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class DivideRequest {
    /**
     * The dividend.
     */
    private int dividend;

    /**
     * The divisor.
     */
    private int divisor;
}
