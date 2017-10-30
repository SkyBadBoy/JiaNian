package wtb.core.data;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;

public class test 
{

	public static void main(String[] args) throws SocketException, UnknownHostException, UnsupportedEncodingException, ParseException {
	
		
		String a="/WeNews/UserInfo";
		String[] b=a.split("/");
		
		String c=b[1];
		System.out.println(c);
		DateFormat d1=DateFormat.getDateInstance();
		d1.format(new Date());
		//System.out.println(d1.format(new Date()));
	
	
	
	}
}
