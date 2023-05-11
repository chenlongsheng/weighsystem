/**
 * 
 */
package com.jeeplus.modules.websoket.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;

/**
 * @author admin
 *
 */
public class ToJsonUtil {
	
	public static JSONObject buildJsonRs(boolean success, String msg, Object obj) {
		JSONObject json = new JSONObject();
		json.put("success", success ? "0" : "1");
		json.put("dataType", msg);
		json.put("datas", obj);
		return json;
	}


	public static String getUserId() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			return (String) subject.getPrincipal();
		} else {
			return "1";
		}
	}

}
