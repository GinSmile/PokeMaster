package com.ginsmile.pokemaster.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ginsmile.pokemaster.R;

import java.util.List;

/**
 * Created by xujin on 16/1/19.
 */
public class SystemInfoAdapter extends ArrayAdapter<String[]> {
    private int resourceId;
    private LayoutInflater mInflater;

    public SystemInfoAdapter(Context context, int textViewResourceId,
                             List<String[]> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_system_info, null);
            holder.key = (TextView) convertView.findViewById(R.id.key);
            holder.value = (TextView) convertView.findViewById(R.id.value);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }


        String[] myStr = getItem(position); // 获取当前项的实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView key = (TextView) view.findViewById(R.id.key);
        TextView value = (TextView) view.findViewById(R.id.value);

        holder.key.setText(myStr[0]);
        holder.value.setText(myStr[1]);

        return convertView;
    }

    public final class ViewHolder{
        public TextView key;
        public TextView value;
    }
}

