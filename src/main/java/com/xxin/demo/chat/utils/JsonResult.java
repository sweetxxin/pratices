package com.xxin.demo.chat.utils;

public class JsonResult {
    private int status;
    private String msg;
    private Object data;
    public static JsonResult ok(String msg, Object o){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setStatus(200);
        jsonResult.setMsg(msg);
        jsonResult.setData(o);
        return jsonResult;
    }
    public static JsonResult ok(String msg){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setStatus(200);
        jsonResult.setMsg(msg);
        jsonResult.setData(null);
        return jsonResult;
    }
    public static JsonResult ok(){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setStatus(200);
        jsonResult.setMsg("成功");
        jsonResult.setData(null);
        return jsonResult;
    }
    public static <T> JsonResult error(String msg, T o){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setStatus(0);
        jsonResult.setMsg(msg);
        jsonResult.setData(o);
        return jsonResult;
    }
    public static JsonResult error(String msg){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setStatus(0);
        jsonResult.setMsg(msg);
        jsonResult.setData(null);
        return jsonResult;
    }
    public static JsonResult error(){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setStatus(0);
        jsonResult.setMsg("错误");
        jsonResult.setData(null);
        return jsonResult;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object object) {
        this.data = object;
    }
}
