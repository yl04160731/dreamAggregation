package com.dream.aggregation.model;

import java.io.Serializable;

public class GameLiveItem implements Serializable{
	private static final long serialVersionUID = 1L;
	private int platform_id;//平台ID
	private String room_id;//房间ID
	private String room_src;//房间图片
	private String vertical_src;//竖直图片
	private String isVertical;//是否垂直
	private String cate_id;//游戏分类 ID
	private String room_name;//房间名称
	private String owner_uid;//房间所属用户的 UID
	private String nickname;//房间所属用户的账号
	private String online;//在线人数
	private String url;//房间的网址
	private String game_url;//游戏分类网址
	private String game_name;//游戏分类名称
	private String avatar;//用户头像图片
	private String key;//直播源KEY

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getRoom_id() {
		return room_id;
	}
	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}
	public String getRoom_src() {
		return room_src;
	}
	public void setRoom_src(String room_src) {
		this.room_src = room_src;
	}
	public String getVertical_src() {
		return vertical_src;
	}
	public void setVertical_src(String vertical_src) {
		this.vertical_src = vertical_src;
	}
	public String getIsVertical() {
		return isVertical;
	}
	public void setIsVertical(String isVertical) {
		this.isVertical = isVertical;
	}
	public String getCate_id() {
		return cate_id;
	}
	public void setCate_id(String cate_id) {
		this.cate_id = cate_id;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public String getOwner_uid() {
		return owner_uid;
	}
	public void setOwner_uid(String owner_uid) {
		this.owner_uid = owner_uid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getOnline() {
		return online;
	}
	public void setOnline(String online) {
		this.online = online;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getGame_url() {
		return game_url;
	}
	public void setGame_url(String game_url) {
		this.game_url = game_url;
	}
	public String getGame_name() {
		return game_name;
	}
	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getPlatform_id() {
		return platform_id;
	}
	public void setPlatform_id(int platform_id) {
		this.platform_id = platform_id;
	}
	
}
