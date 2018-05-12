package com.qingniu.qnble.demo.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * author: yolanda-XY
 * description: ${用户本地信息}
 * date: 2018/4/28
 */

public class User implements Parcelable {
    /**
     * 用户唯一标识，不能为空，每个用户唯一，与存储数据相关
     */
    private String userId;
    /**
     * 身高，单位 cm，最低40，最高240
     */
    private int height;

    /**
     * 性别，男: male，女: female
     */
    private String gender;
    /**
     * 生日，用以计算年龄，日期精度精确到天，计算的年龄范围是 3~80，
     * 超过这个范围的，等于上限或下限
     */
    private Date birthDay;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeInt(this.height);
        dest.writeString(this.gender);
        dest.writeLong(this.birthDay != null ? this.birthDay.getTime() : -1);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.userId = in.readString();
        this.height = in.readInt();
        this.gender = in.readString();
        long tmpBirthDay = in.readLong();
        this.birthDay = tmpBirthDay == -1 ? null : new Date(tmpBirthDay);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
