package com.hjt.test;

import com.hjt.po.AccessToken;
import com.hjt.util.MessageUtil;
import com.hjt.util.WeChatUtil;
import net.sf.json.JSONObject;
import sun.plugin2.message.Message;

import java.io.IOException;

public class WeChatTest {
    public static void main(String args[]){
        try {
            AccessToken accessToken = WeChatUtil.getAccessToken();
            System.out.println("获取到的凭证" + accessToken.getAccess_token());
            System.out.println("凭证有效时间" + accessToken.getExpires_in());

//            String imagepath = "D:/360downloads/wpcache/internetcache/324678_300.jpg";
//            String videopath = "C:/Users/Administrator/Documents/Apowersoft/Apowersoft Online Screen Recorder/抓包改权限.mp4";
//            String mediaId = WeChatUtil.upload(imagepath, accessToken.getAccess_token(), "image");
//            String mediaId = WeChatUtil.upload(videopath, accessToken.getAccess_token(), "video");
//            String mediaId = WeChatUtil.upload(imagepath, accessToken.getAccess_token(), "thumb");

//            System.out.println(mediaId);

            //创建菜单
           /* String menu = JSONObject.fromObject(WeChatUtil.initMenu()).toString();
            int result = WeChatUtil.creatMenu(accessToken.getAccess_token(), menu);
            if (result == 0){
                System.out.println("创建菜单成功");
            }else {
                System.out.println("错误码：" + result);
            }*/

            //查询菜单
            /*JSONObject jsonObject = WeChatUtil.queryMenu(accessToken.getAccess_token());
            System.out.println(jsonObject);*/

            //删除菜单
            /*int result = WeChatUtil.deleteMenu(accessToken.getAccess_token());
            if(result == 0){
                System.out.println("删除成功");
            }else {
                System.out.println("错误码" + result);
            }*/

            //百度翻译测试
            JSONObject jsonObject = WeChatUtil.translate("我");
            String s = MessageUtil.translate("我");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
