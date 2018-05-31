package com.qingniu.qnble.demo.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author: hekang
 * @description:
 * @date: 2018/5/12 11:20
 */
public class Config implements Parcelable {
    private boolean onlyScreenOn;
    private boolean allowDuplicates;
    private int duration;

    public boolean isOnlyScreenOn() {
        return onlyScreenOn;
    }

    public void setOnlyScreenOn(boolean onlyScreenOn) {
        this.onlyScreenOn = onlyScreenOn;
    }

    public boolean isAllowDuplicates() {
        return allowDuplicates;
    }

    public void setAllowDuplicates(boolean allowDuplicates) {
        this.allowDuplicates = allowDuplicates;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    private int unit;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.onlyScreenOn ? (byte) 1 : (byte) 0);
        dest.writeByte(this.allowDuplicates ? (byte) 1 : (byte) 0);
        dest.writeInt(this.duration);
        dest.writeInt(this.unit);
    }

    public Config() {
    }

    protected Config(Parcel in) {
        this.onlyScreenOn = in.readByte() != 0;
        this.allowDuplicates = in.readByte() != 0;
        this.duration = in.readInt();
        this.unit = in.readInt();
    }

    public static final Parcelable.Creator<Config> CREATOR = new Parcelable.Creator<Config>() {
        @Override
        public Config createFromParcel(Parcel source) {
            return new Config(source);
        }

        @Override
        public Config[] newArray(int size) {
            return new Config[size];
        }
    };
}
