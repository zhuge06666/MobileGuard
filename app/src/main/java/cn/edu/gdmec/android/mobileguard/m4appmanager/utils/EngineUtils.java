package cn.edu.gdmec.android.mobileguard.m4appmanager.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.mobileguard.m4appmanager.entity.AppInfo;

/**
 * Created by Administrator on 2017/11/8.
 */

public class EngineUtils {
    public static void shareApplication(Context context, AppInfo appInfo){
        Intent intent = new Intent("android.intent.action.SEND");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"推荐您使用一款软件，名称叫："+appInfo.appName+
        "下载路径：http://play.google.com/store/apps/details?id="+appInfo.packageName);
        context.startActivity(intent);
    }
    public static void startApplication(Context context,AppInfo appInfo){
        PackageManager pm=context.getPackageManager();
        Intent intent=pm.getLaunchIntentForPackage(appInfo.packageName);
        if (intent != null){
            context.startActivity(intent);
        }else {
            Toast.makeText(context,"该应用没有启动界面",Toast.LENGTH_LONG).show();
        }
    }
    public static void SettingAppDetail(Context context,AppInfo appInfo){
        Intent intent=new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("package:"+appInfo.packageName));
        context.startActivity(intent);
    }
    public static void uninstallApplication(Context context,AppInfo appInfo){
        if (appInfo.isUserApp){
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_DELETE);
            intent.setData(Uri.parse("package:"+appInfo.packageName));
            context.startActivity(intent);
        }else {
            Toast.makeText(context,"系统应用无法卸载",Toast.LENGTH_LONG).show();;
        }
    }
    public static void aboutApplication(Context context,AppInfo appInfo){
        PackageManager pm=context.getPackageManager();
        try {
            //获取版本号
            PackageInfo info=pm.getPackageInfo(context.getPackageName(),0);
            String version=info.versionName;
            //获取安装日期
            long firstInstallTime=info.firstInstallTime;
            String date=null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd号 hh：mm：ss");
            date=dateFormat.format(firstInstallTime);
          //获取权限信息
            PackageInfo info1=pm.getPackageInfo(context.getPackageName(),PackageManager.GET_PERMISSIONS);
            String[] permissions = info1.requestedPermissions;
            List<String> per = new ArrayList<String>();
            if(permissions != null){
                for(String str : permissions){
                    per.add(str);
                }
            }
            //获取签名信息
            PackageInfo info2=pm.getPackageInfo(context.getPackageName(),PackageManager.GET_SIGNATURES);
            String certMsg="";
            Signature[] sigs = info2.signatures;
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate)certFactory.generateCertificate(new ByteArrayInputStream(sigs[0].toByteArray()));
            certMsg+=cert.getIssuerDN().toString();
            certMsg+=cert.getSubjectDN().toString();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(appInfo.appName);
            builder.setMessage("version:"+version+"\n"+"Install time:"+date+"\n"+"Certificate issuer:"+certMsg+"Permissions："+per);
            builder.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
