package com.victoryam.wepaws.WebService.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.victoryam.wepaws.Utils.IResult;

import org.json.JSONException;
import org.json.JSONObject;

public class WildSearchModel implements IResult, Parcelable {
    private String category;
    private int clinic_id;
    private String clinic_name;
    private String clinic_name_cn;
    private String clinic_address;
    private String clinic_address_cn;
    private String phone;
    private String overnight;
    private String review;

    public WildSearchModel(JSONObject jsonObject) {

        try {
            category = jsonObject.getString("category");
            clinic_id = jsonObject.getInt("clinic_id");
            clinic_name = jsonObject.getString("clinic_name");
            clinic_name_cn = jsonObject.getString("clinic_name_cn");
            clinic_address = jsonObject.getString("clinic_address");
            clinic_address_cn = jsonObject.getString("clinic_address_cn");
            phone = jsonObject.getString("phone");
            overnight = jsonObject.getString("overnight");
            review = jsonObject.getString("review");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected WildSearchModel(Parcel in) {
        category = in.readString();
        clinic_id = in.readInt();
        clinic_name = in.readString();
        clinic_name_cn = in.readString();
        clinic_address = in.readString();
        clinic_address_cn = in.readString();
        phone = in.readString();
        overnight = in.readString();
        review = in.readString();
    }

    public static final Creator<WildSearchModel> CREATOR = new Creator<WildSearchModel>() {
        @Override
        public WildSearchModel createFromParcel(Parcel in) {
            return new WildSearchModel(in);
        }

        @Override
        public WildSearchModel[] newArray(int size) {
            return new WildSearchModel[size];
        }
    };

    public String getCategory() {
        return category;
    }

    public int getClinicId() {
        return clinic_id;
    }

    public String getClinicName() {
        return clinic_name;
    }

    public String getClinic_name_cn() {
        return clinic_name_cn;
    }

    public String getClinicAddress() {
        return clinic_address;
    }

    public String getClinic_address_cn() {
        return clinic_address_cn;
    }

    public String getPhone() {
        return phone;
    }

    public String getOvernight() {
        return overnight;
    }

    public String getReview() {
        return review;
    }

    @Override
    public int getIDForResult() {
        return getClinicId();
    }

    @Override
    public String getNameForResult() {
        return getClinicName();
    }

    @Override
    public String getNameCNForResult() {
        return getClinic_name_cn();
    }

    @Override
    public String getAddressForResult() {
        return getClinicAddress();
    }

    @Override
    public String getAddressCNForResult() {
        return getClinic_address_cn();
    }

    @Override
    public String getDescriptionForResult() {
//        return getClinicDesc();
        return "";
    }

    @Override
    public int getNegativeCountForResult() {
//        return getNegativeCount();
        return 0;
    }

    @Override
    public int getNeutralCountForResult() {
//        return getNeutralCount();
        return 0;
    }

    @Override
    public int getPositiveCountForResult() {
//        return getPositiveCount();
        return 0;
    }

    @Override
    public boolean getIsOvernightForResult() {
//        return getIsOvernight();
        return false;
    }

    @Override
    public String getPhoneNumberForResult() {
        return getPhone();
    }

    @Override
    public int getCategoryForResult() {
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(category);
        parcel.writeInt(clinic_id);
        parcel.writeString(clinic_name);
        parcel.writeString(clinic_name_cn);
        parcel.writeString(clinic_address);
        parcel.writeString(clinic_address_cn);
        parcel.writeString(phone);
        parcel.writeString(overnight);
        parcel.writeString(review);
    }
}
