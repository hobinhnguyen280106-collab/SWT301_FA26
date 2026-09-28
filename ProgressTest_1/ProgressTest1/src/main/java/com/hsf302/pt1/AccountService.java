package com.hsf302.pt1;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class AccountService {
    public static final int MAX_FAILED_ATTEMPTS = 5;
    public static final int PASSWORD_HISTORY_SIZE = 3;
    public static final int MIN_AGE = 18;

    public AccountService() { /* TODO: khởi tạo các Map */ }
    public ResultCode unlockAccount(String username) { throw new UnsupportedOperationException("TODO"); }
    // ... register, login, changePassword, requestPasswordReset, resetPassword,
    //     disableAccount, findByUsername, isLocked như mục 5.3
}
