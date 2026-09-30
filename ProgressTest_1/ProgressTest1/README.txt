## 2. Kết quả kiểm thử

### Bảng thực nghiệm Mutation Testing (TODO-8)

| # | Lỗi chèn | Test fail | Đã hoàn tác |
|---|---|---|---|
| M1 | Đổi `>= MAX_FAILED_ATTEMPTS` thành `> MAX_FAILED_ATTEMPTS` trong hàm `login()` | `login_FifthFailedAttempt_LocksAccount` | ✅ |
| M2 | Bỏ khối điều kiện `if (acc.isLocked())` trong hàm `login()` | `login_AlreadyLocked_ReturnsAccountLockedWithoutIncreasingCounter` | ✅ |
| M3 | Bỏ dòng `this.failedAttempts = 0;` trong hàm `unlock()` của lớp `Account` | `login_AfterAdminUnlock_CounterRestartsAndCanLogin` | ✅ |

![JaCoCo Coverage](docs/jacoco.png)