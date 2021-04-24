package com.wepaws.wepaws.WebService.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.wepaws.wepaws.Utils.IResult;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

public class MasterModel implements IResult, Parcelable {
    private int category = 2; //0 clinic, 1 hotel, 2 shop
    private int id = -1;
    private String name = "";
    private String name_cn = "";
    private String address = "";
    private String address_cn = "";
    private String desc = "";
    private int district_id = -1;
    private String phone = "";
    private String overnight = ""; //Y/N for clinic, X for hotel and shop
    private int negative_count = 0;
    private int neutral_count = 0;
    private int positive_count = 0;
    private int review_count = 0;


    public MasterModel(Parcel in) {
        category = in.readInt(); // new add
        id = in.readInt();
        name = in.readString();
        name_cn = in.readString();
        address = in.readString();
        address_cn = in.readString();
        desc = in.readString();
        district_id = in.readInt();
        phone = in.readString();
        overnight = in.readString(); // new add
        negative_count = in.readInt();
        neutral_count = in.readInt();
        positive_count = in.readInt();
        review_count = in.readInt();
    }

    public static final Creator<MasterModel> CREATOR = new Creator<MasterModel>() {
        @Override
        public MasterModel createFromParcel(Parcel in) {
            return new MasterModel(in);
        }

        @Override
        public MasterModel[] newArray(int size) {
            return new MasterModel[size];
        }
    };

    public MasterModel() {

    }

    public void SetHotelMasterModel(@NotNull JSONObject jsonObject) {
        try {
            id = jsonObject.getInt("hotel_id");
            name = jsonObject.getString("hotel_name");
            name_cn = jsonObject.getString("hotel_name_cn");
            address = jsonObject.getString("hotel_address");
            address_cn = jsonObject.getString("hotel_address_cn");
            desc = jsonObject.getString("hotel_desc");
            district_id = jsonObject.getInt("district_id");
            phone = jsonObject.getString("phone");
//            overnight = jsonObject.getString("overnight");
            negative_count = jsonObject.getInt("negative_count");
            neutral_count = jsonObject.getInt("neutral_count");
            positive_count = jsonObject.getInt("positive_count");
            review_count = jsonObject.getInt("review_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void SetShopMasterModel(@NotNull JSONObject jsonObject) {
        try {
            id = jsonObject.getInt("shop_id");
            name = jsonObject.getString("shop_name");
            name_cn = jsonObject.getString("shop_name_cn");
            address = jsonObject.getString("shop_address");
            address_cn = jsonObject.getString("shop_address_cn");
            desc = jsonObject.getString("shop_desc");
            district_id = jsonObject.getInt("district_id");
            phone = jsonObject.getString("phone");
            negative_count = jsonObject.getInt("negative_count");
            neutral_count = jsonObject.getInt("neutral_count");
            positive_count = jsonObject.getInt("positive_count");
            review_count = jsonObject.getInt("review_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setWildMasterModel(@NotNull JSONObject jsonObject) {
        try {
            category = getRealCategoryNo(jsonObject.getInt("category"));
            id = jsonObject.getInt("id");
            name = jsonObject.getString("name");
            name_cn = jsonObject.getString("name_cn");
            address = jsonObject.getString("address");
            address_cn = jsonObject.getString("address_cn");
            desc = jsonObject.getString("desc");
            district_id = jsonObject.getInt("district_id");
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(name_cn);
        parcel.writeString(address);
        parcel.writeString(address_cn);
        parcel.writeString(desc);
        parcel.writeInt(district_id);
        parcel.writeString(phone);
//        parcel.writeString(overnight);
        parcel.writeInt(negative_count);
        parcel.writeInt(neutral_count);
        parcel.writeInt(positive_count);
        parcel.writeInt(review_count);
    }

    public int getId() {
        return id;
    }

    public int getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getNameCN() {
        return name_cn;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getAddressCN() {
        return address_cn;
    }

    public String getDesc() {
        return desc;
    }

    public int getNegativeCount() {
        return negative_count;
    }

    public int getNeutralCount() {
        return neutral_count;
    }

    public int getPositiveCount() {
        return positive_count;
    }

    public boolean getIsOvernight() {
        if (!overnight.equals("") && overnight != null) {
            if (overnight.equals("N") || overnight.equals("X")) {
                return false;
            } else if (overnight.equals("Y")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getIDForResult() {
        return getId();
    }

    @Override
    public String getNameForResult() {
        return getName();
    }

    @Override
    public String getNameCNForResult() {
        return getNameCN();
    }

    @Override
    public String getAddressForResult() {
        return getAddress();
    }

    @Override
    public String getAddressCNForResult() {
        return getAddressCN();
    }

    @Override
    public String getDescriptionForResult() {
        return getDesc();
    }

    @Override
    public int getNegativeCountForResult() {
        return getNegativeCount();
    }

    @Override
    public int getNeutralCountForResult() {
        return getNeutralCount();
    }

    @Override
    public int getPositiveCountForResult() {
        return getPositiveCount();
    }

    @Override
    public boolean getIsOvernightForResult() {
        return getIsOvernight();
    }

    @Override
    public String getPhoneNumberForResult() {
        return getPhone();
    }

    @Override
    public int getCategoryForResult() {
        return getCategory();
    }

    private int getRealCategoryNo(int category) {
        int realCategory = -1;
        switch (category) {
            case 0:
                realCategory = 1;
                break;
            case 1:
                realCategory = 3;
                break;
            case 2:
                realCategory = 2;
                break;
        }
        return realCategory;
    }
}
