package com.mw.domain.pathfind.entity;

import com.mw.domain.edge.entity.Edge;
import com.mw.domain.edge.repository.EdgeRepository;
import com.mw.domain.edgeweight.enttiy.EdgeWeight;
import com.mw.domain.edgeweight.enttiy.WeightCode;
import com.mw.domain.node.enttiy.Node;
import com.mw.domain.node.repository.NodeRepository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.Map;

@Component
public class MapProvider {
    private final List<Node> nodeArrayList;
    private final List<Edge> edgeArrayList;
    private static final HashMap<WeightCode, HashMap<Long, List<MapNode>>> graphMap = new HashMap<>();
    
    public MapProvider(NodeRepository nodeRepository, EdgeRepository edgeRepository) {
        this.nodeArrayList = nodeRepository.findAll();
        this.edgeArrayList = edgeRepository.findAll();
        initGraphMap();
        initGraph();
    }
    
    private void initGraph() {
        for(Edge edge : edgeArrayList) {
            Long startId = edge.getStartNode().getId();
            Long endId = edge.getEndNode().getId();
            List<EdgeWeight> edgeWeightList = edge.getEdgeWeightList();

            for (EdgeWeight ew : edgeWeightList) {
                for (WeightCode wc : WeightCode.values()) {
                    if(ew.getWeightCode().equals(wc)) {
                        HashMap<Long, List<MapNode>> wcMap = graphMap.get(wc);
                        if(!wcMap.containsKey(startId)) wcMap.put(startId, new ArrayList<>());
                        wcMap.get(startId).add(new MapNode(endId, ew.getWeightValue(), edge.getDistance()));
                    }
                }
            }
        }

        for (Map.Entry<WeightCode, HashMap<Long, List<MapNode>>> entry : graphMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue().size());
        }
    }
    
    private void initGraphMap() {
        for (WeightCode wc : WeightCode.values()) graphMap.put(wc, new HashMap<>());
    }

    public HashMap<Long, List<MapNode>> selectMap(WeightCode weightCode) {
        if(graphMap.containsKey(weightCode)) return graphMap.get(weightCode);
        
        // 존재하지 않는 weightCode exception
        throw new RuntimeException();
    }
}
