package com.mw.domain.pathfind.service;

import com.mw.domain.dto.NodeDto;
import com.mw.domain.pathfind.entity.Guide;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ResponseBuilderTest {

    DirectionGiver directionGiver = new DirectionGiver();

    @Test
    public void buildGuide() {
        ArrayList<NodeDto.NodeInfoDto> pathList = new ArrayList<>();
        ArrayList<Integer> distance = new ArrayList<>();
        distance.add(3);
        distance.add(4);
        distance.add(5);

        pathList.add(new NodeDto.NodeInfoDto(0L, "0.00001", "0", " test1"));
        pathList.add(new NodeDto.NodeInfoDto(1L, "0", "0"," test2"));
        pathList.add(new NodeDto.NodeInfoDto(2L, "0", "0.00001"," test3"));

        for (NodeDto.NodeInfoDto node : pathList)
            System.out.println(node.getLatitude() + " " + node.getLongitude());

        System.out.println("---------------------------------------");

        List<Guide> guides = directionGiver.buildGuide(pathList, distance);

        for (Guide guide : guides)
            System.out.println(guide.getPointIndex() + " " +  guide.getType() + " " + guide.getDistance());

    }
}