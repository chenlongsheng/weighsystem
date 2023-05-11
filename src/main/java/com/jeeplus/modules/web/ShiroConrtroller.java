package com.jeeplus.modules.web;

import com.alibaba.fastjson.JSONObject;
import com.jeeplus.modules.websoket.utils.ToJsonUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ShiroConrtroller {


//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    @ResponseBody
//    public String defaultLogin() {
//        return "首页";
//    }


    @RequestMapping(value = "/login")
    @ResponseBody
    public JSONObject login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return ToJsonUtil.buildJsonRs(false, "未知账户", "");

        } catch (IncorrectCredentialsException ice) {
            return ToJsonUtil.buildJsonRs(false, "密码不正确", "");

        } catch (AuthenticationException ae) {
            return ToJsonUtil.buildJsonRs(false, "用户名或密码不正确", "");
        }
        if (subject.isAuthenticated()) {
            return ToJsonUtil.buildJsonRs(true, "登入成功", ToJsonUtil.getUserId());
        } else {
            token.clear();
            return ToJsonUtil.buildJsonRs(false, "登录失败", "");
        }
    }


    @ResponseBody
    @RequestMapping("/show")
    public String showUser() {
        System.out.println("userId:   " + ToJsonUtil.getUserId());
        return "这是学生信息";
    }
}
