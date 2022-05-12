package com.mw.domain.pathfind.entity;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class PathResult {
    Integer allDistance;
    ArrayList<Long> pathList;
    ArrayList<Integer> distance;

    public PathResult(Integer allDistance, ArrayList<Long> pathList, ArrayList<Integer> distance) {
        this.allDistance = allDistance;
        this.pathList = pathList;
        this.distance = distance;
    }
}
