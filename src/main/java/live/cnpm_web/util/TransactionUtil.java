package live.cnpm_web.util;

import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.data.transaction.TransactionDB;
import live.cnpm_web.entity.account.TransactionAccount;
import live.cnpm_web.entity.transaction.Savings;
import live.cnpm_web.entity.transaction.Transfer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.TimerTask;

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

    public static void breakSavings(Savings savings) {
        savings.setEndTime(LocalDateTime.now());
        savings.setStatus(Savings.SavingsStatus.BREAK);
        TransactionDB.update(savings);

        Double amount = savings.getAmount();
        TransactionAccount src = savings.getTransactionAccountSource();
        TransactionAccount dest = savings.getTransactionAccountDestination();
        Double penaltyInterest = savings.getPenaltyInterest();
        long dateDiff = ChronoUnit.DAYS.between(savings.getCreatedDate(), savings.getEndTime().toLocalDate());

        Double srcBalance = src.getBalance();
        Double destBalance = dest.getBalance();
        double amountDiff = amount;

        if (savings.getCreatedDate().isBefore(savings.getEndTime().toLocalDate())) {
            amountDiff = amount + amount * penaltyInterest * dateDiff / 365;
        }

        srcBalance += amountDiff;
        destBalance -= amountDiff;

        src.setBalance(srcBalance);
        dest.setBalance(destBalance);

        AccountDB.update(src);
        AccountDB.update(dest);
    }

    public static void completeSavings(Savings savings) {
        savings.setEndTime(LocalDateTime.now());
        savings.setStatus(Savings.SavingsStatus.FINISH);
        TransactionDB.update(savings);

        Double amount = savings.getAmount();
        TransactionAccount src = savings.getTransactionAccountSource();
        TransactionAccount dest = savings.getTransactionAccountDestination();
        Double interest = savings.getInterest();
        long dateDiff = ChronoUnit.DAYS.between(savings.getCreatedDate(), savings.getMaturityDate());

        Double srcBalance = src.getBalance();
        Double destBalance = dest.getBalance();
        double  amountDiff = amount + amount * interest * dateDiff / 365;

        srcBalance += amountDiff;
        destBalance -= amountDiff;

        src.setBalance(srcBalance);
        dest.setBalance(destBalance);

        AccountDB.update(src);
        AccountDB.update(dest);
    }

    public static void renewBaseSavings(Savings savings) {
        completeSavings(savings);

        // renew savings with another savings
        Savings newSavings = new Savings(savings.getTransactionAccountSource(), savings.getName(), savings.getAmount(),  savings.getTerm().getNum(), Savings.RolledOver.NO.getNum());
        savings(newSavings);
    }

    public static void renewBaseAndInterestSavings(Savings savings) {
        savings.setEndTime(LocalDateTime.now());
        savings.setStatus(Savings.SavingsStatus.FINISH);
        TransactionDB.update(savings);

        Double amount = savings.getAmount();
        TransactionAccount src = savings.getTransactionAccountSource();
        TransactionAccount dest = savings.getTransactionAccountDestination();
        Double interest = savings.getInterest();
        long dateDiff = ChronoUnit.DAYS.between(savings.getCreatedDate(), savings.getMaturityDate());

        Double srcBalance = src.getBalance();
        Double destBalance = dest.getBalance();
        double  amountDiff = amount + amount * interest * dateDiff / 365;

        srcBalance += amountDiff;
        destBalance -= amountDiff;

        src.setBalance(srcBalance);
        dest.setBalance(destBalance);

        AccountDB.update(src);
        AccountDB.update(dest);

        Savings newSavings = new Savings(savings.getTransactionAccountSource(), savings.getName(), amountDiff,  savings.getTerm().getNum(), Savings.RolledOver.NO.getNum());
        savings(newSavings);
    }

}
