package com.mw.domain.pathfind.service;

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
import com.mw.domain.pathfind.MapDataUtil;
import com.mw.domain.pathfind.entity.Guide;
import com.mw.domain.pathfind.entity.PathResult;
import com.mw.domain.pathfind.entity.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Service
@Transactional
@AllArgsConstructor
public class PathFindService {
    private final EdgeRepository edgeRepository;
    private final NodeRepository nodeRepository;
    private final EdgeWeightRepository edgeWeightRepository;
    private final MapDataUtil mapDataUtil;

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
            EdgeWeight save = edgeWeightRepository.save(edgeWeight);
            edgeWeightList.add(save);
        }

        edge.setEdgeWeightList(edgeWeightList);
        Edge save = edgeRepository.save(edge);
        return save.getId();
    }

    public MapDto getMap() {
        List<Node> nodeList = nodeRepository.findAll();
        List<Edge> edgeList = edgeRepository.findAll();

        List<NodeDto.MapNodeDto> mapNodeList = new ArrayList<>();
        List<EdgeDto.MapEdgeDto> mapEdgeList = new ArrayList<>();

        for (Node node : nodeList)
            mapNodeList.add(new NodeDto.MapNodeDto(node));

        for (Edge edge : edgeList) {
            Node startNode = edge.getStartNode();
            Node endNode = edge.getEndNode();

            mapEdgeList.add(new EdgeDto.MapEdgeDto(edge, new NodeDto.MapNodeDto(startNode), new NodeDto.MapNodeDto(endNode)));
        }
        MapDto build = MapDto.builder()
                .nodeList(mapNodeList)
                .edgeList(mapEdgeList)
                .build();

        return build;
    }

    public void calculateDistance() {
        List<Edge> all = edgeRepository.findAll();

        for (Edge edge : all)
            edge.distanceTo();
    }

    public ResponseDto pathFind(Long start, Long end) {
        Dijkstra dijkstra = new Dijkstra(mapDataUtil.getLowHillGraph());
        PathResult pathResult = dijkstra.pathFind(start, end);
        ResponseBuilder responseBuilder = new ResponseBuilder();
        List<Guide> guides = responseBuilder.buildGuide(pathListToNodeInfoDtoList(pathResult.getPathList()), pathResult.getDistance());

        return ResponseDto.builder()
                .start(NodeDto.nodeToNodeInfoDto(mapDataUtil.getNodeById(start)))
                .goal(NodeDto.nodeToNodeInfoDto(mapDataUtil.getNodeById(end)))
                .items(pathListToNodeInfoDtoList(pathResult.getPathList()))
                .sumDistance(pathResult.getAllDistance().toString())
                .guide(guides)
                .build();
    }

    private ArrayList<NodeDto.NodeInfoDto> pathListToNodeInfoDtoList(ArrayList<Long> pathList) {
        ArrayList<NodeDto.NodeInfoDto> result = new ArrayList<>();
        for (Long path : pathList)
            result.add(NodeDto.nodeToNodeInfoDto(mapDataUtil.getNodeById(path)));

        return result;
    }
}