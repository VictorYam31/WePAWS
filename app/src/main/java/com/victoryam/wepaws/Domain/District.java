package com.victoryam.wepaws.Domain;

import java.util.ArrayList;
import java.util.List;

public class District {
    public static List<String> district;
    public static List<String> region;

    public District() {
        district = new ArrayList<String>();
        district.add("Islands");
        district.add("Kwai Tsing");
        district.add("North");
        district.add("Sai Kung");
        district.add("Sha Tin");
        district.add("Tai Po");
        district.add("Tsuen Wan");
        district.add("Tuen Mun");
        district.add("Yuen Long");
        district.add("Kowloon City");
        district.add("Kwun Tong");
        district.add("Sham Shui Po");
        district.add("Wong Tai Sin");
        district.add("Yau Tsim Mong");
        district.add("Central and Western");
        district.add("Eastern");
        district.add("Southern");
        district.add("Wan Chai");

        region = new ArrayList<String>();
        district.add("Hong Kong Island");
        district.add("Kowloon");
        district.add("New Territories");
    }
}

