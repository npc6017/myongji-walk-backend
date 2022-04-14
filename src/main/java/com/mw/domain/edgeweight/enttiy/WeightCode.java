package com.mw.domain.edgeweight.enttiy;

public enum WeightCode {
    STREET_LAMP("streetLamp", 0),
    LOW_HILL("lowHill", 1),
    LOW_RAIN("lowRain", 2),
    LOW_STAIR("lowStair", 3);

    private final String name;
    private final int index;

    WeightCode(String name, int index) {
        this.name = name;
        this.index = index;
    }
}
