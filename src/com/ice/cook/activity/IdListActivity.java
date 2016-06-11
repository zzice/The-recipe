package com.ice.cook.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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

public class IdListActivity extends Activity implements OnItemClickListener {
	private ListView idlv;
	private TextView title;
	private List<Recipes>IdData;
	private int id;
	private String idTitle;
	private TextView loadText;
	private int recipeId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_id_list);
		//透明状态栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		setViews();
		setAdapter();
		idlv.setOnItemClickListener(this);
	}

	private void setAdapter() {
		Intent intent=getIntent();
		id=intent.getIntExtra("id", 0);
		idTitle=intent.getStringExtra("idTitle");
		title.setText(idTitle);
		IdData=new ArrayList<Recipes>();
		Log.i("info", "idSearch_id:"+id);
		try {
			ApiStoreSDK.execute("http://apis.baidu.com/tngou/cook/list?id="
					+ id, ApiStoreSDK.GET, null, new ApiCallBack() {
						private RecipeAdapter adapter;

						@Override
						public void onSuccess(int arg0, String arg1) {
							IdData = JsonPaser.JsonParse2(arg1);
							Log.i("info", "IdListActivity.list:"+IdData.size());
							adapter=new RecipeAdapter(IdData, IdListActivity.this);
							idlv.setAdapter(adapter);
							if(!adapter.isEmpty()){
								loadText.setVisibility(View.GONE);
								idlv.setVisibility(View.VISIBLE);
							}
						}
					});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setViews() {
		title=(TextView) findViewById(R.id.title);
		idlv=(ListView) findViewById(R.id.id_list);
		loadText=(TextView) findViewById(R.id.loading_text);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		finish();
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		recipeId = IdData.get(position).getId();
		Intent intent = new Intent(this, RecipeActivity.class);
		intent.putExtra("id", recipeId);
		intent.putExtra("name", IdData.get(position).getName());
		startActivity(intent);
	}
}
