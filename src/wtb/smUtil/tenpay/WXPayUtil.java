package wtb.smUtil.tenpay;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.Transfer;

import wtb.core.model.Students;
import wtb.core.model.WeChatInfo;
import wtb.smUtil.tenpay.client.*;
import wtb.smUtil.tenpay.util.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Console;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by MeetLucky on 16/6/26.
 */
public class WXPayUtil {
    private static WXPayUtil instance;

    public static WXPayUtil sharedInstance() {
        if (instance == null) {
            instance = new WXPayUtil();
        }

        return instance;
    }

    /**
     *
     *
     *
     * @return Ping++对象
     */
    public static String pushClient(HttpServletRequest request, HttpServletResponse response, Students user, int money,String orderNo,WeChatInfo weChatInfo,String appid) throws Exception {
        String result="";
        WeChatPayRequestHandler queryReq = new WeChatPayRequestHandler(request, response);
        Students StudentsTemp = (Students) request.getSession().getAttribute("StudentName");
        queryReq.init();
        queryReq.setKey(weChatInfo.getApiKey());
        queryReq.setGateUrl(ConstantUtil.WXPay_TansfersUrl);
        queryReq.setParameter("amount",String.valueOf(money));
        queryReq.setParameter("check_name", "NO_CHECK");
        queryReq.setParameter("desc", "活动返利!");
        queryReq.setParameter("mch_appid",weChatInfo.getAppID());
        queryReq.setParameter("mchid",String.valueOf(weChatInfo.getMchID()));
        queryReq.setParameter("nonce_str",WXUtil.getNonceStr());
        if(appid!=null && !appid.isEmpty() && StudentsTemp!=null &&  StudentsTemp.getTempOpenID()!=null && !StudentsTemp.getTempOpenID().isEmpty()){
        	 queryReq.setParameter("openid", StudentsTemp.getTempOpenID());
        }else{
            queryReq.setParameter("openid", user.getOpenID());
        }
        queryReq.setParameter("partner_trade_no",orderNo);
        queryReq.setParameter("re_user_name", user.getName());
        queryReq.setParameter("spbill_create_ip", "112.124.107.167");
        queryReq.getSign();


        String WXParam=queryReq.getParamXML();
        TenpayHttpClient httpClient = new TenpayHttpClient();
        httpClient.setTimeOut(5);
        httpClient.setPostData(WXParam);

        
        String path =request.getRealPath("/WEB-INF/rootca.pem");
        if(appid!=null && !appid.isEmpty()){
        	path =request.getRealPath("/WEB-INF/"+ appid +"/rootca.pem");
        }
        httpClient.setCaInfo(new File(path));
        path =request.getRealPath("/WEB-INF/apiclient_cert.p12");
        if(appid!=null && !appid.isEmpty()){
        	path =request.getRealPath("/WEB-INF/"+ appid +"/apiclient_cert.p12");
        }
        httpClient.setCertInfo(new File(path),String.valueOf(weChatInfo.getMchID()));
        //设置请求内容
        httpClient.setReqContent(queryReq.getRequestURL());
       // System.out.println("queryReq:" + queryReq.getRequestURL());
        //后台调用
        if(httpClient.call()) {
            ClientResponseHandler queryRes = new ClientResponseHandler();
            //设置结果参数
            queryRes.setContent(httpClient.getResContent());
            result=queryRes.getContent();
            System.out.println("queryRes:" + queryRes.getContent());
        }
        return result;
    }
    public static String pushClientHB(HttpServletRequest request, HttpServletResponse response, Students user, int money,String orderNo,WeChatInfo weChatInfo) throws Exception {
        String result="";
        WeChatPayRequestHandler queryReq = new WeChatPayRequestHandler(request, response);
        queryReq.init();
        queryReq.setKey(weChatInfo.getApiKey());
        queryReq.setGateUrl(ConstantUtil.WXPay_TansfersUrl);
        queryReq.setParameter("amount",String.valueOf(money));
        queryReq.setParameter("check_name", "NO_CHECK");
        queryReq.setParameter("desc", "微新闻社返利!");
        queryReq.setParameter("mch_appid",weChatInfo.getAppID());
        queryReq.setParameter("mchid",String.valueOf(weChatInfo.getMchID()));
        queryReq.setParameter("nonce_str",WXUtil.getNonceStr());
        queryReq.setParameter("openid", user.getOpenID());
        queryReq.setParameter("partner_trade_no",orderNo);
        queryReq.setParameter("re_user_name", user.getName());
        queryReq.setParameter("spbill_create_ip", getIpAddr(request));
        queryReq.getSign();


        String WXParam=queryReq.getParamXML();
        TenpayHttpClient httpClient = new TenpayHttpClient();
        httpClient.setJkscafilename("tenpay_cacerthb.jks");
        httpClient.setTimeOut(5);
        httpClient.setPostData(WXParam);
        String path =request.getRealPath("/WEB-INF/hubanpay/rootca.pem");
        httpClient.setCaInfo(new File(path));
        path =request.getRealPath("/WEB-INF/hubanpay/apiclient_cert.p12");
        httpClient.setCertInfo(new File(path),String.valueOf(weChatInfo.getMchID()));
        //设置请求内容
        httpClient.setReqContent(queryReq.getRequestURL());
       // System.out.println("queryReq:" + queryReq.getRequestURL());
        //后台调用
        if(httpClient.call()) {
            ClientResponseHandler queryRes = new ClientResponseHandler();
            //设置结果参数
            queryRes.setContent(httpClient.getResContent());
            result=httpClient.getResContent();
            System.out.println("queryRes:" + queryRes.getContent());
        }
        return result;
    }
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


}
