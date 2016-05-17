package com.wonderful.chenguangdianzi.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.Toast;

import com.wonderful.chenguangdianzi.R;
import com.wonderful.chenguangdianzi.fragment.HomeFragment;
import com.wonderful.chenguangdianzi.fragment.MailFragment;
import com.wonderful.chenguangdianzi.fragment.ProfileFragment;
import com.wonderful.chenguangdianzi.fragment.YunFragment;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 主界面
 * Created by Administrator on 2016/5/6.
 */
public class MainActivity extends Activity{

    // 声明底部四个按钮
    private RadioButton rb_home, rb_yun, rb_mail, rb_profile;
    // private WebView myWebView; // WebView组件
    private long exitTime = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initLayout();

        /*//获取fragment中的WebView空间
        LayoutInflater layout = this.getLayoutInflater();
        View view = layout.inflate(R.layout.fragment_home, null);
        myWebView = (WebView) view.findViewById(R.id.myWebView);*/

        // 设置进入默认页面
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        HomeFragment hf = new HomeFragment();
        ft.replace(R.id.main_fragment, hf);
        ft.commit();
    }

    private void initLayout() {
        //实例化底部四个按钮
        rb_home= (RadioButton) findViewById(R.id.rb_home);
        rb_mail=(RadioButton) findViewById(R.id.rb_mail);
        rb_yun=(RadioButton) findViewById(R.id.rb_yunFile);
        rb_profile=(RadioButton) findViewById(R.id.rb_profile);

        //给底部四个按钮设置事件监听
        rb_home.setOnClickListener(new MyClick());
        rb_mail.setOnClickListener(new MyClick());
        rb_yun.setOnClickListener(new MyClick());
        rb_profile.setOnClickListener(new MyClick());
    }

    //各个事件监听功能
    public class MyClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // 创建框架
            FragmentManager fm=getFragmentManager();
            //创建事务
            FragmentTransaction ft=fm.beginTransaction();

            switch(v.getId()){
                case R.id.rb_home:
                    HomeFragment hf=new HomeFragment();
                    ft.replace(R.id.main_fragment, hf);
                    break;

                case R.id.rb_mail:
                    MailFragment mf=new MailFragment();
                    ft.replace(R.id.main_fragment, mf);
                    break;

                case R.id.rb_yunFile:
                    YunFragment yf=new YunFragment();
                    ft.replace(R.id.main_fragment, yf);
                    break;

                case R.id.rb_profile:
                    ProfileFragment pf=new ProfileFragment();
                    ft.replace(R.id.main_fragment, pf);
                    break;

                default:
                    break;
            }

            //提交事件
            ft.commit();
        }
    }

    /**
     * 横竖屏切换时防止重载
     * @param config
     *//*
    @Override
    public void onConfigurationChanged(Configuration config)
    {
        super.onConfigurationChanged(config);
    }*/


    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            exitBy2Click();      //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;
    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }

}
