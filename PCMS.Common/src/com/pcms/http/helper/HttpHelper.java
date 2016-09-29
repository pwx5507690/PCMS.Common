package com.pcms.http.helper;

import java.io.IOException;
import java.net.URLDecoder;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author wx.pan
 */
public class HttpHelper {

    public static JSONObject httpPost(String url, JSONObject jsonParam, boolean noNeedResponse) {
        CloseableHttpClient client = HttpClients.createDefault();
        JSONObject jsonResult = null;

        try {
            url = URLDecoder.decode(url, "UTF-8");
            HttpPost method = new HttpPost(url);
            System.out.println(url);
            if (null != jsonParam) {
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = client.execute(method);
            System.out.println(result.getStatusLine().getStatusCode());
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = StringUtils.EMPTY;
                try {
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    jsonResult = JSONObject.fromObject(str);
                } catch (IOException | ParseException e) {
                     System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
             System.out.println(e.getMessage());
        }
        return jsonResult;
    }

    public static JSONObject httpGet(String url) {
        JSONObject jsonResult = null;
        try {

            CloseableHttpClient client = HttpClients.createDefault();
            url = URLDecoder.decode(url, "UTF-8");
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {

                String strResult = EntityUtils.toString(response.getEntity());
                jsonResult = JSONObject.fromObject(strResult);

            } else {

            }
        } catch (IOException e) {
              System.out.println(e.getMessage());
        }
        return jsonResult;
    }

    public static String httpPostForString(String url, JSONObject jsonParam){
        return httpPost(url, jsonParam, true).toString();
    }

    public static String httpGetForString(String url) {
        return httpGet(url).toString();
    }
}
