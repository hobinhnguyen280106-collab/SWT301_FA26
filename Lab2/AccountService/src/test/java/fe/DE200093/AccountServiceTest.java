package fe.DE200093;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    private AccountService service;

    @BeforeEach
    void setUp() {
        // Arrange chung: Khởi tạo instance mới trước mỗi test case
        service = new AccountService();
    }

    // ==========================================================
    // 1. KIỂM THỬ PHƯƠNG THỨC isValidEmail
    // ==========================================================

    @ParameterizedTest(name = "Email hợp lệ: {0}")
    @ValueSource(strings = {
            "john@example.com",
            "alice.b@mail.co.uk",
            "carol_99@domain.io"
    })
    @DisplayName("isValidEmail trả về true với email đúng định dạng")
    void isValidEmail_ValidEmails_ReturnsTrue(String email) {
        // Arrange đã được inject từ ValueSource

        // Act
        boolean result = service.isValidEmail(email);

        // Assert
        assertTrue(result, "Email hợp lệ phải trả về true");
    }

    @ParameterizedTest(name = "Email không hợp lệ: \"{0}\"")
    @CsvSource(value = {
            "bobmail.com",        // Thiếu ký tự @
            "missing@dot",        // Thiếu phần tên miền (.domain)
            "'@nodomain.com'",    // Thiếu phần người dùng (local part)
            "' '",                // Chỉ chứa khoảng trắng
            "NULL"                // Sẽ map về null
    }, nullValues = "NULL")
    @DisplayName("isValidEmail trả về false với email sai định dạng hoặc null")
    void isValidEmail_InvalidEmails_ReturnsFalse(String email) {
        // Act
        boolean result = service.isValidEmail(email);

        // Assert
        assertFalse(result, "Email không hợp lệ phải trả về false");
    }

    // ==========================================================
    // 2. KIỂM THỬ registerAccount VỚI FILE CSV
    // ==========================================================

    @ParameterizedTest(name = "Dòng {index}: ({0}, {1}, {2}) -> Kỳ vọng: {3}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    @DisplayName("registerAccount chạy với bộ dữ liệu từ test-data.csv")
    void registerAccount_FromCsv_ReturnsExpectedResult(String username, String password,
                                                       String email, boolean expected) {
        // Arrange: dữ liệu được nạp tự động từ CSV

        // Act
        boolean actual = service.registerAccount(username, password, email);

        // Assert
        assertEquals(expected, actual,
                () -> String.format("Đăng ký với (%s, %s, %s) phải trả về %s",
                        username, password, email, expected));
    }

    // ==========================================================
    // 3. CÁC TEST CASES ĐẶC BIỆT & GIÁ TRỊ BIÊN (EDGE CASES)
    // ==========================================================

    @Test
    @DisplayName("registerAccount: Password chính xác 6 ký tự (biên dưới không hợp lệ) -> false")
    void registerAccount_PasswordExactly6Chars_ReturnsFalse() {
        // Arrange
        String username = "validUser";
        String password = "123456"; // độ dài = 6, yêu cầu > 6
        String email = "user@mail.com";

        // Act
        boolean actual = service.registerAccount(username, password, email);

        // Assert
        assertFalse(actual, "Mật khẩu 6 ký tự không đủ điều kiện (>6 ký tự)");
    }

    @Test
    @DisplayName("registerAccount: Password chính xác 7 ký tự (biên trên hợp lệ) -> true")
    void registerAccount_PasswordExactly7Chars_ReturnsTrue() {
        // Arrange
        String username = "validUser";
        String password = "1234567"; // độ dài = 7 (> 6)
        String email = "user@mail.com";

        // Act
        boolean actual = service.registerAccount(username, password, email);

        // Assert
        assertTrue(actual, "Mật khẩu 7 ký tự phải hợp lệ");
    }

    @Test
    @DisplayName("registerAccount: Username chỉ chứa khoảng trắng (Blank) -> false")
    void registerAccount_BlankUsername_ReturnsFalse() {
        // Arrange
        String username = "   ";
        String password = "password123";
        String email = "valid@mail.com";

        // Act
        boolean actual = service.registerAccount(username, password, email);

        // Assert
        assertFalse(actual, "Username khoảng trắng không được chấp nhận");
    }

    @Test
    @DisplayName("registerAccount: Tất cả các tham số đều là null -> false")
    void registerAccount_AllParametersNull_ReturnsFalse() {
        // Arrange & Act
        boolean actual = service.registerAccount(null, null, null);

        // Assert
        assertFalse(actual, "Tất cả null phải trả về false");
    }
}
