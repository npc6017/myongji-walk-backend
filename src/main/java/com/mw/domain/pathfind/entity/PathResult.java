package com.mw.domain.pathfind.entity;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class PathResult {
    Integer allDistance;
    ArrayList<Long> pathList;

    public PathResult(Integer allDistance, ArrayList<Long> pathList) {
        this.allDistance = allDistance;
        this.pathList = pathList;
    }
}
