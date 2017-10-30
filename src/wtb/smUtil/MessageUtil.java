package wtb.smUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

import wtb.core.model.Image;
import wtb.core.model.ImageMessage;
import wtb.core.model.Music;
import wtb.core.model.MusicMessage;
import wtb.core.model.News;
import wtb.core.model.NewsMessage;
import wtb.core.model.TextMessage;
import wtb.core.model.Voice;
import wtb.core.model.VoiceMessage;
import wtb.core.model.wxVideo;
import wtb.core.model.wxVideoMessage;



/**
 * 消息封装类
 * @author Stephen
 *
 */
public class MessageUtil {	
	
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_MUSIC = "music";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVNET = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	public static final String MESSAGE_SCANCODE= "scancode_push";
	
	/**
	 * xml转为map集合
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException{
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		
		Element root = doc.getRootElement();
		
		List<Element> list = root.elements();
		
		for(Element e : list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}
	
	/**
	 * 将文本消息对象转为xml
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
	
	/**
	 * 组装文本消息
	 * @param toUserName
	 * @param fromUserName
	 * @param content
	 * @return
	 */
	public static String initText(String toUserName,String fromUserName,String content){
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return textMessageToXml(text);
	}
	
	/**
	 * 主菜单
	 * @return
	 */
	public static String menuText(){
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎您的关注，请按照菜单提示进行操作：\n\n");
		sb.append("1、互办网介绍\n");
		sb.append("2、互办网介绍\n");
		sb.append("3、互办网介绍\n\n");
		sb.append("回复？调出此菜单。");
		return sb.toString();
	}
	
	public static String firstMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("1互办网介绍，互办网介绍互办网介绍互办网介绍互办网介绍互办网介绍互办网介绍互办网介绍");
		return sb.toString();
	}
	
	public static String secondMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("2互办网介绍，互办网介绍互办网介绍互办网介绍互办网介绍互办网介绍互办网介绍互办网介绍互办网介绍，互办网介绍互办网介绍互办网介绍互办网介绍互办网介绍互办网介绍互办网介绍");
		sb.append("2互办网介绍，互办网介绍互办网介绍互办网介绍互办网介绍互办网介绍互办网介绍互办网介绍互办网介绍，互办网介绍互办网介绍互办网介绍互办网介绍互办网介绍互办网介绍互办网介绍");
		return sb.toString();
	}
	
	public static String threeMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("没有啦我没有啦\n\n");
		sb.append("没有啦我没有啦\n");
		sb.append("没有啦我没有啦\n");
		sb.append("没有啦我没有啦\n");
		sb.append("没有啦我没有啦\n\n");
		sb.append("回复？显示主菜单。");
		return sb.toString();
	}
	/**
	 * 图文消息转为xml
	 * @param newsMessage
	 * @return
	 */
	public static String newsMessageToXml(NewsMessage newsMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new News().getClass());
		return xstream.toXML(newsMessage);
	}
	
	
	/**
	 * 图文消息的组装
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initNewsMessage(String toUserName,String fromUserName,List<News> newsList){
		String message = null;
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MESSAGE_NEWS);
		newsMessage.setArticles(newsList);
		newsMessage.setArticleCount(newsList.size());
		
		message = newsMessageToXml(newsMessage);
		return message;
	}
	
	
	public static List<News> news()
	{
		List<News> newsList = new ArrayList<News>();
		News news = new News();
		news.setTitle("互办网介绍");
		news.setDescription("互办网，互办科技互办科技");
		news.setPicUrl("http://04532882.ngrok.io/WeNewsAgency/img/17cde1040a4c40f29d6d5575cd308ea0.png");
		news.setUrl("www.baidu.com");
		newsList.add(news);
		
		news = new News();
		news.setTitle("互办网介绍2");
		news.setDescription("互办网，互办科技互办科技");
		news.setPicUrl("http://04532882.ngrok.io/WeNewsAgency/img/17cde1040a4c40f29d6d5575cd308ea0.png");
		news.setUrl("www.baidu.com");
		newsList.add(news);
		return newsList;
		
	}
	/**
	 * 图片消息转为xml
	 * @param imageMessage
	 * @return
	 */
	public static String imageMessageToXml(ImageMessage imageMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}
	
	
	/**
	 * 组装图片消息
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initImageMessage(String toUserName,String fromUserName,String MediaId){
		String message = null;
		Image image = new Image();
		
		image.setMediaId(MediaId);
		ImageMessage imageMessage = new ImageMessage();
		imageMessage.setFromUserName(toUserName);
		imageMessage.setToUserName(fromUserName);
		imageMessage.setMsgType(MESSAGE_IMAGE);
		imageMessage.setCreateTime(new Date().getTime());
		imageMessage.setImage(image);
	
		message = imageMessageToXml(imageMessage);
		return message;
	}
	
	/**
	 * 音乐消息转为xml
	 * @param musicMessage
	 * @return
	 */
	public static String musicMessageToXml(MusicMessage musicMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}


	/**
	 * 组装音乐消息
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initMusicMessage(String toUserName,String fromUserName){
		String message = null;
		Music music = new Music();
		music.setThumbMediaId("vEcXqDjaFjpsUF7j6cntzSaDE4_Z9uDhpxDGv7ZXj9JNaWpPQ1_2hGAZuWy0diIR");//缩图
		music.setTitle("see you again");
		music.setDescription("我是详情哈哈哈哈");
		music.setMusicUrl("http://04532882.ngrok.io/WeNewsAgency/music/SeeYouAgain.mp3");
		music.setHQMusicUrl("http://04532882.ngrok.io/WeNewsAgency/music/SeeYouAgain.mp3");
		
		MusicMessage musicMessage = new MusicMessage();
		musicMessage.setFromUserName(toUserName);
		musicMessage.setToUserName(fromUserName);
		musicMessage.setMsgType(MESSAGE_MUSIC);
		musicMessage.setCreateTime(new Date().getTime());
		musicMessage.setMusic(music);
		message = musicMessageToXml(musicMessage);
		return message;
	}
	
	/**
	 * 视屏消息转为xml
	 * @param musicMessage
	 * @return
	 */
	public static String videoMessageToXml(wxVideoMessage wxVideoMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", wxVideoMessage.getClass());
		return xstream.toXML(wxVideoMessage);
	}
	
	/**
	 * 组装视频消息
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initVideoMessage(String toUserName,String fromUserName){
		String message = null;
		wxVideo wxVideo = new wxVideo();
		wxVideo.setTitle("我是视屏锕锕锕锕");
		wxVideo.setDescription("我是视屏的详情");
		wxVideo.setMediaId("");//微信视屏上传的id；
		
		wxVideoMessage wxVideoMessage = new wxVideoMessage();
		wxVideoMessage.setFromUserName(toUserName);
		wxVideoMessage.setToUserName(fromUserName);
		wxVideoMessage.setMsgType(MESSAGE_VIDEO);
		wxVideoMessage.setCreateTime(new Date().getTime());
		wxVideoMessage.setWxVideo(wxVideo);
		
		message = videoMessageToXml(wxVideoMessage);
		return message;
	}
	
	
	
	/**
	 * 语音消息转为xml
	 * @param musicMessage
	 * @return
	 */
	public static String VoiceMessageToXml(VoiceMessage VoiceMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", VoiceMessage.getClass());
		return xstream.toXML(VoiceMessage);
	}
	
	/**
	 * 组装语音消息
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initVoiceMessage(String toUserName,String fromUserName){
		String message = null;
		Voice Voice = new Voice();
		Voice.setMediaId("");;//微信视屏上传的id；
		
		VoiceMessage VoiceMessage = new VoiceMessage();
		VoiceMessage.setFromUserName(toUserName);
		VoiceMessage.setToUserName(fromUserName);
		VoiceMessage.setMsgType(MESSAGE_VIDEO);
		VoiceMessage.setCreateTime(new Date().getTime());
		VoiceMessage.setVoice(Voice);
		
		message = VoiceMessageToXml(VoiceMessage);
		return message;
	}
}
