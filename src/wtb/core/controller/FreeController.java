package wtb.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import wtb.core.model.Freetest;
import wtb.core.model.Students;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;

/**
 * wtb_users
 */
@Controller
@RequestMapping("/Free")
public class FreeController extends BaseController {

	/**
	 * 
		 * 
		 * @Author 作者：马健
		 * @Phone  联系qq：1039510196
		 * @CreateTime 创建时间：2017年11月19日 下午12:34:11 
		 * @Details 免费试用
	 */
	@RequestMapping(value = "/phoneFree", method = RequestMethod.GET)
	public ModelAndView phoneFree(HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneFree");
	}
	
	/**
	 * 
		 * 
		 * @Author 作者：马健
		 * @Phone  联系qq：1039510196
		 * @CreateTime 创建时间：2017年11月19日 下午3:31:44 
		 * @Details 预约
	 */
	@ResponseBody
	@RequestMapping(value = "/phoneFree", method = RequestMethod.POST)
	public Map<String, Object> phoneFreePost(HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		Map<String, Object> map=new HashMap<>();
		Students students=(Students) request.getSession().getAttribute("StudentName");
		String phone=request.getParameter("Phone");
		String content=request.getParameter("Content");
		map.put(Freetest.COLUMN_freetest_userid, students.getID());
		List<Freetest> freetests=FreetestService.FindFreetestsByCondition(map);
		if (freetests.size()>0) {
			map.put("status", false);
			map.put("message", "你已经申请过咯，不能重复申请！");
		}else {
			Freetest freetest=new Freetest();
			freetest.setFreetestId(SmBaseUtil.CreateNewID());
			freetest.setFreetestStatus(1);
			freetest.setFreetestPhone(phone);
			freetest.setFreetestContent(content);
			freetest.setFreetestUserid(students.getID());
			freetest.setFreetestReply("无回复");
			freetest.setFreetestType(SmBaseGlobal.FreeTestType.Nowing.getid());
			int i=FreetestService.addFreetest(freetest);
			if (i>0) {
				map.put("status", true);
				map.put("message", "申请成功，稍后我们讲联系你");
			}else {
				map.put("status", false);
				map.put("message", "申请失败了，请稍后再试");
			}
		}
		return map;
	}
	

}