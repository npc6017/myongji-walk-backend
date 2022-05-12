package com.mw.domain.pathfind.service;

import com.mw.domain.node.enttiy.NodeDto;
import com.mw.domain.pathfind.entity.Guide;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


// angle이 음수가 나오는 것에 대해서도 처리가 가능해야할 것 같음..?
public class ResponseBuilder {
    public List<Guide> buildGuide(ArrayList<NodeDto.NodeInfoDto> pathList, ArrayList<Integer> distance) {
        Deque<NodeDto.NodeInfoDto> pathQueue = new ArrayDeque<>(pathList);
        List<Guide> guideList = new ArrayList<>();
        int count = 0;
        int countDistance = 0;

        while(pathQueue.size() >= 3) {
            NodeDto.NodeInfoDto one = pathQueue.poll();
            countDistance += distance.get(count);
            count++;
            NodeDto.NodeInfoDto two = pathQueue.poll();
            countDistance += distance.get(count);
            count++;
            NodeDto.NodeInfoDto three = pathQueue.poll();

            double v1 = Double.parseDouble(three.getLatitude()) - Double.parseDouble(two.getLatitude());
            double v2 = Double.parseDouble(three.getLongitude()) - Double.parseDouble(two.getLongitude());

            double v3 = Double.parseDouble(one.getLatitude()) - Double.parseDouble(two.getLatitude());
            double v4 = Double.parseDouble(one.getLongitude()) - Double.parseDouble(two.getLongitude());

            double angle = (Math.atan(v3 / v4) - Math.atan(v1 / v2)) * 180 / Math.PI;

            String guide = defineTypeByAngle(angle);
            if(guide != null) guideList.add(new Guide(count-1, guide, countDistance + "M"));

            countDistance += distance.get(count);
            count++;

            System.out.println(angle);
        }

        return guideList;
    }

    private String defineTypeByAngle(double angle) {
        if(angle >= 20 && angle <= 160) return "우회전";
        if(angle >= 200 && angle <= 340) return "좌회전";
        return null;
    }
}
