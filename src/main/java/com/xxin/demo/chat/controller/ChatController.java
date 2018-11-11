package com.xxin.demo.chat.controller;

import com.xxin.demo.contants.WechatConstant;
import com.xxin.demo.chat.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @GetMapping("/index")
    public String index(){
        return "/chat/index";
    }
    @GetMapping("/code")
    @ResponseBody
    public String getCode(String scope,String state){
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx670ee8b4536fb8b0&redirect_uri=https://www.sweetxxin.top/WeChat/&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        return url;
    }
    @GetMapping("/accessToken")
    @ResponseBody
    public String getAccessToken(String code){
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx670ee8b4536fb8b0&secret=49249bd45597b38ea09cedd07e5ec909&code=+"+code+"&grant_type=authorization_code";
        String res = HttpUtil.httpGet(url,null);
        return res;
    }
}
