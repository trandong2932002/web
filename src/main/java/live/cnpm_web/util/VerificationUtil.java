package live.cnpm_web.util;

import live.cnpm_web.data.verification.VerificationDB;
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

    public static Verification createVerification(String email) {
        Verification verification = new Verification();
        VerificationDB.insert(verification);

        // send email
        EmailUtil.sendEmail(email, verification.getVerificationCodeList().get(0).getCode());
        return verification;
    }

    public static String createNewCode(Verification verification, String email) {
        LocalDateTime now = LocalDateTime.now();
        if (verification.getExpiredTime().isAfter(now)) {
            VerificationCode verificationCode = new VerificationCode();
            verification.addVerificationCode(verificationCode);
            VerificationDB.update(verification);

            // make sure id of code is right (conflict: null id of verification code)
            List<VerificationCode> verificationCodeList = verification.getVerificationCodeList();
            VerificationCode lastCode = verificationCodeList.get(verificationCodeList.size() - 1);
            lastCode.setId(VerificationDB.selectVerificationCodeIdByCode(verification, lastCode.getCode()));

            // send email
            EmailUtil.sendEmail(email, lastCode.getCode());
            return "";
        } else {
            return "Phiên xác thực đã hết hạn, hãy làm lại";
        }
    }
}
