package wtb.core.controller;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import wtb.core.model.WeChatGroupPart;
import wtb.core.model.WeChatPublic;
import wtb.core.service.WeChatGroupPartService;
import wtb.core.service.WeChatPublicService;
import wtb.smUtil.FKSequenceGenerator;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;

/**
 * wtb_users
 */
@Controller
@RequestMapping("weChatGroupPart")
public class WeChatGroupPartController  extends BaseController {

	@Autowired
	private WeChatGroupPartService weChatGroupPartService;
	@Autowired
	private WeChatPublicService weChatPublicService;
	FKSequenceGenerator FKS=new FKSequenceGenerator();
	SmBaseUtil amBaseUtil = SmBaseUtil.getInstance();
	Calendar ca=Calendar.getInstance();
	


	@RequestMapping(value = "/getWeChatGroupPartList", method = RequestMethod.GET)
	public @ResponseBody
	List<WeChatGroupPart> getWeChatGroupPartList(HttpServletRequest request ){
		List<WeChatGroupPart>lswe=null;
			String GroupID = request.getParameter("GroupID");

			Map<String, Object> responseMap = new HashMap<String, Object>();
			Map<String, Object> checkParammap = new HashMap<String, Object>();
			checkParammap.put("Status", 1);
			if (GroupID != null && !GroupID.isEmpty()) {
				checkParammap.put("GroupID", Long.parseLong(GroupID));
			}
			lswe = weChatGroupPartService.getWeChatGroupPartList(checkParammap);
			responseMap.put("data", lswe);
		return lswe;
	}
	
	
	
	@RequestMapping(value = "/deleteWeChatGroupPart", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteWeChatGroupPart(HttpServletRequest request){
		Map<String, Object> responseMap = new HashMap<String, Object>();
			String WeChat = request.getParameter("wid");
			String[] wids = WeChat.split(",");
			int result = 0;

			Map<String, Object> responsePartMap;
			for (String id : wids) {
				responseMap = new HashMap<String, Object>();
				responsePartMap = new HashMap<String, Object>();
				if (!id.isEmpty()) {
					responseMap.put("ID", Long.parseLong(id));
					List<WeChatGroupPart> lswpp = weChatGroupPartService.getWeChatGroupPartList(responseMap);
					if (lswpp.size() > 0) {
						responsePartMap = new HashMap<String, Object>();
						responsePartMap.put("ID", lswpp.get(0).getWeChatID());
						List<WeChatPublic> lswcl = ReadWeChatPublicService.getWeChatPublicList(responsePartMap);
						if (lswcl.size() > 0) {
							WeChatPublic wcl = lswcl.get(0);
							wcl.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
							weChatPublicService.updateWeChatPublic(wcl);
						}
						weChatGroupPartService.deleteWeChatGroupPart(responseMap);
						result++;
					}

				}
			}
			responseMap = new HashMap<String, Object>();
			responseMap.put("result", result);
		return responseMap;
	}
}