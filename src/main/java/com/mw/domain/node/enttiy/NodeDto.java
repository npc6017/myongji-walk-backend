package com.mw.domain.node.enttiy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class NodeDto {
    private NodeDto() {}

    @Getter
    public static class nodeInfoDto {
        @Schema
        private String latitude;
        @Schema
        private String longitude;

        @Builder
        public nodeInfoDto(String latitude, String longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }

    @Getter
    public static class MapNodeDto {
        @Schema
        private Long nodeId;

        @Builder
        public MapNodeDto(Node node) {
            this.nodeId = node.getId();
        }
    }
}
