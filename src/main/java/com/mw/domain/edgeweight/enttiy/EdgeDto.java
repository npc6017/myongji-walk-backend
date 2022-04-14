package com.mw.domain.edgeweight.enttiy;

import com.mw.domain.node.enttiy.NodeDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class EdgeDto {
    private EdgeDto() {}

    @Getter
    public static class EdgeInfoDto {
        private long startNode;
        private long endNode;
        List<EdgeWeightDto> edgeWeightDtoList;

        @Builder
        public EdgeInfoDto(long startNode, long endNode, List<EdgeWeightDto> edgeWeightDtoList) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.edgeWeightDtoList = edgeWeightDtoList;
        }
    }

    public static class MapEdgeDto {
        @Schema
        private NodeDto.NodeInfoDto startNode;
        @Schema
        private NodeDto.NodeInfoDto endNode;

        @Builder
        public MapEdgeDto(NodeDto.NodeInfoDto startNode, NodeDto.NodeInfoDto endNode) {
            this.startNode = startNode;
            this.endNode = endNode;
        }
    }
}
