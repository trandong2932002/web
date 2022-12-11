package live.cnpm_web.util;

import live.cnpm_web.data.BaseDB;
import live.cnpm_web.entity.transaction.savings.SavingsInterest;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

public final class SavingsInterestUtil {


    private static List<SavingsInterest> interestList;

    private SavingsInterestUtil() {
    }

    public static List<SavingsInterest> getSavingsInterestList() {
        return interestList;
    }

    public static void loadSavingsInterestList() {
        interestList = BaseDB.selectAll(SavingsInterest.class);
    }

    public static String validateSavingsInterest(String i0, String i1, String i2, String i3, String i4, String i5,
                                                 String pI0, String pI1, String pI2, String pI3, String pI4, String pI5) {
        try {
            Double.parseDouble(i0);
            Double.parseDouble(i1);
            Double.parseDouble(i2);
            Double.parseDouble(i3);
            Double.parseDouble(i4);
            Double.parseDouble(i5);

            Double.parseDouble(pI0);
            Double.parseDouble(pI1);
            Double.parseDouble(pI2);
            Double.parseDouble(pI3);
            Double.parseDouble(pI4);
            Double.parseDouble(pI5);
        } catch (NumberFormatException e) {
            return "Nhập lại lãi suất";
        }
        return "";
    }

    public static void writeSavingsInterest(String i0, String i1, String i2, String i3, String i4, String i5,
                                            String pI0, String pI1, String pI2, String pI3, String pI4, String pI5) throws FileNotFoundException, URISyntaxException {
        for (SavingsInterest x : interestList) {
            switch (x.getTerm()) {
                case ONEMONTH:
                    x.setInterest(Double.parseDouble(i0));
                    x.setPenaltyInterest(Double.parseDouble(pI0));
                    break;
                case THREEMONTHS:
                    x.setInterest(Double.parseDouble(i1));
                    x.setPenaltyInterest(Double.parseDouble(pI1));
                    break;
                case SIXMONTHS:
                    x.setInterest(Double.parseDouble(i2));
                    x.setPenaltyInterest(Double.parseDouble(pI2));
                    break;
                case TWELVEMONTHS:
                    x.setInterest(Double.parseDouble(i3));
                    x.setPenaltyInterest(Double.parseDouble(pI3));
                    break;
                case TWENTYFOURMONTHS:
                    x.setInterest(Double.parseDouble(i4));
                    x.setPenaltyInterest(Double.parseDouble(pI4));
                    break;
                case THIRTYSIXMONTHS:
                    x.setInterest(Double.parseDouble(i5));
                    x.setPenaltyInterest(Double.parseDouble(pI5));
                    break;
            }

            BaseDB.update(x);
        }
    }
}