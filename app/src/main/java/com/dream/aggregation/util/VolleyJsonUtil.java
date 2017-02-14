package com.dream.aggregation.util;

import com.dream.aggregation.model.BannerItem;
import com.dream.aggregation.model.GameLiveItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by inno-y on 2017/2/8.
 */

public class VolleyJsonUtil {

    public static String getUrlSlide(int platform_id) {
        String url_slide = "";
        switch (platform_id) {
            case URLUtil.DOUYU:
                url_slide = URLUtil.URL_DOUYU_SLIDE;
                break;
            case URLUtil.ZHANQI:
                url_slide = URLUtil.URL_ZHANQI_SLIDE;
                break;
            case URLUtil.XIONGMAO:
                url_slide = URLUtil.URL_XIONGMAO_SLIDE;
                break;
            case URLUtil.QUANMIN:
                url_slide = URLUtil.URL_QUANMIN_SLIDE;
                break;
            case URLUtil.HUYA:
                url_slide = URLUtil.URL_QUANMIN_SLIDE; //TODO:虎牙目前未抓取，全民代替
                break;
        }
        return url_slide;
    }

    public static ArrayList<BannerItem> getBannerList(int platform_id, int iCount, JSONObject response) {

        ArrayList<BannerItem> gliList = new ArrayList<BannerItem>();
        int iTempCount = 0;
        JSONArray array = null;

        switch (platform_id) {
            case URLUtil.DOUYU:
                try {
                    array = response.getJSONArray("data");
                    if (array.length() > iCount) {
                        iTempCount = iCount;
                    } else {
                        iTempCount = array.length();
                    }
                    for (int i = 0; i < iTempCount; i++) {
                        BannerItem item = new BannerItem();
                        item.setImg(array.getJSONObject(i).getString("tv_pic_url").toString());
                        item.setTitle(array.getJSONObject(i).getString("title").toString());
                        JSONObject jsonRoom = array.getJSONObject(i).getJSONObject("room");
                        item.setLink(URLUtil.URL_DOUYU_HOME + jsonRoom.getString("room_id").toString());
                        gliList.add(item);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case URLUtil.XIONGMAO:
                try {
                    array = response.getJSONArray("data");
                    if (array.length() > iCount) {
                        iTempCount = iCount;
                    } else {
                        iTempCount = array.length();
                    }

                    for (int i = 0; i < iTempCount; i++) {
                        BannerItem item = new BannerItem();
                        item.setImg(array.getJSONObject(i).getString("bigimg").toString());
                        item.setTitle(array.getJSONObject(i).getString("title").toString());
                        item.setLink(array.getJSONObject(i).getString("url").toString().replace("/room", ""));
                        item.setKey(array.getJSONObject(i).getString("room_key").toString());
                        gliList.add(item);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case URLUtil.QUANMIN:
                try {
                    array = response.getJSONArray("app-focus");
                    if (array.length() > iCount) {
                        iTempCount = iCount;
                    } else {
                        iTempCount = array.length();
                    }

                    for (int i = 0; i < iTempCount; i++) {
                        BannerItem item = new BannerItem();
                        item.setImg(array.getJSONObject(i).getString("thumb").toString());
                        item.setTitle(array.getJSONObject(i).getString("title").toString());
                        String fk = array.getJSONObject(i).getString("fk").toString();
                        if (fk == null || "".equals(fk)) {
                            continue;
                        }
                        item.setLink(URLUtil.URL_QUANMIN_HOME + fk.replace("/", ""));
                        item.setKey(fk.replace("/", ""));
                        gliList.add(item);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case URLUtil.ZHANQI:
                try {
                    array = response.getJSONArray("data");
                    if (array.length() > iCount) {
                        iTempCount = iCount;
                    } else {
                        iTempCount = array.length();
                    }

                    for (int i = 0; i < iTempCount; i++) {
                        BannerItem item = new BannerItem();
                        String imgPath = array.getJSONObject(i).getString("spic").toString();
                        if (imgPath == null || "".equals(imgPath)) {
                            continue;
                        }
                        item.setImg(imgPath);
                        item.setTitle(array.getJSONObject(i).getString("title").toString());
                        item.setLink(URLUtil.URL_ZHANQI_HOME + array.getJSONObject(i).getString("url").toString());
                        gliList.add(item);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
        return gliList;
    }

    public static ArrayList<GameLiveItem> getGameList(int platform_id, int iCount, JSONObject response) {

        ArrayList<GameLiveItem> gliList = new ArrayList<GameLiveItem>();
        int iTempCount = 0;
        JSONArray array = null;

        switch (platform_id) {
            case URLUtil.DOUYU:
                try {
                    array = response.getJSONArray("data");

                    if (array.length() > iCount) {
                        iTempCount = iCount;
                    } else {
                        iTempCount = array.length();
                    }
                    for (int i = 0; i < iTempCount; i++) {
                        GameLiveItem glItem = new GameLiveItem();
                        glItem.setPlatform_id(platform_id);
                        glItem.setRoom_id(array.getJSONObject(i).getString("room_id").toString());
                        glItem.setRoom_name(array.getJSONObject(i).getString("room_name").toString());
                        glItem.setRoom_src(array.getJSONObject(i).getString("room_src").toString());
                        glItem.setVertical_src(array.getJSONObject(i).getString("vertical_src").toString());
                        glItem.setIsVertical(array.getJSONObject(i).getString("isVertical").toString());
                        glItem.setCate_id(array.getJSONObject(i).getString("cate_id").toString());
                        glItem.setOwner_uid(array.getJSONObject(i).getString("owner_uid").toString());
                        glItem.setNickname(array.getJSONObject(i).getString("nickname").toString());
                        glItem.setOnline(array.getJSONObject(i).getString("online").toString());
                        glItem.setUrl(array.getJSONObject(i).getString("url").toString());
                        glItem.setGame_url(array.getJSONObject(i).getString("game_url").toString());
                        glItem.setGame_name(array.getJSONObject(i).getString("game_name").toString());
                        glItem.setAvatar(array.getJSONObject(i).getString("avatar").toString());
                        gliList.add(glItem);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case URLUtil.ZHANQI:
                try {
                    JSONObject jsonRoom = response.getJSONObject("data");
                    array = jsonRoom.getJSONArray("rooms");
                    if (array.length() > iCount) {
                        iTempCount = iCount;
                    } else {
                        iTempCount = array.length();
                    }
                    for (int i = 0; i < iTempCount; i++) {
                        GameLiveItem glItem = new GameLiveItem();
                        glItem.setPlatform_id(platform_id);
                        glItem.setRoom_id(array.getJSONObject(i).getString("id").toString());
                        glItem.setRoom_name(array.getJSONObject(i).getString("title").toString());
                        glItem.setRoom_src(array.getJSONObject(i).getString("bpic").toString());
                        glItem.setVertical_src(array.getJSONObject(i).getString("spic").toString());
                        glItem.setIsVertical(array.getJSONObject(i).getString("status").toString());
                        glItem.setCate_id(array.getJSONObject(i).getString("gameId").toString());
                        glItem.setOwner_uid(array.getJSONObject(i).getString("uid").toString());
                        glItem.setNickname(array.getJSONObject(i).getString("nickname").toString());
                        glItem.setOnline(array.getJSONObject(i).getString("online").toString());
                        glItem.setUrl(URLUtil.URL_ZHANQI_HOME + array.getJSONObject(i).getString("url").toString().replace("/", ""));
                        glItem.setGame_url(URLUtil.URL_ZHANQI_HOME + array.getJSONObject(i).getString("gameId").toString());
                        glItem.setGame_name(array.getJSONObject(i).getString("gameName").toString());
                        glItem.setAvatar(array.getJSONObject(i).getString("avatar").toString());
                        gliList.add(glItem);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case URLUtil.XIONGMAO:
                try {
                    JSONObject jsonItems = response.getJSONObject("data");
                    array = jsonItems.getJSONArray("items");
                    if (array.length() > iCount) {
                        iTempCount = iCount;
                    } else {
                        iTempCount = array.length();
                    }
                    for (int i = 0; i < iTempCount; i++) {
                        GameLiveItem glItem = new GameLiveItem();
                        glItem.setPlatform_id(platform_id);
                        glItem.setRoom_id(array.getJSONObject(i).getString("id").toString());
                        glItem.setRoom_name(array.getJSONObject(i).getString("name").toString());
                        glItem.setRoom_src(array.getJSONObject(i).getJSONObject("pictures").getString("img").toString());
                        glItem.setVertical_src(array.getJSONObject(i).getJSONObject("pictures").getString("img").toString());
                        glItem.setIsVertical(array.getJSONObject(i).getString("status").toString());
                        glItem.setCate_id(array.getJSONObject(i).getJSONObject("classification").getString("ename").toString());
                        glItem.setOwner_uid(array.getJSONObject(i).getString("hostid").toString());
                        glItem.setNickname(array.getJSONObject(i).getJSONObject("userinfo").getString("nickName").toString());
                        glItem.setOnline(array.getJSONObject(i).getString("person_num").toString());
                        glItem.setUrl(URLUtil.URL_XIONGMAO_HOME + array.getJSONObject(i).getString("id").toString());
                        glItem.setGame_url(URLUtil.URL_XIONGMAO_HOME_CATE + array.getJSONObject(i).getJSONObject("classification").getString("ename").toString());
                        glItem.setGame_name(array.getJSONObject(i).getJSONObject("classification").getString("cname").toString());
                        glItem.setAvatar(array.getJSONObject(i).getJSONObject("userinfo").getString("avatar").toString());
                        gliList.add(glItem);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case URLUtil.QUANMIN:
                try {
                    array = response.getJSONArray("data");
                    if (array.length() > iCount) {
                        iTempCount = iCount;
                    } else {
                        iTempCount = array.length();
                    }
                    for (int i = 0; i < iTempCount; i++) {
                        GameLiveItem glItem = new GameLiveItem();
                        glItem.setPlatform_id(platform_id);
                        glItem.setRoom_id(array.getJSONObject(i).getString("uid").toString());
                        glItem.setRoom_name(array.getJSONObject(i).getString("title").toString());
                        glItem.setRoom_src(array.getJSONObject(i).getString("thumb").toString());
                        glItem.setVertical_src(array.getJSONObject(i).getString("thumb").toString());
                        glItem.setIsVertical(array.getJSONObject(i).getString("status").toString());
                        glItem.setCate_id(array.getJSONObject(i).getString("category_slug").toString());
                        glItem.setOwner_uid(array.getJSONObject(i).getString("uid").toString());
                        glItem.setNickname(array.getJSONObject(i).getString("nick").toString());
                        glItem.setOnline(array.getJSONObject(i).getString("view").toString());
                        glItem.setUrl(URLUtil.URL_QUANMIN_HOME + array.getJSONObject(i).getString("uid").toString());
                        glItem.setKey(array.getJSONObject(i).getString("uid").toString());
                        glItem.setGame_url(URLUtil.URL_QUANMIN_HOME_CATE + array.getJSONObject(i).getString("category_slug").toString());
                        glItem.setGame_name(array.getJSONObject(i).getString("category_name").toString());
                        glItem.setAvatar(array.getJSONObject(i).getString("avatar").toString());
                        gliList.add(glItem);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }

        return gliList;
    }
}
