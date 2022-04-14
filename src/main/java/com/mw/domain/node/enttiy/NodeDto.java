package com.mw.domain.node.enttiy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class NodeDto {
    private NodeDto() {}

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
}
