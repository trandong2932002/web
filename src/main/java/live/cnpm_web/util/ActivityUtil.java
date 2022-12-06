package live.cnpm_web.util;

import live.cnpm_web.data.account.ActivityDB;
import live.cnpm_web.entity.account.Activity;
import live.cnpm_web.entity.account.account.BaseAccount;
import live.cnpm_web.entity.verification.Verification;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Enumeration;

public class ActivityUtil {
    public static String checkActivity(Activity activity) {
        LocalDateTime now = LocalDateTime.now();
        if (activity == null) return "null";
        if (activity.getEndTime() != null &&  activity.getEndTime().isBefore(now)) {
            return "Phiên đã hết hạn, hãy đăng nhập lại";
        } else if (activity.getExpiredTime().isAfter(now)) {
            return "";
        } else {
            return "Phiên đã hết hạn, hãy đăng nhập lại";
        }
    }

    public static Activity createActivity(BaseAccount account, Verification verification, String sessionId) {
        Activity activity = new Activity(account, verification, sessionId);
        ActivityDB.insert(activity);
        return activity;
    }

    public static void clearSession(HttpSession session) {
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            session.removeAttribute(attributeNames.nextElement());
        }
    }
}
