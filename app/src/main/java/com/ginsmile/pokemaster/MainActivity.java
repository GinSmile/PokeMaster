package com.ginsmile.pokemaster;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import me.itangqi.waveloadingview.WaveLoadingView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置已用内存占比
        WaveLoadingView mWaveView = (WaveLoadingView)findViewById(R.id.waveLoadingView);
        ActivityManager mActivityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        mActivityManager.getMemoryInfo(info);
        double availMem = (double)info.availMem/1024/1024/1024;
        double totalMem = (double)info.totalMem/1024/1024/1024;
        double usedMem = totalMem - availMem;
        int memPer = (int)(usedMem/totalMem * 100);

        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");//取小数点后后两位
        mWaveView.setCenterTitle(memPer + "%");
        mWaveView.setBottomTitle(df.format(usedMem) + "/" + df.format(totalMem) + "GB");
        mWaveView.setProgressValue(100 - memPer);
        mWaveView.setBorderWidth(3);
        //mWaveView.setBackground();

        //设置存储
        WaveLoadingView mWaveView2 = (WaveLoadingView)findViewById(R.id.waveLoadingView2);
        mWaveView2.setCenterTitle(10 + "%");
        mWaveView2.setProgressValue(100 - 10);
        mWaveView2.setBorderWidth(3);


        //设置菜单
        GridView gridview = (GridView) findViewById(R.id.gridview);
        //生成动态数组，并且转入数据
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();

        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", R.drawable.ic_folder_open_black_24dp);//添加图像资源的ID
            map.put("ItemText", "所有应用");//按序号做ItemText
            lstImageItem.add(map);

            HashMap<String, Object> map2 = new HashMap<String, Object>();
            map2.put("ItemImage", R.drawable.ic_folder_open_black_24dp);//添加图像资源的ID
            map2.put("ItemText", "正在运行");//按序号做ItemText
            lstImageItem.add(map2);

            HashMap<String, Object> map3 = new HashMap<String, Object>();
            map3.put("ItemImage", R.drawable.ic_folder_open_black_24dp);//添加图像资源的ID
            map3.put("ItemText", "文件管理");//按序号做ItemText
            lstImageItem.add(map3);

            HashMap<String, Object> map4 = new HashMap<String, Object>();
            map4.put("ItemImage", R.drawable.ic_folder_open_black_24dp);//添加图像资源的ID
            map4.put("ItemText", "系统信息");
            lstImageItem.add(map4);
        }

        SimpleAdapter saImageItems = new SimpleAdapter(this,
                lstImageItem,//数据来源
                R.layout.item_menu,
                //动态数组与ImageItem对应的子项
                new String[] {"ItemImage","ItemText"},
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[] {R.id.ItemImage,R.id.ItemText});
        //添加并且显示
        gridview.setAdapter(saImageItems);
        //添加消息处理
        gridview.setOnItemClickListener(new ItemClickListener());
    }

    //当AdapterView被单击(触摸屏或者键盘)，则返回的Item单击事件
    class  ItemClickListener implements AdapterView.OnItemClickListener
    {
        public void onItemClick(AdapterView<?> arg0,//The AdapterView where the click happened
                                View arg1,//The view within the AdapterView that was clicked
                                int arg2,//The position of the view in the adapter
                                long arg3//The row id of the item that was clicked
        ) {
            HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(arg2);
            Toast.makeText(MainActivity.this,(String)item.get("ItemText"),Toast.LENGTH_SHORT).show();

            switch (arg2){
                case 0:
                    Intent intent1 = new Intent(MainActivity.this, AllAppActivity.class);
                    startActivity(intent1);
                    break;
                case 1:
                    Intent intent2 = new Intent(MainActivity.this, RunningActivity.class);
                    startActivity(intent2);
                    break;
                case 2:
                    Intent intent3 = new Intent(MainActivity.this, FileActivity.class);
                    startActivity(intent3);
                    break;
                case 3:
                    Intent intent4 = new Intent(MainActivity.this, SystemInfoActivity.class);
                    startActivity(intent4);
                    break;
            }
        }

    }

}
