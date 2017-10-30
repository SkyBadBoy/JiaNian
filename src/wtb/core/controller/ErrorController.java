package wtb.core.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wtb.smUtil.ResultUtil;


/**
 * Created by MeetLucky on 16/5/27.
 */
@Controller
@RequestMapping("/Error")
public class ErrorController {
	
	
    //  未知错误
    @RequestMapping(value = "/Interface/Unknown")
    public
    @ResponseBody
    Map<String, Object> unknownMethod() {
        ResultUtil errorDetail = ResultUtil.sharedInstance();
        return errorDetail.unknownError();
    }

    //  参数错误
    @RequestMapping(value = "/Interface/Parameter")
    public
    @ResponseBody
    Map<String, Object> parameterMethod() {
        ResultUtil errorDetail = ResultUtil.sharedInstance();
        return errorDetail.parameterError();
    }

    //  认证失败
    @RequestMapping(value = "/Interface/Authorized")
    public
    @ResponseBody
    Map<String, Object> authorizedMethod() {
        ResultUtil errorDetail = ResultUtil.sharedInstance();
        return errorDetail.authorizedError();
    }
    //  认证失败
    @RequestMapping(value = "/Interface/JsonError")
    public
    @ResponseBody
    Map<String, Object> jsonErrorMethod() {
        ResultUtil errorDetail = ResultUtil.sharedInstance();
        return errorDetail.otherError("Json解析错误,请检查JSON是否正确");
    }
}
