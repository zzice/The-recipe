package com.ice.cook.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.ice.cook.R;
import com.ice.cook.activity.IdListActivity;
import com.ice.cook.activity.RecipeListActivity;
import com.ice.cook.adapter.MainPagerAdapter;
import com.ice.cook.util.Constants;

public class DiscoverFragment extends Fragment implements Constants,
OnClickListener, OnPageChangeListener {
	private ViewPager mainPager;
	private EditText editText;
	private ImageView serchButton;
	private ScrollView scrollView;
	private LinearLayout layout_meiRong;
	private LinearLayout layout_jianFei;
	private LinearLayout layout_baoJian;
	private LinearLayout layout_renQun;
	private LinearLayout layout_shiJie;
	private LinearLayout layout_canShi;
	private LinearLayout layout_qiGuan;
	private LinearLayout layout_tiaoYang;
	private boolean isRunning = true;
	private MainPagerAdapter pagerAdapter;
	private int currentIndex;
	private ImageView[] dots;
	private LinearLayout dotLayout;
	private View view;
	private int id;
	private List<LinearLayout> layouts;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			mainPager.setCurrentItem(mainPager.getCurrentItem() + 1);
			if (isRunning) {
				handler.sendEmptyMessageDelayed(0, 3000);
			}
		}
	};
	private String idTitle;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_discover, container, false);
		setViews();
		setLayoutLists();
		scrollView.smoothScrollTo(0, 20);
		setListeners();
		handler.sendEmptyMessageDelayed(0, 3000);
		pagerAdapter = new MainPagerAdapter(getActivity(), mainPagers);
		mainPager.setAdapter(pagerAdapter);
		dots=new ImageView[guideSteps.length];
		for(int i=0;i<guideSteps.length;i++){
			dots[i]=(ImageView) dotLayout.getChildAt(i);
			dots[i].setEnabled(true);
		}
		currentIndex=0;
		dots[currentIndex].setEnabled(false);
		return view;
	}


	private void setViews() {
		mainPager = (ViewPager) view.findViewById(R.id.mainPager);
		editText = (EditText) view.findViewById(R.id.editText1);
		serchButton = (ImageView) view.findViewById(R.id.searchBtn);
		scrollView = (ScrollView) view.findViewById(R.id.scrollView1);
		layout_meiRong = (LinearLayout) view.findViewById(R.id.id_meiRong);
		layout_jianFei = (LinearLayout) view.findViewById(R.id.id_jianFei);
		layout_baoJian = (LinearLayout) view.findViewById(R.id.id_baoJian);
		layout_renQun = (LinearLayout) view.findViewById(R.id.id_renQun);
		layout_shiJie = (LinearLayout) view.findViewById(R.id.id_shiJie);
		layout_canShi = (LinearLayout) view.findViewById(R.id.id_canShi);
		layout_qiGuan = (LinearLayout) view.findViewById(R.id.id_qiGuan);
		layout_tiaoYang = (LinearLayout) view.findViewById(R.id.id_tiaoYang);
		dotLayout=(LinearLayout) view.findViewById(R.id.dotLayout);
	}

	private void setListeners() {
		mainPager.setOnPageChangeListener(this);
		serchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String searchText = editText.getText().toString();
				if(TextUtils.isEmpty(searchText)){
					Toast.makeText(getActivity(), "未输入内容", Toast.LENGTH_LONG).show();
					return;
				}
				Log.i("info", "搜索信息"+searchText);
				Intent intent = new Intent(getActivity(),RecipeListActivity.class);
				intent.putExtra("searchText", searchText);
				startActivity(intent);
			}
		});
		for (int i = 0; i < layouts.size(); i++) {
			layouts.get(i).setOnClickListener(this);
		}
	}

	private void setLayoutLists() {
		layouts = new ArrayList<LinearLayout>();
		layouts.add(layout_meiRong);
		layouts.add(layout_jianFei);
		layouts.add(layout_baoJian);
		layouts.add(layout_renQun);
		layouts.add(layout_shiJie);
		layouts.add(layout_canShi);
		layouts.add(layout_qiGuan);
		layouts.add(layout_tiaoYang);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		isRunning = false;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.id_meiRong:
			id = 1;
			idTitle = "美容";
			break;
		case R.id.id_jianFei:
			id = 10;
			idTitle = "减肥";
			break;
		case R.id.id_baoJian:
			id = 15;
			idTitle = "保健";
			break;
		case R.id.id_renQun:
			id = 52;
			idTitle = "人群";
			break;
		case R.id.id_shiJie:
			id = 62;
			idTitle = "时节";
			break;
		case R.id.id_qiGuan:
			id = 75;
			idTitle = "器官";
			break;
		case R.id.id_tiaoYang:
			id = 82;
			idTitle = "调养";
			break;
		case R.id.id_canShi:
			id = 68;
			idTitle = "餐时";
			break;
		}
		sendId(id, idTitle);

	}

	private void sendId(int id2, String idTitle2) {
		Intent intent = new Intent(getActivity(), IdListActivity.class);
		intent.putExtra("id", id2);
		intent.putExtra("idTitle", idTitle2);
		startActivity(intent);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int positon) {
		dots[positon%mainPagers.length].setEnabled(false);
		dots[currentIndex].setEnabled(true);
		currentIndex = positon%mainPagers.length;
	}


}
