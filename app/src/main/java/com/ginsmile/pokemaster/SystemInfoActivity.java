package com.ginsmile.pokemaster;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.ginsmile.pokemaster.Util.SystemInfoAdapter;

import java.util.ArrayList;

public class SystemInfoActivity extends AppCompatActivity {

    private ArrayList<String[]> systemInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SystemInfoActivity.this.finish();
            }
        });


        systemInfoList = getSystemInfoList();
        SystemInfoAdapter adapter = new SystemInfoAdapter(SystemInfoActivity.this,
                R.layout.item_system_info, systemInfoList);

        ListView listView = (ListView)findViewById(R.id.systemInfoList);
        listView.setAdapter(adapter);
    }

    private ArrayList<String[]> getSystemInfoList(){
        ArrayList<String[]> myList = new ArrayList<String[]>();

        myList.add(new String[]{"主板：", Build.BOARD});
        myList.add(new String[]{"系统定制商：" , Build.BRAND});
        myList.add(new String[]{"唯一编号：" , Build.FINGERPRINT});
        myList.add(new String[]{"硬件名：" , Build.HARDWARE});
        myList.add(new String[]{"手机产品名：" , Build.PRODUCT});
        myList.add(new String[]{"源码控制版本号：" , Build.VERSION.INCREMENTAL});
        myList.add(new String[]{"硬件制造商：" , Build.MANUFACTURER});

        myList.add(new String[]{"OS名称：" , System.getProperty("os.name")});
        myList.add(new String[]{"JAVA版本：" , System.getProperty("java.version")});



        return myList;
    }
}
