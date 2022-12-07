package live.cnpm_web.util;

import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.data.transaction.TransactionDB;
import live.cnpm_web.entity.account.TransactionAccount;
import live.cnpm_web.entity.transaction.Savings;
import live.cnpm_web.entity.transaction.Transfer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionUtil {
    public static void transfer(Transfer transfer) {
        transfer.setCreatedTime(LocalDateTime.now());
        TransactionDB.insert(transfer);

        // transfer money here
        // must be sync
        Double amount = transfer.getAmount();
        TransactionAccount src = transfer.getTransactionAccountSource();
        TransactionAccount dest = transfer.getTransactionAccountDestination();

        Double srcBalance = src.getBalance();
        Double destBalance = dest.getBalance();
        srcBalance -= amount;
        destBalance += amount;

        src.setBalance(srcBalance);
        dest.setBalance(destBalance);

        AccountDB.update(src);
        AccountDB.update(dest);
    }

    public static void savings(Savings savings) {
        savings.setCreatedTime(LocalDateTime.now());
        savings.setCreatedDate(LocalDate.now().plusDays(1));
        savings.setMaturityDate();
        TransactionDB.insert(savings);

        Double amount = savings.getAmount();
        TransactionAccount src = savings.getTransactionAccountSource();
        TransactionAccount dest = savings.getTransactionAccountDestination();

        Double srcBalance = src.getBalance();
        Double destBalance = dest.getBalance();
        srcBalance -= amount;
        destBalance += amount;

        src.setBalance(srcBalance);
        dest.setBalance(destBalance);

        AccountDB.update(src);
        AccountDB.update(dest);
    }
}
