package wtb.core.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import wtb.core.model.Students;
import wtb.smUtil.IdWorker;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;
import wtb.core.model.Feedback;
import wtb.core.model.ProdPictures;

@Controller
@RequestMapping("Feedback")
public class FeedbackController extends BaseController {

	@RequestMapping(value = "/phoneFeedback", method = RequestMethod.GET)
	public ModelAndView Feedback(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model) {
		return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneSinaFeedback");
	}

	@RequestMapping(value = "/FeedbackList", method = RequestMethod.GET)
	public ModelAndView FeedbackList(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model) {
		return new ModelAndView(SmBaseGlobal.WebViewPath + "FeedbackList");
	}

	@RequestMapping(value = "/getFeedbackList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getFeedbackList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {

		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		List<Feedback> lswe = ReadFeedbackService.getFeedbackList(checkParammap);
		for (int i = 0; i < lswe.size(); i++) {
			if (lswe.get(i).getStudent() != null) {
				lswe.get(i).setName(lswe.get(i).getStudent().getName());
			} else {
				lswe.get(i).setName("游客");
				Students students=new Students();
				students.setName("游客");
				students.setSchool("-");
				students.setPhone("-");
				lswe.get(i).setStudent(students);
			}
		}
		int Prodcount = ReadFeedbackService.getFeedbackCount(checkParammap);
		responseMap.put("Data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", Prodcount);
		return responseMap;
	}

	@RequestMapping(value = "/sendFeedback", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getCommentList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Students user = (Students) request.getSession().getAttribute("StudentName");
		String content = request.getParameter("content");
		Feedback feedback = new Feedback();
		Long ID = new IdWorker(1, 0).nextId();
		feedback.setID(ID);
		feedback.setContent(SmBaseUtil.URLDecoderString(content));
		feedback.setStatus(1);
		feedback.setUserID(user==null?0:user.getID());
		feedback.setCreateTime(sdf.format(new Date()));
		int i = FeedbackService.addFeedback(feedback);
		if (i == 1) {
			responseMap.put("Status", true);
		} else {
			responseMap.put("Status", false);
		}
		return responseMap;
	}
	
	@RequestMapping(value = "/phoneCreateCard", method = RequestMethod.GET)
	public ModelAndView phoneCreateCard(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model) {
		List<String> strList=new ArrayList<String>();
		strList.add("笔灰染白了您的青丝岁月，岁月加深了您的皱纹。孩子们，在时光荏苒中长大成人，只有您还守候着那份辛劳。祝您教师节快乐。");
		strList.add("这条短信的目的是提醒你，今天是九月十日教师节，你该发条祝福短信给我，什么？我不是你老师？孔子不是说三人行必有我师吗？我俩天天在一块，应该能算你老师吧！");
		strList.add("您是知识的超链接，是我们学习的主页。我们这“移动硬盘”有了您的教导才丰富。送上最诚挚的祝福：教师节快乐！");
		strList.add("老师，赛雄狮；女老师，赛西施。教我们求真务实，实事求是！祝老师们时时开心，事事顺心，世世代代受人爱戴尊敬！");
		strList.add("感谢您的关怀，感谢您的帮助，感谢您为我所做的一切。请接受学生美好的祝愿，祝您教师节快乐，天天快乐！");
		strList.add("小小讲台，霜染发；桃李满园，遍天下。腰背虽弯，精神飒；培育栋梁，报国家。祝您教师节快乐。");
		strList.add("教师佳节，学子欢庆，真诚祝福，老师年轻，愿天下的老师，平安幸福，好运永驻，个人魅力四射，才华横溢一身，欢天喜地，做美丽的天使，节日快乐！");
		strList.add("经历了人生的坎坷，才知道您的可贵；走上了辉煌的舞台，才知道你的伟大；在这个属于您的日子里，我想对您说：谢谢您了，我尊敬的老师。");
		strList.add("如果老师是树，我就是一片树荫。如果老师是一盏灯，我就是舍不得燃烧的灯芯。如果老师是星光，我就是星光心中的明星。愿您平安健康，节日快乐！");
		strList.add("亲爱的老师：感谢您用心血和汗水为我做的一切，您的美好身影在学生心中永远不会磨灭。");
		strList.add("您把人生的春天奉献给了芬芳的桃李，却给自己留下了冬的干净");
		strList.add("敬爱的老师，您的教诲如春风，似瑞雨，永铭我心.我虔诚地祝福您：安康，如意！");
		strList.add("三尺讲台，染苍苍白发，桃李满园，露美美笑厣。赞美您，敬爱的老师，祝福您，敬爱的老师！");
		strList.add("高山在欢呼，流水在歌唱；太阳在欢笑，小草在舞蹈：赞美您，为人师表，劳苦功高！");
		strList.add("不敢用华丽的辞藻来修饰你的光环；不敢用鲜艳的花朵来感谢你的恩赐；我只能用我的真心来歌颂你的为人师表！");
		strList.add("教师节，已来到，老师们，大家好！我祝你们节日里“师”分快乐，“师”分健康，“师”分幸福，“师”分成功，更祝你们桃李满天下！");
		strList.add("我这份美好的祝福通过电波，跨过重重高山，越过滔滔江水，掠过高楼大厦，飞到您的身边：祝您教师节快乐！");
		strList.add("梦想展翅高飞，铭记您的教诲，前路不再迷茫，勇敢不再躲藏，未来我会奋力向前闯！祝老师节日快乐，学生一定会加油！");
		strList.add("老师我永远记得您对我的教诲，正是您让我明白了做人的道理。");
		strList.add("因为有了您的引导，才有了梦想和希望；因为有了您的鼓励，才有了喜悦和丰收。在这属于您的日子里，道声：老师，您节日快乐！");
		strList.add("湖水因为有天鹅而优雅恬淡，天空因为有白云而浪漫温存，高山因为有碧水而更加巍峨，人生因为有老师而不断进步！老师，祝您节日愉快！");
		strList.add("您用生命的光亮，点燃我对知识的渴望；您用人性的光辉，让我懂得为人处世的原则；我最最亲爱的老师，节日快乐。");
		strList.add("老师：在新春之际，学生送你一幅对联：两袖清风琴剑书画文韬武略，一身正气马列毛邓教书育人。");
		strList.add("都说你是人类灵魂的工程师；都说你是传播知识的天使；都说你是蜡烛，燃烧自己照亮别人；都说你是粉笔，牺牲自己书写希望。老师，节日快乐！");
		strList.add("我要用最温馨的爱，报答老师的传业之恩，用最深情的关切，祝福老师笑口常开，用最感激的行动，让老师事事顺心愉快。教师节，送上我最真诚的祝福！");
		strList.add("“成功=您的教导+我们的努力”。您把公式填了一半给了我们，我们也会出色地完成另一半送给您，相信我们！送上最诚挚的祝福：教师节快乐！");
		strList.add("那些已经被用的烂熟的词不想再重复在你的身上，用最直接简单的话语告诉你：老师，你给予了我们很多，我们感谢您！");
		strList.add("一路上有您的教导，才不会迷失方向；一路上有您的关注，才更加的自信勇敢...老师，谢谢您！");
		strList.add("您把哲学教给了我们，我们的思考加了力度！您把做人教给了我们，我们的人生加了色彩！送上最诚挚的祝福：教师节快乐！");
		strList.add("亲爱的老师，教师节了，送一捧清凉的秋露滋润您的生活，祝您安康；送你一束鲜花点缀你的美好，祝您幸福。节日快乐！");
		model.addAttribute("blessing", strList.get((int)(Math.random()*strList.size())));
		//for (int i = 0; i < 100; i++) {
		///	drawStringForImage(strList.get((int)(Math.random()*strList.size())),req,ProdPicturesService);
		//}
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneCreateCard");
	}
	@RequestMapping(value = "/phoneCreateCard", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> CreateCard(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		ProdPictures i=drawStringForImage(request,ProdPicturesService);
		if (i != null) {
			responseMap.put("Status", true);
			responseMap.put("Data", i);
		} else {
			responseMap.put("Status", false);
		}
		return responseMap;
	}

	@RequestMapping(value = "/deleteFeedback", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteFeedback(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = null;

		String pid = request.getParameter("pid");
		String[] wids = pid.split(",");
		int result = 0;

		for (String id : wids) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				responseMap.put("ID", Long.parseLong(id));
				FeedbackService.deleteFeedback(responseMap);
				result++;
			}
		}

		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}
	
	/**
	 * @Description:给图片添加文字信息
	 * @author:liuyc
	 * @time:2016年5月31日 上午10:23:36
	 */
	public static wtb.core.model.ProdPictures drawStringForImage (HttpServletRequest request, wtb.core.service.ProdPicturesService ProdPicturesService) {
		String targetFile=request.getSession().getServletContext().getRealPath("/")+"upload/111_"+UUID.randomUUID()+".jpg";
		ProdPictures resultPic=null;
		String content = SmBaseUtil.URLDecoderString(request.getParameter("content"));
		String teacher = SmBaseUtil.URLDecoderString(request.getParameter("teacher"));
		String name = SmBaseUtil.URLDecoderString(request.getParameter("name"));
		Students user = (Students) request.getSession().getAttribute("StudentName");
		if((name==null || name.isEmpty())&&user!=null){
			name = user.getName();
		}
		if(teacher==null || teacher.isEmpty()){
			teacher="尊敬的老师";
		}else{
			teacher+="老师";
		}
		int index_img=(int) (Math.random() * 10);;
		//index_img=19;
		String filePath=request.getSession().getServletContext().getRealPath("/")+"images/teacherDay/"+(index_img+1)+".jpg";
		List<Integer> temp_fontsizes=new ArrayList<Integer>()
		{{add(27);add(27);add(27);add(27);add(27);add(27);add(27);add(27);add(27);
		add(27);add(27);add(27);add(27);add(27);add(27);add(27);add(27);add(27);add(27);add(27);}};
		
		List<Integer> temp_fontlenghts=new ArrayList<Integer>()
		{{add(25);add(25);add(18);add(25);add(25);add(25);add(25);add(25);add(30);add(18);
		;add(25);add(25);add(25);add(25);add(22);add(25);add(25);add(25);add(25);add(25);}};
		
		List<Integer[]> temp_colors=new ArrayList<Integer[]>()
		{{add(new Integer[]{116, 99, 75});add(new Integer[]{0, 0, 0});add(new Integer[]{101, 101, 101});add(new Integer[]{0, 0, 0});
		add(new Integer[]{114, 57, 38});add(new Integer[]{53, 53, 53});add(new Integer[]{101, 101, 101});add(new Integer[]{255, 255, 255});add(new Integer[]{53, 53, 53});add(new Integer[]{101, 101, 101});
		add(new Integer[]{101, 101, 101});add(new Integer[]{255, 255, 255});add(new Integer[]{101, 101, 101});add(new Integer[]{101, 101, 101});add(new Integer[]{101, 101, 101});
		add(new Integer[]{101, 101, 101});add(new Integer[]{101, 101, 101});add(new Integer[]{101, 101, 101});add(new Integer[]{255, 255, 255});add(new Integer[]{119, 105, 89});}};
		
		List<Integer> temp_begin_ys=new ArrayList<Integer>()
		{{add(360);add(660);add(500);add(400);add(550);add(550);add(550);add(550);add(680);add(480);
		add(630);add(520);add(600);add(170);add(580);add(660);add(460);add(560);add(630);add(630);}};
		
		List<Integer> temp_begin_xs=new ArrayList<Integer>()
		{{add(100);add(100);add(450);add(100);add(100);add(110);add(110);add(110);add(60);add(500);
		add(100);add(100);add(100);add(800);add(60);add(100);add(100);add(100);add(100);add(100);}};
		
		List<Integer> temp_end_xs=new ArrayList<Integer>()
		{{add(100);add(120);add(120);add(100);add(100);add(100);add(100);add(100);add(30);add(120);
		add(100);add(100);add(100);add(100);add(230);add(100);add(100);add(100);add(100);add(100);}};
		
		List<Integer> temp_direction=new ArrayList<Integer>()
		{{add(0);add(0);add(1);add(0);add(0);add(0);add(0);add(0);add(0);add(1);
		add(0);add(0);add(0);add(1);add(0);add(0);add(0);add(0);add(0);add(0);}};
		
		int temp_fontsize=temp_fontsizes.get(index_img);
		int temp_fontlenght=temp_fontlenghts.get(index_img);
		Integer[] temp_color=temp_colors.get(index_img);
		int temp_begin_y=temp_begin_ys.get(index_img);
		int temp_begin_x=temp_begin_xs.get(index_img);
		int temp_end_x=temp_end_xs.get(index_img);
		ImageIcon imgIcon = new ImageIcon(filePath);
		Image theImg = imgIcon.getImage();
		int width = theImg.getWidth(null) == -1 ? 200 : theImg.getWidth(null);
		int height = theImg.getHeight(null) == -1 ? 200 : theImg.getHeight(null);
		BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		List<String> strs=new ArrayList<String>();
		int textCount=1;
		if(content.length()>temp_fontlenght){
			textCount=content.length()/temp_fontlenght+1;
		}
		if(temp_direction.get(index_img)==1){
			strs.add(teacher);
			//String[] str_temp=content.split(",，。.;；、？?！!:：");
			//这么替换，是为了拆分后，还能保存标点符号，//可再优化 
			String line=content;
			line = line.replaceAll("、", "、|");
			line = line.replaceAll("，", "，|");
			line = line.replaceAll("。", "。|");
			line = line.replaceAll("；", "；|");
			line = line.replaceAll("？", "？|");
			line = line.replaceAll("！", "！|");
			line = line.replaceAll(",", ",|");
			line = line.replaceAll("\\.", ".|");
			line = line.replaceAll(";", ";|");
			line = line.replaceAll("\\?", "?|");
			line = line.replaceAll("!", "!|");
			String[] str_temp = line.split("\\|");
			String tempstr="";
			textCount=1;
			
			for(int i=0;i<str_temp.length;i++){
				tempstr=str_temp[i];
				if(tempstr.length()>temp_fontlenght){
					textCount=tempstr.length()/temp_fontlenght+1;
				}
				if(tempstr.length()>temp_fontlenght){
					for(int k=0;k<textCount;k++){
						int endindex=(k*temp_fontlenght)+(temp_fontlenght-1);
						if(endindex>tempstr.length()){
							endindex=tempstr.length();
						}
						if((k+1)==textCount){
							tempstr = tempstr.substring(k*temp_fontlenght, endindex);
						}else{
							strs.add(tempstr.substring(k*temp_fontlenght, endindex));
						}
						
					}
				}
				if(tempstr.length()<10 && i<str_temp.length-1 && str_temp[i+1].length()<10){
					strs.add(tempstr+str_temp[++i]);
				}else if(tempstr.length()<10 && i>0 && strs.get(strs.size()-1).length()<10){
					strs.set(strs.size()-1, strs.get(strs.size()-1)+tempstr); 
					//strs.add(tempstr+str_temp[++i]);
				}else{
					strs.add(tempstr);
				}
				
			}
			strs.add("您的学生："+name);
			strs.add("二零一七年九月十日");
			if(strs.size()<6){
				temp_begin_x=temp_begin_x-100;		
			}else if(strs.size()<8){
				temp_begin_x=temp_begin_x-50;		
			}
		}else{
			for(int i=0;i<textCount;i++){
				int endindex=(i*temp_fontlenght)+(temp_fontlenght-1);
				if(endindex>content.length()){
					endindex=content.length();
				}
				strs.add(content.substring(i*temp_fontlenght, endindex));
			}
		}
		
		
		
		Graphics2D g = bimage.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g.setColor(new Color(temp_color[0], temp_color[1], temp_color[2]));
		g.setBackground(Color.red);
		g.drawImage(theImg, 0, 0, null);
		// 设置字体、字型、字号
		String str_name="宋体";
		g.setFont(new Font(str_name, Font.BOLD, temp_fontsize));
		if(temp_direction.get(index_img)==0){
			g.drawString(teacher, temp_begin_x, temp_begin_y-50);
			// 写入文字
			for(int j=0;j<strs.size();j++){
				g.drawString(strs.get(j), temp_begin_x, temp_begin_y+(j*50)); 
			}
			g.drawString("您的学生："+name, width-(temp_end_x+(name.length()+5)*30), temp_begin_y+(strs.size()*50)); 
			g.drawString("2017年9月10日", width-(temp_end_x+240), temp_begin_y+(strs.size()*50)+50); 
		}else{
			for(int j=0;j<strs.size();j++){
				String[] temp_str=strs.get(j).split("");
				System.err.println(temp_str);
				for(int i_str=0;i_str<temp_str.length;i_str++){
					System.err.println(temp_str[i_str]);
					g.drawString(temp_str[i_str], temp_begin_x-(j*50), temp_begin_y+(i_str*30)); 
				}
				
			}
		}
		
		g.dispose();
		
		
		  ByteArrayOutputStream bs = new ByteArrayOutputStream();  
		  ImageOutputStream imOut; 
		  InputStream is = null; 
	        try { 
	            imOut = ImageIO.createImageOutputStream(bs); 
	             
	            ImageIO.write(bimage, "jpg",imOut); 
	             
	            is= new ByteArrayInputStream(bs.toByteArray()); 
				String realurl=SmBaseGlobal.UploadAliYunFileService(is, "jpg", "Images");
				
				ProdPictureController piccon=new ProdPictureController();
				
				resultPic = piccon.addPicture(realurl, filePath, 0, 0);
				resultPic.setType(SmBaseGlobal.PictureType.Game.getid());
				ProdPicturesService.addPictures(resultPic);
				return resultPic;
	        } catch (Exception e) { 
	            e.printStackTrace(); 
	            return null;
	        }  
	        
/*
		FileOutputStream out =null;
		try {
			out = new FileOutputStream(targetFile);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
			param.setQuality(100, true);
			encoder.encode(bimage, param);
			out.flush();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					out = null;
					throw new RuntimeException(e);
					
				}
			}
			return resultPic;
		}
			*/
	} 
}
