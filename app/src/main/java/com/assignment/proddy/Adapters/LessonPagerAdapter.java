package com.assignment.proddy.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.proddy.Entity.Lesson;
import com.assignment.proddy.R;

import java.util.List;

// LessonPagerAdapter.java
public class LessonPagerAdapter extends RecyclerView.Adapter<LessonPagerAdapter.LessonViewHolder> {

    private List<Lesson> lessons;

    public LessonPagerAdapter(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson_card, parent, false);
        return new LessonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder holder, int position) {
        Lesson lesson = lessons.get(position);
        holder.bind(lesson);
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    static class LessonViewHolder extends RecyclerView.ViewHolder {
        ImageView cardImage;
        TextView cardTitle, cardSubtitle;

        LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.cardImage);
            cardTitle = itemView.findViewById(R.id.cardTitle);
            cardSubtitle = itemView.findViewById(R.id.cardSubtitle);
        }

        void bind(Lesson lesson) {
            cardImage.setImageResource(lesson.getImageResId());
            cardTitle.setText(lesson.getTitle());
            cardSubtitle.setText(lesson.getSubtitle());
        }
    }
}

