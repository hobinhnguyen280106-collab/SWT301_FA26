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
        // Arrange chung cho toàn bộ test case
        service = new AccountService();
    }

    // ==========================================
    // 1. Kiểm thử isValidEmail
    // ==========================================

    @ParameterizedTest(name = "Email hợp lệ: {0}")
    @ValueSource(strings = {
            "john@example.com",
            "alice.b@mail.co.uk",
            "carol_99@domain.io"
    })
    @DisplayName("isValidEmail trả về true với email đúng định dạng")
    void isValidEmail_ValidEmails_ReturnsTrue(String email) {
        // Act
        boolean result = service.isValidEmail(email);

        // Assert
        assertTrue(result);
    }

    @ParameterizedTest(name = "Email không hợp lệ: \"{0}\"")
    @CsvSource(value = {
            "bobmail.com",        // thiếu @
            "missing@dot",        // thiếu .domain
            "'@nodomain.com'",    // thiếu tên người dùng trước @
            "' '",                // chỉ khoảng trắng
            "NULL"                // giá trị null
    }, nullValues = "NULL")
    @DisplayName("isValidEmail trả về false với email sai định dạng hoặc null")
    void isValidEmail_InvalidEmails_ReturnsFalse(String email) {
        // Act & Assert
        assertFalse(service.isValidEmail(email));
    }

    // ==========================================
    // 2. Kiểm thử registerAccount với CSV File
    // ==========================================

    @ParameterizedTest(name = "Dòng {index}: ({0}, {1}, {2}) => {3}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    @DisplayName("registerAccount với dữ liệu lấy từ test-data.csv")
    void registerAccount_FromCsv(String username, String password, String email, boolean expected) {
        // Act
        boolean actual = service.registerAccount(username, password, email);

        // Assert
        assertEquals(expected, actual,
                () -> String.format("(%s, %s, %s) phải trả về %s", username, password, email, expected));
    }

    // ==========================================
    // 3. Kiểm thử Edge Cases (Giá trị biên & Null)
    // ==========================================

    @Test
    @DisplayName("registerAccount: password = 6 ký tự (biên không hợp lệ) => false")
    void registerAccount_PasswordExactly6Chars_ReturnsFalse() {
        // Arrange
        String username = "bob";
        String password = "abcdef"; // đúng 6 ký tự
        String email = "bob@mail.com";

        // Act
        boolean actual = service.registerAccount(username, password, email);

        // Assert
        assertFalse(actual, "Password phải lớn hơn 6 ký tự");
    }

    @Test
    @DisplayName("registerAccount: password = 7 ký tự (biên hợp lệ) => true")
    void registerAccount_PasswordExactly7Chars_ReturnsTrue() {
        // Arrange
        String username = "bob";
        String password = "abcdefg"; // đúng 7 ký tự
        String email = "bob@mail.com";

        // Act
        boolean actual = service.registerAccount(username, password, email);

        // Assert
        assertTrue(actual, "Password 7 ký tự thỏa mãn điều kiện > 6");
    }

    @Test
    @DisplayName("registerAccount: tất cả tham số đều null => false")
    void registerAccount_AllNull_ReturnsFalse() {
        // Act
        boolean actual = service.registerAccount(null, null, null);

        // Assert
        assertFalse(actual);
    }
}