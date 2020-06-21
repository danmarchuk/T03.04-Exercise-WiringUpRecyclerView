package com.example.android.popular_movies;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private String mTitle;
    private String mYear;
    private String mDescription;
    private String mLength;
    private String mRating;
    private String mTrailer;
    private String mImageUrl;

    public Movie (String imageUrl, String title, String year, String description, String length, String rating, String trailer) {
        mImageUrl = imageUrl;
        mTitle = title;
        mYear = year;
        mDescription = description;
        mLength = length;
        mRating = rating;
        mTrailer = trailer;
    }

    protected Movie(Parcel in) {
        mTitle = in.readString();
        mYear = in.readString();
        mDescription = in.readString();
        mLength = in.readString();
        mRating = in.readString();
        mTrailer = in.readString();
        mImageUrl = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() {return mTitle;}
    public String getYear() {return mYear;}
    public String getLength() {return mLength;}
    public String getDescription () {return mDescription;}
    public String getRating() {return mRating;}
    public String getImageUrl() {return mImageUrl;}
    public String getTrailer() {return mTrailer;}


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mYear);
        dest.writeString(mDescription);
        dest.writeString(mLength);
        dest.writeString(mRating);
        dest.writeString(mTrailer);
        dest.writeString(mImageUrl);
    }
}
