package com.ice.cook.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ice.cook.R;
import com.ice.cook.entity.Recipes;
import com.ice.cook.entity.User;
import com.ice.cook.util.StringUtils;
import com.lidroid.xutils.exception.DbException;
/**
 * 详情页
 */
public class CollectRecipeActivity extends Activity {
	private String recipeName;
	private int recipeId;
	private List<Recipes> recipe;
	private TextView name;
	private TextView food;
	private TextView description;
	private TextView message;
	private ImageView imageView;
	private Button button;
	private User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collect_recipe);
		//透明状态栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		init();
		Intent intent=getIntent();
		recipeName=intent.getStringExtra("name");
		try {
			List<User> list = MainFragmentActivity.dbUtils.findAll(User.class);
			for (int i = 0; i < list.size(); i++) {
				String t1 = list.get(i).getName();
				user=list.get(i);
				if(t1.equals(recipeName)){
					name.setText(t1);
					food.setText(user.getFood());
					description.setText(user.getDescription());
					message.setText(StringUtils.replaceAll(user.getMessage()));
				}
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	private void init() {
		name=(TextView) findViewById(R.id.recipe_name);
		food=(TextView) findViewById(R.id.recipe_food1);
		description=(TextView) findViewById(R.id.recipe_description);
		message=(TextView) findViewById(R.id.recipe_message);
	}



}
