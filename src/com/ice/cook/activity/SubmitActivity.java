package com.ice.cook.activity;

import android.os.Bundle;

import com.ice.cook.R;
import com.ice.cook.activity.base.BaseActivity;

public class SubmitActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit);
		setTitle("提交反馈");
		setBack();
	}

}
