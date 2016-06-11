package com.ice.cook.activity.base;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.ice.cook.R;

public class BaseActivity extends Activity{
	private TextView returnText;
	private TextView titleText;
	public void setTitle(String title){
		titleText=(TextView) findViewById(R.id.title_text);
		titleText.setText(title);
	}
	public void setBack(){
		returnText=(TextView) findViewById(R.id.return_text);
		returnText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
}
