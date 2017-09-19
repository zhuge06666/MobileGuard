package cn.edu.gdmec.android.mobileguard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cn.edu.gdmec.android.mobileguard.m1home.utils.MyUtils;
import cn.edu.gdmec.android.mobileguard.m1home.utils.VersionUpdateUtils;

public class SpiashActivity extends AppCompatActivity {
private TextView mTvVerson;
    private  String mVerson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiash);
        mVerson = MyUtils.getVerson(getApplicationContext());
        mTvVerson = (TextView)findViewById(R.id.tv_spiash_version);
        mTvVerson.setText("版本号："+mVerson);
        final VersionUpdateUtils versionUpdateUtils=new VersionUpdateUtils(mVerson,SpiashActivity.this);
        new Thread(){
            public void run(){
                super.run();
                versionUpdateUtils.getCloudVersion();
            }
        }.start();
    }
}
