package com.ice.cook.entity;

import java.io.Serializable;

public class User implements Serializable{
	private int id;
	private String name;//名称
	private String  food;//食物
	private String img;//图片
	private String images;//图片, 
	private String description;//描述
	private String keywords;//关键�?
	private String message;//资讯内容
	private int count ;//访问次数
	private int fcount;//收藏�?
	private int rcount;//评论读数
	public User() {
		super();
	}
	public User(int id, String name, String food, String img, String images,
			String description, String keywords, String message, int count,
			int fcount, int rcount) {
		super();
		this.id = id;
		this.name = name;
		this.food = food;
		this.img = img;
		this.images = images;
		this.description = description;
		this.keywords = keywords;
		this.message = message;
		this.count = count;
		this.fcount = fcount;
		this.rcount = rcount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getFcount() {
		return fcount;
	}
	public void setFcount(int fcount) {
		this.fcount = fcount;
	}
	public int getRcount() {
		return rcount;
	}
	public void setRcount(int rcount) {
		this.rcount = rcount;
	}
	
	
}
