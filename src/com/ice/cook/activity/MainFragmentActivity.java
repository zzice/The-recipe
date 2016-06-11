package com.ice.cook.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.ice.cook.R;
import com.ice.cook.fragment.CollectionFragment;
import com.ice.cook.fragment.DiscoverFragment;
import com.ice.cook.fragment.RecipeFragment;
import com.ice.cook.fragment.SettingFragment;
import com.ice.cook.util.Constants;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;

public class MainFragmentActivity extends FragmentActivity implements
OnCheckedChangeListener, Constants {
	private RadioGroup MenuBar;
	private DiscoverFragment discoverFragment;
	private RecipeFragment recipeFragment;
	private CollectionFragment collectionFragment;
	private SettingFragment settingFragment;
	private int currentFragmentIndex = 0;
	private int selectedButtonIndex = 0;
	private long exitTime = 0;
	private Fragment[] fragments = new Fragment[4];
	private Fragment showFragment;
	public static DbUtils dbUtils;
	public static BitmapUtils bitmapUtils;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//透明状态栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		setViews();
		setListeners();
		initFragments();
		setFragments();
	}

	private void setViews() {
		MenuBar = (RadioGroup) findViewById(R.id.MenuBar);
		dbUtils=DbUtils.create(this);
		bitmapUtils=new BitmapUtils(this);
	}

	private void setListeners() {
		MenuBar.setOnCheckedChangeListener(this);
	}

	private void initFragments() {
		discoverFragment = new DiscoverFragment();
		recipeFragment = new RecipeFragment();
		collectionFragment = new CollectionFragment();
		settingFragment = new SettingFragment();
		fragments[0] = discoverFragment;
		fragments[1] = recipeFragment;
		fragments[2] = collectionFragment;
		fragments[3] = settingFragment;
	}

	private void setFragments() {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.fragmentContainer, discoverFragment);
		transaction.show(discoverFragment);
		transaction.commit();
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.discoverButton:
			selectedButtonIndex = 0;
			break;
		case R.id.recipeButton:
			selectedButtonIndex = 1;
			break;
		case R.id.collectionButton:
			selectedButtonIndex = 2;
			break;
		case R.id.settingsButton:
			selectedButtonIndex = 3;
			break;
		}
		selectFragment(selectedButtonIndex);

	}

	public  void selectFragment(int selectedButtonIndex) {
		if (selectedButtonIndex != currentFragmentIndex) {
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.hide(fragments[currentFragmentIndex]);
			showFragment = fragments[selectedButtonIndex];

			if (!showFragment.isAdded()) {
				transaction.replace(R.id.fragmentContainer, showFragment);
			}
			transaction.show(showFragment);
			transaction.commit();
			currentFragmentIndex = selectedButtonIndex;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		dbUtils.close();
		Log.i("info", "dbUtils.close()");
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(MainFragmentActivity.this, "再按一次退出程序",Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
