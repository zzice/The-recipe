package com.ice.cook.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ice.cook.R;
import com.ice.cook.activity.CollectRecipeActivity;
import com.ice.cook.activity.MainFragmentActivity;
import com.ice.cook.entity.User;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.table.DbModel;
import com.lidroid.xutils.exception.DbException;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class CollectionFragment extends Fragment implements OnItemClickListener {
	private View view;
	private ListView collectLv;
	private TextView tv;
	public List<String> names = new ArrayList<String>();
	private List<DbModel> dbModel=new ArrayList<DbModel>();;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_collection, null);
		setViews();
		names.clear();
		try {
			dbModel = MainFragmentActivity.dbUtils.findDbModelAll(Selector.from(User.class).select("name"));
			if (dbModel!=null) {
				for (int i = 0; i < dbModel.size(); i++) {
					String t1 = dbModel.get(i).getString("name");
					names.add(t1);
				}
				tv.setVisibility(View.GONE);
				collectLv.setVisibility(View.VISIBLE);
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						getActivity(), android.R.layout.simple_list_item_1,
						names);
				collectLv.setAdapter(adapter);
				if(adapter.isEmpty()){
					collectLv.setVisibility(View.GONE);
					tv.setVisibility(View.VISIBLE);
				}
			} 


		} catch (DbException e) {
			e.printStackTrace();
		}
		collectLv.setOnItemClickListener(this);
		return view;
	}

	private void setViews() {
		tv = (TextView) view.findViewById(R.id.info);
		collectLv = (ListView) view.findViewById(R.id.listView1);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String name=names.get(position);
		Intent intent=new Intent(getActivity(),CollectRecipeActivity.class);
		intent.putExtra("name", name);
		startActivity(intent);
	}

}
