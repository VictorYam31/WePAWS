package com.victoryam.wepaws.WebService.Model;

import android.media.Rating;
import android.os.Parcel;
import android.os.Parcelable;

import com.victoryam.wepaws.Domain.Species;
import com.victoryam.wepaws.Utils.IResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ClinicMasterModel implements IResult, Parcelable {
    int clinic_id;
    String clinic_name;
    String clinic_name_cn;
    String clinic_address;
    String clinic_address_cn;
    int district;
    String phone;
    String overnight;
    int negative_count;
    int neutral_count;
    int positive_count;
    int review_count;

    public ClinicMasterModel(JSONObject jsonObject) {
        try {
            clinic_id = jsonObject.getInt("clinic_id");
            clinic_name = jsonObject.getString("clinic_name");
            clinic_name_cn = jsonObject.getString("clinic_name_cn");
            clinic_address = jsonObject.getString("clinic_address");
            clinic_address_cn = jsonObject.getString("clinic_address_cn");
            district = jsonObject.getInt("district");
            phone = jsonObject.getString("phone");
            overnight = jsonObject.getString("overnight");
            negative_count = jsonObject.getInt("negative_count");
            neutral_count = jsonObject.getInt("neutral_count");
            positive_count = jsonObject.getInt("positive_count");
            review_count = jsonObject.getInt("review_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getClinicName() {
        return clinic_name;
    }

    public String getClinicAddress() {
        return clinic_address;
    }

    public int getRating() {
        int totalRating = negative_count + positive_count;
        if (totalRating > 0) {
            return totalRating;
        } else {
            return 0;
        }
    }


    @Override
    public String getNameForResult() {
        return getClinicName();
    }

    @Override
    public String getAddressForResult() {
        return getClinicAddress();
    }

    @Override
    public String getSpeciesForResult() {
        return "";
    }

    @Override
    public String getDescriptionForResult() {
        return "";
    }

    @Override
    public String getRatingForResult() {
        return String.valueOf(getRating());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected ClinicMasterModel(Parcel in) {
        clinic_id = in.readInt();
        clinic_name = in.readString();
        clinic_name_cn = in.readString();
        clinic_address = in.readString();
        clinic_address_cn = in.readString();
        district = in.readInt();
        phone = in.readString();
        overnight = in.readString();
        negative_count = in.readInt();
        neutral_count = in.readInt();
        positive_count = in.readInt();
        review_count = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(clinic_id);
        parcel.writeString(clinic_name);
        parcel.writeString(clinic_name_cn);
        parcel.writeString(clinic_address);
        parcel.writeString(clinic_address_cn);
        parcel.writeInt(district);
        parcel.writeString(phone);
        parcel.writeString(overnight);
        parcel.writeInt(negative_count);
        parcel.writeInt(neutral_count);
        parcel.writeInt(positive_count);
        parcel.writeInt(review_count);
    }

    public static final Creator<ClinicMasterModel> CREATOR = new Creator<ClinicMasterModel>() {
        @Override
        public ClinicMasterModel createFromParcel(Parcel in) {
            return new ClinicMasterModel(in);
        }

        @Override
        public ClinicMasterModel[] newArray(int size) {
            return new ClinicMasterModel[size];
        }
    };
}
