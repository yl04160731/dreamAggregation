package com.dream.aggregation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dream.aggregation.Adapter.VideoTabAdapter;
import com.dream.aggregation.R;
import com.viewpagerindicator.PageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VideoFragment extends BaseFragment {

    private View view = null;
    @BindView(R.id.pager)
    ViewPager pager;

    @BindView(R.id.indicator)
    PageIndicator indicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        view = inflater.inflate(R.layout.fragment_video, container, false);
//        checkConnectivity(view);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filtershow_history:
                Toast.makeText(getActivity(),"历史",Toast.LENGTH_SHORT).show();
                break;
            case R.id.filtershow_like:
                Toast.makeText(getActivity(),"收藏",Toast.LENGTH_SHORT).show();
                break;
            case R.id.filtershow_search:
                Toast.makeText(getActivity(),"搜索",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initDate();
        initView();
        super.onViewCreated(view, savedInstanceState);
    }

    public void initDate(){
        FragmentPagerAdapter adapter = new VideoTabAdapter(
                getChildFragmentManager());
        // 视图切换器
        pager.setOffscreenPageLimit(0);
        pager.setAdapter(adapter);

        // 页面指示器
        indicator.setViewPager(pager);

    }

    public void initView(){

    }

}
