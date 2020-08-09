package com.example.logicmarketchallenge.core.entities;
import android.os.Parcel;
import android.os.Parcelable;

import com.bumptech.glide.Glide;

public class Laptop implements Parcelable {

    private String title;
    private String description;
    private String image;

    public Laptop(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.image);
    }

    protected Laptop(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Laptop> CREATOR = new Parcelable.Creator<Laptop>() {
        @Override
        public Laptop createFromParcel(Parcel source) {
            return new Laptop(source);
        }

        @Override
        public Laptop[] newArray(int size) {
            return new Laptop[size];
        }
    };
}
