package com.victoryam.wepaws.Utils;

import android.os.Parcelable;

public interface IResult extends Parcelable {
    public String getNameForResult();
    public String getAddressForResult();
    public String getSpeciesForResult();
    public String getDescriptionForResult();
    public int getNegativeCountForResult();
    public int getNeutralCountForResult();
    public int getPositiveCountForResult();

}
