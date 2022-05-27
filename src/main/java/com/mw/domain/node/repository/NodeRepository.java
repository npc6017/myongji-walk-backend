package com.mw.domain.node.repository;

import com.mw.domain.node.enttiy.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {
    Node findNodeByLongitudeAndLatitude(String longitude, String latitude);
    Optional<Node> findNodeByName(String name);
}
