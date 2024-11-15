package com.assignment.proddy.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.assignment.proddy.R;


public class ReflectionFragment extends Fragment {

    public ReflectionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reflection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView moodDateView = view.findViewById(R.id.moodDate);
        TextView moodTextView = view.findViewById(R.id.moodText);
        ImageView moodEmojiView = view.findViewById(R.id.moodEmoji);
        TextView moodNumView = view.findViewById(R.id.moodNum);
        TextView thoughtsView = view.findViewById(R.id.thoughts);
        GridLayout feelingsGridView = view.findViewById(R.id.feelingsGrid);
        GridLayout activitiesGridView = view.findViewById(R.id.activitiesGrid);
        TextView avgMoodTextView = view.findViewById(R.id.avgMoodText);
        ImageView avgMoodEmojiView = view.findViewById(R.id.avgMoodEmoji);
        TextView avgMoodNumView = view.findViewById(R.id.avgMoodNum);


        String date = "Nov 10, 2024"; // get from db thru create reflection------------
        moodDateView.setText("Your mood on " + date);
        String selectedMood = "Good"; // get from db thru create reflection------------
        moodTextView.setText(selectedMood);
        moodEmojiView.setImageResource(R.drawable.mood1);
        String num = "4"; // get from db thru create reflection------------
        moodNumView.setText(num);
        String thoughts = "good thoughts only"; // get from db thru create reflection------------
        thoughtsView.setText(thoughts);

// FEELINGS

        // textview for 1 feeling
        TextView newTextView = new TextView(requireContext());
        newTextView.setText("Happy"); // get from db thru create reflection------------
        newTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.feeling_tag_positive_font)); // get from db thru create reflection------------
        newTextView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.feeling_tag_positive_bg)); // get from db thru create reflection------------

        // Set padding (convert dp to pixels)
        float density = getResources().getDisplayMetrics().density; // Get the screen density
        int paddingHorizontal = (int) (8 * density); // Convert 8dp to pixels
        int paddingVertical = (int) (3 * density);  // Convert 3dp to pixels
        newTextView.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical);

        // Set margin using LayoutParams
        ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins((int) (4 * density), (int) (4 * density), (int) (4 * density), (int) (4 * density)); // 4dp margins
        newTextView.setLayoutParams(layoutParams);
        feelingsGridView.addView(newTextView);


// ACTIVITIES

        // LinearLayout for 1 activity
        LinearLayout linearLayout = new LinearLayout(requireContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        GridLayout.LayoutParams layoutparams = new GridLayout.LayoutParams();
        layoutparams.width = (int) (35 * getResources().getDisplayMetrics().density); // Convert 35dp to px
        layoutparams.height = GridLayout.LayoutParams.WRAP_CONTENT;
        layoutparams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f); // column weight
        linearLayout.setLayoutParams(layoutparams);

        // first TextView for emoji
        TextView emojiTextView = new TextView(requireContext());
        emojiTextView.setText("💪"); // get from db thru create reflection------------
        emojiTextView.setTextSize(30); // 30sp
        emojiTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        emojiTextView.setPadding(0, 8, 0, 8); // Margin in dp to px

        // second TextView for text
        TextView studyingTextView = new TextView(requireContext());
        studyingTextView.setText("Studying"); // get from create reflection----------------
        studyingTextView.setTextSize(15); // 15sp
        studyingTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.reflection_date_font_purple));
        studyingTextView.setGravity(Gravity.CENTER_HORIZONTAL);

        // Add TextViews to the LinearLayout
        linearLayout.addView(emojiTextView);
        linearLayout.addView(studyingTextView);

        // Add LinearLayout to the GridLayout
        activitiesGridView.addView(linearLayout);

// YOUR WEEK

        // Create LinearLayout for each day reflection exists (1 linear layout for now)
        LinearLayout daylinearLayout = new LinearLayout(requireContext());
        daylinearLayout.setOrientation(LinearLayout.VERTICAL);
        daylinearLayout.setGravity(Gravity.BOTTOM);

        // Create the Spacer View
        View spacerView = new View(requireContext());
        LinearLayout.LayoutParams spacerParams = new LinearLayout.LayoutParams(
                0, 0 // 0dp width and height
        );
        spacerParams.weight = 1;
        spacerView.setLayoutParams(spacerParams);

        // Create TextView for mood number
        TextView moodNum = new TextView(requireContext());
        moodNum.setText("3");
        moodNum.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
        moodNum.setTypeface(null, Typeface.BOLD);
        moodNum.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16); // 16sp
        LinearLayout.LayoutParams moodNumParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        moodNumParams.gravity = Gravity.CENTER_HORIZONTAL;
        moodNum.setLayoutParams(moodNumParams);

        // Create the Bar View
        View bar = new View(requireContext());
        bar.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.rounded_background_1));
        bar.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.light_purple));
        LinearLayout.LayoutParams barParams = new LinearLayout.LayoutParams(
                (int) (12 * getResources().getDisplayMetrics().density), // 12dp in pixels
                (int) (80 * getResources().getDisplayMetrics().density)  // 80dp in pixels // calc height using mood num from db -------
        );
        barParams.gravity = Gravity.CENTER_HORIZONTAL;
        barParams.topMargin = (int) (4 * getResources().getDisplayMetrics().density); // 4dp top margin
        barParams.bottomMargin = (int) (20 * getResources().getDisplayMetrics().density); // 20dp bottom margin
        bar.setLayoutParams(barParams);

        // Create the Day TextView
        TextView text = new TextView(requireContext());
        text.setText("Sat"); // get from db ---------
        text.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        textParams.gravity = Gravity.CENTER_HORIZONTAL;
        text.setLayoutParams(textParams);

        // Add views to the LinearLayout
        daylinearLayout.addView(spacerView);
        daylinearLayout.addView(moodNum);
        daylinearLayout.addView(bar);
        daylinearLayout.addView(text);



// AVERAGE MOOD
        avgMoodTextView.setText("Normal");
        avgMoodEmojiView.setImageResource(R.drawable.mood1);
        avgMoodNumView.setText("3.0");

    }
}