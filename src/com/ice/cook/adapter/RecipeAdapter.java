package com.ice.cook.adapter;

import java.util.List;

import com.ice.cook.R;
import com.ice.cook.activity.MainFragmentActivity;
import com.ice.cook.entity.Recipes;
import com.ice.cook.util.Constants;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeAdapter extends BaseAdapter implements Constants{
	private List<Recipes> list;
	private Context context;
	
	
	public RecipeAdapter(List<Recipes> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if (convertView==null) {
			holder=new ViewHolder();
			convertView=View.inflate(context, R.layout.recipe_list_item, null);
			holder.message=new Message();
			holder.name=(TextView) convertView.findViewById(R.id.name_health);
			holder.description=(TextView) convertView.findViewById(R.id.description_health);
			holder.imgView=(ImageView) convertView.findViewById(R.id.img_recipe);
			convertView.setTag(holder);
		}
		holder=(ViewHolder) convertView.getTag();
		Recipes recipes=list.get(position);
		holder.name.setText(recipes.getName());
		holder.message.obj=recipes.getId();
		holder.message.what=ID_RECIPE;
		//RecipeActivity.handler.sendMessage(holder.message);
		holder.description.setText(recipes.getDescription());
		MainFragmentActivity.bitmapUtils=new BitmapUtils(context);
		MainFragmentActivity.bitmapUtils.display(holder.imgView, "http://tnfs.tngou.net/image"+recipes.getImg());
		return convertView;
	}
	class ViewHolder{
		TextView name;
		TextView description;
		ImageView imgView;
		Message message;
	}
}
