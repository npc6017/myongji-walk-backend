package com.mw.domain.node.enttiy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class NodeDto {
    private NodeDto() {}

    public static NodeInfoDto nodeToNodeInfoDto(Node node) {
        return new NodeInfoDto(node.getLatitude(), node.getLongitude());
    }

    @Getter
    public static class NodeInfoDto {
        @Schema
        private String latitude;
        @Schema
        private String longitude;

        @Builder
        public NodeInfoDto(String latitude, String longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }

    @Getter
    public static class MapNodeDto {
        @Schema
        private Long nodeId;

        private NodeInfoDto node;

        @Builder
        public MapNodeDto(Node node) {
            this.nodeId = node.getId();
            this.node = new NodeInfoDto(node.getLatitude(), node.getLongitude());
        }
    }
}
