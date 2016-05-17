package com.wonderful.chenguangdianzi.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;

/**
 * Created by Administrator on 2016/5/7.
 */
public class MyWebView {

    private static final String APP_CACHE_DIRNAME = "/webcache"; // web缓存目录

    public void initWebView(String url, WebView myWebView,Context context){
        // 启用支持javascript脚本语言
        WebSettings settings = myWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 判断是否有网络，有的话，使用LOAD_DEFAULT,无网络时，使用LOAD_CACHE_ELSE_NETWORK
        if (isNetworkAvailable(context)){
            settings.setCacheMode(WebSettings.LOAD_DEFAULT); // 设置默认模式
        }else {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 设置缓存模式
        }

        // 设置可以支持缩放
        settings.setSupportZoom(true);
        // 设置出现缩放工具
        settings.setBuiltInZoomControls(true);
        //扩大比例的缩放
        settings.setUseWideViewPort(true);
        //自适应屏幕
        //settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        // 开启DOM storage API 功能
        settings.setDomStorageEnabled(true);
        // 开启database storage API功能
        settings.setDatabaseEnabled(true);
        String cacheDirPath = context.getFilesDir().getAbsolutePath()
                + APP_CACHE_DIRNAME;
        //Log.i("cachePath", cacheDirPath);
        // 设置数据库缓存路径
        settings.setDatabasePath(cacheDirPath); // API 19 deprecated
        // 设置Application caches缓存目录
        settings.setAppCachePath(cacheDirPath);
        // 开启Application Cache功能
        settings.setAppCacheEnabled(true);
        // 这里是支持flash的相关设置
        settings.setPluginState(WebSettings.PluginState.ON);

        myWebView.loadUrl(url);

        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        myWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

        //适应横竖屏
        myWebView.setInitialScale(100);
        //myWebView.setOnKeyListener(backlistener);
    }

    /**
     * 检测当的网络（WLAN、3G/2G）状态
     * @param context Context
     * @return true 表示网络可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 系统返回键监听
     */
    /*private View.OnKeyListener backlistener = new View.OnKeyListener() {

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
                        System.exit(0);//退出程序
                    }
                }
            }
            return false;
        }
    };*/

    /**
     * 清除WebView缓存
     */
    public void clearWebViewCache(Context context) {
        // 清理WebView缓存数据库
        try {
            context.deleteDatabase("webview.db");
            context.deleteDatabase("webviewCache.db");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // WebView缓存文件
        File appCacheDir = new File(context.getFilesDir().getAbsolutePath()
                + APP_CACHE_DIRNAME);
        //Log.e(TAG, "appCacheDir path=" + appCacheDir.getAbsolutePath());

        File webviewCacheDir = new File(context.getCacheDir().getAbsolutePath()
                + "/webviewCache");
        //Log.e(TAG, "appCacheDir path=" + webviewCacheDir.getAbsolutePath());

        // 删除webView缓存目录
        if (webviewCacheDir.exists()) {
            deleteFile(webviewCacheDir);
        }
        // 删除webView缓存，缓存目录
        if (appCacheDir.exists()) {
            deleteFile(appCacheDir);
        }
    }

    /**
     * 递归删除 文件/文件夹
     *
     * @param file
     */
    public void deleteFile(File file) {
        // Log.i(TAG, "delete file path=" + file.getAbsolutePath());
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        } else {
            //  Log.e(TAG, "delete file no exists " + file.getAbsolutePath());
        }
    }


}
