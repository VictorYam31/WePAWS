package com.wepaws.wepaws.Utils;

import android.os.Parcelable;

public interface IResult extends Parcelable {
    public int getIDForResult();
    public String getNameForResult();
    public String getNameCNForResult();
    public String getAddressForResult();
    public String getAddressCNForResult();
    public String getDescriptionForResult();
    public int getNegativeCountForResult();
    public int getNeutralCountForResult();
    public int getPositiveCountForResult();
    public boolean getIsOvernightForResult();
    public String getPhoneNumberForResult();
    public int getCategoryForResult();
}
