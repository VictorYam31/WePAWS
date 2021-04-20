package com.wepaws.wepaws.WebService.Model;


import android.os.Parcel;
import android.os.Parcelable;

import com.wepaws.wepaws.Utils.IReview;

import org.json.JSONException;
import org.json.JSONObject;

public class ReviewModel implements IReview, Parcelable {
    private int id = -1;
    private String login = "Guest";
    private int rate = -1;
    private String review = "";
    private String created_datetime = "";
    private String updated_datetime = "";

    protected ReviewModel(Parcel in) {
        id = in.readInt();
        login = in.readString();
        rate = in.readInt();
        review = in.readString();
        created_datetime = in.readString();
        updated_datetime = in.readString();
    }

    public ReviewModel() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(login);
        dest.writeInt(rate);
        dest.writeString(review);
        dest.writeString(created_datetime);
        dest.writeString(updated_datetime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReviewModel> CREATOR = new Creator<ReviewModel>() {
        @Override
        public ReviewModel createFromParcel(Parcel in) {
            return new ReviewModel(in);
        }

        @Override
        public ReviewModel[] newArray(int size) {
            return new ReviewModel[size];
        }
    };

    public void setHotelReviewModel(JSONObject jsonObject) {
        try {
            id = jsonObject.getInt("hotel_id");
            login = jsonObject.getString("login");
            rate = jsonObject.getInt("rate");
            review = jsonObject.getString("review");
            created_datetime = jsonObject.getString("created_datetime");
            updated_datetime = jsonObject.getString("updated_datetime");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setShopReviewModel(JSONObject jsonObject) {
        try {
            id = jsonObject.getInt("shop_id");
            login = jsonObject.getString("login");
            rate = jsonObject.getInt("rate");
            review = jsonObject.getString("review");
            created_datetime = jsonObject.getString("created_datetime");
            updated_datetime = jsonObject.getString("updated_datetime");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public int getRate() {
        return rate;
    }

    public String getReview() {
        return review;
    }

    public String getCreation_datetime() {
        return created_datetime;
    }

    public String getUpdated_datetime() {
        return updated_datetime;
    }

    @Override
    public int getIDForReview() {
        return getId();
    }

    @Override
    public String getLoginIDForReview() {
        return getLogin();
    }

    @Override
    public int getRateForReview() {
        return getRate();
    }

    @Override
    public String getReviewForReview() {
        return getReview();
    }

    @Override
    public String gerCreateDateForReview() {
        return getCreation_datetime();
    }

    @Override
    public String getLastUpdateDateForReview() {
        return getUpdated_datetime();
    }
}
