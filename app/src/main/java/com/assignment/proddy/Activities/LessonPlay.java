package com.assignment.proddy.Activities;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.assignment.proddy.Entity.Lesson;
import com.assignment.proddy.R;

public class LessonPlay extends AppCompatActivity {
    //back F690
    //next F699
    private MediaPlayer mediaPlayer;
    TextView lessonTitle, lessonSubtitle;
    ImageView lessonIcon, lessonPlayBackButton;
    TextView playButton;
    LinearLayout lessonPlayRootLayout;
    LinearLayout playbackControls;
    ImageView previousButton, nextButton;
    CardView lessonCard;
    SeekBar seekBar;
    Runnable updateSeekBar;
    private boolean isPlaying = false;
    Handler handler = new Handler();
    String play = "\uE9BB";
    String pause = "\uE9AF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_play);
        seekBar = findViewById(R.id.seekBar);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setEnterTransition(new Fade().setDuration(500));
//        }

        Lesson lesson = (Lesson) getIntent().getSerializableExtra("Lesson");
        initViews();
        setValues(lesson);
        definePlayButton();
        defineLessonPlayBackButton();
        defineNextButton();
        definePrevButton();

        ViewCompat.setTransitionName(lessonCard, "lessonImageTransition");
        mediaPlayer = MediaPlayer.create(this, lesson.getLessonAudio());
        seekBar.setMax(mediaPlayer.getDuration());

        defineHandlerForRunnable();

        mediaPlayer.start();
        isPlaying = true;
        playButton.setText(pause);

        defineSeekBar();
        mediaPlayer.setOnCompletionListener(mp -> {
            handler.removeCallbacks(updateSeekBar);
            seekBar.setProgress(0);
            playButton.setText(play);
            isPlaying = false;
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPlaying = false;
            playButton.setText(play);
        }
        handler.removeCallbacks(updateSeekBar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        handler.removeCallbacks(updateSeekBar);
    }

    private void initViews(){
        lessonCard = findViewById(R.id.lessonPlayCard);
        lessonTitle = findViewById(R.id.lessonPlayTitle);
        lessonSubtitle = findViewById(R.id.lessonPlaySubtitle);
        lessonIcon = findViewById(R.id.lessonPlayIcon);
        playButton = findViewById(R.id.playButton);
        playbackControls = findViewById(R.id.playbackControls);
        lessonPlayRootLayout = findViewById(R.id.lessonPlayRootLayout);
        lessonPlayBackButton = findViewById(R.id.lessonPlayBackButton);
        previousButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);
    }

    private void setValues(Lesson lesson){
        lessonTitle.setText(lesson.getTitle());
        lessonSubtitle.setText(lesson.getSubtitle());
        lessonIcon.setImageResource(lesson.getImageResId());
        lessonPlayRootLayout.setBackgroundResource(lesson.getGradientResId());
        playbackControls.setBackgroundResource(R.drawable.rounded_background_5);
        ColorStateList tintColorStateList = ColorStateList.valueOf(lesson.getTheme().getTitleBgColour());
        playbackControls.setBackgroundTintList(tintColorStateList);
        playbackControls.setAlpha(0.6f);
    }

    private void definePlayButton(){

        playButton.setOnClickListener(v -> {
            if (isPlaying) {
                mediaPlayer.pause();
                playButton.setText(play);
            } else {
                mediaPlayer.start();
                playButton.setText(pause);
            }
            isPlaying = !isPlaying;
        });
    }

    private void defineLessonPlayBackButton(){
        lessonPlayBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void defineHandlerForRunnable(){
        updateSeekBar = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                }
                handler.postDelayed(this, 250);
            }
        };
        handler.post(updateSeekBar);
    }

    private void defineSeekBar(){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && mediaPlayer != null) {
                    mediaPlayer.seekTo(progress); // Seek to the specified position
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                handler.removeCallbacks(updateSeekBar); // Stop updating SeekBar while user is seeking
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                handler.post(updateSeekBar); // Resume updating SeekBar after user stops seeking
            }
        });

    }

    private void definePrevButton(){
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    int newPosition = mediaPlayer.getCurrentPosition() - 5000;
                    mediaPlayer.seekTo(Math.max(newPosition, 0));
                }
            }
        });
    }

    private void defineNextButton(){
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer != null){
                    int newPosition = mediaPlayer.getCurrentPosition() + 5000;
                    mediaPlayer.seekTo(Math.min(newPosition, mediaPlayer.getDuration()));
                }
            }
        });
    }
}