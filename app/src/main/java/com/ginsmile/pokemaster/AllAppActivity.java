/*
* 所有应用界面
* */
package com.ginsmile.pokemaster;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.ginsmile.pokemaster.Util.AppInfo;
import com.ginsmile.pokemaster.Util.SimpleFragmentPagerAdapter;

public class AllAppActivity extends AppCompatActivity {
    private SimpleFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_activity_all_apps);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllAppActivity.this.finish();
            }
        });

        pb = (ProgressBar)findViewById(R.id.pb_all_app);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        tabLayout = (TabLayout)findViewById(R.id.sliding_tabs);

        GetAllAppTask getAllAppTask = new GetAllAppTask();
        getAllAppTask.execute(1000);

    }


    class GetAllAppTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            //第二个执行方法,onPreExecute()执行完后执行
            //一定不要有UI操作，会阻塞UI！！！
            AppInfo.list_system = AppInfo.getAppInfo(2, AllAppActivity.this.getPackageManager());
            AppInfo.list_third = AppInfo.getAppInfo(1, AllAppActivity.this.getPackageManager());

            return "执行完毕";
        }

        @Override
        protected void onPostExecute(String result) {
            //doInBackground返回时触发
            //这里的result就是上面doInBackground执行后的返回值，所以这里是"执行完毕"
            pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), AllAppActivity.this);
            viewPager.setAdapter(pagerAdapter);
            tabLayout.setupWithViewPager(viewPager);
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
            pb.setVisibility(View.GONE);
            super.onPostExecute(result);
        }

    }




}




