package wtb.smUtil;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by MeetLucky on 16/5/20.
 */
public class SessionContext {
    private static SessionContext instance;
    private HashMap hashMap;

    private SessionContext() {
        this.hashMap = new HashMap();
    }

    /**
     * 获取当前会话 session 对象
     *
     * @return 返回 session 对象
     */
    public static SessionContext sharedInstance() {
        if (instance == null) {
            instance = new SessionContext();
        }

        return instance;
    }

    /**
     * 注册当前会话 session
     *
     * @param session 当前会话 session
     */
    public synchronized void signInSession(HttpSession session) {
        if (session != null) {
            session.setAttribute("UserID",session.getId());
            hashMap.put(session.getId(), session);
        }
    }

    /**
     * 注销当前会话 session
     *
     * @param session 当前会话 session
     */
    public synchronized void signOutSession(HttpSession session) {
        if (session != null && session.getAttribute("UserID")!=null) {
            session.removeAttribute("UserID");
            hashMap.remove(session);
        }
    }

    /**
     * 查找当前会话 session
     *
     * @param session 当前 session 的 id
     * @return 如果不存在返回 null 否则返回 session
     */
    public synchronized HttpSession currentSession(String session) {
        if (session == null) {
            return null;
        } else {
            return (HttpSession) hashMap.get(session);
        }
    }
}
