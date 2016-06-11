package com.ice.cook.entity;

import java.io.Serializable;

public class Recipes implements Serializable{
	private int id;
	private String name;//鍚嶇О
	private String  food;//椋熺�?
	private String img;//鍥剧�?
	private String images;//鍥剧�?, 
	private String description;//鎻忚�?
	private String keywords;//鍏抽敭�?��
	private String message;//璧勮鍐呭
	private int count ;//璁块棶娆℃暟
	private int fcount;//�?惰棌鏁�
	private int rcount;//璇勮璇绘暟
	public Recipes() {
		super();
	}
	public Recipes(int id, String name, String food, String img, String images,
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
