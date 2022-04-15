package com.mw.domain.edge.entity;

import com.mw.domain.edgeweight.enttiy.EdgeWeight;
import com.mw.domain.node.enttiy.Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
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

    @OneToMany(
            cascade = CascadeType.ALL
    )
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
}