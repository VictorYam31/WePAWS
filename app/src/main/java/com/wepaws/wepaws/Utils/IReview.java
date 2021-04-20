package com.wepaws.wepaws.Utils;

import android.os.Parcelable;

public interface IReview extends Parcelable {
    public int getIDForReview();
    public String getLoginIDForReview();
    public int getRateForReview();
    public String getReviewForReview();
    public String gerCreateDateForReview();
    public String getLastUpdateDateForReview();
}
