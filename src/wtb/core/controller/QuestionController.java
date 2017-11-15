package wtb.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import wtb.smUtil.SmBaseGlobal;

/**
 * wtb_users
 */
@Controller
@RequestMapping("/Question")
public class QuestionController extends BaseController {

	@RequestMapping(value = "/phoneQuestion", method = RequestMethod.GET)
	public ModelAndView WeChatPublicList(HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneQuestion");
	}
}