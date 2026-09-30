# SWT301 - ProgressTest 1: Unit Testing với JUnit 5
**Module:** Account Management  
**Sinh viên:** Hồ Bình Nguyên - DE200093

---

## 1. Hướng dẫn chạy và xuất báo cáo

Chạy toàn bộ test suite và tự động tạo báo cáo độ bao phủ mã nguồn (JaCoCo):

```bash
mvn clean test


2. Báo cáo Độ bao phủ Mã nguồn (JaCoCo Coverage)
Dựa trên kết quả đo lường từ JaCoCo:

Line Coverage (Tổng): 90% (vượt mốc yêu cầu 80%)

Branch Coverage (Tổng): 90% (vượt mốc yêu cầu 70%)

AccountService: Instructions 99%, Branch 98%, Line 100%

AccountValidator: Instructions 100%, Branch 98%, Line 100%

3. Bảng thực nghiệm Mutation Testing (Lỗi giả lập)

#    Lỗi chèn                                                      |Test bị FAIL tương ứng                                        Đã hoàn tác
M1   Đổi >= MAX_FAILED_ATTEMPTS thành > MAX_FAILED_ATTEMPTS trong  |login()login_FifthFailedAttempt_LocksAccount                         ✅

M2   Bỏ khối điều kiện if (acc.isLocked()) trong login()           |login_AlreadyLocked_ReturnsAccountLockedWithoutIncreasingCounter     ✅

M3Bỏ dòng this.failedAttempts = 0; trong Account.unlock()          |login_AfterAdminUnlock_CounterRestartsAndCanLogin                    ✅


4. Ma trận Truy vết Quy tắc Nghiệp vụ (Traceability Matrix)

Mã Quy tắc(BR)                   Mô tả                                                              Phương thức Test tương ứng

BR-LOG-01Đăng         nhập sai mật khẩu tăng bộ đếm, trả INVALID_CREDENTIALS              login_FailedAttemptsUnderThreshold

BR-LOG-02Sai          5 lần liên tiếp thì khóa tài khoản                                  login_FifthFailedAttempt_LocksAccount, login_CorrectPasswordAfterNFailures

BR-LOG-03Tài          khoản đang bị khóa thì nhập đúng/sai đều trả ACCOUNT_LOCKED         login_AlreadyLocked_ReturnsAccountLockedWithoutIncreasingCounter

BR-ADM-03Admin        mở khóa tài khoản và reset bộ đếm về 0                              login_AfterAdminUnlock_CounterRestartsAndCanLogin


5. Checklist Tự Đánh Giá Trước Khi Nộp Bài
A. Mã Production

[x] A1 mvn clean compile thành công không lỗi.
[x] A2 AccountValidator cài đặt đủ các hàm theo đặc tả.
[x] A3 Mật khẩu được băm SHA-256 + Salt.[x] A4 register() thực hiện đúng các kiểm tra và thứ tự.
[x] A5 login() khóa tài khoản sau 5 lần sai, reset bộ đếm khi đúng.
[x] A6 unlockAccount() mở khóa và gán failedAttempts = 0.
[x] A7 Chuẩn hóa Username/Email không phân biệt hoa thường (toLowerCase).

B. Mã Test

[x] B1 Có đủ phương thức test và @ParameterizedTest.
[x] B2 Sử dụng đa dạng các Annotation nguồn dữ liệu (@ValueSource, @CsvSource, v.v.).
[x] B3 Kiểm tra các giá trị biên (Boundary Value Analysis).
[x] B4 Kiểm tra biên 4/5 lần nhập sai và mở khóa.
[x] B6 Cấu trúc bài test rõ ràng với @Nested và @BeforeEach.
[x] B8 Tên hàm test đặt theo chuẩn method_TinhHuong_KetQua.

C. Chất lượng & Nộp bài
[x] C1 mvn clean test qua 100% (0 failures, 0 errors).
[x] C2 Đạt mục tiêu JaCoCo Line >= 80%, Branch >= 70%.
[x] C3 Hoàn thành thực nghiệm Mutation Testing với 3 lỗi giả lập.
[x] C4 Lịch sử commit rõ ràng theo chuẩn Conventional Commits.
[x] C5 Đóng gói file zip không bao gồm thư mục target/, .idea/, .vscode/.