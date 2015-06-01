package com.game.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.game.Model.Gamer;
import com.game.R;

import java.util.ArrayList;

public class ChartsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Gamer> mList;

    public ChartsAdapter(Context context, ArrayList<Gamer> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.charts_item, null);
            viewHolder.tv_index = (TextView) convertView.findViewById(R.id.gamer_index);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.gamer_name);
            viewHolder.tv_score = (TextView) convertView.findViewById(R.id.gamer_score);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_index.setText(String.valueOf(position + 1));

        viewHolder.tv_name.setText(mList.get(position).getName());

        viewHolder.tv_score.setText(mList.get(position).getScore() + "");

        return convertView;
    }

    static class ViewHolder {
        public TextView tv_index;
        public TextView tv_name;
        public TextView tv_score;
    }
}
