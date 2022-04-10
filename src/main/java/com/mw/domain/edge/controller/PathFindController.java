package com.mw.domain.edge.controller;

import com.mw.domain.edge.service.PathFindService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathFindController {
    private final PathFindService pathFindService;

    public PathFindController(PathFindService pathFindService) {
        this.pathFindService = pathFindService;
    }
}
