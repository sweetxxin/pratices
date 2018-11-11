package com.xxin.demo.restful.controller;

import com.xxin.demo.restful.entity.RestSession;
import com.xxin.demo.restful.entity.RestUser;
import com.xxin.demo.restful.service.RestSessionService;
import com.xxin.demo.restful.service.RestUserService;
import com.xxin.demo.restful.utils.Encrypt;
import com.xxin.demo.restful.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/restful")
public class RestfulController {
    @Autowired
    private RestSessionService sessionService;
    @Autowired
    private RestUserService userService;
    @PostMapping("/get/token")
    public JsonResult getAccessToken(@RequestParam("appId")int appId,
                                      @RequestParam("appSecret") String appSecret,
                                      @RequestParam("grant_type")String grantType,
                                      @RequestParam(value = "state",required = false)String state
                                      ){
        RestUser user = userService.getUser(appId);
        String secret = Encrypt.sha1(appSecret);
        if (user.getAppSecret().equals(secret)){
            RestSession session = sessionService.getSession(appId);
            String access_token;
            if (session !=null ){
                access_token = session.getAccessToken();
            }else{
                access_token = sessionService.createSession(user.getAppId());
            }
            return JsonResult.ok(access_token);
        }

        return JsonResult.error();
    }
    @PostMapping("/get/service")
    public JsonResult getService(@RequestParam("accessToken")String accessToken,
                                 @RequestParam("appId")int appId){
        RestSession session = sessionService.getSession(appId);
        if (accessToken.equals(session.getAccessToken())){
            return JsonResult.ok("已经验证过用户","这是服务");
        }else{
            return JsonResult.error("未验证过");
        }
    }
    @PostMapping("/user/new")
    public JsonResult createUser(@RequestParam("appId")int appId,@RequestParam("appSecret")String appSecret){
        RestUser user = new RestUser();
        user.setAppId(appId);
        user.setAppSecret(Encrypt.sha1(appSecret));
        userService.createUser(user);
        return JsonResult.ok();
    }
    @GetMapping("/register")
    public ModelAndView register(){
        ModelAndView mv = new ModelAndView("/restful/register");
        return mv;
    }
    @GetMapping("/service")
    public ModelAndView service(){
        ModelAndView mv = new ModelAndView("/restful/service");
        return mv;
    }
}
