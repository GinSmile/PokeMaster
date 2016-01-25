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
public class AppInfoAdapter extends ArrayAdapter<AppInfo> {
    private int resourceId;
    private LayoutInflater mInflater;

    public AppInfoAdapter(Context context, int textViewResourceId,
                        List<AppInfo> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_all_app, null);
            holder.appImage = (ImageView) convertView.findViewById(R.id.app_image);
            holder.appName = (TextView) convertView.findViewById(R.id.app_name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }


        AppInfo app = getItem(position); // 获取当前项的Fruit实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView appImage = (ImageView) view.findViewById(R.id.app_image);
        TextView appName = (TextView) view.findViewById(R.id.app_name);
        holder.appImage.setImageDrawable(app.getAppIcon());
        holder.appName.setText(app.getAppLabel());

        return convertView;
    }

    public final class ViewHolder{
        public ImageView appImage;
        public TextView appName;
    }
}

