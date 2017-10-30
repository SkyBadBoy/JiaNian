package wtb.smUtil;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;  
import org.springframework.web.context.request.ServletRequestAttributes;

import wtb.core.model.Students;
import wtb.core.model.Users;  
public class SessionUtils {
	public static Object getSessionAttribute(String key) {  
        return ((ServletRequestAttributes) RequestContextHolder  
                .getRequestAttributes()).getRequest().getSession()  
                .getAttribute(key);  
    }  
  
    public static void setSessionAttribute(String key, Object object) {  
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())  
                .getRequest().getSession().setAttribute(key, object);  
    }  
    
    public static Students getStudentSession(HttpServletRequest req){
    	Students stu=(Students)req.getSession().getAttribute("StudentName");
    	return stu;
    }
    
    public static Users getAdminSession(HttpServletRequest req){
    	Users stu=(Users) req.getSession().getAttribute("UserName");
    	return stu;
    }
}
