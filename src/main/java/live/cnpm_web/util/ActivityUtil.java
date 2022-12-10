package live.cnpm_web.util;

import live.cnpm_web.data.account.ActivityDB;
import live.cnpm_web.entity.account.Activity;
import live.cnpm_web.entity.account.account.BaseAccount;
import live.cnpm_web.entity.verification.Verification;
import org.apache.commons.lang3.tuple.ImmutablePair;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Enumeration;

public class ActivityUtil {
    public static ImmutablePair<Activity, String> checkGuestSessionActivity(String sessionId) {
        Activity activity = ActivityDB.selectBySessionId(sessionId);
        LocalDateTime now = LocalDateTime.now();
        if (activity == null) return new ImmutablePair<>(null, "Phiên đã hết hạn, hãy đăng nhập lại");
        else if ((activity.getEndTime() != null && activity.getEndTime().isBefore(now)) || activity.getExpiredTime().isBefore(now))
            return new ImmutablePair<>(null, "Phiên đã hết hạn, hãy đăng nhập lại");
        else return new ImmutablePair<>(activity, "");
    }

//    public static void check

    public static Activity createActivity(BaseAccount account, Verification verification, String sessionId) {
        Activity activity = new Activity(account, verification, sessionId);
        ActivityDB.insert(activity);
        return activity;
    }

    public static void clearTempSession(HttpSession session) {
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            if (attributeName.contains("temp")) {
                session.removeAttribute(attributeName);
            }
        }
    }
}
