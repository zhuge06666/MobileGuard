package cn.edu.gdmec.android.mobileguard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cn.edu.gdmec.android.mobileguard.m1home.utils.MyUtils;
import cn.edu.gdmec.android.mobileguard.m1home.utils.VersionUpdateUtils;

public class SplashActivity extends AppCompatActivity {
private TextView mTvVersion;
    private  String mVersion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiash);
        mVersion = MyUtils.getVersion(getApplicationContext());
        mTvVersion = (TextView)findViewById(R.id.tv_spiash_version);
        mTvVersion.setText("版本号："+mVersion);
        final VersionUpdateUtils versionUpdateUtils=new VersionUpdateUtils(mVersion,SplashActivity.this,null,null);
        new Thread(){
            public void run(){
                super.run();
                versionUpdateUtils.getCloudVersion("http://android2017.duapp.com/updateinfo.html");
            }
        }.start();
    }
}
