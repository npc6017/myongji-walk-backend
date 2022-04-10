package com.mw.domain.edge.service;

import com.mw.domain.edge.repository.EdgeRepository;
import com.mw.domain.edgeweight.repository.EdgeWeightRepository;
import com.mw.domain.node.repository.NodeRepository;
import org.springframework.stereotype.Service;

@Service
public class PathFindService {
    private final EdgeRepository edgeRepository;
    private final NodeRepository nodeRepository;
    private final EdgeWeightRepository edgeWeightRepository;

    public PathFindService(EdgeRepository edgeRepository, NodeRepository nodeRepository, EdgeWeightRepository edgeWeightRepository) {
        this.edgeRepository = edgeRepository;
        this.nodeRepository = nodeRepository;
        this.edgeWeightRepository = edgeWeightRepository;
    }


}
