package com.mw.domain.pathfind.entity;

import com.mw.domain.dto.NodeDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResponseDto {
    @Schema
    NodeDto.NodeInfoDto start;
    @Schema
    NodeDto.NodeInfoDto goal;
    @Schema
    String sumDistance;
    @Schema
    List<NodeDto.NodeInfoDto> items;
    @Schema
    List<Guide> guide;
}
