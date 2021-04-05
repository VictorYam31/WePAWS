package com.victoryam.wepaws.WebService.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.PrecomputedText;

import com.victoryam.wepaws.Utils.IReview;

import org.json.JSONException;
import org.json.JSONObject;

public class ClinicReviewModel implements IReview, Parcelable {
    int clinic_id;
    String login;
    int rate;
    String review;
    String created_datetime;
    String updated_datetime;

    protected ClinicReviewModel(Parcel in) {
        clinic_id = in.readInt();
        login = in.readString();
        rate = in.readInt();
        review = in.readString();
        created_datetime = in.readString();
        updated_datetime = in.readString();
    }

    public static final Creator<ClinicReviewModel> CREATOR = new Creator<ClinicReviewModel>() {
        @Override
        public ClinicReviewModel createFromParcel(Parcel in) {
            return new ClinicReviewModel(in);
        }

        @Override
        public ClinicReviewModel[] newArray(int size) {
            return new ClinicReviewModel[size];
        }
    };

    public int getClinicID() {
        return clinic_id;
    }

    public String getLoginID() {
        return login;
    }

    public int getRate() {
        return rate;
    }

    public String getReview() {
        return review;
    }

    public String getCreateDateTime() {
        return created_datetime;
    }

    public String getLastUpdateDateTime() {
        return updated_datetime;
    }

    @Override
    public int getIDForReview() {
        return getClinicID();
    }

    @Override
    public String getLoginIDForReview() {
        return getLoginID();
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
        return getCreateDateTime();
    }

    @Override
    public String getLastUpdateDateForReview() {
        return getLastUpdateDateTime();
    }

    public ClinicReviewModel(JSONObject jsonObject) {
        try {
            clinic_id = jsonObject.getInt("clinic_id");
            login = jsonObject.getString("login");
            rate = jsonObject.getInt("rate");
            review = jsonObject.getString("review");
            created_datetime = jsonObject.getString("created_datetime");
            updated_datetime = jsonObject.getString("updated_datetime");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(clinic_id);
        parcel.writeString(login);
        parcel.writeInt(rate);
        parcel.writeString(review);
        parcel.writeString(created_datetime);
        parcel.writeString(updated_datetime);
    }
}

