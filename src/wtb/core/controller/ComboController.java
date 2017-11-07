package wtb.core.controller;

import java.io.UnsupportedEncodingException;
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

import com.sun.org.apache.xml.internal.security.Init;

import wtb.core.model.ApplyList;
import wtb.core.model.Combo;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;

/**
 * wtb_users
 */
@Controller
@RequestMapping("/Combo")
public class ComboController extends BaseController {
	
	/**
	 * 
		 * @Author 作者：马健
		 * @Phone  联系qq：1039510196
		 * @CreateTime 创建时间：2017年11月1日 下午3:24:41 
		 * @Details 套餐管理
	 */
	@RequestMapping(value = "/ComboList", method = RequestMethod.GET)
	public ModelAndView WeChatPublicList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		return new ModelAndView(SmBaseGlobal.WebViewPath + "ComboList");
	}
	
	
	/**
		 * 
		 * @Author 作者：马健
		 * @Phone  联系qq：1039510196
		 * @CreateTime 创建时间：2017年11月1日 下午3:26:28 
		 * @Details 套餐列表
	 */
	@RequestMapping(value = "/getComboList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getApplyListList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		String state = request.getParameter("Status");

		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		if (state == null) {
			checkParammap.put(Combo.COLUMN_combo_status, 1);
		} else {
			checkParammap.put(Combo.COLUMN_combo_status, state);
		}
		checkParammap.put("Rand", SmBaseUtil.Random());
		responseMap = new HashMap<String, Object>();
		int Prodcount = ComboService.getCountByCondition(checkParammap);
		List<Combo> lswe = ComboService.FindCombosByCondition(checkParammap);
		responseMap.put("data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", Prodcount);
		return responseMap;
	}
	
	/**
	 * 
		 * 
		 * @Author 作者：马健
		 * @Phone  联系qq：1039510196
		 * @CreateTime 创建时间：2017年11月1日 下午10:34:02 
		 * @Details 修改套餐
	 */
	@RequestMapping(value = "/ModifyCombo", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody
	Map<String, Object> ModifyCombo(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String pid=request.getParameter("pid");
		String title=request.getParameter("title");
		String price=request.getParameter("price");
		String comboParameter1=request.getParameter("comboParameter1");
		String comboParameter2=request.getParameter("comboParameter2");
		String comboParameter3=request.getParameter("comboParameter3");
		String comboParameter4=request.getParameter("comboParameter4");
		String comboParameter5=request.getParameter("comboParameter5");
		if (SmBaseUtil.CheckIsNull(pid)) {
			Map<String, Object> map=new HashMap<>();
			map.put(Combo.COLUMN_combo_id, pid);
			map.put("Rand", SmBaseUtil.Random());
			List<Combo> combos=ComboService.FindCombosByCondition(map);
			Combo combo=combos.get(0);
			combo.setComboTitle(title);
			combo.setComboPrice(price);
			combo.setComboParameter1(Integer.parseInt(comboParameter1));
			combo.setComboParameter2(Integer.parseInt(comboParameter2));
			combo.setComboParameter3(Integer.parseInt(comboParameter3));
			combo.setComboParameter4(Integer.parseInt(comboParameter4));
			combo.setComboParameter5(Integer.parseInt(comboParameter5));
			int i=ComboService.UpdateCombo(combo);
			if (i>0) {
				responseMap.put("status", true);
				responseMap.put("message", "修改成功");
			}else {
				responseMap.put("status", false);
				responseMap.put("message", "修改失败");
			}
		}else {
			responseMap.put("status", false);
			responseMap.put("message", "编号不能为空");
		}
		return responseMap;
	}
	
	
	
}