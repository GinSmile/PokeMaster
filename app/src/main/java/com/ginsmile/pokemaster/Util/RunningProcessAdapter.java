package com.ginsmile.pokemaster.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ginsmile.pokemaster.R;

import java.util.List;

/**
 * Created by xujin on 16/1/19.
 */
public class RunningProcessAdapter extends ArrayAdapter<ProcessInfo> {
    private int resourceId;
    private LayoutInflater mInflater;

    public RunningProcessAdapter(Context context, int textViewResourceId,
                          List<ProcessInfo> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_process, null);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.processName = (TextView) convertView.findViewById(R.id.processName);
            holder.memorySize = (TextView) convertView.findViewById(R.id.memorySize);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }


        ProcessInfo processInfo = getItem(position); // 获取当前项的实例
        holder.icon.setImageDrawable(processInfo.getAppIcon());

        //保留两位小数
        double memSizeStr = processInfo.getMemorySize()/1024.0;
        memSizeStr = (int)(memSizeStr * 100);
        memSizeStr = memSizeStr/100;

        holder.memorySize.setText(memSizeStr + "MB");
        holder.processName.setText(processInfo.getProcessName());

        return convertView;
    }

    public final class ViewHolder{
        public ImageView icon;
        public TextView processName;
        public TextView memorySize;
    }
}

