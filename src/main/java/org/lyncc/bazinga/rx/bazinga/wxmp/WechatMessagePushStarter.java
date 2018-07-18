package org.lyncc.bazinga.rx.bazinga.wxmp;

import me.chanjar.weixin.common.WxType;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liguolin
 * @create 2018-07-13 9:54
 **/
public class WechatMessagePushStarter {

    public static void main(String[] args) throws WxErrorException, IOException {

        WxMpTemplateMessage wxMessageTemplate;
        WxMpService wxMpService = getWxMpService();

        wxMessageTemplate = new WxMpTemplateMessage();
        wxMessageTemplate.setToUser("o3aGIt5KFRpynSPWrliJvERIjFuE");
        wxMessageTemplate.setTemplateId("V9GGCH_VSIov6yBCYccKYXgSUXJXjd0U1mJmm0tbJ0U");

        if (wxMpService.getWxMpConfigStorage() == null) {
            return;
        }
        List<WxMpTemplateData> data = new ArrayList<>();
        WxMpTemplateData wxMpTemplateData1 = new WxMpTemplateData("first.DATA","您好，您于2013/10/29  15:24 有一笔现金帐户充值到账：");
        WxMpTemplateData wxMpTemplateData2 = new WxMpTemplateData("date.DATA","2018-07-12 12:21:34");
        WxMpTemplateData wxMpTemplateData3 = new WxMpTemplateData("adCharge.DATA","￥5618.63");
        WxMpTemplateData wxMpTemplateData4 = new WxMpTemplateData("type.DATA","现金");
        WxMpTemplateData wxMpTemplateData5 = new WxMpTemplateData("cashBalance.DATA","￥8454.74");
        WxMpTemplateData wxMpTemplateData6 = new WxMpTemplateData("remark.DATA","点击“查看详情“立即查阅您的帐户财务记录。");
        data.add(wxMpTemplateData1);
        data.add(wxMpTemplateData2);
        data.add(wxMpTemplateData3);
        data.add(wxMpTemplateData4);
        data.add(wxMpTemplateData5);
        data.add(wxMpTemplateData6);
        wxMessageTemplate.setData(data);



        // ！！！发送模板消息！！！
        wxMpService.getTemplateMsgService().sendTemplateMsg(wxMessageTemplate);

    }



    /**
     * 获取微信公众号工具服务
     *
     * @return
     */
    public static WxMpService getWxMpService() throws WxErrorException, IOException {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    private static WxMpConfigStorage wxMpConfigStorage() throws IOException, WxErrorException {
        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
        configStorage.setAppId("wx6e6da3d54cbf104e");
        configStorage.setSecret("88def669f09b71cf89bee5b7cbfb9992");
        configStorage.setToken(getToken());
        configStorage.setAesKey("CRdsXwoXJO3yzFm9QkoOgOfRgLKy1VxJgnQfa260QGI");
        return configStorage;
    }

    private static String getAesKey() {
        return null;
    }

    private static String getToken() throws IOException, WxErrorException {
        OkHttpClient httpClient  = new OkHttpClient();
        String url = String.format(WxMpService.GET_ACCESS_TOKEN_URL,
                "wx6e6da3d54cbf104e", "88def669f09b71cf89bee5b7cbfb9992");

        Request request = new Request.Builder().url(url).get().build();
        Response response = httpClient.newCall(request).execute();
        String resultContent = null;
        try {
            resultContent = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        WxError error = WxError.fromJson(resultContent, WxType.MP);
        if (error.getErrorCode() != 0) {
            throw new WxErrorException(error);
        }
        WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
        System.out.println(accessToken.getAccessToken());
        System.out.println(accessToken.getExpiresIn());

        return accessToken.getAccessToken();
    }
}
