package com.dream.aggregation.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dream.aggregation.R;
import com.dream.aggregation.activity.PlayActivity;
import com.dream.aggregation.model.GameLiveItem;
import com.dream.aggregation.util.GlideCircleTransform;
import com.dream.aggregation.util.URLUtil;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by inno-y on 2017/2/10.
 */

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private List<GameLiveItem> mList;

    public GridAdapter(Context mContext, List<GameLiveItem> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public GameLiveItem getItem(int position) {
        return mList == null ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder;
        GameLiveItem videoitem = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item, null);
            mHolder = new ViewHolder();
            mHolder.icon = (ImageView) convertView.findViewById(R.id.list_item_image);
            mHolder.msg = (TextView) convertView.findViewById(R.id.list_item_title);
            mHolder.bofang = (TextView) convertView.findViewById(R.id.BangumiImageView_baofang);
            mHolder.user = (TextView) convertView.findViewById(R.id.list_item_user);
            mHolder.lineraLayoutId = (LinearLayout) convertView.findViewById(R.id.lineraLayoutId);
            mHolder.pingtaiID = (TextView) convertView.findViewById(R.id.pingtaiID);
            mHolder.lineraLayoutPingtai = (LinearLayout) convertView.findViewById(R.id.lineraLayoutPingtai);

            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }


//            mImageLoader.displayImage(videoitem.getRoom_src(), mHolder.icon);
//        Glide.with(mContext).load(videoitem.getRoom_src()).into(mHolder.icon);
        Glide.with(mContext).load(videoitem.getRoom_src()).transform(new GlideCircleTransform(mContext, 7)).into(mHolder.icon);

        mHolder.msg.setText(videoitem.getRoom_name());
        double dOnline = Double.parseDouble(videoitem.getOnline());
        String strOnline = "";
        if (dOnline > 10000) {
            if (dOnline > 1000000) {
                LayoutParams linearParams = (LayoutParams) mHolder.lineraLayoutId.getLayoutParams();
                linearParams.width = 170;
                mHolder.lineraLayoutId.setLayoutParams(linearParams);
            } else if (dOnline > 100000) {
                LayoutParams linearParams = (LayoutParams) mHolder.lineraLayoutId.getLayoutParams();
                linearParams.width = 150;
                mHolder.lineraLayoutId.setLayoutParams(linearParams);
            } else {
                LayoutParams linearParams = (LayoutParams) mHolder.lineraLayoutId.getLayoutParams();
                linearParams.width = 135;
                mHolder.lineraLayoutId.setLayoutParams(linearParams);
            }
            dOnline = dOnline / 10000;
            DecimalFormat df = new DecimalFormat("######0.0");
            strOnline = df.format(dOnline) + "万";

        } else {
            LayoutParams linearParams = (LayoutParams) mHolder.lineraLayoutId.getLayoutParams();
            strOnline = (int) dOnline + "";
            if (dOnline > 1000) {
                linearParams.width = 125;
            } else if (dOnline > 100) {
                linearParams.width = 105;
            } else {
                linearParams.width = 95;
            }

            mHolder.lineraLayoutId.setLayoutParams(linearParams);
        }

        switch (videoitem.getPlatform_id()) {
            case URLUtil.DOUYU:
                mHolder.pingtaiID.setText(URLUtil.DOUYU_NAME);
                mHolder.lineraLayoutPingtai.setBackgroundColor(Color.parseColor(URLUtil.DOUYU_COLOR));
                break;
            case URLUtil.ZHANQI:
                mHolder.pingtaiID.setText(URLUtil.ZHANQI_NAME);
                mHolder.lineraLayoutPingtai.setBackgroundColor(Color.parseColor(URLUtil.ZHANQI_COLOR));
                break;
            case URLUtil.XIONGMAO:
                mHolder.pingtaiID.setText(URLUtil.XIONGMAO_NAME);
                mHolder.lineraLayoutPingtai.setBackgroundColor(Color.parseColor(URLUtil.XIONGMAO_COLOR));
                break;
            case URLUtil.QUANMIN:
                mHolder.pingtaiID.setText(URLUtil.QUANMIN_NAME);
                mHolder.lineraLayoutPingtai.setBackgroundColor(Color.parseColor(URLUtil.QUANMIN_COLOR));
                break;
            case URLUtil.HUYA:
                mHolder.pingtaiID.setText(URLUtil.HUYA_NAME);
                mHolder.lineraLayoutPingtai.setBackgroundColor(Color.parseColor(URLUtil.HUYA_COLOR));
                break;
        }

        mHolder.user.setText(videoitem.getNickname());
        mHolder.bofang.setText(strOnline);

        convertView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                GameLiveItem item = (GameLiveItem) mList.get(position);
                if (item.getUrl() == null || "".equals(item.getUrl())) {
                    return;
                }

                Intent intent = new Intent();
                intent.setClass(mContext, PlayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("htmlUrl", item.getUrl());
                bundle.putSerializable("key", item.getKey());
                intent.putExtras(bundle);
                mContext.startActivity(intent);

//                intent.setAction("android.intent.action.VIEW");
//                Uri content_url = Uri.parse(item.getUrl());
//                intent.setData(content_url);
//                mContext.startActivity(intent);

                ((Activity) mContext).overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_no);
            }
        });


        return convertView;
    }

    private class ViewHolder {
        private ImageView icon;
        private TextView msg;
        private TextView bofang;
        private TextView user;
        private LinearLayout lineraLayoutId;
        private LinearLayout lineraLayoutPingtai;
        private TextView pingtaiID;
    }
}
