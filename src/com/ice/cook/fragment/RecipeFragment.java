package com.ice.cook.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.ice.cook.R;
import com.ice.cook.activity.MainFragmentActivity;
import com.ice.cook.activity.RecipeActivity;
import com.ice.cook.adapter.RecipeAdapter;
import com.ice.cook.biz.JsonPaser;
import com.ice.cook.customs.CustomProgressDialog;
import com.ice.cook.entity.Recipes;
import com.ice.cook.util.Constants;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;

@SuppressLint("HandlerLeak") public class RecipeFragment extends Fragment implements Constants, OnItemClickListener {
	private View view;
	private ListView hotList;
	public static List<Recipes>list=new ArrayList<Recipes>();
	private int recipeId;
	private RecipeAdapter adapter;
	private TextView loadText;
	private CustomProgressDialog dialog;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.fragment_recipe, null);
		setViews();
		int[] id = { 1, 10, 15, 52, 62, 68, 75, 82, 98, 112, 132, 147, 161,
				166, 182, 188, 192, 197, 202, 205, 212, 218, 227 };
		int randomIndex=new Random().nextInt(22);
		dialog.setMessage("正在加载中...");
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
		ApiStoreSDK.execute("http://apis.baidu.com/tngou/cook/list?rows=10&id="+ id[randomIndex], ApiStoreSDK.GET, null, new ApiCallBack() {
			@Override
			public void onSuccess(int arg0, String arg1) {
				list = JsonPaser.JsonParse2(arg1);
				setAdapter();
				dialog.dismiss();
			}
		});
		setListeners();
		return view;
	}

	private void setListeners() {
		hotList.setOnItemClickListener(this);
	}
	private void setViews() {
		hotList=(ListView) view.findViewById(R.id.hot_list);
		loadText=(TextView) view.findViewById(R.id.loading_text);
		dialog = CustomProgressDialog.createDialog(getActivity());
	}
	private void setAdapter() {
		
		adapter=new RecipeAdapter(list, getActivity());
		hotList.setAdapter(adapter);
		hotList.setOnScrollListener(new PauseOnScrollListener(MainFragmentActivity.bitmapUtils, false, true));
		if(!adapter.isEmpty()){
			hotList.setVisibility(View.VISIBLE);
		}
}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
    	recipeId=list.get(position).getId();
		Intent intent=new Intent(getActivity(),RecipeActivity.class);
		intent.putExtra("id", recipeId);
		intent.putExtra("name", list.get(position).getName());
		startActivity(intent);
	}
	


	
	
}
