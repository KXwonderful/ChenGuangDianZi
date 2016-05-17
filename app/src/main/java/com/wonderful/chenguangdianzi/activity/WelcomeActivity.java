package com.wonderful.chenguangdianzi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.wonderful.chenguangdianzi.R;
import com.wonderful.chenguangdianzi.util.PrefUtils;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//设置无标题
        setContentView(R.layout.activity_welcome);

        // 判断之前有没有显示过新手引导
        boolean userGuide = PrefUtils.getBoolean(this, "is_user_guide_showed",false);

        if (!userGuide) {
            // 跳转到新手引导页
            startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
        } else {
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        }

        finish();
    }
}
