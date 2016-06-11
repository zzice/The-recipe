package com.ice.cook.adapter;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class GuidePagerAdapter  extends PagerAdapter{
	private Context context;
	private int[] guideImgs;
	

	public GuidePagerAdapter(Context context, int[] guideImgs) {
		super();
		this.context = context;
		this.guideImgs = guideImgs;
	}

	@Override
	public int getCount() {
		return guideImgs.length;
	}
	/**判定参数view与Object指向的是否是同一对象*/
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}
	/**初始化一个item,并将item添加到viewPager*/
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView imageView=new ImageView(context);
		imageView.setScaleType(ScaleType.FIT_XY);
		int imgId=guideImgs[position];
		imageView.setImageResource(imgId);
		container.addView(imageView);
		return imageView;
	}
	/**从ViewPager中移除item*/
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
}
