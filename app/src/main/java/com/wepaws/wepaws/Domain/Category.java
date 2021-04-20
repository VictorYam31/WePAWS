package com.wepaws.wepaws.Domain;

public class Category {
    private int category_id;
    private String desc;
    private String desc_cn;

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

    public String getDescCN() {
        return desc_cn;
    }

    public void setDescCN(String desc_cn) {
        this.desc_cn = desc_cn;
    }
}
