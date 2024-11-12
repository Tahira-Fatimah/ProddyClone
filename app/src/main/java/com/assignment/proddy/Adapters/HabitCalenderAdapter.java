package com.assignment.proddy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.assignment.proddy.R;

import java.util.List;

public class HabitCalenderAdapter extends BaseAdapter {

    private Context context;
    private List<String> calendarItems;

    public HabitCalenderAdapter(Context context, List<String> calendarItems) {
        this.context = context;
        this.calendarItems = calendarItems;
    }

    @Override
    public int getCount() {
        return calendarItems.size();
    }

    @Override
    public Object getItem(int position) {
        return calendarItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.calendar_grid_item, parent, false);
        }

        TextView calendarTextView = convertView.findViewById(R.id.calenderItem);
        calendarTextView.setText(calendarItems.get(position));

        return convertView;
    }
}

