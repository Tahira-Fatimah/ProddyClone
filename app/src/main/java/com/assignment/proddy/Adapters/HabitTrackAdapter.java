package com.assignment.proddy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.assignment.proddy.R;

public class HabitTrackAdapter extends BaseAdapter {

    private Context context;
    private String[] days;

    public HabitTrackAdapter(Context context, String[] days) {
        this.context = context;
        this.days = days;
    }

    @Override
    public int getCount() {
        return days.length;
    }

    @Override
    public Object getItem(int position) {
        return days[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.habit_track_item, null);
        }
        TextView textView = convertView.findViewById(R.id.day);
        textView.setText(days[position]);

        return convertView;
    }
}
