package com.hjt.util;

import com.hjt.po.*;
import com.thoughtworks.xstream.XStream;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MessageUtil {
    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VIOCE = "voice";
    public static final String MESSAGE_VIDEO= "video";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVENT = "event";
    public static final String MESSAGE_SUBSCRIBE = "subscribe";
    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
    public static final String MESSAGE_CLICK = "CLICK";
    public static final String MESSAGE_VIEW = "VIEW";
    public static final String MESSAGE_NEWS = "news";
    public static final String MESSAGE_MUSIC = "music";
    public static final String MESSAGE_SCANCODE = "scancode_push";

    /**
     * xml转map方法
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();

        InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);

        Element root = doc.getRootElement();
        List<Element> list = root.elements();

        for(Element e: list){
            map.put(e.getName(), e.getText());
        }
        ins.close();
        return map;
    }

    /**
     * 将文本消息对象转为XML
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);

    }
    public static String initText(String toUserName, String fromUserName, String content){
        TextMessage textMessage = new TextMessage();
        textMessage.setFromUserName(toUserName);
        textMessage.setToUserName(fromUserName);
        textMessage.setMsgType(MessageUtil.MESSAGE_TEXT);
        textMessage.setCreateTime(String.valueOf(new Date().getTime()));
        textMessage.setContent(content);
        return MessageUtil.textMessageToXml(textMessage);
    }

    /**
     * 主菜单
     * @return
     */
    public static String menuText(){
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎您的关注，请按照菜单提示进行操作：\n");
        sb.append("1、公众号介绍\n");
        sb.append("2、公众号开发者介绍\n");
        sb.append("3、获取图文消息\n");
        sb.append("4、获取图片消息\n");
        sb.append("5、获取视频消息\n");
        sb.append("6、获取音乐消息\n");
        sb.append("默认回复翻译内容\n");
        sb.append("回复？调出此菜单。");
        return sb.toString();
    }

    public static String firstMenu(){
        StringBuffer sb = new StringBuffer();
        sb.append("本公众号是用来学习的！");
        return sb.toString();
    }
    public static String secondMenu(){
        StringBuffer sb = new StringBuffer();
        sb.append("本公众号的开发者有：HJT");
        return sb.toString();
    }

    public static String translate(String q){
        System.out.println("MessageUtil：" + q);
        StringBuffer sb = new StringBuffer();
        try {
            //下方走不通！！！！！
            JSONObject jsonObject = WeChatUtil.translate(q);
            sb.append(jsonObject.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 图文消息转XML
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new News().getClass());
        return xstream.toXML(newsMessage);

    }

    /**
     * 图片消息转xml
     * @param imageMessage
     * @return
     */
    public static String imageMessageToXml(ImageMessage imageMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", imageMessage.getClass());
        return xstream.toXML(imageMessage);

    }

    /**
     * 视频消息转xml
     * @param videoMessage
     * @return
     */
    public static String videoMessageToXml(VideoMessage videoMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", videoMessage.getClass());
        return xstream.toXML(videoMessage);

    }


    /**
     * 音乐消息转xml
     * @param musicMessage
     * @return
     */
    public static String musicMessageToXml(MusicMessage musicMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);

    }

    /**
     * 图文消息组装
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initNewsMessage(String toUserName, String fromUserName){
        String message = null;
        List<News> newsList = new ArrayList<News>();
        NewsMessage newsMessage = new NewsMessage();
        News news = new News();

        news.setTitle("公众号介绍");
        news.setDescription("不知道介绍怎么写！！！ ");
        news.setPicUrl("http://hjt.ngrok.xiaomiqiu.cn/WChat/image/a.jpg");
        news.setUrl("https://baike.baidu.com/item/%E9%99%88%E9%92%B0%E7%90%AA/4859301?fr=aladdin");

        newsList.add(news);

        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(String.valueOf(new Date().getTime()));
        newsMessage.setMsgType(MessageUtil.MESSAGE_NEWS);
        newsMessage.setArticles(newsList);
        newsMessage.setArticleCount(newsList.size());

        message = newsMessageToXml(newsMessage);

        return message;
    }

    /**
     * 图片消息组装
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initImageMessage(String toUserName, String fromUserName){
        String message = null;
        ImageMessage imageMessage = new ImageMessage();
        Image image = new Image();
        image.setMediaId("04-eJVEuXhu-8_D7YqAEX0I0QTf-cOYROXtxMYoXxLLIHg-sKDiUC7_dlAEZryvG");

        imageMessage.setToUserName(fromUserName);
        imageMessage.setFromUserName(toUserName);
        imageMessage.setCreateTime(String.valueOf(new Date().getTime()));
        imageMessage.setMsgType(MESSAGE_IMAGE);
        imageMessage.setImage(image);

        message = imageMessageToXml(imageMessage);
        return message;
    }

    /**
     * 视频消息组装
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initVideoMessage(String toUserName, String fromUserName){
        String message = null;
        VideoMessage videoMessage = new VideoMessage();
        Video video = new Video();
        video.setTitle("HJT的测试视频");
        video.setDescription("HJT的测试视频描述信息");
        video.setMediaId("G3rwYODsUlcFX10qJI0NuRbU_-atLIJSnFK3Qon0q2fk5BvgZ80qJ6Oid6E5_zv9");

        videoMessage.setToUserName(fromUserName);
        videoMessage.setFromUserName(toUserName);
        videoMessage.setCreateTime(String.valueOf(new Date().getTime()));
        videoMessage.setMsgType(MESSAGE_VIDEO);
        videoMessage.setVideo(video);

        message = videoMessageToXml(videoMessage);
        return message;
    }

    public static String initMusicMessage(String toUserName, String fromUserName){
        String message = null;
        MusicMessage musicMessage = new MusicMessage();
        Music music = new Music();

        music.setTitle("绿色");
        music.setDescription("歌手-陈雪凝");
        music.setMusicUrl("http://hjt.ngrok.xiaomiqiu.cn/WChat/music/green.mp3");
        music.setHQMusicUrl("https://music.163.com/song?id=1345848098");
        music.setThumbMediaId("eSGW2FV3-YFLW7RMYFuF3x_omHpf1eudqwUMxi8LgYQmC-OmIzbDy0yfJkzW5KKM");

        musicMessage.setToUserName(fromUserName);
        musicMessage.setFromUserName(toUserName);
        musicMessage.setCreateTime(String.valueOf(new Date().getTime()));
        musicMessage.setMsgType(MESSAGE_MUSIC);
        musicMessage.setMusic(music);

        message = musicMessageToXml(musicMessage);

        return message;
    }
}
