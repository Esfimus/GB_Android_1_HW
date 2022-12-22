package com.esfimus.gbandroid1hw;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Presenter implements Parcelable {

    private String input;

    public Presenter() {
        this.input = "";
    }

    public String getResult() {
        return input;
    }

    public String getSubResult() {
        return "Subresult";
    }

    public void passValue(String value) {
        input += value;
    }

    protected Presenter(Parcel in) {
        input = in.readString();
    }

    public static final Creator<Presenter> CREATOR = new Creator<Presenter>() {
        @Override
        public Presenter createFromParcel(Parcel in) {
            return new Presenter(in);
        }

        @Override
        public Presenter[] newArray(int size) {
            return new Presenter[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(input);
    }
}
