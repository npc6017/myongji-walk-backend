package com.mw.domain.pathfind.entity;

import lombok.Getter;

@Getter
public class MapNode implements Comparable<MapNode>{
    Long id;
    Integer cost;
    Integer distance;

    public MapNode(Long id, Integer cost, Integer distance) {
        this.id = id;
        this.cost = cost;
        this.distance = distance;
    }

    @Override
    public int compareTo(MapNode o) {
        return this.cost - o.getCost();
    }
}