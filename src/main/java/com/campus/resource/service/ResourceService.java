
package com.campus.resource.service;

import com.campus.resource.entity.Resource;
import com.campus.resource.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public Resource addResource(Resource resource) {
        if (resource.getAvailableCopies() == null) {
            resource.setAvailableCopies(resource.getTotalCopies());
        }

        return resourceRepository.save(resource);
    }

    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    public Resource getResourceById(Long resourceId) {
        return resourceRepository.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
    }

    public Resource updateResource(Long resourceId, Resource updatedResource) {
        Resource existingResource = getResourceById(resourceId);

        existingResource.setTitle(updatedResource.getTitle());
        existingResource.setCategory(updatedResource.getCategory());
        existingResource.setTotalCopies(updatedResource.getTotalCopies());
        existingResource.setAvailableCopies(updatedResource.getAvailableCopies());

        return resourceRepository.save(existingResource);
    }

    public void deleteResource(Long resourceId) {
        Resource resource = getResourceById(resourceId);
        resourceRepository.delete(resource);
    }
}