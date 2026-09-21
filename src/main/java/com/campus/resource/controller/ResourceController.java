
package com.campus.resource.controller;

import com.campus.resource.entity.Resource;
import com.campus.resource.service.ResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
    public ResponseEntity<Resource> addResource(
            @RequestBody Resource resource) {

        Resource savedResource = resourceService.addResource(resource);

        return new ResponseEntity<>(
                savedResource,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Resource>> getAllResources() {
        return ResponseEntity.ok(
                resourceService.getAllResources()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResourceById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                resourceService.getResourceById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateResource(
            @PathVariable Long id,
            @RequestBody Resource resource) {

        return ResponseEntity.ok(
                resourceService.updateResource(id, resource)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResource(
            @PathVariable Long id) {

        resourceService.deleteResource(id);

        return ResponseEntity.ok(
                "Resource deleted successfully"
        );
    }
}