package com.mw.domain.pathfind.service;

import com.mw.domain.node.enttiy.NodeDto;
import com.mw.domain.pathfind.entity.Guide;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResponseBuilderTest {

    ResponseBuilder responseBuilder = new ResponseBuilder();

    @Test
    public void buildGuide() {
        ArrayList<NodeDto.NodeInfoDto> pathList = new ArrayList<>();
        ArrayList<Integer> distance = new ArrayList<>();
        distance.add(3);
        distance.add(4);
        distance.add(5);

        pathList.add(new NodeDto.NodeInfoDto("0.00001", "0"));
        pathList.add(new NodeDto.NodeInfoDto("0", "0"));
        pathList.add(new NodeDto.NodeInfoDto("0", "0.00001"));

        for (NodeDto.NodeInfoDto node : pathList)
            System.out.println(node.getLatitude() + " " + node.getLongitude());

        System.out.println("---------------------------------------");

        List<Guide> guides = responseBuilder.buildGuide(pathList, distance);

        for (Guide guide : guides)
            System.out.println(guide.getPointIndex() + " " +  guide.getType() + " " + guide.getDistance());

    }
}