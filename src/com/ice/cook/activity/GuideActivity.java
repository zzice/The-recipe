package com.ice.cook.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ice.cook.R;
import com.ice.cook.adapter.GuidePagerAdapter;
import com.ice.cook.util.Constants;

public class GuideActivity extends Activity implements Constants,
OnPageChangeListener, OnClickListener {
	private Button enterButton;
	private LinearLayout dotLayout;
	private ViewPager viewPager;
	private GuidePagerAdapter adapter;

	private int currentIndex;
	private ImageView[] dots;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		//透明状态栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		enterButton = (Button) findViewById(R.id.enterButton);
		viewPager = (ViewPager) findViewById(R.id.guidePager);
		dotLayout=(LinearLayout) findViewById(R.id.dotLayout);
		enterButton.setOnClickListener(this);
		adapter = new GuidePagerAdapter(this, guideSteps);
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(this);
		dots=new ImageView[guideSteps.length];
		for(int i=0;i<guideSteps.length;i++){
			dots[i]=(ImageView) dotLayout.getChildAt(i);
			dots[i].setEnabled(true);
		}
		currentIndex=0;
		dots[currentIndex].setEnabled(false);
	}

	@Override
	public void onPageSelected(int positon) {
		dots[positon].setEnabled(false);
		dots[currentIndex].setEnabled(true);
		currentIndex = positon;
		if (positon == guideSteps.length - 1) {
			enterButton.setVisibility(View.VISIBLE);
		} else {
			enterButton.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(GuideActivity.this,
				MainFragmentActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

}
