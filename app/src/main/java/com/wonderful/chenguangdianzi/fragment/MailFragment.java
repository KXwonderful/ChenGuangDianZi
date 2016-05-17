package com.wonderful.chenguangdianzi.fragment;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import com.wonderful.chenguangdianzi.R;
import com.wonderful.chenguangdianzi.util.MyWebView;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by KXwon on 2016/3/18.
 */
public class MailFragment extends Fragment {

    private WebView myWebView; // WebView组件
    private String url;
    private MyWebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_mail, null);

        getActivity().setRequestedOrientation(//通过程序改变屏幕显示的方向
                ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        //横竖屏切换时防止重载
        //setRetainInstance(true);

        myWebView = (WebView) view.findViewById(R.id.myWebView);
        url = "http://cg217.com/yx/cgyx.html";

        mWebView = new MyWebView();
        mWebView.initWebView(url,myWebView,getActivity());

        myWebView.setOnKeyListener(backlistener);
        return view;
    }

    /**
     * 系统返回键监听
     */
    private View.OnKeyListener backlistener = new View.OnKeyListener() {

        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                if (i == KeyEvent.KEYCODE_BACK) {  //表示按返回键 时的操作
                    if(myWebView.canGoBack())
                    {
                        myWebView.goBack();//返回上一页面
                        return true;
                    }
                    else
                    {
                        //System.exit(0);//退出程序
                        exitBy2Click();
                    }

                    }
                }
            return false;
        }
    };

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;
    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(getActivity(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            getActivity().finish();
            System.exit(0);
        }
    }

}
