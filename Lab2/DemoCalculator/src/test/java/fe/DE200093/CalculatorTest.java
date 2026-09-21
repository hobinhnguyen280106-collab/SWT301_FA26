package fe.DE200093;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @ParameterizedTest(name = "Test {index} => {0} * {1} = {2}")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    @DisplayName("multiply: kiểm thử với nhiều bộ dữ liệu từ CSV")
    void multiply_VariousInputs_ReturnsProduct(int a, int b, int expected) {
        int actual = calculator.multiply(a, b);
        assertEquals(expected, actual, () -> a + " * " + b + " phải bằng " + expected);
    }
}