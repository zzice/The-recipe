package com.ice.cook.activity;

import android.os.Bundle;

import com.ice.cook.R;
import com.ice.cook.activity.base.BaseActivity;

public class AboutUsActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_us);
		setTitle("关于我们");
		setBack();
	}


}
