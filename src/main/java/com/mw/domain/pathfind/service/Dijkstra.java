package com.mw.domain.pathfind.service;

import com.mw.domain.pathfind.entity.MapNode;
import com.mw.domain.pathfind.entity.PathResult;
import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
public class Dijkstra {
    private static final int COST_RATIO = 60;
    private static final int DISTANCE_RATIO = 40;
    private static final int MAX_COST_VALUE = 5;
    private static final int MAX_DISTANCE_VALUE = 110;

    private HashMap<Long, List<MapNode>> graph;
    private HashMap<Long, Double> shortestPaths;
    private static HashMap<Long, MapNode> hashParent;

    public PathResult pathFind(Long start, Long end) {
        pathFindByDijkstra(start);
        return getPath(start, end);
    }

    public Dijkstra(HashMap<Long, List<MapNode>> graph) {
        this.graph = graph;
        shortestPaths = new HashMap<>();
        hashParent = new HashMap<>();
    }

    private PathResult getPath(Long start, Long end) {
        Integer allDistance = 0;
        Deque<Long> result = new ArrayDeque<>();

        while(!start.equals(end)) {
            result.push(end);
            if(!hashParent.containsKey(end)) break;
            MapNode node = hashParent.get(end);
            allDistance += node.getDistance();
            end = node.getId();
        }
        result.push(start);

        return new PathResult(allDistance, new ArrayList<>(result));
    }

    // 길찾아서 경로 주자
    private void pathFindByDijkstra(Long start) {
        PriorityQueue<MapNode> pq = new PriorityQueue<>();
        pq.offer(new MapNode(start, 0, 0));
        shortestPaths.put(start, (double) 0);

        // 최단 경로 작성
        while(!pq.isEmpty()) {
            MapNode now = pq.poll();
            Integer cost = now.getCost();
            Long id = now.getId();

            if(!shortestPaths.containsKey(id)) shortestPaths.put(id, (double) 120);
            if(shortestPaths.get(id) < cost) continue;
            if(!graph.containsKey(id)) continue;

            for (MapNode next : graph.get(id)) {
                double nextCost = getCost(now, next);
                if(!shortestPaths.containsKey(next.getId())) shortestPaths.put(next.getId(), (double) 120);

                if(nextCost < shortestPaths.get(next.getId())) {
                    hashParent.put(next.getId(), now);
                    shortestPaths.put(next.getId(), nextCost);
                    pq.offer(new MapNode(next.getId(), (int) nextCost, next.getDistance()));
                }
            }
        }
    }

    private double getCost(MapNode now, MapNode next) {
        if (next.getCost() == 0 && next.getDistance() == 0) return shortestPaths.get(now.getId());
        else if (next.getCost() == 0)
            return shortestPaths.get(now.getId()) + (((double) next.getDistance() / MAX_DISTANCE_VALUE) * DISTANCE_RATIO);
        else if (next.getDistance() == 0)
            return shortestPaths.get(now.getId()) + ((double) next.getCost() / MAX_COST_VALUE) * COST_RATIO;
        else
            return shortestPaths.get(now.getId()) + (((double) next.getCost() / MAX_COST_VALUE) * COST_RATIO) + (((double) next.getDistance() / MAX_DISTANCE_VALUE) * DISTANCE_RATIO);
    }
}
