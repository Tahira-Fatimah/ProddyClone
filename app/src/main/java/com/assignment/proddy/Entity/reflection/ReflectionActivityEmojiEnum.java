package com.assignment.proddy.Entity.reflection;

import android.util.Log;

import com.assignment.proddy.R;

public enum ReflectionActivityEmojiEnum {
    SPORTS("\uD83D\uDCAA"),
    LOVE("❤"),
    FRIENDS("✌"),
    WORK("\uD83E\uDDD1\u200D\uD83D\uDCBB"),
    STUDYING("\uD83D\uDCDA"),
    SELF_CARE("✨"),
    CHORES("\uD83E\uDDF9"),
    NATURE("\uD83C\uDF31"),
    RELAXING("\uD83C\uDF34");

    private final String displayName;

    ReflectionActivityEmojiEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ReflectionActivityEmojiEnum fromString(String displayName) {
        for (ReflectionActivityEmojiEnum reflectionActivityEmojiEnum : ReflectionActivityEmojiEnum.values()) {
            if (reflectionActivityEmojiEnum.displayName.equalsIgnoreCase(displayName)) {
                return reflectionActivityEmojiEnum;
            }
        }
        // Log the error or throw an exception for better debugging
        Log.e("ReflectionActivityEmojiEnum", "No match found for displayName: " + displayName);
        return null; // or throw an exception
    }

    public static ReflectionActivityEmojiEnum fromEnumName(String enumName) {
        for (ReflectionActivityEmojiEnum emojiEnum : ReflectionActivityEmojiEnum.values()) {
            if (emojiEnum.name().equalsIgnoreCase(enumName)) {
                return emojiEnum;
            }
        }
        return null; // or handle no match case more gracefully
    }

}

