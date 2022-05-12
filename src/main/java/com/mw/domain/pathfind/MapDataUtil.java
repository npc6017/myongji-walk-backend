package com.mw.domain.pathfind;

import com.mw.domain.edge.entity.Edge;
import com.mw.domain.edge.repository.EdgeRepository;
import com.mw.domain.edgeweight.enttiy.EdgeWeight;
import com.mw.domain.edgeweight.enttiy.WeightCode;
import com.mw.domain.node.enttiy.Node;
import com.mw.domain.node.repository.NodeRepository;
import com.mw.domain.pathfind.entity.MapNode;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Getter
public class MapDataUtil {
    private final List<Node> nodeArrayList;
    private final List<Edge> edgeArrayList;
    private final HashMap<Long, Node> nodeHashMap = new HashMap<>();

    private final HashMap<Long, List<MapNode>> lowHillGraph = new HashMap<>();

    public MapDataUtil(NodeRepository nodeRepository, EdgeRepository edgeRepository) {
        this.nodeArrayList = nodeRepository.findAll();
        this.edgeArrayList = edgeRepository.findAll();
        initLowHillGraph();
        saveNodeInformation();
    }

    private void saveNodeInformation() {
        for (Node node : nodeArrayList) nodeHashMap.put(node.getId(), node);
    }

    public Node getNodeById(Long id) {
        return nodeHashMap.get(id);
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
                    lowHillGraph.get(startId).add(new MapNode(endId, ew.getWeightValue(), edge.getDistance()));
                }
            }
        }

        // 거리순 정렬
        for (Map.Entry<Long, List<MapNode>> entry : lowHillGraph.entrySet()) {
            List<MapNode> collect = entry.getValue().stream()
                    .sorted(Comparator.comparing(MapNode::getDistance))
                    .collect(Collectors.toList());
            lowHillGraph.put(entry.getKey(), collect);
        }
    }
}

