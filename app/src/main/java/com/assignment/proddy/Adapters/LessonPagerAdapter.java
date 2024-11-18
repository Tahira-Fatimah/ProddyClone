package com.assignment.proddy.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.proddy.Activities.LessonPlay;
import com.assignment.proddy.Entity.Lesson;
import com.assignment.proddy.R;

import java.util.List;

// LessonPagerAdapter.java
public class LessonPagerAdapter extends RecyclerView.Adapter<LessonPagerAdapter.LessonViewHolder> {

    private List<Lesson> lessons;
    private Context context;

    public LessonPagerAdapter(List<Lesson> lessons, Context context) {
        this.lessons = lessons;
        this.context = context;
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
        holder.bind(lesson, context);
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    static class LessonViewHolder extends RecyclerView.ViewHolder {
        ImageView lessonImage;
        TextView lessonTitle, lessonSubtitle;
        LinearLayout lessonInfoContainer;
        CardView lessonCard;

        LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            lessonImage = itemView.findViewById(R.id.lessonImage);
            lessonTitle = itemView.findViewById(R.id.lessonTitle);
            lessonSubtitle = itemView.findViewById(R.id.lessonSubtitle);
            lessonInfoContainer = itemView.findViewById(R.id.lessonInfoContainer);
            lessonCard = itemView.findViewById(R.id.lessonCard);
        }

        void bind(Lesson lesson, Context context) {
            lessonImage.setImageResource(lesson.getImageResId());
            lessonTitle.setText(lesson.getId() + ". " + lesson.getTitle());
            lessonSubtitle.setText(lesson.getSubtitle());

            Lesson.Theme theme = lesson.getTheme();

            lessonInfoContainer.setBackgroundColor(theme.getTitleBgColour());
            lessonSubtitle.setTextColor(theme.getSubtitleTextColour());

            lessonCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, LessonPlay.class);
                    intent.putExtra("Lesson", lesson);
                    Activity activity = (Activity) context;
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                    activity,
                                    lessonImage, "lessonImageTransition"
                    );

                    context.startActivity(intent, options.toBundle());
                }
            });
        }
    }
}

