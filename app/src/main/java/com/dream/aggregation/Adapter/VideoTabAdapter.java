package com.dream.aggregation.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.dream.aggregation.fragment.VideoSubFragment;
import com.dream.aggregation.util.URLUtil;

public class VideoTabAdapter extends FragmentPagerAdapter {
	// 内容标题
	public static final String[] Video_TITLE = new String[] { "首页",
			"斗鱼","战旗", "熊猫", "全民","虎牙"  };
	
	public VideoTabAdapter(FragmentManager fm) {
		super(fm);
	}

	// 获取项
	@Override
	public Fragment getItem(int position) {
		VideoSubFragment videoSubFragment = null;
		switch (position) {
			case 0:
				videoSubFragment = new VideoSubFragment(URLUtil.DOUYU);
				return videoSubFragment;
			case 1:
				videoSubFragment = new VideoSubFragment(URLUtil.DOUYU);
				return videoSubFragment;
			case 2:
				videoSubFragment = new VideoSubFragment(URLUtil.ZHANQI);
				return videoSubFragment;
			case 3:
				videoSubFragment = new VideoSubFragment(URLUtil.XIONGMAO);
				return videoSubFragment;
			case 4:
				videoSubFragment = new VideoSubFragment(URLUtil.QUANMIN);
				return videoSubFragment;
			case 5:
				videoSubFragment = new VideoSubFragment(URLUtil.HUYA);
				return videoSubFragment;
		}
		return videoSubFragment;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// 返回页面标题
		return Video_TITLE[position % Video_TITLE.length].toUpperCase();
	}

	@Override
	public int getCount() {
		// 页面个数
		return Video_TITLE.length;
	}

}
