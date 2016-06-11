package com.ice.cook.adapter;

import com.ice.cook.activity.RecipeActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MainPagerAdapter extends PagerAdapter {
	private Context context;
	private int[] mainPagers;
	private int imgId;

	public MainPagerAdapter(Context context, int[] mainPagers) {
		super();
		this.context = context;
		this.mainPagers = mainPagers;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView imageView = new ImageView(context);
		imageView.setScaleType(ScaleType.FIT_XY);
		imgId=position%mainPagers.length;
		imageView.setImageResource(mainPagers[imgId]);
		container.addView(imageView);
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(imgId==1){
					Intent intent=new Intent(context, RecipeActivity.class);
					intent.putExtra("id", 105142);
					context.startActivity(intent);
				}else if(imgId==2){
					Intent intent=new Intent(context, RecipeActivity.class);
					intent.putExtra("id", 103466);
					context.startActivity(intent);
				}else if(imgId==0){
					Intent intent=new Intent(context, RecipeActivity.class);
					intent.putExtra("id", 106404);
					context.startActivity(intent);
					
				}
				
			}
		});
		return imageView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
}
