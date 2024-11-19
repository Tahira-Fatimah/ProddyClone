package com.assignment.proddy.Entity;

import java.io.Serial;
import java.io.Serializable;

public class Lesson implements Serializable {
    private int id;
    private int imageResId;
    private String title;
    private String subtitle;
    private Theme theme;
    private int lessonAudio;

    public int getGradientResId() {
        return gradientResId;
    }

    public void setGradientResId(int gradientResId) {
        this.gradientResId = gradientResId;
    }

    private int gradientResId;

    public int getLessonAudio() {
        return lessonAudio;
    }

    public void setLessonAudio(int lessonAudio) {
        this.lessonAudio = lessonAudio;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lesson(int id, int imageResId, String title, String subtitle, Theme theme, int lessonAudio, int gradientResId) {
        this.id = id;
        this.imageResId = imageResId;
        this.title = title;
        this.subtitle = subtitle;
        this.theme = theme;
        this.lessonAudio = lessonAudio;
        this.gradientResId = gradientResId;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public static class Theme implements Serializable{
        private int titleBgColour;
        private int subtitleTextColour;
        private int bgCircleColour;

        public Theme(int backgroundColor, int textColor, int bgCircleColour) {
            this.titleBgColour = backgroundColor;
            this.subtitleTextColour = textColor;
            this.bgCircleColour = bgCircleColour;
        }

        public int getTitleBgColour() {
            return titleBgColour;
        }

        public void setTitleBgColour(int titleBgColour) {
            this.titleBgColour = titleBgColour;
        }

        private void setBgCircleColour(int bgCircleColour){
            this.bgCircleColour = bgCircleColour;
        }

        private int getBgCircleColour(){
            return this.bgCircleColour;
        }
        public int getSubtitleTextColour() {
            return subtitleTextColour;
        }

        public void setSubtitleTextColour(int subtitleTextColour) {
            this.subtitleTextColour = subtitleTextColour;
        }
    }
}
