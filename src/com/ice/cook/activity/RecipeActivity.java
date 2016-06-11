package com.ice.cook.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.ice.cook.R;
import com.ice.cook.biz.JsonPaser;
import com.ice.cook.entity.Recipes;
import com.ice.cook.entity.User;
import com.ice.cook.util.Constants;
import com.ice.cook.util.StringUtils;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.exception.DbException;

public class RecipeActivity extends Activity implements Constants, OnClickListener {
	private String recipeName;
	private int recipeId;
	private List<Recipes> recipe;
	private TextView name;
	private TextView food;
	private TextView description;
	private ImageView collectiv;
	private ImageView backiv;
	private TextView message;
	private ImageView imageView;
	private Recipes recipes;
	private TextView loadtv;
	private ScrollView scrollView;
	//	public static 	DbUtils dbUtils;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe);
		//透明状态栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		setViews();
		getData();
		setListener();
	}
	private void getData() {
		recipe=new ArrayList<Recipes>();
		Intent intent=getIntent();
		recipeId=intent.getIntExtra("id", 0);
		ApiStoreSDK.execute("http://apis.baidu.com/tngou/cook/show?id="+recipeId, ApiStoreSDK.GET, null, new ApiCallBack(){
			@Override
			public void onSuccess(int arg0, String arg1) {
				super.onSuccess(arg0, arg1);
				Log.i("data", arg1);
				recipe=JsonPaser.JsonParse3(arg1);
				recipes=recipe.get(0);
				name.setText(recipes.getName());
				food.setText(recipes.getFood());
				description.setText(recipes.getDescription());
				message.setText(StringUtils.replaceAll(recipes.getMessage()));
				Handler handler=new Handler();
				handler.postDelayed(new Runnable() {

					@Override
					public void run() {

						loadtv.setVisibility(View.GONE);
						scrollView.setVisibility(View.VISIBLE);
					}
				}, 1500);
				BitmapUtils bitmapUtils=new BitmapUtils(RecipeActivity.this);
				bitmapUtils.display(imageView, "http://tnfs.tngou.net/image"+recipes.getImg());
				if(imageView.getDrawable()!=null){
					imageView.setVisibility(View.VISIBLE);
				}
			}
		});
	}
	private void setListener() {
		collectiv.setOnClickListener(this);
		backiv.setOnClickListener(this);
	}
	private void setViews() {
		name=(TextView) findViewById(R.id.recipe_name);
		food=(TextView) findViewById(R.id.recipe_food1);
		description=(TextView) findViewById(R.id.recipe_description);
		message=(TextView) findViewById(R.id.recipe_message);
		imageView=(ImageView) findViewById(R.id.recipe_imag);
		collectiv=(ImageView) findViewById(R.id.collect_iv);
		backiv=(ImageView) findViewById(R.id.backiv);
		loadtv=(TextView) findViewById(R.id.loading_text);
		scrollView=(ScrollView) findViewById(R.id.scrollView1);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.collect_iv:
			dbUtil();
			break;
		case R.id.backiv:
			finish();
			break;
		}
	}
	public void dbUtil(){
		try {
			User user=new User();
			user.setName(recipes.getName());
			user.setFood(recipes.getFood());
			user.setDescription(recipes.getDescription());
			user.setMessage(recipes.getMessage());
			MainFragmentActivity.dbUtils.saveBindingId(user);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

}
