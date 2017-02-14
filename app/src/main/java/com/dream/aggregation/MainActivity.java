package com.dream.aggregation;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationBar.OnTabSelectedListener;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.dream.aggregation.activity.BaseActivity;
import com.dream.aggregation.fragment.MovieFragment;
import com.dream.aggregation.fragment.TVFragment;
import com.dream.aggregation.fragment.UserFragment;
import com.dream.aggregation.fragment.VideoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements OnTabSelectedListener {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    private Fragment currentFragment;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((DreamAggApplication)DreamAggApplication.getContext()).addActivity(this);
        ButterKnife.bind(this);

        if(Build.VERSION.SDK_INT>=21){
            getSupportActionBar().setElevation(0);
        }

        initUI();
        initListener();
    }



    private void initUI(){
        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);

        bottomNavigationBar.setBarBackgroundColor(R.color.colorPrimary);
        bottomNavigationBar.setInActiveColor(R.color.gray);//未选中时的颜色
        bottomNavigationBar.setActiveColor(R.color.white);//选中时的颜色

        bottomNavigationBar
//                .addItem(new BottomNavigationItem(R.drawable.home_bottom, R.string.bottom_home))
                .addItem(new BottomNavigationItem(R.drawable.video_bottom, R.string.bottom_video))
                .addItem(new BottomNavigationItem(R.drawable.tv_bottom, R.string.bottom_tv))
                .addItem(new BottomNavigationItem(R.drawable.movie_bottom, R.string.bottom_movie))
                .addItem(new BottomNavigationItem(R.drawable.user_bottom, R.string.bottom_user))
                .initialise();

        if(currentFragment == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, getFragmentById(0)).commit();
        }
    }

    private void initListener(){
        bottomNavigationBar.setTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(int position) {

        Fragment fragment = getFragmentById(position);

        if (currentFragment == null || !currentFragment
                .getClass().getName().equals(fragment.getClass().getName())) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment).commit();
        }
        currentFragment = fragment;

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    private Fragment getFragmentById(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new VideoFragment();
                break;
            case 1:
                fragment=new TVFragment();
                break;
            case 2:
                fragment=new MovieFragment();
                break;
            case 3:
                fragment=new UserFragment();
                break;

        }
        return fragment;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000)
            {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
//                finish();
//                android.os.Process.killProcess(android.os.Process.myPid());
                DreamAggApplication.quiteApplication();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
