package com.dicoding.picodiploma.vedoalfarizi.consumervemovie.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class Show implements Parcelable {
    private int id;
    private String title;
    private String overview;
    private String premiere;
    private float rating;
    private String photo;

    public Show(Cursor s){
        this.id = s.getInt(s.getColumnIndex("id"));
        this.title = s.getString(s.getColumnIndex("name"));
        this.overview = s.getString(s.getColumnIndex("overview"));
        this.premiere = s.getString(s.getColumnIndex("first_air_date"));
        this.rating = s.getFloat(s.getColumnIndex("vote_average"));
        this.photo = s.getString(s.getColumnIndex("poster_path"));
        s.moveToNext();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPremiere() {
        return premiere;
    }

    public void setPremiere(String premiere) {
        this.premiere = premiere;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.premiere);
        dest.writeFloat(this.rating);
        dest.writeString(this.photo);
    }

    private Show(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.premiere = in.readString();
        this.rating = in.readFloat();
        this.photo = in.readString();
    }

    public static final Parcelable.Creator<Show> CREATOR = new Parcelable.Creator<Show>() {
        @Override
        public Show createFromParcel(Parcel source) {
            return new Show(source);
        }

        @Override
        public Show[] newArray(int size) {
            return new Show[size];
        }
    };
}
