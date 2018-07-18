package org.lyncc.bazinga.rx.bazinga.wxmp;

import java.util.HashMap;
import java.util.Map;

import static org.lyncc.bazinga.rx.bazinga.wxmp.WX_TemplateMsgUtil.getWXTemplateMsgId;
import static org.lyncc.bazinga.rx.bazinga.wxmp.WX_TemplateMsgUtil.packJsonmsg;

/**
 * @author liguolin
 * @create 2018-07-13 11:05
 **/
public class Test {

    public static void main(String[] args) {
        //新增用户成功 - 推送微信消息
        senMsg("oZ5sB0dLctJ-THdarKRD5L6-VJoI");
    }
    static void senMsg(String openId){
        //用户是否订阅该公众号标识 (0代表此用户没有关注该公众号 1表示关注了该公众号)
        Integer  state= WX_UserUtil.subscribeState(openId);
        // 绑定了微信并且关注了服务号的用户 , 注册成功-推送注册短信
        if(state==1){
            Map<String,TemplateData> param = new HashMap<>();
            param.put("keyword1.DATA",new TemplateData("200.00元","#696969"));
            param.put("keyword2.DATA",new TemplateData("2018-07-12 12:21:34","#696969"));
            param.put("keyword3.DATA",new TemplateData("经纪人佣金","#696969"));
            param.put("keyword4.DATA",new TemplateData("加油加油","#696969"));
            param.put("keyword5.DATA",new TemplateData("赵梦晨","#696969"));
            //注册的微信-模板Id
            String regTempId =  "8OCoUx6WkY_PiPj6FdLfK_XNlPixegTAgZu9yN_0_VI";
            //调用发送微信消息给用户的接口
            WX_TemplateMsgUtil.sendWechatMsgToUser(openId,regTempId, "", "#000000", packJsonmsg(param));
        }
    }
}
