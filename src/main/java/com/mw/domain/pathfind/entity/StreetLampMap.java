package com.mw.domain.pathfind.entity;

import com.mw.domain.edgeweight.enttiy.WeightCode;

public class StreetLampMap extends MapTest{
    public StreetLampMap() {
        this.weightCode = WeightCode.STREET_LAMP;
    }

    @Override
    boolean isWeightCode(WeightCode weightCode) {
        return this.weightCode.equals(weightCode);
    }
}
