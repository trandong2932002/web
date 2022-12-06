package live.cnpm_web.util;

public class JSONMessage {
    boolean status;
    String message;

    public JSONMessage(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}
