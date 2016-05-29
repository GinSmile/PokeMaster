package com.ginsmile.pokemaster;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.ginsmile.pokemaster.Util.ProcessInfo;
import com.ginsmile.pokemaster.Util.RunningProcessAdapter;

import java.util.ArrayList;
import java.util.List;

public class RunningActivity extends AppCompatActivity {

    private List<ProcessInfo> processInfoList;
    private ActivityManager mActivityManager;
    private PackageManager mPackageManager;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RunningActivity.this.finish();
            }
        });

        pb = (ProgressBar)findViewById(R.id.pb);

        GetRunningProcessInfoTask gTask = new GetRunningProcessInfoTask();
        gTask.execute(100);
    }

    //获取正在运行进程的信息
    private List<ProcessInfo> getRunningProcessInfo(){
        mPackageManager = RunningActivity.this.getPackageManager();
        mActivityManager = (ActivityManager) RunningActivity.this.getSystemService(Context.ACTIVITY_SERVICE);

        List<ProcessInfo> processInfoList = new ArrayList<ProcessInfo>();
        List<ActivityManager.RunningAppProcessInfo> appProcessList =
                mActivityManager.getRunningAppProcesses();

        Log.v("TAG___>",appProcessList.size()+"");
        for(int i = 0; i < appProcessList.size(); i++){
            ActivityManager.RunningAppProcessInfo info = appProcessList.get(i);

            //去掉系统和电话
            if(info.processName.equals("system") || info.processName.equals("com.android.phone")){
                continue;
            }

            //获取pid，uid，processName
            int pid = info.pid;
            int uid = info.uid;
            String processName = info.processName;

            //获取icon
            Drawable icon = null;
            try {
                String pkgName = info.pkgList[0];
                ApplicationInfo appInfo =  mPackageManager.getApplicationInfo(pkgName, PackageManager.GET_META_DATA);
                icon = mPackageManager.getApplicationIcon(appInfo);

            }catch (PackageManager.NameNotFoundException e){
                e.printStackTrace();
            }

            //获取memorySize
            int[] memoryPid = new int[]{pid};
            Debug.MemoryInfo[] memoryInfo = mActivityManager.getProcessMemoryInfo(memoryPid);
            int memorySize = memoryInfo[0].getTotalPss();

            ProcessInfo processInfo = new ProcessInfo();
            processInfo.setPid(pid);
            processInfo.setUid(uid);
            processInfo.setMemorySize(memorySize);
            processInfo.setProcessName(processName);
            processInfo.setAppIcon(icon);

            processInfoList.add(processInfo);


        }

        return processInfoList;

    }

    class GetRunningProcessInfoTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            processInfoList = getRunningProcessInfo();
            return "执行完毕";
        }

        @Override
        protected void onPostExecute(String result) {
            //doInBackground返回时触发
            RunningProcessAdapter adapter = new RunningProcessAdapter(RunningActivity.this,
                    R.layout.item_process, processInfoList);

            ListView listView  = (ListView)findViewById(R.id.listViewRunningProcess);
            listView.setAdapter(adapter);
            pb.setVisibility(View.GONE);
            super.onPostExecute(result);
        }

    }
}
