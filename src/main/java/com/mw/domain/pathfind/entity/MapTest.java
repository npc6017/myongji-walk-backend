package com.mw.domain.pathfind.entity;

import com.mw.domain.edgeweight.enttiy.WeightCode;

import java.util.HashMap;
import java.util.List;

public abstract class MapTest {
    HashMap<Long, List<MapNode>> graph;
    WeightCode weightCode;

    HashMap<Long, List<MapNode>> getGraph() {
        return this.graph;
    }

    abstract boolean isWeightCode(WeightCode weightCode);
}
