package live.cnpm_web.util;

import live.cnpm_web.entity.account.TransactionAccount;

public class ValidateTransactionUtil {
    public static String validateTransfer(TransactionAccount src, TransactionAccount dest, String s_amount) {
        Double amount;
        try {
            amount = Double.parseDouble(s_amount);
            if (amount <= 0D) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
             return "Nhập lại tiền";
        }

        if (dest == null || src == null) {
            return "Số tài khoản nhận không tồn tại";
        } else if (src.getBalance() < amount) {
            return "Số dư không đủ";
        } else if (src.getAccountNumber().equals(dest.getAccountNumber())) {
            return "Không thể chuyển cho chính mình";
        } else {
            return "";
        }
    }

    public static String validateSavings(TransactionAccount src, String s_amount) {
        Double amount;
        try {
            amount = Double.parseDouble(s_amount);
            if (amount <= 0D) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            return "Nhập lại tiền";
        }

        if (src.getBalance() < amount) {
            return "Số dư không đủ";
        } else {
            return "";
        }
    }
}
