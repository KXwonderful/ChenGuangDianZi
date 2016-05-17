package com.wonderful.chenguangdianzi.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.wonderful.chenguangdianzi.R;
import com.wonderful.chenguangdianzi.util.ColorAnimationView;
import com.wonderful.chenguangdianzi.util.PrefUtils;

/**
 * Created by Administrator on 2016/5/6.
 */
public class GuideActivity extends FragmentActivity {

    private static final int[] resource = new int[] { R.mipmap.welcome01,
            R.mipmap.welcome02, R.mipmap.welcome03, R.mipmap.welcome04 };
    private static final String TAG = MainActivity.class.getSimpleName();

    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        btn_start = (Button) findViewById(R.id.btn_start);

        MyFragmentStatePager adpter = new MyFragmentStatePager(
                getSupportFragmentManager());
        ColorAnimationView colorAnimationView = (ColorAnimationView) findViewById(R.id.ColorAnimationView);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adpter);
        colorAnimationView.setmViewPager(viewPager, resource.length);
        colorAnimationView
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position,
                                               float positionOffset, int positionOffsetPixels) {
                        Log.e("TAG", "onPageScrolled");
                    }

                    @Override
                    public void onPageSelected(int position) {
                        if (position == 3) {
                            btn_start.setVisibility(View.VISIBLE);
                        } else {
                            btn_start.setVisibility(View.GONE);
                        }
                        Log.e("TAG", "onPageSelected");
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                        Log.e("TAG", "onPageScrollStateChanged");
                    }
                });

        btn_start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 更新sp, 表示已经展示了新手引导
                PrefUtils.setBoolean(GuideActivity.this, "is_user_guide_showed", true);
                // 跳转主页面
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });

    }


    public class MyFragmentStatePager extends FragmentStatePagerAdapter {

        public MyFragmentStatePager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new MyFragment(position);
        }

        @Override
        public int getCount() {
            return resource.length;
        }
    }

    @SuppressLint("ValidFragment")
    public class MyFragment extends Fragment {
        private int position;

        public MyFragment(int position) {
            this.position = position;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(resource[position]);
            return imageView;
        }
    }
}
