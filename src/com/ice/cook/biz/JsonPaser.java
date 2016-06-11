package com.ice.cook.biz;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ice.cook.entity.Recipes;
import com.ice.cook.util.Constants;


public class JsonPaser implements Constants {



	public static List<Recipes> JsonParse2(String string){
		List<Recipes>list=new ArrayList<Recipes>();
		JSONObject object=JSON.parseObject(string);
		JSONArray array=object.getJSONArray("tngou");
		list=JSON.parseArray(array.toString(), Recipes.class);
		return list;
	}
	public static List<Recipes> JsonParse3(String string){
		ArrayList<Recipes> list =new ArrayList<Recipes>();
		try {

			Recipes recipes=JSON.parseObject(string, Recipes.class);
			list.add(recipes);
			Log.i("info", "list:"+list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
