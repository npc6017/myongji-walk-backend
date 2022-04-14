package com.mw.domain.edgeweight.enttiy;

import com.mw.domain.node.enttiy.NodeDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class EdgeDto {
    private EdgeDto() {}

    @Getter
    @Schema
    public static class EdgeInfoDto {
        @Schema
        private long startNode;
        @Schema
        private long endNode;
        @Schema
        private List<EdgeWeightDto> edgeWeightDtoList;

        @Builder
        public EdgeInfoDto(long startNode, long endNode, List<EdgeWeightDto> edgeWeightDtoList) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.edgeWeightDtoList = edgeWeightDtoList;
        }
    }

    @Getter
    public static class MapEdgeDto {
        @Schema
        private NodeDto.MapNodeDto startNode;
        @Schema
        private NodeDto.MapNodeDto endNode;

        @Builder
        public MapEdgeDto(NodeDto.MapNodeDto startNode, NodeDto.MapNodeDto endNode) {
            this.startNode = startNode;
            this.endNode = endNode;
        }
    }
}
