package com.ice.cook.activity;

import android.app.Application;

import com.baidu.apistore.sdk.ApiStoreSDK;
import com.ice.cook.util.Constants;

public class InitApplication extends Application implements Constants{

	@Override
	public void onCreate() {
		super.onCreate();
		
		//初始化ApiStoreSDK
		ApiStoreSDK.init(this, ConstantKey);

	}

}
