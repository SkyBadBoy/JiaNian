package wtb.core.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import wtb.core.model.GameQuestion;
import wtb.core.model.Notices;
import wtb.core.model.QuestionLimit;
import wtb.core.model.QuestionRecord;
import wtb.core.model.QuestionStat;
import wtb.core.model.SerchParam;
import wtb.core.model.Students;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;
import wtb.smUtil.SmBaseGlobal.WeMoneyClassify;
import wtb.smUtil.SmBaseGlobal.WeMoneyType;

@Controller
@RequestMapping("GameQuestion")
public class GameQuestionController extends BaseController {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping(value = "/addGameQuestion", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> addGameQuestion(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String pid = request.getParameter("pid");
		String content =  request.getParameter("content");// 评论内容
		String type =  request.getParameter("type");// 评论内容
		String title =  request.getParameter("title");
		String answer = request.getParameter("answer");
		String imageUrl = request.getParameter("imageUrl");
		boolean isnew=false;
		// 0为游客
		GameQuestion GameQuestion = new GameQuestion();
		Long InID = SmBaseUtil.CreateNewID();
		if(content==null){
			content="";
		}
		if(pid!=null && !pid.isEmpty() && !pid.equals("0")){
			responseMap = new HashMap<String, Object>();
			responseMap.put("ID", pid);
			List<GameQuestion> number=ReadGameQuestionService.queryGameQuestion(responseMap);
			if(number.size()>0){
				GameQuestion=number.get(0);
			}else{
				pid="0";
			}
		}
		if(pid==null || pid.isEmpty()|| pid.equals("0")){
			isnew=true;
			responseMap = new HashMap<String, Object>();
			responseMap.put("Type", type);
			int number=ReadGameQuestionService.findGameQuestion(responseMap);
			GameQuestion.setNumber(number+1);
			GameQuestion.setID(InID);
			GameQuestion.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
		}
		
		
		GameQuestion.setAnswer(answer);
		GameQuestion.setTitle(title);
		GameQuestion.setImageUrl(imageUrl);
		GameQuestion.setType(Integer.parseInt(type));
		
		GameQuestion.setContent(SmBaseUtil.URLDecoderString(content));
		

		int i =0;
		if(isnew){
			i=GameQuestionService.addGameQuestion(GameQuestion);
		}else{
			i=GameQuestionService.changeGameQuestion(GameQuestion);
		}
		if (i == 1) {
			
			responseMap.put("status", true);
			responseMap.put("Message", "添加成功");
		} else {
			responseMap.put("status", false);
			responseMap.put("Message", "添加失败");
		}
		return responseMap;
	}

	@RequestMapping(value = "/phoneGameQuestionRank", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> phoneGameQuestionRank(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ParseException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Students user = (Students) request.getSession().getAttribute("StudentName");
		String type = request.getParameter("type");
		if(type==null || type.isEmpty()){
			type="0";
		}
		responseMap.put(QuestionStat.attributeQuestionStatType, type);
		responseMap.put(SerchParam.attributeRand, SmBaseUtil.Random());
		responseMap.putAll(SmBaseUtil.AddPageParam(request));
		List<QuestionStat>lsque=ReadQuestionStatService.queryQuestionStatRank(responseMap);
		for (int i = 0; i < lsque.size(); i++) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("ID", lsque.get(i).getStudentID());
			List<Students>list=ReadStudentsService.getStudentsList(param);
			if(list.size()>0){
				lsque.get(i).setStudent(list.get(0));
			}else{
				lsque.get(i).setStudent(new Students());
			}
			lsque.set(i,lsque.get(i));
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("Status",true);
		responseMap.put("data",lsque);
		
		return responseMap;
	}
	
	
	@RequestMapping(value = "/phoneGetGameQuestion", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> phoneGetGameQuestion(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ParseException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		Students user = (Students) request.getSession().getAttribute("StudentName");
		String type = request.getParameter("type");
		if(type==null || type.isEmpty()){
			type="0";
		}
		
		long aid=0;
		param.put(QuestionLimit.attributeQuestionLimitType, type);
		param.put(QuestionLimit.attributeQuestionLimitStudentID, user.getID());
		List<QuestionLimit> result=ReadQuestionLimitService.queryQuestionLimit(param);
		if(result.size()<=0){
			result = new ArrayList<QuestionLimit>();
			QuestionLimit ql=new QuestionLimit();
			ql.setID(SmBaseUtil.CreateNewID());
			ql.setLimitNo(SmBaseUtil.Random());
			ql.setLimitNumber(100);
			ql.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
			ql.setStudentID(user.getID());
			ql.setType(Integer.parseInt(type));
			ql.setVersion(0);
			QuestionLimitService.addQuestionLimit(ql);
			result.add(ql);
		}
		responseMap.put(QuestionRecord.attributeQuestionRecordStudentID, user.getID());
		responseMap.put(QuestionRecord.attributeQuestionRecordType, type);
		responseMap.put(SerchParam.attributePageInfoStart, 0);
		responseMap.put(SerchParam.attributePageInfoLimit, 1);
		responseMap.put(SerchParam.attributeRand, SmBaseUtil.Random());
		List<QuestionRecord>lsque=ReadQuestionRecordService.queryQuestionRecord(responseMap);
		if(lsque.size()>0){
			aid=lsque.get(0).getQuestionID();
		}
		responseMap = new HashMap<String, Object>();
		if(aid!=0){
			responseMap.put(GameQuestion.attributeGameQuestionID, aid);
		}else{
			responseMap.put(GameQuestion.attributeGameQuestionType, type);
			responseMap.put(GameQuestion.attributeGameQuestionNumber, 1);
		}
		List<GameQuestion> GameQuestions = ReadGameQuestionService.queryGameQuestion(responseMap);
		responseMap = new HashMap<String, Object>();
		if(GameQuestions.size()>0){
			if(aid>=543){
				responseMap.put("Status",false);
				return responseMap;
			}
			/* 判断是否购买服务 */
			if(false){//GameQuestions.get(0).getNumber()>result.get(0).getLimitNumber()
				responseMap.put("Status",false);
				responseMap.put("IsLimit",true);
			}else{
				responseMap.put("data", GameQuestions.get(0));
				if(lsque.size()<=0){
					WriteAnswerRecord(GameQuestions.get(0),user.getID());
				}
				responseMap.put("Status",true);
			}
		}else{
			responseMap.put("Status",false);
		}
		
		return responseMap;
	}
	
	@RequestMapping(value = "/phoneCheckAnswer", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> CheckAnswer(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ParseException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		Students user = (Students) request.getSession().getAttribute("StudentName");
		String pid = request.getParameter("pid");
		String answer = request.getParameter("answer");
		if(pid==null || pid.isEmpty()){
			pid="139";
		}
		
		responseMap = new HashMap<String, Object>();
		responseMap.put(GameQuestion.attributeGameQuestionID, pid);
		List<GameQuestion> GameQuestions = ReadGameQuestionService.queryGameQuestion(responseMap);
		responseMap = new HashMap<String, Object>();
		
		if(GameQuestions.size()>0){
			param = new HashMap<String, Object>();
			param.put(QuestionStat.attributeQuestionStatType, GameQuestions.get(0).getType());
			param.put(QuestionStat.attributeQuestionStatStudentID,user.getID());
			QuestionStat qt=new QuestionStat();
			List<QuestionStat> qts = ReadQuestionStatService.queryQuestionStat(param);
			if(qts.size()<=0){
				qt.setID(SmBaseUtil.CreateNewID());
				qt.setIntegral(0);
				qt.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
				qt.setStudentID(user.getID());
				qt.setType(GameQuestions.get(0).getType());
				QuestionStatService.addQuestionStat(qt);
				
			}else{
				qt=qts.get(0); 
			}
			if(GameQuestions.get(0).getAnswer().equals(answer)){
				/* 答题正确,记录正确信息 */
				GameQuestion gg=new GameQuestion();
				gg.setRightCount(1);
				gg.setID(GameQuestions.get(0).getID());
				GameQuestionService.changeGameQuestionRightCount(gg);
				responseMap.put("Status",true);
				param = new HashMap<String, Object>();
				param.put(GameQuestion.attributeGameQuestionType, GameQuestions.get(0).getType());
				param.put(GameQuestion.attributeGameQuestionNumber, (GameQuestions.get(0).getNumber()+1));
				List<GameQuestion> GameQs = ReadGameQuestionService.queryGameQuestion(param);
				if(GameQs.size()>0){
					WriteAnswerRecord(GameQs.get(0),user.getID());
					responseMap.put("data",GameQs.get(0));
				}
				QuestionStat temp=new QuestionStat();
				temp.setID(qt.getID());
				temp.setRightCount(1);
				temp.setIntegral(1);
				QuestionStatService.changeQuestionStatRightCount(temp);
				int rightCount=(qt.getRightCount()+1);
				int wemoney=0;
				if(rightCount>0 && (rightCount%100==0)){//每答对100题奖励答题数的微米
					wemoney=rightCount;
					NoticesController lsn=new NoticesController();
					Notices notice=new Notices();
					notice.setID(GameQuestions.get(0).getID());
					lsn.AddWeMoneyRecord(WeMoneyService, WeMoneyRecordService, ReadWeMoneyService, user.getID(), (long)wemoney/3, notice, WeMoneyType.Added , "游戏答对奖励",WeMoneyClassify.None,0);
				}
				responseMap.put("wemoney", wemoney);
				return responseMap;
			}else{
				/* 答题错误,记录错误情况 */
				param = new HashMap<String, Object>();
				param.put(QuestionRecord.attributeQuestionRecordStudentID, user.getID());
				param.put(QuestionRecord.attributeQuestionRecordQuestionID, pid);
				List<QuestionRecord>lsque=ReadQuestionRecordService.queryQuestionRecord(param);
				if(lsque.size()>0){
					lsque.get(0).setAnswerResult(lsque.get(0).getAnswerResult()+1);
					QuestionRecordService.changeQuestionRecord(lsque.get(0));
				}
				GameQuestion gg=new GameQuestion();
				gg.setRightCount(0);
				gg.setID(GameQuestions.get(0).getID());
				GameQuestionService.changeGameQuestionRightCount(gg);
				QuestionStat temp=new QuestionStat();
				temp.setID(qt.getID());
				temp.setRightCount(0);
				QuestionStatService.changeQuestionStatRightCount(temp);
			}
		}
		
		responseMap.put("Status",false);
		return responseMap;
	}
	private void WriteAnswerRecord(GameQuestion gq,long uid){
		QuestionRecord questionRecord=new QuestionRecord(SmBaseUtil.CreateNewID());
		questionRecord.setAnswerResult(0);
		questionRecord.setQuestionID(gq.getID());
		questionRecord.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
		questionRecord.setStudentID(uid);
		questionRecord.setType(gq.getType());
		questionRecord.setVersion(0);
		try{
			QuestionRecordService.addQuestionRecord(questionRecord);
		}catch (Exception e) {
			QuestionRecordService.changeQuestionRecord(questionRecord);
		}
	}
	
	@RequestMapping(value = "/getGameQuestionPCList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getGameQuestionPCList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException,
			ParseException {

		Map<String, Object> responseMap = new HashMap<String, Object>();
		String nid = request.getParameter("nid");
		if (nid!=null&&nid!="") {
			responseMap.put("NoticesID", nid);
		}
		// responseMap.put("AreaID", AreaID);
		responseMap.putAll(SmBaseUtil.AddPageParam(request));
		List<GameQuestion> GameQuestions = ReadGameQuestionService.queryGameQuestion(responseMap);
		int Prodcount = ReadGameQuestionService.findGameQuestion(responseMap);
		

		responseMap = new HashMap<String, Object>();
		responseMap.put("Data", GameQuestions);
		responseMap.put("Status", 1);
		responseMap.put("total", Prodcount);
		return responseMap;
	}

	@RequestMapping(value = "/deleteGameQuestion", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteWeChatPublic(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = null;

		String pid = request.getParameter("pid");
		
		String[] wids = pid.split(",");
		int result = 0;
		GameQuestion game=new GameQuestion();
		for (String id : wids) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				game.setID( Long.parseLong(id));
				int count=GameQuestionService.removeGameQuestion(game);
				result++;
			}
		}

		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}

	@RequestMapping(value = "/GameQuestionList", method = RequestMethod.GET)
	public ModelAndView WeChatPublicList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		return new ModelAndView(SmBaseGlobal.WebViewPath + "GameQuestionList");
	}
	
	@RequestMapping(value = "/phoneGameRank_1", method = RequestMethod.GET)
	public ModelAndView phoneGameRank_1(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		String type = request.getParameter("type");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		if(type==null || type.isEmpty()){
			type="0";
		}
		Students user = (Students) request.getSession().getAttribute("StudentName");
		responseMap.put(QuestionStat.attributeQuestionStatStudentID, user.getID());
		responseMap.put(QuestionStat.attributeQuestionStatType, type);
		responseMap.put(SerchParam.attributeRand, SmBaseUtil.Random());
		List<QuestionStat>lsque=ReadQuestionStatService.queryQuestionStatRankBySelf(responseMap);
		if(lsque.size()<=0){
			lsque = new ArrayList<QuestionStat>();
			lsque.add(new QuestionStat());
		}
		model.addAttribute("data", lsque.get(0));
		model.addAttribute("type", type);
		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneGameRank_1");
	}

}
