package com.mw.domain.pathfind.entity;

import com.mw.domain.edgeweight.enttiy.WeightCode;
import com.mw.domain.pathfind.service.Dijkstra;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
public class PathFind {
    private final MapProvider mapProvider;

    public PathResult findPath(Long startId, Long finishId, WeightCode weightCode) {
        HashMap<Long, List<MapNode>> graph = mapProvider.selectMap(weightCode);
        Dijkstra dijkstra = new Dijkstra(graph);
        return dijkstra.pathFind(startId, finishId);
    }
}
