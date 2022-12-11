package live.cnpm_web.util;

import live.cnpm_web.data.transaction.TransactionDB;
import live.cnpm_web.entity.transaction.savings.Savings;

import java.time.LocalDate;
import java.util.List;
import java.util.TimerTask;

public class CheckSavingsEachDay extends TimerTask {
    public void run() {
        System.out.println("check savings each day running");

        List<Savings> savingsList = TransactionDB.selectAllSavingsInProgress();
        for (Savings savings : savingsList) {
            if (savings.getMaturityDate().isEqual(LocalDate.now())) {
                Savings.RolledOver rolledOver = savings.getRolledOver();
                if (rolledOver == Savings.RolledOver.NO) {
                    TransactionUtil.completeSavings(savings);
                } else if (rolledOver == Savings.RolledOver.RENEWALSBASE) {
                    TransactionUtil.renewBaseSavings(savings);
                } else if (rolledOver == Savings.RolledOver.RENEWALSBASEANDINTEREST) {
                    TransactionUtil.renewBaseAndInterestSavings(savings);
                }
            }
        }
    }
}