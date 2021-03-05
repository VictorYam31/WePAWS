package com.victoryam.wepaws.Domain;

public class PetHotel {
    private int hotel_id;
    private String hotel_name;
    private String hotel_name_cn;
    private String hotel_address;
    private String hotel_address_cn;
    private String district;
    private int phone;
    private String status;

    public int getHotelId() {
        return hotel_id;
    }

    public void setHotelId(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotelName() {
        return hotel_name;
    }

    public void setHotelName(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotelNameCN() {
        return hotel_name_cn;
    }

    public void setHotelNameCN(String hotel_name_cn) {
        this.hotel_name_cn = hotel_name_cn;
    }

    public String getHotelAddress() {
        return hotel_address;
    }

    public void setHotelAddress(String hotel_address) {
        this.hotel_address = hotel_address;
    }

    public String getHotelAddressCN() {
        return hotel_address_cn;
    }

    public void setHotelAddressCN(String hotel_address_cn) {
        this.hotel_address_cn = hotel_address_cn;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
