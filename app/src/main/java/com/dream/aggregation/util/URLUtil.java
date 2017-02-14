package com.dream.aggregation.util;

public class URLUtil {
	
	//各平台ID
	public final static int DOUYU = 1;       //斗鱼
	public final static int ZHANQI = 2;		 //战旗
	public final static int XIONGMAO = 3;	 //熊猫
	public final static int QUANMIN = 4;	 //全民
	public final static int HUYA = 5;	 	 //虎牙
	
	// 个平台中文名
	public final static String DOUYU_NAME = "斗鱼";
	public final static String ZHANQI_NAME = "战旗";
	public final static String XIONGMAO_NAME = "熊猫";
	public final static String QUANMIN_NAME = "全民";
	public final static String HUYA_NAME = "虎牙";

	//域名标识
	public final static String DOUYU_NAME_BS = "douyu";
	public final static String ZHANQI_NAME_BS = "zhanqi";
	public final static String XIONGMAO_NAME_BS = "panda";
	public final static String QUANMIN_NAME_BS = "quanmin";
	public final static String HUYA_NAME_BS = "huya";
	
	// 个平台图标颜色
	public final static String DOUYU_COLOR = "#ff7700"; 		//斗鱼
	public final static String ZHANQI_COLOR = "#0d7ec0";		//战旗
	public final static String XIONGMAO_COLOR = "#4ca7e0";		//熊猫
	public final static String QUANMIN_COLOR = "#ff6a49";		//全民
	public final static String HUYA_COLOR = "#f7b835";			//虎牙
	
	//游戏分类代码
	public final static int BANNER = 0;
	public final static int YINGXIONG = 1;
	public final static int LUSHI = 2;
	public final static int DOTA = 3;
	public final static int ZHUJI = 4;
	public final static int SHOUWANG = 5;
	public final static int DIXIACHENG = 6;
	public final static int CHUANYUE = 7;

	
	//各平台分类简称
	//斗鱼分类简称
	public final static String DOUYU_YINGXIONG = "LOL";
	public final static String DOUYU_LUSHI = "How";
	public final static String DOUYU_DOTA = "DOTA2";
	public final static String DOUYU_SHOUWANG = "Overwatch";
	public final static String DOUYU_DIXIACHENG = "DNF";
	public final static String DOUYU_CHUANYUE = "CF";
	public final static String DOUYU_ZHUJI = "TVgame";
	
	//战旗分类简称
	public final static String ZHANQI_YINGXIONG = "6";
	public final static String ZHANQI_LUSHI = "9";
	public final static String ZHANQI_DOTA = "10";
	public final static String ZHANQI_SHOUWANG = "82";
	public final static String ZHANQI_DIXIACHENG = "22";
	public final static String ZHANQI_CHUANYUE = "67";
	public final static String ZHANQI_ZHUJI = "49";
	
	//熊猫分类简称
	public final static String XIONGMAO_YINGXIONG = "lol";
	public final static String XIONGMAO_LUSHI = "hearthstone";
	public final static String XIONGMAO_DOTA = "dota2";
	public final static String XIONGMAO_SHOUWANG = "overwatch";
	public final static String XIONGMAO_DIXIACHENG = "dnf";
	public final static String XIONGMAO_CHUANYUE = "cf";
	public final static String XIONGMAO_ZHUJI = "zhuji";
	
	//全民分类简称
	public final static String QUANMIN_YINGXIONG = "lol";
	public final static String QUANMIN_LUSHI = "heartstone";
	public final static String QUANMIN_DOTA = "dota2";
	public final static String QUANMIN_SHOUWANG = "overwatch";
	public final static String QUANMIN_DIXIACHENG = "dnf";
	public final static String QUANMIN_CHUANYUE = "cfpc";
	public final static String QUANMIN_ZHUJI = "tvgame";
	
	// 斗鱼URL链接
	public static String URL_DOUYU_HOME = "http://www.douyu.com/"; //斗鱼主页
	public static String URL_DOUYU_SLIDE = "http://capi.douyucdn.cn/api/v1/slide/6?client_sys=android"; //banner栏5幅广告
	public static String URL_DOUYU_GAME_BY_TYPE = "http://open.douyucdn.cn/api/RoomApi/live/"; //根据分类简称获得游戏列表 例如后 +lol
	public static String URL_DOUYU_ROOM = "http://open.douyucdn.cn/api/RoomApi/room/"; //根据房间ID或别名获得房间信息{房间 Id 或者房间别名}
	
	// 战旗URL链接
	public static String URL_ZHANQI_HOME = "http://m.zhanqi.tv/"; //战旗主页
	public static String URL_ZHANQI_SLIDE = "http://www.zhanqi.tv/api/touch/apps.banner?time" + System.currentTimeMillis(); //banner栏5幅广告
	public static String URL_ZHANQI_GAME_BY_TYPE1 = "http://www.zhanqi.tv/api/static/game.lives/"; 
	public static String URL_ZHANQI_GAME_BY_TYPE2 = "/30-1.json"; // 游戏ID后加上这个 例如9+"/30-1.json" 30代表去30条
	public static String URL_ZHANQI_ROOM = "http://www.zhanqi.tv/api/static/live.roomid/xxx.json/"; //xxx为房间号
	
	//熊猫URL链接
	public static String URL_XIONGMAO_HOME = "http://www.panda.tv/"; //熊猫主页
	public static String URL_XIONGMAO_HOME_CATE = "http://www.panda.tv/cate/"; //熊猫分类主页
	public static String URL_XIONGMAO_SLIDE = "http://api.m.panda.tv/ajax_rmd_ads_get"; // banner栏5幅
	public static String URL_XIONGMAO_GAME_BY_TYPE1 = "http://api.m.panda.tv/ajax_get_live_list_by_cate?cate="; 
	public static String URL_XIONGMAO_GAME_BY_TYPE2 = "&pageno=1&pagenum=30"; // 游戏ID后加上这个 例如hearthstone+"&pageno=1&pagenum=30" 30代表去30条
	public static String URL_XIONGMAO_ROOM = "http://api.m.panda.tv/ajax_get_liveroom_baseinfo?roomid=xxx"; //xxx为房间号
	
	// 全民URL链接
	public static String URL_QUANMIN_HOME = "http://m.quanmin.tv/"; //全民主页
	public static String URL_QUANMIN_HOME_CATE = "http://www.quanmin.tv/game/"; //全民分类主页
	public static String URL_QUANMIN_HOME_V = "http://www.quanmin.tv/v/"; //全民游戏主页
	public static String URL_QUANMIN_SLIDE = "http://www.quanmin.tv/json/page/app-data/info.json"; // banner栏5幅
	public static String URL_QUANMIN_GAME_BY_TYPE1 = "http://www.quanmin.tv/json/categories/"; 
	public static String URL_QUANMIN_GAME_BY_TYPE2 = "/list.json"; // 游戏ID后加上这个 例如heartstone+"/list.json" 
//	public static String URL_QUANMIN_ROOM = "http://www.zhanqi.tv/api/static/live.roomid/xxx.json/"; 
	
	// 斗鱼有些分类简称对照（英雄联盟：LOL;炉石传说：How;DOTA2:DOTA2;守望先锋：Overwatch;地下城与勇士：DNF;穿越火线：CF;主机游戏：TVgame;
	
	/**
	 * 获取某游戏列表的URL
	 * 
	 * @param platformType
	 *            平台代码
	 * @param gameID
	 *            游戏id
	 * @return
	 */
	public static String getGameListURL(int platformType,int gameID) {
		String url = "";
		switch (platformType) {
		case DOUYU:
			url = URL_DOUYU_GAME_BY_TYPE;
			switch (gameID){
			case YINGXIONG:
				url = url + DOUYU_YINGXIONG;
				break;
			case LUSHI:
				url = url + DOUYU_LUSHI;
				break;
			case DOTA:
				url = url + DOUYU_DOTA;
				break;
			case SHOUWANG:
				url = url + DOUYU_SHOUWANG;
				break;
			case DIXIACHENG:
				url = url + DOUYU_DIXIACHENG;
				break;
			case CHUANYUE:
				url = url + DOUYU_CHUANYUE;
				break;
			case ZHUJI:
				url = url + DOUYU_ZHUJI;
				break;
			}
			break;
			
		case ZHANQI:
			url = "";
			switch (gameID){
			case YINGXIONG:
				url = URL_ZHANQI_GAME_BY_TYPE1 + ZHANQI_YINGXIONG + URL_ZHANQI_GAME_BY_TYPE2;
				break;
			case LUSHI:
				url = URL_ZHANQI_GAME_BY_TYPE1 + ZHANQI_LUSHI + URL_ZHANQI_GAME_BY_TYPE2;
				break;
			case DOTA:
				url = URL_ZHANQI_GAME_BY_TYPE1 + ZHANQI_DOTA + URL_ZHANQI_GAME_BY_TYPE2;
				break;
			case SHOUWANG:
				url = URL_ZHANQI_GAME_BY_TYPE1 + ZHANQI_SHOUWANG + URL_ZHANQI_GAME_BY_TYPE2;
				break;
			case DIXIACHENG:
				url = URL_ZHANQI_GAME_BY_TYPE1 + ZHANQI_DIXIACHENG + URL_ZHANQI_GAME_BY_TYPE2;
				break;
			case CHUANYUE:
				url = URL_ZHANQI_GAME_BY_TYPE1 + ZHANQI_CHUANYUE + URL_ZHANQI_GAME_BY_TYPE2;
				break;
			case ZHUJI:
				url = URL_ZHANQI_GAME_BY_TYPE1 + ZHANQI_ZHUJI + URL_ZHANQI_GAME_BY_TYPE2;
				break;
			}
			break;
			
		case XIONGMAO:
			url = "";
			switch (gameID){
			case YINGXIONG:
				url = URL_XIONGMAO_GAME_BY_TYPE1 + XIONGMAO_YINGXIONG + URL_XIONGMAO_GAME_BY_TYPE2;
				break;
			case LUSHI:
				url = URL_XIONGMAO_GAME_BY_TYPE1 + XIONGMAO_LUSHI + URL_XIONGMAO_GAME_BY_TYPE2;
				break;
			case DOTA:
				url = URL_XIONGMAO_GAME_BY_TYPE1 + XIONGMAO_DOTA + URL_XIONGMAO_GAME_BY_TYPE2;
				break;
			case SHOUWANG:
				url = URL_XIONGMAO_GAME_BY_TYPE1 + XIONGMAO_SHOUWANG + URL_XIONGMAO_GAME_BY_TYPE2;
				break;
			case DIXIACHENG:
				url = URL_XIONGMAO_GAME_BY_TYPE1 + XIONGMAO_DIXIACHENG + URL_XIONGMAO_GAME_BY_TYPE2;
				break;
			case CHUANYUE:
				url = URL_XIONGMAO_GAME_BY_TYPE1 + XIONGMAO_CHUANYUE + URL_XIONGMAO_GAME_BY_TYPE2;
				break;
			case ZHUJI:
				url = URL_XIONGMAO_GAME_BY_TYPE1 + XIONGMAO_ZHUJI + URL_XIONGMAO_GAME_BY_TYPE2;
				break;
			}
			break;
		
		case QUANMIN:
			url = "";
			switch (gameID){
			case YINGXIONG:
				url = URL_QUANMIN_GAME_BY_TYPE1 + QUANMIN_YINGXIONG + URL_QUANMIN_GAME_BY_TYPE2;
				break;
			case LUSHI:
				url = URL_QUANMIN_GAME_BY_TYPE1 + QUANMIN_LUSHI + URL_QUANMIN_GAME_BY_TYPE2;
				break;
			case DOTA:
				url = URL_QUANMIN_GAME_BY_TYPE1 + QUANMIN_DOTA + URL_QUANMIN_GAME_BY_TYPE2;
				break;
			case SHOUWANG:
				url = URL_QUANMIN_GAME_BY_TYPE1 + QUANMIN_SHOUWANG + URL_QUANMIN_GAME_BY_TYPE2;
				break;
			case DIXIACHENG:
				url = URL_QUANMIN_GAME_BY_TYPE1 + QUANMIN_DIXIACHENG + URL_QUANMIN_GAME_BY_TYPE2;
				break;
			case CHUANYUE:
				url = URL_QUANMIN_GAME_BY_TYPE1 + QUANMIN_CHUANYUE + URL_QUANMIN_GAME_BY_TYPE2;
				break;
			case ZHUJI:
				url = URL_QUANMIN_GAME_BY_TYPE1 + QUANMIN_ZHUJI + URL_QUANMIN_GAME_BY_TYPE2;
				break;
			}
			break;
		}
		return url;
	}

}
