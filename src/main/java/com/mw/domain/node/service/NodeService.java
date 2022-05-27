package com.mw.domain.node.service;

import com.mw.domain.dto.NodeDto;
import com.mw.domain.node.enttiy.Node;
import com.mw.domain.node.repository.NodeRepository;
import com.mw.exception.custom.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Service
public class NodeService {
    private final NodeRepository nodeRepository;

    @Transactional
    public NodeDto.NodeInfoDto inputNodeName(NodeDto.NodeInfoDto nodeNameDto) {
        Node node = nodeRepository.findNodeByLongitudeAndLatitude(nodeNameDto.getLongitude(), nodeNameDto.getLatitude());
        node.inputName(nodeNameDto.getName());
        return NodeDto.nodeToNodeInfoDto(nodeRepository.save(node));
    }

    public NodeDto.NodeInfoDto findByNodeName(NodeDto.NodeNameDto nodeNameDto) {
        return NodeDto.nodeToNodeInfoDto(nodeRepository.findNodeByName(nodeNameDto.getName()).orElseThrow(ObjectNotFoundException::new));
    }
}
