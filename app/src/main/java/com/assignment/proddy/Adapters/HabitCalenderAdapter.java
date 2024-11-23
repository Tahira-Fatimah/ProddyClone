package com.assignment.proddy.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.assignment.proddy.R;
import com.assignment.proddy.Utils.DrawableUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HabitCalenderAdapter extends BaseAdapter {

    private Context context;
    private List<Map.Entry<String, String>> calendarItems;

    public HabitCalenderAdapter(Context context, List<Map.Entry<String, String>> calendarItems) {
        this.context = context;
        this.calendarItems = calendarItems;
    }

    public void addItem(Map.Entry<String, String> item){
        calendarItems.add(item);
        notifyDataSetChanged();
    }

    public void empty(){
        calendarItems.clear();
        notifyDataSetChanged();
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


        calendarTextView.setText(calendarItems.get(position).getKey());

        String status = calendarItems.get(position).getValue();
        int color = R.color.grid_item_calendar;
        if(Objects.equals(status, "NONE")){
            color = R.color.grid_item_calendar;
        } else if (Objects.equals(status, "SOME")) {
            color = R.color.today_item_calendar;
        } else if (Objects.equals(status, "ALL")){
            color = R.color.completed_habits_scale_1;
        }
        calendarTextView.setBackgroundTintList(ColorStateList.valueOf(
                context.getResources().getColor(color)
        ));

        return convertView;
    }
}

