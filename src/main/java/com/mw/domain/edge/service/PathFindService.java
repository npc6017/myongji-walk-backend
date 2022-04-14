package com.mw.domain.edge.service;

import com.mw.domain.MapDto;
import com.mw.domain.edge.entity.Edge;
import com.mw.domain.edge.repository.EdgeRepository;
import com.mw.domain.edgeweight.enttiy.EdgeDto;
import com.mw.domain.edgeweight.enttiy.EdgeWeight;
import com.mw.domain.edgeweight.enttiy.EdgeWeightDto;
import com.mw.domain.edgeweight.repository.EdgeWeightRepository;
import com.mw.domain.node.enttiy.Node;
import com.mw.domain.node.enttiy.NodeDto;
import com.mw.domain.node.repository.NodeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class PathFindService {
    private final EdgeRepository edgeRepository;
    private final NodeRepository nodeRepository;
    private final EdgeWeightRepository edgeWeightRepository;

    public PathFindService(EdgeRepository edgeRepository, NodeRepository nodeRepository, EdgeWeightRepository edgeWeightRepository) {
        this.edgeRepository = edgeRepository;
        this.nodeRepository = nodeRepository;
        this.edgeWeightRepository = edgeWeightRepository;
    }

    public Long createNode(NodeDto.NodeInfoDto newNode) {
        Node save = nodeRepository.save(Node.builder()
                .newNode(newNode)
                .build()
        );

        return save.getId();
    }

    public void deleteNode(long nodeId) {
        Node node = nodeRepository.findById(nodeId).orElseThrow(RuntimeException::new);

        edgeRepository.deleteAllByStartNodeOrEndNode(node, node);
        nodeRepository.deleteById(nodeId);
    }

    public Long createEdge(EdgeDto.EdgeInfoDto newEdge) {
        Node startNode = nodeRepository.findById(newEdge.getStartNode()).orElseThrow(RuntimeException::new);
        Node endNode = nodeRepository.findById(newEdge.getEndNode()).orElseThrow(RuntimeException::new);
        Edge edge = Edge.builder()
                .startNode(startNode)
                .endNode(endNode)
                .build();

        List<EdgeWeightDto> edgeWeightDtoList = newEdge.getEdgeWeightDtoList();
        List<EdgeWeight> edgeWeightList = new ArrayList<>();

        for (EdgeWeightDto e : edgeWeightDtoList) {
            EdgeWeight edgeWeight = new EdgeWeight(e);
            edgeWeight.setEdge(edge);
            edgeWeightList.add(edgeWeight);
        }

        edge.setEdgeWeightList(edgeWeightList);
        Edge save = edgeRepository.save(edge);
        return save.getId();
    }

    public MapDto getMap() {
        List<Node> nodeList = nodeRepository.findAll();
        List<Edge> edgeList = edgeRepository.findAll();

        List<NodeDto.NodeInfoDto> mapNodeList = new ArrayList<>();
        List<EdgeDto.MapEdgeDto> mapEdgeList = new ArrayList<>();

        for (Node node : nodeList)
            mapNodeList.add(new NodeDto.NodeInfoDto(node.getLatitude(), node.getLongitude()));

        for (Edge edge : edgeList) {
            Node startNode = edge.getStartNode();
            Node endNode = edge.getEndNode();

            mapEdgeList.add(new EdgeDto.MapEdgeDto(new NodeDto.NodeInfoDto(startNode.getLatitude(), startNode.getLongitude()), new NodeDto.NodeInfoDto(endNode.getLatitude(), endNode.getLongitude())));
        }
        MapDto build = MapDto.builder()
                .nodeList(mapNodeList)
                .edgeList(mapEdgeList)
                .build();

        return build;
    }
}