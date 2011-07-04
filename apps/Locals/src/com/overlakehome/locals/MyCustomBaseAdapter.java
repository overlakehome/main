package com.overlakehome.locals;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyCustomBaseAdapter extends BaseAdapter {
    private LayoutInflater mInflater;

    public MyCustomBaseAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return NearBys.getInstance().getPlaces().length;
    }

    public Object getItem(int position) {
        return NearBys.getInstance().getPlaces()[position];
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

        
        holder.txtName.setText(NearBys.getInstance().getPlaces()[0].getName());
        holder.txtAdress.setText(NearBys.getInstance().getPlaces()[0].getName());
        holder.txtDistance.setText(NearBys.getInstance().getPlaces()[0].getAddress());
        NearBys.toClazz(NearBys.getInstance().getPlaces()[0]).getDrawableId();
        holder.Icon.setImageResource(NearBys.toClazz(NearBys.getInstance().getPlaces()[0]).getDrawableId()); // arts;
     

        return convertView;
    }

    static class ViewHolder {
        TextView txtName;
        TextView txtAdress;
        TextView txtDistance;
        ImageView Icon;
    }
}
