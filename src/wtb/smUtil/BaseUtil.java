package wtb.smUtil;

public class BaseUtil {
	public static final String tokenKey    = "token";
    public static final String paramKey    = "param";
    public static final String statusKey   = "status";
    public static final String dataKey     = "data";
    public static final String codeKey     = "code";
    public static final String messageKey  = "message";
    public static final String typeKey     = "type";
    public static final String mobileKey   = "mobile";
    public static final String previousKey = "previous";
    public static final String portraitKey = "portrait";
    public static final String sessionKey  = "session";
    public static final String pageLimitKey  = "limit";
    public static final String pageStartKey  = "start";
    public static final String pageKey  = "page";

    
    /**
     * 用户状态类型
     */
    public enum UserStatus {
        Enable {    //  可用: 正常状态

            public byte getCode() {
                return 0;
            }
        },

        Disabled {  //  禁用: 一段时间后继续可以使用

            public byte getCode() {
                return 1;
            }
        },

        Invalid {   //  无效: 永久封号不允许登录系统

            public byte getCode() {
                return 2;
            }
        },

        Previous {  //  废弃: 关联的上次使用的用户

            public byte getCode() {
                return 3;
            }
        };

        public abstract byte getCode();
    }
    /**
     * 会话状态, 登录状态
     */
    public enum SessionStatus {
        OffLine {    //  离线

            public byte getCode() {
                return 0;
            }
        },

        OnLine {    //  在线

            public byte getCode() {
                return 1;
            }
        };

        public abstract byte getCode();
    }
}
