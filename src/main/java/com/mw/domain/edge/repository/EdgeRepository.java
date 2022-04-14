package com.mw.domain.edge.repository;

import com.mw.domain.edge.entity.Edge;
import com.mw.domain.node.enttiy.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdgeRepository extends JpaRepository<Edge, Long> {
    void deleteAllByStartNodeOrEndNode(Node startNode, Node endNode);
}
