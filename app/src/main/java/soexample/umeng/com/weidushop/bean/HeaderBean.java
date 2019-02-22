package soexample.umeng.com.weidushop.bean;

/**
 * date:2019/2/21
 * author:冯泽林(asus)
 * function:
 */
public class HeaderBean {
    public String sessionId;
    public int userId;

    public HeaderBean(String sessionId, int userId) {
        this.sessionId = sessionId;
        this.userId = userId;
    }
}
