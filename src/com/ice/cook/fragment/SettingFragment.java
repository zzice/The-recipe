package com.ice.cook.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.ice.cook.R;
import com.ice.cook.activity.AboutUsActivity;
import com.ice.cook.activity.MainFragmentActivity;
import com.ice.cook.activity.SubmitActivity;
import com.ice.cook.entity.User;
import com.lidroid.xutils.exception.DbException;

public class SettingFragment extends Fragment implements OnClickListener {
	private View view;
	private LinearLayout cleanLayout;
	private LinearLayout shareLayout;
	private LinearLayout updateVersion;
	private LinearLayout aboutUs;
	private LinearLayout takeInfo;
	public SettingFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.fragment_setting, container, false);
		setViews();
		setListeners();
		return view;
	}

	private void setListeners() {
		shareLayout.setOnClickListener(this);
		cleanLayout.setOnClickListener(this);
		updateVersion.setOnClickListener(this);
		aboutUs.setOnClickListener(this);
		takeInfo.setOnClickListener(this);
	}

	private void setViews() {
		cleanLayout=(LinearLayout) view.findViewById(R.id.clean);
		shareLayout=(LinearLayout) view.findViewById(R.id.shareLayout);
		updateVersion=(LinearLayout) view.findViewById(R.id.updateVersion);
		aboutUs=(LinearLayout) view.findViewById(R.id.about_us);
		takeInfo=(LinearLayout) view.findViewById(R.id.take_info);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.clean:
			try {
				MainFragmentActivity.dbUtils.deleteAll(User.class);
				Toast.makeText(getActivity(), "清除成功", Toast.LENGTH_SHORT).show();
			} catch (DbException e) {
				e.printStackTrace();
			}
			break;
		case R.id.updateVersion:
			Toast.makeText(getActivity(), "版本检测中...", Toast.LENGTH_SHORT).show();
			Toast.makeText(getActivity(), "尚无新版本", Toast.LENGTH_SHORT).show();
			break;
		case R.id.take_info:
			Intent SubmitIntent=new Intent(getActivity(),SubmitActivity.class);
			startActivity(SubmitIntent);
			break;
		case R.id.about_us:
			Intent AboutUsIntent=new Intent(getActivity(),AboutUsActivity.class);
			startActivity(AboutUsIntent);
			break;
		case R.id.shareLayout:
			showShare();
			break;

		}

	}


	private void showShare() {
		ShareSDK.initSDK(getActivity());
		OnekeyShare oks = new OnekeyShare();
		oks.disableSSOWhenAuthorize(); 

		oks.setTitleUrl("http://sharesdk.cn");
		oks.setText("菜谱小当家，这个应用值得下。");
		oks.setImagePath("/sdcard/test.jpg");
		oks.setUrl("http://sharesdk.cn");
		oks.setComment("很好");
		oks.setSite(getString(R.string.app_name));
		oks.setSiteUrl("http://sharesdk.cn");

		oks.show(getActivity());
	}
}
