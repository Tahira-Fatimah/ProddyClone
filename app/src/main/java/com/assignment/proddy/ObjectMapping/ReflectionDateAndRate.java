package com.assignment.proddy.ObjectMapping;

import java.util.Date;

public class ReflectionDateAndRate {
    private Date reflectionCreationDate;
    private int reflectionFeelingRate;

    public ReflectionDateAndRate(Date reflectionCreationDate, int reflectionFeelingRate) {
        this.reflectionCreationDate = reflectionCreationDate;
        this.reflectionFeelingRate = reflectionFeelingRate;
    }

    public Date getReflectionCreationDate() {
        return reflectionCreationDate;
    }

    public void setReflectionCreationDate(Date reflectionCreationDate) {
        this.reflectionCreationDate = reflectionCreationDate;
    }

    public int getReflectionFeelingRate() {
        return reflectionFeelingRate;
    }

    public void setReflectionFeelingRate(int reflectionFeelingRate) {
        this.reflectionFeelingRate = reflectionFeelingRate;
    }
}
