package cn.edu.gdmec.android.mobileguard.m1home.utils;

import android.app.Activity;
import android.provider.Settings;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.edu.gdmec.android.mobileguard.m1home.entity.VersionEntity;

/**
 * Created by Administrator on 2017/9/16.
 */

public class VersionUpdateUtils {
private String mVerson;
    private Activity context;
    private VersionEntity versionEntity;
    public VersionUpdateUtils(String mVerson, Activity context){
        this.mVerson=mVerson;
        this.context=context;
    }
    public void getCloudVersion(){
     try{
         HttpClient httpclient =new DefaultHttpClient();
         HttpConnectionParams.setConnectionTimeout(httpclient.getParams(),5000);
         HttpConnectionParams.setSoTimeout(httpclient.getParams(),5000);
         HttpGet httpGet =new HttpGet("http://android.duapp.com/updateinfo.html");
         HttpResponse execute = httpclient.execute(httpGet);
         if(execute.getStatusLine().getStatusCode()==200){
             HttpEntity httpEntity=execute.getEntity();
             String result = EntityUtils.toString(httpEntity,"utf-8");
             JSONObject jsonObject = new JSONObject(result);
             versionEntity = new VersionEntity();
             versionEntity.versioncode=jsonObject.getString("code");
             versionEntity.description=jsonObject.getString("des");
             versionEntity.apkurl=jsonObject.getString("apkurl");
             if(!mVerson.equals(versionEntity.versioncode)){
                 System.out.println(versionEntity.description);
                 DownloadUtils downloadUtils=new DownloadUtils();
                 downloadUtils.downloadApk(versionEntity.apkurl,"mobileguard.apk",context);
             }
         }


     }catch (IOException e){
         e.printStackTrace();
     }catch (JSONException e){
         e.printStackTrace();
     }
    }
}