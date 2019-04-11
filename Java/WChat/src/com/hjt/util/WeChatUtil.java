package com.hjt.util;

import com.hjt.menu.Button;
import com.hjt.menu.ClickButton;
import com.hjt.menu.Menu;
import com.hjt.menu.ViewButton;
import com.hjt.po.AccessToken;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.channels.AcceptPendingException;

public class WeChatUtil {
    private static final String APPID = "wxe8d39d823a344908";
    private static final String APPSECRET = "eb8f8023d807ccd7d8ae7f40e54385cc";
    private static final String BAIDUAPPID = "20170923000084802";
    private static final String BAIDUSECRET = "jjyXCratipYoUNFxS4jV";

    private static final String ACCESS_TOKEN_URL = "https:api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    private static final String UPLOAD_URL = "https:api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

    private static final String CREATE_MENU_URL = "https:api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    private static final String QUERY_MENU_URL = "https:api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

    private static final String DELETE_MENU_URL = "https:api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    private static final String BAIDU_TRANSLATE_URL = "http:api.fanyi.baidu.com/api/trans/vip/translate?" +
            "q=QUERY&" +
            "from=FROM&" +
            "to=TO&" +
            "appid=APPID&" +
            "salt=SALT&" +
            "sign=SIGN";
    /**
     * get请求
     * @param url
     * @return
     */
    public static JSONObject doGetStr(String url){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if(entity != null){
                String result = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSONObject.fromObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    /**
     * post请求
     * @param url
     * @param outStr
     * @return
     */
    public static JSONObject doPostStr(String url, String outStr){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            jsonObject = JSONObject.fromObject(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    /**
     * 获取access_token
     * @return
     */
    public static AccessToken getAccessToken(){
        AccessToken token = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        JSONObject jsonObject = doGetStr(url);
        if(jsonObject != null){
            token.setAccess_token(jsonObject.getString("access_token"));
            token.setExpires_in(jsonObject.getInt("expires_in"));
        }
        return token;
    }

    public static String upload(String filePath, String accessToken, String type) throws IOException {
        File file = new File(filePath);
        if(!file.exists() || !file.isFile()){
            throw new IOException("文件不存在");
        }
        String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
        URL urlObj = new URL(url);
//        连接
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

        con.setRequestMethod("POST");
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);

//        设置请求头信息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");

//        设置边界
        String BOUNDARY = "------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + BOUNDARY);

        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition:form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");

        byte[] head = sb.toString().getBytes("utf-8");

//        获得输出流
        OutputStream out = new DataOutputStream(con.getOutputStream());
//        输出表头
        out.write(head);

//        文件正文部分
//        把文件以流文件的方式 推入到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1){
            out.write(bufferOut, 0, bytes);
        }
        in.close();

//        结尾部分
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分割线

        out.write(foot);

        out.flush();
        out.close();

        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        String result = null;
        try{
//            定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null){
                buffer.append(line);
            }
            if(result == null){
                result = buffer.toString();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (reader != null){
                reader.close();
            }
        }

        JSONObject jsonObject = JSONObject.fromObject(result);
        System.out.println(jsonObject);
        String typeName = "media_id";
        if(!"image".equals(type)){
            typeName = type + "_media_id";
        }
        String mediaId = jsonObject.getString(typeName);
        return mediaId;
    }

    /**
     * 组装菜单
     * @return
     */
    public static Menu initMenu(){
        Menu menu = new Menu();
        ClickButton clickButton_1 = new ClickButton();
        clickButton_1.setName("菜单");
        clickButton_1.setType("click");
        clickButton_1.setKey("菜单");

        ViewButton viewButton_1 = new ViewButton();
        viewButton_1.setName("微信开放平台");
        viewButton_1.setType("view");
        viewButton_1.setUrl("http:mp.weixin.qq.com");


        ClickButton clickButton_2 = new ClickButton();
        clickButton_2.setName("扫码");
        clickButton_2.setType("scancode_push");
        clickButton_2.setKey("扫码");

        ClickButton clickButton_3 = new ClickButton();
        clickButton_3.setName("位置");
        clickButton_3.setType("location_select");
        clickButton_3.setKey("位置");

        Button button = new Button();
        button.setName("更多");
        button.setSub_button(new Button[]{clickButton_2, clickButton_3});

        menu.setButton(new Button[]{clickButton_1, viewButton_1, button});
        return menu;
    }

    public static int creatMenu(String token, String menu){
        int result = 0;
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = doPostStr(url, menu);
        if(jsonObject != null){
            result = jsonObject.getInt("errcode");

        }
        return result;
    }

    public static JSONObject queryMenu(String token){
        String url = QUERY_MENU_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = doGetStr(url);
        return jsonObject;
    }

    public static int deleteMenu(String token){
        String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = doGetStr(url);
        int result = 0;
        if(jsonObject != null){
            result = jsonObject.getInt("errcode");
        }
        return result;
    }

    public static JSONObject translate(String q){
        JSONObject jsonObject = null;
        System.out.println("WeChatUtil：" + q);
        String sign = CheckUtil.getMD5String(BAIDUAPPID + q + "15392680884" + BAIDUSECRET);
        try {
            q = URLEncoder.encode(q, "UTF-8");
            System.out.println(sign);
            System.out.println("WeChatUtil：" + q);
            String url = BAIDU_TRANSLATE_URL.replace("QUERY", q).replace("FROM", "auto")
                    .replace("TO", "en").replace("APPID", BAIDUAPPID)
                    .replace("SALT", "15392680884").replace("SIGN", sign);
            jsonObject = doGetStr(url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(jsonObject);
        return jsonObject;
    }
}
