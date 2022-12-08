package live.cnpm_web.util;

import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.data.account.ActivityDB;
import live.cnpm_web.data.transaction.TransactionDB;
import live.cnpm_web.data.verification.VerificationDB;
import live.cnpm_web.entity.account.Activity;
import live.cnpm_web.entity.transaction.BaseTransaction;
import live.cnpm_web.entity.verification.Verification;
import live.cnpm_web.entity.verification.VerificationCode;

import java.time.LocalDateTime;
import java.util.List;

public class VerificationUtil {
    public static String verify(Verification verification, String code) {
        LocalDateTime now = LocalDateTime.now();
        if (verification.getExpiredTime().isAfter(now)) {
            // get last code in code list
            List<VerificationCode> codeList = verification.getVerificationCodeList();
            VerificationCode verificationCode = codeList.get(codeList.size() - 1);

            if (verificationCode.getExpiredTime().isAfter(now)) {
                if (verificationCode.getCode().equals(code)) {

                    verification.setStatus(true);
                    VerificationDB.update(verification);
                    return "";
                } else {
                    return "Code không chính xác";
                }
            } else {
                return "Code đã hết hạn, hãy tạo code mới";
            }
        } else {
            return "Phiên xác thực đã hết hạn, hãy làm lại";
        }
    }

    public static String createNewCode(Verification verification) {
        LocalDateTime now = LocalDateTime.now();
        if (verification.getExpiredTime().isAfter(now)) {
            VerificationCode verificationCode = new VerificationCode();
            verification.addVerificationCode(verificationCode);

//            System.out.println("-------------------" + verificationCode.getCode());

            //
            //
            //

            VerificationDB.update(verification);

            List<VerificationCode> verificationCodeList = verification.getVerificationCodeList();
            VerificationCode temp = verificationCodeList.get(verificationCodeList.size() - 1);
            temp.setId(VerificationDB.selectVerificationCodeIdByCode(verification, temp.getCode()));

            return "";
        } else {
            return "Phiên xác thực đã hết hạn, hãy làm lại";
        }
    }
}
