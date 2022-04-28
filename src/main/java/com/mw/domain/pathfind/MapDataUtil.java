package com.mw.domain.pathfind;

import com.mw.domain.edge.entity.Edge;
import com.mw.domain.edge.repository.EdgeRepository;
import com.mw.domain.edgeweight.enttiy.EdgeWeight;
import com.mw.domain.edgeweight.enttiy.WeightCode;
import com.mw.domain.node.enttiy.Node;
import com.mw.domain.node.repository.NodeRepository;
import com.mw.domain.pathfind.entity.MapNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class MapDataUtil {
    private final List<Node> nodeArrayList;
    private final List<Edge> edgeArrayList;

    private final HashMap<Long, List<MapNode>> lowHillGraph = new HashMap<>();

    public MapDataUtil(NodeRepository nodeRepository, EdgeRepository edgeRepository) {
        this.nodeArrayList = nodeRepository.findAll();
        this.edgeArrayList = edgeRepository.findAll();
        initLowHillGraph();
    }

    // TODO: 2022/04/28 이중 for문을 한번만 돌면셔 모든 graph를 만들 수 있도록 리펙토링합시다.
    public void initLowHillGraph() {
        for(Edge edge : edgeArrayList) {
            Long startId = edge.getStartNode().getId();
            Long endId = edge.getEndNode().getId();
            List<EdgeWeight> edgeWeightList = edge.getEdgeWeightList();

            for (EdgeWeight ew : edgeWeightList) {
                if(ew.getWeightCode().equals(WeightCode.LOW_HILL)) {
                    if(!lowHillGraph.containsKey(startId)) lowHillGraph.put(startId, new ArrayList<>());
                    lowHillGraph.get(startId).add(new MapNode(endId, ew.getWeightValue()));
                }
            }
        }
    }
}

