package com.mw.domain.pathfind.controller;

import com.mw.domain.MapDto;
import com.mw.domain.dto.NodeDto;
import com.mw.domain.pathfind.entity.ResponseDto;
import com.mw.domain.pathfind.service.PathFindService;
import com.mw.domain.edgeweight.enttiy.EdgeDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/map")
@AllArgsConstructor
public class PathFindController {
    private final PathFindService pathFindService;

    @ApiOperation(value = "새로운 노드 추가")
    @PostMapping("/node")
    public ResponseEntity<Long> createNode(@RequestBody NodeDto.NodeInfoDto newNode) {
        return ResponseEntity.status(HttpStatus.OK).body(pathFindService.createNode(newNode));
    }

    @ApiOperation(value = "기존 노드 삭제")
    @DeleteMapping("/node/{nodeId}")
    public ResponseEntity<String> deleteNode(@PathVariable long nodeId) {
        pathFindService.deleteNode(nodeId);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @ApiOperation(value = "새로운 엣지 추가")
    @PostMapping("/edge")
    public ResponseEntity<Long> createEDge(@RequestBody EdgeDto.EdgeInfoDto newEdge) {
        return ResponseEntity.status(HttpStatus.OK).body(pathFindService.createEdge(newEdge));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "지도 불러오기", content = @Content(schema = @Schema(implementation = MapDto.class)))
    })
    @Operation(summary = "맵 전체 조회", description = "맵 전체를 다 긁어옵니다.")
    @GetMapping
    public ResponseEntity<MapDto> getMap() {
        return ResponseEntity.status(HttpStatus.OK).body(pathFindService.getMap());
    }

    @GetMapping("/calculate")
    public ResponseEntity<String> calculateDistance() {
        pathFindService.calculateDistance();
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "경로 탐색", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @Operation(summary = "경로 탐색 결과 조회", description = "경로 탐색을 조집니다.")
    @GetMapping("/pathFind")
    public ResponseEntity<ResponseDto> pathFind(@RequestParam Long start, @RequestParam Long end) {
        return ResponseEntity.status(HttpStatus.OK).body(pathFindService.pathFind(start, end));
    }
}
