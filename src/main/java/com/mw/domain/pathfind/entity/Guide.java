package com.mw.domain.pathfind.entity;

import lombok.Getter;

@Getter
public class Guide {
    Integer pointIndex;
    String type;
    String distance;

    public Guide(Integer pointIndex, String type, String distance) {
        this.pointIndex = pointIndex;
        this.type = type;
        this.distance = distance;
    }
}
