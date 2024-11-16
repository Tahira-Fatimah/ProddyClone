package com.assignment.proddy.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.assignment.proddy.Models.HabitCategory;
import com.assignment.proddy.R;

import java.util.List;

public class HabitCategoryAdapter extends RecyclerView.Adapter<HabitCategoryAdapter.HabitViewHolder> {

    private List<HabitCategory> habitCategories;

    public HabitCategoryAdapter(List<HabitCategory> habitList) {
        this.habitCategories = habitList;
    }

    @Override
    public HabitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.habit_category_item, parent, false);
        return new HabitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HabitViewHolder holder, int position) {
        // Use modulo to make the position circular
        int circularPosition = position % habitCategories.size();
        HabitCategory habitCategory = habitCategories.get(circularPosition);

        holder.habitImage.setImageResource(habitCategory.getdrawable());
        holder.habitName.setText(habitCategory.getCategoryName());
    }

    @Override
    public int getItemCount() {
        // Return a very large number to create infinite scrolling
        return Integer.MAX_VALUE;
    }

    public static class HabitViewHolder extends RecyclerView.ViewHolder {
        ImageView habitImage;
        TextView habitName;

        public HabitViewHolder(View itemView) {
            super(itemView);
            habitImage = itemView.findViewById(R.id.categoryImage);
            habitName = itemView.findViewById(R.id.categoryName);
        }
    }
}
