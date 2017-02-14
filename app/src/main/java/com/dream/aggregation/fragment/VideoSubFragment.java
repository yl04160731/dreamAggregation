package com.dream.aggregation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dream.aggregation.Adapter.GridAdapter;
import com.dream.aggregation.R;
import com.dream.aggregation.activity.PlayActivity;
import com.dream.aggregation.model.BannerItem;
import com.dream.aggregation.model.GameLiveItem;
import com.dream.aggregation.util.GlideImageLoader;
import com.dream.aggregation.util.URLUtil;
import com.dream.aggregation.util.VolleyJsonUtil;
import com.dream.aggregation.view.CustomGridView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dream.aggregation.util.URLUtil.BANNER;
import static com.dream.aggregation.util.URLUtil.CHUANYUE;
import static com.dream.aggregation.util.URLUtil.DIXIACHENG;
import static com.dream.aggregation.util.URLUtil.DOTA;
import static com.dream.aggregation.util.URLUtil.DOUYU_CHUANYUE;
import static com.dream.aggregation.util.URLUtil.DOUYU_DIXIACHENG;
import static com.dream.aggregation.util.URLUtil.DOUYU_DOTA;
import static com.dream.aggregation.util.URLUtil.DOUYU_LUSHI;
import static com.dream.aggregation.util.URLUtil.DOUYU_SHOUWANG;
import static com.dream.aggregation.util.URLUtil.DOUYU_YINGXIONG;
import static com.dream.aggregation.util.URLUtil.DOUYU_ZHUJI;
import static com.dream.aggregation.util.URLUtil.LUSHI;
import static com.dream.aggregation.util.URLUtil.SHOUWANG;
import static com.dream.aggregation.util.URLUtil.YINGXIONG;
import static com.dream.aggregation.util.URLUtil.ZHUJI;


public class VideoSubFragment extends BaseFragment implements OnBannerClickListener {

    private final int BANNER_CCOUNT = 10;
    private final int GRID_CCOUNT = 6;

    RequestQueue requestQueue;

    private int platform_id;
    private View view = null;

    @BindView(R.id.banner)
    public Banner banner;

    @BindView(R.id.LushiGridView)
    public CustomGridView LushiGridView;

    @BindView(R.id.YingxiongGridView)
    public CustomGridView YingxiongGridView;

    @BindView(R.id.DOTA2GridView)
    public CustomGridView DOTA2GridView;

    @BindView(R.id.ZhujiGridView)
    public CustomGridView ZhujiGridView;

    @BindView(R.id.ShouwangGridView)
    public CustomGridView ShouwangGridView;

    public VideoSubFragment() {
    }

    public VideoSubFragment(int platform_id) {
        this.platform_id = platform_id;
    }
    ArrayList<BannerItem> getBannerList = null;


    List<String> images = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        view = inflater.inflate(R.layout.fragment_sub_video, container, false);
//        checkConnectivity(view);
        ButterKnife.bind(this, view);
        requestQueue = Volley.newRequestQueue(getActivity());

        return view;
    }

    @Override
    public void OnBannerClick(int position) {

        Intent intent = new Intent();
        intent.setClass(getActivity(), PlayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("htmlUrl", getBannerList.get(position - 1).getLink());
        bundle.putSerializable("key", getBannerList.get(position - 1).getKey());
        intent.putExtras(bundle);
        this.startActivity(intent);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initDate();
        super.onViewCreated(view, savedInstanceState);
    }

    public void initDate(){

        ArrayList<String> url_all = new ArrayList<String>();
        url_all.add(VolleyJsonUtil.getUrlSlide(platform_id));
        url_all.add(URLUtil.getGameListURL(platform_id, YINGXIONG));
        url_all.add(URLUtil.getGameListURL(platform_id, LUSHI));
        url_all.add(URLUtil.getGameListURL(platform_id, DOTA));
        url_all.add(URLUtil.getGameListURL(platform_id, ZHUJI));
        url_all.add(URLUtil.getGameListURL(platform_id, SHOUWANG));

        for(int i = 0;i < url_all.size();i++){
            final int requestIndex = i;
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET, url_all.get(requestIndex), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            switch (requestIndex){
                                case BANNER:
                                    initBanner(response);
                                    break;
                                case YINGXIONG:
                                    initYingxiongGrid(response);
                                    break;
                                case LUSHI:
                                    initLushiGrid(response);
                                    break;
                                case DOTA:
                                    initDOTA2Grid(response);
                                    break;
                                case SHOUWANG:
                                    initShouwangGrid(response);
                                    break;
                                case ZHUJI:
                                    initZhujiGrid(response);
                                    break;
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError arg0) {
                }
            });
            requestQueue.add(jsonObjectRequest);
        }
    }

    public void initLushiGrid(JSONObject response){
        LushiGridView.setAdapter(getAdapter(platform_id,GRID_CCOUNT,response));
    }

    public void initYingxiongGrid(JSONObject response){
        YingxiongGridView.setAdapter(getAdapter(platform_id,GRID_CCOUNT,response));
    }

    public void initDOTA2Grid(JSONObject response){
        DOTA2GridView.setAdapter(getAdapter(platform_id,GRID_CCOUNT,response));
    }

    public void initShouwangGrid(JSONObject response){
        ShouwangGridView.setAdapter(getAdapter(platform_id,GRID_CCOUNT,response));
    }

    public void initZhujiGrid(JSONObject response){
        ZhujiGridView.setAdapter(getAdapter(platform_id,GRID_CCOUNT,response));
    }

    public GridAdapter getAdapter(int platform_id,int count,JSONObject response){
        ArrayList<GameLiveItem> gameLiveItemsList = VolleyJsonUtil.getGameList(platform_id,GRID_CCOUNT,response);
        GridAdapter gridAdapter = new GridAdapter(getActivity(), gameLiveItemsList);
        return gridAdapter;
    }

    public void initBanner(JSONObject response){

        getBannerList = VolleyJsonUtil.getBannerList(platform_id, BANNER_CCOUNT, response);

        if (getBannerList == null || getBannerList.size() == 0) {
            return;
        }
        images.clear();
        titles.clear();
        for (BannerItem bannerItem : getBannerList) {
            images.add(bannerItem.getImg());
            titles.add(bannerItem.getTitle());
        }

        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Accordion);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3200);
        banner.setOnBannerClickListener(this);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

}
