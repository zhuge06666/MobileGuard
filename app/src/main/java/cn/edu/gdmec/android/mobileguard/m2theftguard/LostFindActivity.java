package cn.edu.gdmec.android.mobileguard.m2theftguard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;


import cn.edu.gdmec.android.mobileguard.R;


/**
 * Created by 黄煜辉 on 2017/9/20.
 */

public class LostFindActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mSafePhoneTV;
    private RelativeLayout mInterSetupRL;
    private SharedPreferences msharedPrefences;
    private ToggleButton mToggleButton;
    private TextView mProtectStatusTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_lost_find);
        msharedPrefences = getSharedPreferences("config", MODE_PRIVATE);
        if (!isSetUp()) {
            startSetup1Activity();
        }
        initView();

    }

    private void initView() {
        TextView mTitleTV = (TextView) findViewById(R.id.tv_title);
        mTitleTV.setText("手机防盗");
        ImageView mLeftImgv = (ImageView) findViewById(R.id.imgv_leftbtn);
        mLeftImgv.setOnClickListener(this);
        mLeftImgv.setImageResource(R.drawable.back);
        findViewById(R.id.rl_titlebar).setBackgroundColor(getResources().getColor(R.color.purple));
        mSafePhoneTV = (TextView) findViewById(R.id.tv_safephone);
        mSafePhoneTV.setText(msharedPrefences.getString("safephone", ""));
        mToggleButton = (ToggleButton) findViewById(R.id.togglebtn_lostfind);
        mInterSetupRL = (RelativeLayout) findViewById(R.id.rl_inter_setup_wizard);
        mInterSetupRL.setOnClickListener(this);
        mProtectStatusTV = (TextView) findViewById(R.id.tv_lostfind_protectstauts);
        boolean protecting = msharedPrefences.getBoolean("protecting", true);
        if (protecting) {
            mProtectStatusTV.setText("防盗保护已开启");
            mToggleButton.setChecked(true);
        } else {
            mProtectStatusTV.setText("防盗保护没有开启");
            mToggleButton.setChecked(false);
        }
        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mProtectStatusTV.setText("防盗保护已开启");
                } else {
                    mProtectStatusTV.setText("防盗保护没有开启");
                }
                SharedPreferences.Editor editor = msharedPrefences.edit();
                editor.putBoolean("protecting", isChecked);
                editor.commit();
            }
        });

    }

    private boolean isSetUp() {
        return msharedPrefences.getBoolean("isSetUp", false);
    }

    private void startSetup1Activity() {
        Intent intent = new Intent(LostFindActivity.this, Setup1Activity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_inter_setup_wizard:
                startSetup1Activity();
                break;
            case R.id.imgv_leftbtn:
                finish();
                break;
        }

    }
}