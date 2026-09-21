package fe.DE200093;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a / b;
    }

    // Phương thức nhân cần thêm cho Bước 5
    public int multiply(int a, int b) {
        return a * b;
    }
}