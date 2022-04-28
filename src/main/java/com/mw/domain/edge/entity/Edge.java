package com.mw.domain.edge.entity;

import com.mw.domain.edgeweight.enttiy.EdgeWeight;
import com.mw.domain.node.enttiy.Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Edge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Node startNode;

    @OneToOne
    private Node endNode;

    private Integer distance;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EdgeWeight> edgeWeightList;

    @Builder
    public Edge(Node startNode, Node endNode, List<EdgeWeight> edgeWeightList) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.edgeWeightList = edgeWeightList;
    }

    public void setEdgeWeightList(List<EdgeWeight> edgeWeightList) {
        this.edgeWeightList = edgeWeightList;
    }

    @Transactional
    public void distanceTo() {
        double startLat = Double.parseDouble(this.startNode.getLatitude());
        double startLong = Double.parseDouble(this.startNode.getLongitude());
        double endLat = Double.parseDouble(this.endNode.getLatitude());
        double endLong = Double.parseDouble(this.endNode.getLongitude());

        double theta = startLong - endLong;
        double dist = Math.sin(deg2rad(startLat)) * Math.sin(deg2rad(endLat)) + Math.cos(deg2rad(startLat)) * Math.cos(deg2rad(endLat)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        this.distance = (int) Math.round(dist * 60 * 1.1515 * 1609.344);
        System.out.println(this.distance);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

}