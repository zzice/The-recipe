package com.ice.cook.activity;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.ice.cook.R;
import com.ice.cook.adapter.RecipeAdapter;
import com.ice.cook.biz.JsonPaser;
import com.ice.cook.entity.Recipes;
import com.ice.cook.util.Constants;

public class RecipeListActivity extends Activity implements OnItemClickListener ,Constants{
	private ListView recipeListview;
	private int recipeId;
	private String searchText;
	public static List<Recipes> recipe_list;
	private TextView loadtv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_list);
		//透明状态栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		recipeListview = (ListView) findViewById(R.id.listView1);
		loadtv=(TextView) findViewById(R.id.loading_text);
		Intent intent=getIntent();
		searchText=intent.getStringExtra("searchText");
		recipe_list=new ArrayList<Recipes>();
		try {
			searchText = URLEncoder.encode(searchText, "UTF-8");
			ApiStoreSDK.execute(HttpUrl + "?name=" + searchText,
					ApiStoreSDK.GET, null, new ApiCallBack() {
				@Override
				public void onSuccess(int arg0, String arg1) {
					recipe_list = JsonPaser
							.JsonParse2(arg1);
					Log.i("info", "RecipeListActivity.list:"+recipe_list.size());
					RecipeAdapter adapter=new RecipeAdapter(recipe_list, RecipeListActivity.this);
					recipeListview.setAdapter(adapter);
					if(!adapter.isEmpty()){
						loadtv.setVisibility(View.GONE);
						recipeListview.setVisibility(View.VISIBLE);
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		recipeListview.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		recipeId = recipe_list.get(position).getId();
		Intent intent = new Intent(this, RecipeActivity.class);
		intent.putExtra("id", recipeId);
		intent.putExtra("name", recipe_list.get(position).getName());
		startActivity(intent);
	}

}
