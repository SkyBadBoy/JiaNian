package wtb.sessions;

import javax.servlet.http.HttpSessionListener;  
import javax.servlet.http.HttpSessionEvent;  
import javax.servlet.http.HttpSession;    
  
public class SessionListener implements HttpSessionListener {    
	private  MySessionContext myc=MySessionContext.getInstance();  
	  
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {  
		myc.AddSession(httpSessionEvent.getSession());  
	}  
	  
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {  
		HttpSession session = httpSessionEvent.getSession();  
		myc.DelSession(session);  
	}  
}
