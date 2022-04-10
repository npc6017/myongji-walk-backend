package com.mw.domain.edgeweight.repository;

import com.mw.domain.edgeweight.enttiy.EdgeWeight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdgeWeightRepository extends JpaRepository<EdgeWeight, Long> {
}
