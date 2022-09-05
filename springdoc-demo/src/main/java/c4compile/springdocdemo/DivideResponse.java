package c4compile.springdocdemo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Response of performing division.
 */
@Getter
@Setter
@Accessors(chain = true)
public class DivideResponse {
    /**
     * The quotient.
     */
    private int quotient;

    /**
     * The remainder.
     */
    private int remainder;
}
