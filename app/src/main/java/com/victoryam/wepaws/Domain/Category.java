package com.victoryam.wepaws.Domain;

public class Category {
    private int category_id;
    private String desc;
    private String desc_chi;

    public int getCategoryId() {
        return category_id;
    }

    public void setCategoryId(int category_id) {
        this.category_id = category_id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDescChi() {
        return desc_chi;
    }

    public void setDescChi(String descChi) {
        this.desc_chi = descChi;
    }
}
