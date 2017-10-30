package wtb.smUtil;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class MySSLSocketFactory extends SSLSocketFactory {
    
    static{  
        mySSLSocketFactory = new MySSLSocketFactory(createSContext());  
    }  
      
    private static MySSLSocketFactory mySSLSocketFactory = null;  
      
      
    private static SSLContext createSContext(){  
        SSLContext sslcontext = null;  
        try {  
            sslcontext = SSLContext.getInstance("SSL");  
        } catch (NoSuchAlgorithmException e) {  
    	
			ErrorUtil.HandleError(null, "wtb.smUtil.MySSLSocketFactory.createSContext", e);
		
        }  
        try {  
            sslcontext.init(null, new TrustManager[]{new TrustAnyTrustManager()}, null);  
        } catch (KeyManagementException e) {  
    	
			ErrorUtil.HandleError(null, "wtb.smUtil.MySSLSocketFactory.createSContext", e);
		
        }  
        return sslcontext;  
    }  
      
    private MySSLSocketFactory(SSLContext sslContext) {  
        super(sslContext);  
        this.setHostnameVerifier(ALLOW_ALL_HOSTNAME_VERIFIER);  
    }  
      
    public static MySSLSocketFactory getInstance(){  
        if(mySSLSocketFactory != null){  
            return mySSLSocketFactory;  
        }else{  
            return mySSLSocketFactory = new MySSLSocketFactory(createSContext());  
        }  
    }  
}
