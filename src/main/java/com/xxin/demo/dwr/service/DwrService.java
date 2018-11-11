package com.xxin.demo.dwr.service;

import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.proxy.dwr.Util;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RemoteProxy  // spring 的注解，相当于暴露服务
public class DwrService{

    //TODO  这块可以注入服务
    @RemoteMethod
    public String hello(){
        return "hello  dada " ;
    }
    @RemoteMethod
    public String echo(String  string){
        return "hello   " + string ;
    }
    @RemoteMethod
    public void chat(String  string){
        System.out.println(string);
        WebContext webContext = WebContextFactory.get();
        Collection<ScriptSession> sessions =  webContext.getAllScriptSessions();
        ScriptBuffer buffer = new ScriptBuffer();
        buffer.appendScript("chat('");
        buffer.appendScript(string);
        buffer.appendScript("')");
        Util util = new Util(sessions);
        util.addScript(buffer);
    }
}
