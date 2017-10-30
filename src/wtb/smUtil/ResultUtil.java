package wtb.smUtil;

import java.util.HashMap;
import java.util.Map;

import wtb.smUtil.ResultUtil.ErrorCode;




public class ResultUtil {
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
	
	 private static ResultUtil instance;

	    public static ResultUtil sharedInstance() {
	        if (instance == null) {
	            instance = new ResultUtil();
	        }

	        return instance;
	    }
	    /**
	     * 错误码
	     */
	    public enum ErrorCode {
	        Unknown {   //  未知错误

	            public int getCode() {
	                return 0;
	            }
	        },

	        Parameter { //  参数错误

	            public int getCode() {
	                return 1;
	            }
	        },

	        Authorized { // 认证失败

	            public int getCode() {
	                return 2;
	            }
	        };

	        public abstract int getCode();
	    }
	    /**
	     * 其他交互错误返回值处理方法
	     * @param message 信息
	     * @return 返回错误描述的 JSON 键值
	     */


	    /**
	     * 操作成功返回值处理方法
	     * @param data 数据
	     * @return 返回操作成功描述的 JSON 键值
	     */
	    public Map<String, Object> handleCorrect(Object data) {
	        Map<String, Object> result = new HashMap<String, Object>();
	        result.put(BaseUtil.statusKey, true);
	        result.put(BaseUtil.dataKey, data);
	        return result;
	    }
	    
	    /**
	     * 提交的参数不能转换为 JSONObject 时报错
	     *
	     * @return 返回错误描述的 JSON 键值
	     */
	    public Map<String, Object> unknownError() {
	        Map<String, Object> result = new HashMap<String, Object>();
	        result.put(BaseUtil.statusKey, false);

	        Map<String, Object> data = new HashMap<String, Object>();
	        data.put(BaseUtil.codeKey, ErrorCode.Unknown.getCode());
	        data.put(BaseUtil.messageKey, "系统发生未知错误");

	        result.put(BaseUtil.dataKey, data);

	        return result;
	    }
	    /**
	     * 没有参数错误返回值处理方法
	     *
	     * @return 返回错误描述的 JSON 键值
	     */
	    public Map<String, Object> parameterError() {
	        Map<String, Object> result = new HashMap<String, Object>();
	        result.put(BaseUtil.statusKey, false);

	        Map<String, Object> data = new HashMap<String, Object>();
	        data.put(BaseUtil.codeKey, ErrorCode.Parameter.getCode());
	        data.put(BaseUtil.messageKey, "请求参数不能为空");


	        result.put(BaseUtil.dataKey, data);

	        return result;
	    }
	    
	    
	    /**
	     * 用户未登入或者认证失败时报错
	     *
	     * @return 返回错误描述的 JSON 键值
	     */
	    public Map<String, Object> authorizedError() {
	        Map<String, Object> result = new HashMap<String, Object>();
	        result.put(BaseUtil.statusKey, false);

	        Map<String, Object> data = new HashMap<String, Object>();
	        data.put(BaseUtil.codeKey, ErrorCode.Authorized.getCode());
	        data.put(BaseUtil.messageKey, "用户未能通过认证");

	        result.put(BaseUtil.dataKey, data);

	        return result;
	    }

		/**
		 * 
			 * 
			 * @Author 作者：马健
			 * @Phone  联系qq：1039510196
			 * @CreateTime 创建时间：2017年10月24日 上午10:01:38 
			 * @Details 交互错误都这边处理
		 */
	    public static Map<String, Object> otherError(String message) {
	        Map<String, Object> result = new HashMap<String, Object>();
	        result.put("wh_code", -1);
	        result.put("message", message);
	        return result;
	    }
	    
		//单个值的
		public static Map<String, Object> resultMap(int wh_code,String message,Object data){
			Map<String, Object> result=new HashMap<>();
			result.put("wh_code", wh_code);
			result.put("message", message);
			result.put("data", data);
			return result;
		}
		//单个值的
		public static Map<String, Object> resultMapToken(int wh_code,String message,Object data,String Token){
			Map<String, Object> result=new HashMap<>();
			result.put("wh_code", wh_code);
			result.put("message", message);
			result.put("data", data);
			result.put("token", Token);
			return result;
		}
		
		//用于列表
		public static Map<String, Object> resultListMap(int wh_code,String message,Object data,long Count){
			Map<String, Object> result=new HashMap<>();
			result.put("wh_code", wh_code);
			result.put("message", message);
			result.put("count", Count);
			result.put("data", data);
			return result;
		}
		//用于主页数据
		public static Map<String, Object> resultListMapHome(int wh_code,String message,Object data,long Count,long newcount){
			Map<String, Object> result=new HashMap<>();
			result.put("wh_code", wh_code);
			result.put("message", message);
			result.put("listcount", Count);
			result.put("newcount", newcount);
			result.put("data", data);
			return result;
		}

	  
}
