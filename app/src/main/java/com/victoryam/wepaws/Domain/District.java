package com.victoryam.wepaws.Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class District {
    public static HashMap<Integer, String> district;
    public static HashMap<Integer, String> region;

    public District() {
        district = new HashMap<Integer, String>();
        district.put(1, "Central and Western");
        district.put(2, "Eastern");
        district.put(3, "Southern");
        district.put(4, "Wan Chai");
        district.put(5, "Sham Shui Po");
        district.put(6, "Kowloon City");
        district.put(7, "Kwun Tong");
        district.put(8, "Wong Tai Sin");
        district.put(9, "Yau Tsim Mong");
        district.put(10, "Islands");
        district.put(11, "Kwai Tsing");
        district.put(12, "North");
        district.put(13, "Sai Kung");
        district.put(14, "Sha Tin");
        district.put(15, "Tai Po");
        district.put(16, "Tsuen Wan");
        district.put(17, "Tuen Mun");
        district.put(18, "Yuen Long");

        region = new HashMap<Integer, String>();
        region.put(1, "Hong Kong Island");
        region.put(2, "Kowloon");
        region.put(3, "New Territories");
    }

    public HashMap<Integer, String> GetDistrictMap() {
        return district;
    }

    public String GetDistrictbyNumber(Integer key) {
        if (district.containsKey(key)) {
            return district.get(key);
        } else {
            return "";
        }
    }

    public Integer GetNumberbyDistrict(String value) {
        for (Map.Entry<Integer, String> entry : district.entrySet()) {
            if (entry.getValue().equals(value)) {
                return (entry.getKey());
            }
        }
        return -1;
    }
}

