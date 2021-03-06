package com.example.john.simulatesms.ui.activity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.john.simulatesms.R;
import com.example.john.simulatesms.adapter.MainPageAdapter;
import com.example.john.simulatesms.ui.fragment.ConversationFragment;
import com.example.john.simulatesms.ui.fragment.GroupFragment;
import com.example.john.simulatesms.ui.fragment.SearchFragment;
import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by John on 2016/11/19.
 * <p>
 * 消息显示活动
 */

public class SMSActivity extends BaseActivity {
    public static final String TAG = "SMSActivity";
    private final int animateDuration = 200;
    private ViewPager viewPager;
    private View viewLine;
    private MainPageAdapter mainPageAdapter;


    private TextView tvConversation;
    private TextView tvGroup;
    private TextView tvSearch;

    private int screenWidth;

    private LinearLayout llConversation;
    private LinearLayout llGroup;
    private LinearLayout llSearch;


    @Override
    public void initView() {
        setContentView(R.layout.activity_main);

        viewLine = findViewById(R.id.view_line);
        viewPager = (ViewPager) findViewById(R.id.view_page);

        tvConversation = (TextView) findViewById(R.id.tv_conversation);
        tvGroup = (TextView) findViewById(R.id.tv_group);
        tvSearch = (TextView) findViewById(R.id.tv_search);

        llConversation = (LinearLayout) findViewById(R.id.ll_conversation);
        llGroup = (LinearLayout) findViewById(R.id.ll_group);
        llSearch = (LinearLayout) findViewById(R.id.ll_search);

    }

    @Override
    public void initData() {
        initLineWidth();

        //初始话动画效果
        ObjectAnimator.ofFloat(tvConversation, "scaleX", 1.0f, 1.2f).setDuration(animateDuration).start();
        ObjectAnimator.ofFloat(tvConversation, "scaleY", 1.0f, 1.2f).setDuration(animateDuration).start();

        FragmentManager fm = getSupportFragmentManager();
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new ConversationFragment());
        fragments.add(new GroupFragment());
        fragments.add(new SearchFragment());
        mainPageAdapter = new MainPageAdapter(fm, fragments);
        viewPager.setAdapter(mainPageAdapter);
    }


    /**
     * 初始化
     * 给滑动线设置1/3
     */
    private void initLineWidth() {
        //窗口管理器
        WindowManager windowManager = getWindowManager();
        //得到屏幕宽度
        screenWidth = windowManager.getDefaultDisplay().getWidth();

        //得到控件布局参数
        ViewGroup.LayoutParams params = viewLine.getLayoutParams();

        //设置线的长度
        params.width = (int) Math.floor(screenWidth / 3) - 2;

        //将布局参数，设置给控件，是控件的宽度发生改变
        viewLine.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        //ViewPager控件的滑动监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //页面滑动时触发事件
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position != 2) {
                    ObjectAnimator.ofFloat(viewLine, "translationX", (float) Math.floor((position * screenWidth + positionOffsetPixels) / 3)).setDuration(0).start();
                } else {
                    //+2dp为了弥补像素损失
                    ObjectAnimator.ofFloat(viewLine, "translationX", (float) Math.floor((position * screenWidth + positionOffsetPixels) / 3) + 2).setDuration(0).start();
                }
            }

            /**
             * 滑动完成时
             * @param position
             */
            @Override
            public void onPageSelected(int position) {
                //选项卡的文字的颜色和大小
                changeTextScale();
            }

            //页面滑动状态改变
            @Override
            public void onPageScrollStateChanged(int state) {
                //隐藏输入法
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        //注册监听
        llConversation.setOnClickListener(this);
        llGroup.setOnClickListener(this);
        llSearch.setOnClickListener(this);

    }

    /**
     * 改变文字
     */
    private void changeTextScale() {
        //得到ViewPage当前页面的索引
        int index = viewPager.getCurrentItem();

        //设置动画
        tvConversation.setTextColor(getResources().getColor(index == 0 ? R.color.colorOrange : R.color.colorWhite));
        tvGroup.setTextColor(getResources().getColor(index == 1 ? R.color.colorOrange : R.color.colorWhite));
        tvSearch.setTextColor(getResources().getColor(index == 2 ? R.color.colorOrange : R.color.colorWhite));

        //如果是第一个fragment，那么文字的宽度和高度放大1.2f
        if (index == 0) {
            ViewPropertyAnimator.animate(tvConversation).scaleX(1.2f).setDuration(animateDuration).start();
            ViewPropertyAnimator.animate(tvConversation).scaleY(1.2f).setDuration(animateDuration).start();

        } else {
            ViewPropertyAnimator.animate(tvConversation).scaleX(1.0f).setDuration(animateDuration).start();
            ViewPropertyAnimator.animate(tvConversation).scaleY(1.0f).setDuration(animateDuration).start();
        }

        if (index == 1) {
            ViewPropertyAnimator.animate(tvGroup).scaleX(1.2f).setDuration(animateDuration).start();
            ViewPropertyAnimator.animate(tvGroup).scaleY(1.2f).setDuration(animateDuration).start();

        } else {
            ViewPropertyAnimator.animate(tvGroup).scaleX(1.0f).setDuration(animateDuration).start();
            ViewPropertyAnimator.animate(tvGroup).scaleY(1.0f).setDuration(animateDuration).start();
        }

        if (index == 2) {
            ViewPropertyAnimator.animate(tvSearch).scaleX(1.2f).setDuration(animateDuration).start();
            ViewPropertyAnimator.animate(tvSearch).scaleY(1.2f).setDuration(animateDuration).start();

        } else {
            ViewPropertyAnimator.animate(tvSearch).scaleX(1.0f).setDuration(animateDuration).start();
            ViewPropertyAnimator.animate(tvSearch).scaleY(1.0f).setDuration(animateDuration).start();
        }
    }

    @Override
    public void handleClickEvent(View view) {
        switch (view.getId()) {
            case R.id.ll_conversation:
                viewPager.setCurrentItem(0);
                break;
            case R.id.ll_group:
                viewPager.setCurrentItem(1);
                break;
            case R.id.ll_search:
                viewPager.setCurrentItem(2);
                break;
        }

    }
}
