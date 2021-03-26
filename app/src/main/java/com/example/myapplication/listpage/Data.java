package com.example.myapplication.listpage;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * Created by ELEVER-H on 2021/2/19.
 */

public class Data implements Parcelable {

    //0为普通，1为计时
    private int type;
    //待办名称
    private String name;
    //开始分/秒钟
    private int startMinute;
    private int startSecond;
    //结束分/秒钟
    private int endMinute;
    private int endSecond;

    //0为未开始，1为已开始，2为取消中，3为停止
    private int state;

    protected Data(Parcel in) {
        type = in.readInt();
        name = in.readString();
        startMinute = in.readInt();
        startSecond = in.readInt();
        endMinute = in.readInt();
        endSecond = in.readInt();
        state = in.readInt();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public int getStartSecond() {
        return startSecond;
    }

    public void setStartSecond(int startSecond) {
        this.startSecond = startSecond;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    public int getEndSecond() {
        return endSecond;
    }

    public void setEndSecond(int endSecond) {
        this.endSecond = endSecond;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Data(int type , String name){
        this.type = type;
        this.name = name;
    }

    public Data(int type , String name, int startMinute, int startSecond, int endMinute, int endSecond){
        this.type = type;
        this.name = name;
        this.startMinute = startMinute;
        this.startSecond = startSecond;
        this.endMinute = endMinute;
        this.endSecond = endSecond;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return type == data.type &&
                startMinute == data.startMinute &&
                startSecond == data.startSecond &&
                endMinute == data.endMinute &&
                endSecond == data.endSecond &&
                Objects.equals(name, data.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, startMinute, startSecond, endMinute, endSecond);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeString(this.name);
        dest.writeInt(this.startMinute);
        dest.writeInt(this.startSecond);
        dest.writeInt(this.endMinute);
        dest.writeInt(this.endSecond);
        dest.writeInt(this.state);
    }
}
