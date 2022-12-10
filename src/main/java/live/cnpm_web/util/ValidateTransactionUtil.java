package live.cnpm_web.util;

import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.entity.account.ServiceAccount;
import live.cnpm_web.entity.account.TransactionAccount;
import org.apache.commons.lang3.tuple.ImmutablePair;

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

    public static ImmutablePair<ServiceAccount, String> validateServicePayment(TransactionAccount src, String provider, String customerCode) {
        ServiceAccount serviceAccount = AccountDB.selectServiceAccountByCustomerCode(customerCode);

        if (serviceAccount == null) return new ImmutablePair<>(null, "Nhập lại mã khách hàng");
        else if (serviceAccount.getProvider().getId() != Long.parseLong(provider))
            return new ImmutablePair<>(null, "Nhập lại mã khách hàng");
        else if (serviceAccount.getStatus() == ServiceAccount.ServiceAccountStatus.STOPPED)
            return new ImmutablePair<>(null, "Mã khách hàng này đã bị khóa");
        else if (serviceAccount.getDebt() == 0) return new ImmutablePair<>(null, "Bạn không có nợ phải thanh toán");
        else if (serviceAccount.getDebt() > src.getBalance()) return new ImmutablePair<>(null, "Số dư không đủ");
        else return new ImmutablePair<>(serviceAccount, "");


    }
}
