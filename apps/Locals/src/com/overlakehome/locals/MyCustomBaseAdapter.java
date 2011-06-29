package com.overlakehome.locals;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyCustomBaseAdapter extends BaseAdapter {
    private static ArrayList<SearchResults> searchArrayList;
    private LayoutInflater mInflater;

    public MyCustomBaseAdapter(Context context, ArrayList<SearchResults> results) {
        searchArrayList = results;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return searchArrayList.size();
    }

    public Object getItem(int position) {
        return searchArrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.deals_row_view, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.name);
            holder.txtAdress = (TextView) convertView.findViewById(R.id.address);
            holder.txtDistance = (TextView) convertView.findViewById(R.id.distance);
            holder.Icon = (ImageView) convertView.findViewById(R.id.categoryicon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtName.setText(searchArrayList.get(position).getName());
        holder.txtAdress.setText(searchArrayList.get(position).getAddress());
        holder.txtDistance.setText(searchArrayList.get(position).getDistance());
        holder.Icon.setImageResource((Integer)searchArrayList.get(position).getCategoryIcon());

        return convertView;
    }

    static class ViewHolder {
        TextView txtName;
        TextView txtAdress;
        TextView txtDistance;
        ImageView Icon;
    }
}
