package com.active.sunnypoint;

/**
 * Created by cvnhan on 09-Apr-15.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AlphabetListAdapter extends BaseAdapter {

    private ArrayList<Info> arr;

    public AlphabetListAdapter(ArrayList<Info> _arr) {
        this.arr = _arr;
        generateOrien();
    }
    private void generateOrien(){
        int tmp=-1;
        for(int i=0; i<arr.size(); i++){
            if(!arr.get(i).isTitle){
                arr.get(i).orien=tmp;
                tmp*=-1;
            }
        }
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Info getItem(int position) {
        return arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_item, parent, false);
            holder = new ViewHolder();
            holder.InfoView=(LinearLayout) view.findViewById(R.id.vInfo);
            holder.text1 = (TextView) view.findViewById(R.id.textView1);
            holder.text2 = (TextView) view.findViewById(R.id.textView2);
            holder.title = (TextView) view.findViewById(R.id.title);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Info item = getItem(position);
        if (!item.isTitle) {
            holder.title.setVisibility(View.GONE);
            holder.InfoView.setVisibility(View.VISIBLE);
            if (item.orien==-1) {
                holder.text1.setText(item.info);
                holder.text2.setText("");
            } else if(item.orien==1) {
                holder.text1.setText("");
                holder.text2.setText(item.info);
            }
        } else {
            holder.InfoView.setVisibility(View.GONE);
            holder.title.setVisibility(View.VISIBLE);
            holder.title.setText(item.info);
        }

        Animation anim= AnimationUtils.loadAnimation(parent.getContext(),R.anim.fade_anim);

        view.startAnimation(anim);
        return view;
    }

    static class ViewHolder {
        LinearLayout InfoView;
        TextView text1;
        TextView text2;
        TextView title;
    }
}

class Info {
    public String info;
    public int orien=0;
    public boolean isTitle;

    public Info(String _info) {
        this.info = _info;
        isTitle = false;
    }

    public Info(String _info, boolean _isTitle) {
        this(_info);
        this.isTitle = _isTitle;
    }
}
