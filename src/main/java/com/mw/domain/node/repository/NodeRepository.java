package com.mw.domain.node.repository;

import com.mw.domain.node.enttiy.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {
    public Node findNodeByLongitudeAndLatitude(String longitude, String latitude);
}
