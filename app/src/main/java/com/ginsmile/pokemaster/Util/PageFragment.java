package com.ginsmile.pokemaster.Util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ginsmile.pokemaster.R;

import java.util.List;

/**
 * Created by xujin on 16/1/19.
 */
public class PageFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private List<AppInfo> my_apps;
    private static Context context;

    private PackageManager pm = null;

    public static PageFragment newInstance(int page, Context con) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        context = con;
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);

        if(mPage == 1){
            my_apps = AppInfo.list_third;

        }else{
            my_apps = AppInfo.list_system;
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_all_app, container, false);

        ListView listView = (ListView)view.findViewById(R.id.listView);
        AppInfoAdapter adapter = new AppInfoAdapter(getContext(),
                R.layout.item_all_app, my_apps);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
            }
        });

        return view;
    }






}