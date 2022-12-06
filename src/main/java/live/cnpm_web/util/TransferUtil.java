package live.cnpm_web.util;

import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.data.transaction.TransactionDB;
import live.cnpm_web.entity.account.TransactionAccount;
import live.cnpm_web.entity.transaction.Transfer;

import java.time.LocalDateTime;

public class TransferUtil {
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
}
