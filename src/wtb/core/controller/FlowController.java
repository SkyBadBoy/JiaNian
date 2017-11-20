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
@RequestMapping("/Flow")
public class FlowController extends BaseController {

	@RequestMapping(value = "/phoneFlow", method = RequestMethod.GET)
	public ModelAndView phoneFlow(HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneFlow");
	}
	
	@RequestMapping(value = "/phonePlace", method = RequestMethod.GET)
	public ModelAndView phonePlace(HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phonePlace");
	}
}