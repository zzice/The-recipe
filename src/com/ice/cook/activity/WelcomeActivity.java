package com.ice.cook.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.ice.cook.R;
/**
 * 欢迎页
 * @author z_ice
 *
 */
public class WelcomeActivity extends Activity {
	private final int delayMillis=3000;
	private SharedPreferences preferences;
	private Editor editor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		//透明状态栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		preferences=getSharedPreferences("launch", Context.MODE_PRIVATE);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if(preferences.getBoolean("isFirst", true)){
					editor=preferences.edit();
					editor.putBoolean("isFirst", false);
					editor.commit();
					Intent intent =new Intent(WelcomeActivity.this,GuideActivity.class);
					startActivity(intent);
				}else{
					Intent intent=new Intent(WelcomeActivity.this, MainFragmentActivity.class);
					startActivity(intent);
				}
				finish();
			}
		}, delayMillis);
	}

}
