<%@page import="com.alipay.api.AlipayApiException"%>
<%@page import="com.alipay.api.request.AlipayTradeWapPayRequest"%>
<%@page import="com.alipay.api.DefaultAlipayClient"%>
<%@page import="com.alipay.api.AlipayClient"%>
<%@page import="java.util.Date"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="wtb.smUtil.alipay.config.*"%>
<%@ page import="wtb.smUtil.alipay.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付跳转中</title>
	</head>
	<%
		////////////////////////////////////请求参数//////////////////////////////////////
        //订单名称，必填
        String subject = request.getParameter("WIDsubject");
		
        //付款金额，必填
        String total_fee =request.getParameter("WIDtotal_fee");
        //收银台页面上，商品展示的超链接，必填
        String show_url = request.getParameter("WIDshow_url");

        String passback_params = request.getParameter("attach");
        String orderNo=String.valueOf(SmBaseUtil.CreateNewID());
		/*
		
		//////////////////////////////////////////////////////////////////////////////////
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		
		sParaTemp.put("app_id", AlipayConfig.app_id);
		sParaTemp.put("method", "alipay.trade.wap.pay");
		sParaTemp.put("notify_url", URLEncoder.encode(AlipayConfig.notify_url));
		sParaTemp.put("return_url", URLEncoder.encode(AlipayConfig.return_url));
		sParaTemp.put("timestamp",SmBaseGlobal.sdfDateTime.format(new Date()));
		sParaTemp.put("version", "1.0");
		sParaTemp.put("charset", AlipayConfig.input_charset);
		
		
		sParaTemp.put("biz_content", "{" +
		        "\"out_trade_no\":\""+orderNo +"\"," +
		        "\"total_amount\":\""+ total_fee +"\"," +
		        "\"subject\":\""+ subject +"\"," +
		        "\"body\":\""+ subject +"\"," +
		        "\"payment_type\":\""+ AlipayConfig.payment_type +"\"," +
		        "\"_input_charset\":\""+ AlipayConfig.input_charset +"\"," +
		        "\"partner\":\""+ AlipayConfig.partner +"\"," +
		        "\"service\":\""+ AlipayConfig.service +"\"," +
		        "\"seller_id\":\""+ AlipayConfig.seller_id +"\"," +
		      //  "\"passback_params\":\""+ URLEncoder.encode(passback_params) +"\"," +
		        "\"product_code\":\"QUICK_WAP_PAY\"" +
		        "}");
		
		//sParaTemp.put("app_pay","Y");//启用此参数可唤起钱包APP支付。
		//
		//其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.2Z6TSk&treeId=60&articleId=103693&docType=1
		//建立请求
		System.err.println(sParaTemp);
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"post","确认");
		System.err.println(sHtmlText);
		out.println(sHtmlText);
		
		*/
		 session = request.getSession();
		session.setAttribute("show_url", show_url);
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.app_id, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.input_charset, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);
		 AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
		    alipayRequest.setReturnUrl(AlipayConfig.return_url);
		    alipayRequest.setNotifyUrl(AlipayConfig.notify_url);//在公共参数中设置回跳和通知地址
		    alipayRequest.setBizContent("{" +
			        "\"out_trade_no\":\""+orderNo +"\"," +
			        "\"total_amount\":\""+ total_fee +"\"," +
			        "\"subject\":\""+ subject +"\"," +
			      //  "\"body\":\""+ subject +"\"," +
			    //    "\"payment_type\":\""+ AlipayConfig.payment_type +"\"," +
			     //   "\"_input_charset\":\""+ AlipayConfig.input_charset +"\"," +
			  //      "\"partner\":\""+ AlipayConfig.partner +"\"," +
			   //     "\"service\":\""+ AlipayConfig.service +"\"," +
			        "\"seller_id\":\""+ AlipayConfig.seller_id +"\"," +
			       "\"passback_params\":\""+ passback_params.replace("\"", "'") +"\"," +
			        "\"product_code\":\"QUICK_WAP_PAY\"" +
			        "}");//填充业务参数
		    String form="";
		    try {
		        form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
		    } catch (AlipayApiException e) {
		        e.printStackTrace();
		    }
		    System.err.println(form);
		    response.setContentType("text/html;charset=" + AlipayConfig.input_charset);
		    response.getWriter().write(form);//直接将完整的表单html输出到页面
		    response.getWriter().flush();
		    response.getWriter().close();
		
		
	%>
	<body>
	</body>
</html>
