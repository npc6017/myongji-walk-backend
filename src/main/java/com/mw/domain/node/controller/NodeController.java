package com.mw.domain.node.controller;

import com.mw.domain.dto.NodeDto;
import com.mw.domain.node.service.NodeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/node")
public class NodeController {
    private final NodeService nodeService;

    @PostMapping
    public ResponseEntity<NodeDto.NodeInfoDto> inputNodeName(NodeDto.NodeInfoDto nodeNameDto) {
        return ResponseEntity.status(HttpStatus.OK).body(nodeService.inputNodeName(nodeNameDto));
    }
}
