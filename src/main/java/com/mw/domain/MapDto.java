package com.mw.domain;

import com.mw.domain.dto.NodeDto;
import com.mw.domain.edgeweight.enttiy.EdgeDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MapDto {
    @Schema
    private List<NodeDto.MapNodeDto> nodeList;
    @Schema
    private List<EdgeDto.MapEdgeDto> edgeList;

    @Builder
    public MapDto(List<NodeDto.MapNodeDto> nodeList, List<EdgeDto.MapEdgeDto> edgeList) {
        this.nodeList = nodeList;
        this.edgeList = edgeList;
    }
}
