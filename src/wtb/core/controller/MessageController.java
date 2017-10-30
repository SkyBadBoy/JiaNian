package wtb.core.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import wtb.core.model.ApplyList;
import wtb.core.model.Messages;
import wtb.core.model.Users;
import wtb.core.service.MessagesService;
import wtb.core.service.UsersService;
import wtb.smUtil.IdWorker;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;

/**
 * wtb_users
 */
@Controller
@RequestMapping("/Message")
public class MessageController extends BaseController {

	@RequestMapping(value = "/addMessage", method = RequestMethod.GET)
	public ModelAndView addWeChatPublic(@ModelAttribute("MessageForm") Messages Message, BindingResult result, HttpServletResponse response,
			HttpServletRequest req, HttpSession session, Model model) throws IOException {
		int Status = 0;
		String preView = null;
		String PID = req.getParameter("pid");
		preView = req.getParameter("preView");
		if (PID != null && !PID.isEmpty()) {
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("ID", Long.parseLong(PID));
			List<Messages> lsps = MessageService.getMessagesList(responseMap);
			if (lsps.size() > 0) {
				Message = lsps.get(0);
				model.addAttribute("Content", Message.getContent());
				Status = 1;
			} else {
				Status = 404;
			}
		}

		model.addAttribute("MessageForm", Message);
		model.addAttribute("Status", Status);
		if (preView != null && preView.equals("1")) {
			return new ModelAndView(SmBaseGlobal.WebViewPath + "viewMessage");
		} else {
			return new ModelAndView(SmBaseGlobal.WebViewPath + "addMessage");
		}
	}

	public void SendMessageToAdminWaitWork(read.core.service.ReadUsersService ReadUsersService, MessagesService messageService, ApplyList aply, String title, int Type) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
		responseMap.put("Level", SmBaseGlobal.LevelStatus.Manage.getid());
		List<Users> userList = ReadUsersService.getHomeUsersList(responseMap);

		for (int i = 0; i < userList.size(); i++) {

			Messages message = new Messages();
			message.setID(new IdWorker(1, 0).nextId());
			message.setContent("申请人:" + aply.getUserName() + "<br/>申请人联系方式:" + aply.getUserPhone() + "<br/>申请理由:" + aply.getContent());
			message.setTitle(title);
			message.setCreateTime(sdf.format(new Date()));
			message.setIsRead(0);
			message.setType(Type);
			message.setSrcID(String.valueOf(aply.getID()));
			message.setModifyTime(sdf.format(new Date()));
			message.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
			message.setTo(userList.get(i).getID());
			messageService.addMessages(message);
		}
	}

	@RequestMapping(value = "/MessageList", method = RequestMethod.GET)
	public ModelAndView WeChatPublicList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		return new ModelAndView(SmBaseGlobal.WebViewPath + "MessageList");
	}

	@RequestMapping(value = "/getMessageList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getMessageList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Users user = null;
		Map<String, Object> responseMap = new HashMap<String, Object>();
		user = (Users) session.getAttribute("UserName");
		String Title = request.getParameter("Title");
		String IsRead = request.getParameter("IsRead");
		String state = request.getParameter("state");
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		if (state != null && state.equals("1")) {
			checkParammap.put("Status", 1);
		}
		if (Title != null && !Title.isEmpty()) {
			checkParammap.put("Title", Title);
		}
		if (IsRead != null && !IsRead.isEmpty()) {
			checkParammap.put("IsRead", IsRead);
		} else {
			checkParammap.put("IsRead", 0);
		}

		checkParammap.put("To", user.getID());

		responseMap = new HashMap<String, Object>();
		List<Messages> lswe = MessageService.getMessagesList(checkParammap);
		int Prodcount = MessageService.getMessagesCount(checkParammap);
		responseMap.put("data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", Prodcount);

		return responseMap;
	}

}