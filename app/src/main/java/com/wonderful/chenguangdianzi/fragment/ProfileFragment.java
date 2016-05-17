package com.wonderful.chenguangdianzi.fragment;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnDrawListener;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.wonderful.chenguangdianzi.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by KXwon on 2016/3/18.
 */
public class ProfileFragment extends Fragment implements OnPageChangeListener
        , OnLoadCompleteListener, OnDrawListener {

    private PDFView pdfView ;
    private TextView tv_pagination;//显示页码

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_profile, null);
        //getActivity().setRequestedOrientation(//通过程序改变屏幕显示的方向为竖屏
        //       ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getActivity().setRequestedOrientation(//通过程序改变屏幕显示的方向为横竖屏
                       ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        pdfView = (PDFView) view.findViewById( R.id.pdfView );
        tv_pagination = (TextView) view.findViewById(R.id.tv_pagination);

        //从assets目录读取pdf
        displayFromAssets("chenguangdianzi.pdf");

        return view;
    }

    /**
     * pdf相关设置
     * @param assetFileName
     */
    private void displayFromAssets(String assetFileName ) {
        pdfView.fromAsset(assetFileName)   //设置pdf文件地址
                .defaultPage(1)         //设置默认显示第1页
                .onPageChange(this)     //设置翻页监听
                .onLoad(this)           //设置加载监听
                .onDraw(this)            //绘图监听
                .showMinimap(false)     //pdf放大的时候，是否在屏幕的右上角生成小地图
                .swipeVertical( false )  //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                .enableSwipe(true)   //是否允许翻页，默认是允许翻页
                // .pages( 2 , 3 , 4 , 5  )  //把2 , 3 , 4 , 5 过滤掉
                .load();
    }

    /**
     * 翻页回调
     * @param page
     * @param pageCount
     */
    @Override
    public void onPageChanged(int page, int pageCount) {
        tv_pagination.setText(page+"/"+pageCount);
        //Toast.makeText( getActivity() , "page= " + page +
        //        " pageCount= " + pageCount , Toast.LENGTH_SHORT).show();
    }

    /**
     * 加载完成回调
     * @param nbPages  总共的页数
     */
    @Override
    public void loadComplete(int nbPages) {
        //Toast.makeText( getActivity(),  "加载完成" + nbPages  , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
        // Toast.makeText( MainActivity.this ,  "pageWidth= " + pageWidth + "
        // pageHeight= " + pageHeight + " displayedPage="  + displayedPage , Toast.LENGTH_SHORT).show();
    }

    /**
     * 系统返回键监听
     */
    private View.OnKeyListener backlistener = new View.OnKeyListener() {

        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                if (i == KeyEvent.KEYCODE_BACK) {
                        //System.exit(0);//退出程序
                        exitBy2Click();
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
